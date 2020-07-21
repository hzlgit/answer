package com.hh.aws.web;
import com.hh.aws.comm.LoggerManage;
import com.hh.aws.model.Authority;
import com.hh.aws.model.PageData;
import com.hh.aws.model.ResponseData;
import com.hh.aws.model.User;
import com.hh.aws.model.enums.ResultCode;
import com.hh.aws.repository.AuthorityRepository;
import com.hh.aws.repository.UserRepository;
import com.hh.aws.security.JWTFilter;
import com.hh.aws.security.TokenProvider;
import com.hh.aws.service.UserService;
import com.hh.aws.utils.DateUtils;
import com.hh.aws.utils.FileUtil;
import com.hh.aws.web.dto.LoginDto;
import com.hh.aws.web.dto.PageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/admin/user")
public class UserController extends BaseController{
    @Resource
    UserService userService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @GetMapping("/info")
    public ResponseData getUserInfo() {
        Optional<User> user  = userService.getUserWithAuthorities();
        ResponseData responseData = new ResponseData();
        responseData.setData(user.get().toString());
        return responseData;

    }

    @PostMapping("/login")
    public ResponseData login(@Valid @RequestBody LoginDto loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        boolean rememberMe = (loginDto.isRememberMe() == null) ? false : loginDto.isRememberMe();
        String jwt = tokenProvider.createToken(authentication, rememberMe);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        ResponseData responseData = new ResponseData();
        responseData.setData(jwt);
        return responseData;
    }

    /**
     * 列表
     * @param pageDto
     * @return
     */
    @RequestMapping("/list")
    public  ResponseData list(@RequestBody PageDto pageDto) {
        Page<User> pageUsers=userService.getUserList(pageDto.getPage(), pageDto.getSize());
        PageData pageData = new PageData();
        pageData.setCurrentPage(pageUsers.getNumber());
        pageData.setList(pageUsers.getContent());
        pageData.setSize(pageUsers.getSize());
        pageData.setTotalPage(pageUsers.getTotalPages());
        ResponseData responseData = new ResponseData();
        responseData.setData(pageData);
        return responseData;
    }
    @RequestMapping("/getRoles")
    public ResponseData roles() {
        List<Authority> authoritys = authorityRepository.findAll();
        return new ResponseData(ResultCode.SUCCESS,authoritys);
    }
    @RequestMapping("/add")
    public ResponseData add(@RequestBody User user) {
        userService.save(user);
        return new ResponseData(ResultCode.SUCCESS);
    }
    @RequestMapping("/save")
    public ResponseData save(@RequestBody User user) {
        user.setCreateTime(new Date());
        user.setActivated(true);
        userService.save(user);
        return new ResponseData(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/uploadAvatar", method = RequestMethod.POST)
    @LoggerManage(description="上传头像")
    public ResponseData uploadAvatar(String dataUrl) {
        try {
            String filePath="/Users/jj/Documents/java";
            String fileName= UUID.randomUUID().toString()+".png";
            String savePath = "/images/"+fileName;
            String image = dataUrl;
            String header ="data:image";
            String[] imageArr=image.split(",");
            if(imageArr[0].contains(header)){
                image=imageArr[1];
                Base64.Decoder decoder = Base64.getDecoder();
                byte[] decodedBytes = decoder.decode(image);
                FileUtil.uploadFile(decodedBytes, filePath, fileName);
                User user = userService.getUserWithAuthorities().get();
                userRepository.setAvatar(savePath, user.getId());
            }
            logger.info("背景地址：" + savePath);
            return new ResponseData(ResultCode.SUCCESS, savePath);
        } catch (Exception e) {
            logger.error("upload background picture failed, ", e);
            return new ResponseData(ResultCode.FAILED);
        }
    }
}
