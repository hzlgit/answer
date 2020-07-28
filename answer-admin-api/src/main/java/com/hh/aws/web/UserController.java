package com.hh.aws.web;
import com.hh.aws.bean.RequestCheckResult;
import com.hh.aws.comm.LoggerManage;
import com.hh.aws.model.Authority;
import com.hh.aws.model.PageData;
import com.hh.aws.bean.ResponseData;
import com.hh.aws.model.User;
import com.hh.aws.model.enums.ResultCode;
import com.hh.aws.repository.UserRepository;
import com.hh.aws.security.JWTFilter;
import com.hh.aws.security.TokenProvider;
import com.hh.aws.service.RequestService;
import com.hh.aws.service.UserService;
import com.hh.aws.utils.FileUtil;
import com.hh.aws.utils.ResponseUtil;
import com.hh.aws.web.dto.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/admin/user")
public class UserController extends BaseController{
    @Resource
    UserService userService;
    @Resource
    RequestService requestService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    /**
     * 当前登录用户信息
     * @return
     */
    @GetMapping("/info")
    public @ResponseBody ResponseEntity<String> getUserInfo() {
        Optional<User> user  = userService.getUserWithAuthorities();
        String []keys = {"password"}; // 过滤输出
        ResponseData responseData = new ResponseData();
        responseData.setData(user.get().toString());
        return ResponseUtil.responseEntity(responseData,keys);
    }

    /**
     * 登录
     * @param loginDto
     * @return
     */
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
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/list")
    public  @ResponseBody ResponseEntity<String> list(HttpServletRequest request, HttpServletResponse response) {
        String []keys={"password"};
        ResponseData responseData = new ResponseData();
        RequestCheckResult checkRes = requestService.checkRequest(request);

        if(checkRes.isSuccess()) {
            Map<String, String> params = checkRes.getRequestParams();
            String userName = params.getOrDefault("userName", "");
            String nickName = params.getOrDefault("nickName", "");
            int page = Integer.parseInt(params.getOrDefault("page", "1"));
            int pageSize = Integer.parseInt(params.getOrDefault("pageSize", "10"));
            String sortName = params.getOrDefault("sortName", "createTime");
            String sortType = params.getOrDefault("sortType", "desc");
            // 查询参数
            Map<String, String> param = new HashMap<String, String>();
            param.put("userName", userName);
            param.put("nickName", nickName);

            PageData<User> dataPage = userService.getUserList(page, pageSize, param, sortName, sortType);
            responseData.setData(dataPage);
            responseData.setMsg("保存成功");
        } else {
            responseData.setCode(checkRes.getCode());
            responseData.setMsg(checkRes.getMsg());
        }

        return ResponseUtil.responseEntity(responseData,keys);
    }

    @RequestMapping("/add")
    public ResponseData add(@RequestBody User user) {
        userService.save(user);
        return new ResponseData(ResultCode.SUCCESS);
    }
    @RequestMapping("/save")
    public @ResponseBody
    ResponseEntity<String> save(HttpServletRequest request, HttpServletResponse response) {
        ResponseData responseData = new ResponseData();
        RequestCheckResult checkRes = requestService.checkRequest(request, "userName", "avatar", "trueName", "classId", "nickName", "sex");
        User user = new User();
        if(checkRes.isSuccess()) {
            Map<String, String> params = checkRes.getRequestParams();
            long id = Integer.parseInt(params.getOrDefault("id", "0"));
            String userName = params.getOrDefault("userName", "");
            String avatar = params.getOrDefault("avatar", "");
            String trueName = params.getOrDefault("trueName", "");
            long classId = Integer.parseInt(params.getOrDefault("classId", "0"));
            String nickName = params.getOrDefault("nickName", "");
            String sex = params.getOrDefault("sex", "");
            String authorities = params.getOrDefault("authorities", "");
            if(id > 0) {
                user = userRepository.findById(id).get();
            } else {
                user.setCreateTime(new Date());
            }
            user.setAvatar(avatar);
            user.setUserName(userName);
            user.setTrueName(trueName);
            user.setClassId(classId);
            user.setNickName(nickName);
            user.setSex(sex);
            Set<Authority> authorityList = new HashSet<>();
            String [] auths = authorities.split(",");
            for(int i =0;i<auths.length-1;i++) {
                authorityList.add(new Authority(auths[i]));
            }
            // 角色
            user.setAuthorities(authorityList);
            user.setActivated(true);
            userService.save(user);

            responseData.setMsg("保存成功");
        } else {
            responseData.setCode(checkRes.getCode());
            responseData.setMsg(checkRes.getMsg());
        }
        return ResponseUtil.responseEntity(responseData);
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
