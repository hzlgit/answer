package com.hh.aws.web;

import com.alibaba.fastjson.JSON;
import com.hh.aws.model.PageData;
import com.hh.aws.model.ResponseData;
import com.hh.aws.model.User;
import com.hh.aws.security.JWTFilter;
import com.hh.aws.security.TokenProvider;
import com.hh.aws.service.UserService;
import com.hh.aws.web.dto.LoginDto;
import com.hh.aws.web.dto.PageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/user")
public class UserController {
    @Resource
    UserService userService;

    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @GetMapping("/info")
    public String getUserInfo() {
        Optional<User> user  = userService.getUserWithAuthorities();
        ResponseData responseData = new ResponseData();
        responseData.setData(user.get().toString());
        return JSON.toJSONString(responseData);

    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginDto loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        boolean rememberMe = (loginDto.isRememberMe() == null) ? false : loginDto.isRememberMe();
        String jwt = tokenProvider.createToken(authentication, rememberMe);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        ResponseData responseData = new ResponseData();
        responseData.setCode("0000");
        responseData.setData(jwt);
        return JSON.toJSONString(responseData);
    }

    /**
     * 列表
     * @param pageDto
     * @return
     */
    @RequestMapping("/list")
    public  String list(@RequestBody PageDto pageDto) {
        Page<User> pageUsers=userService.getUserList(pageDto.getPage(), pageDto.getSize());
        PageData pageData = new PageData();
        pageData.setCurrentPage(pageUsers.getNumber());
        pageData.setList(pageUsers.getContent());
        pageData.setSize(pageUsers.getSize());
        pageData.setTotalPage(pageUsers.getTotalPages());
        ResponseData responseData = new ResponseData();
        responseData.setData(pageData);
        return JSON.toJSONString(responseData);
    }
}
