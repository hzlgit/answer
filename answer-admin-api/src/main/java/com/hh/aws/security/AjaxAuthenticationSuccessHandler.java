package com.hh.aws.security;


import com.alibaba.fastjson.JSON;
import com.hh.aws.model.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private TokenProvider tokenProvider;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        ResponseData responseBody = new ResponseData();

        responseBody.setCode("200");
        responseBody.setMsg("登录成功!");

        UserModelDetailsService userDetails = (UserModelDetailsService) authentication.getPrincipal();

        String jwtToken = tokenProvider.createToken(authentication,true);
        responseBody.setData(jwtToken);

        httpServletResponse.getWriter().write(JSON.toJSONString(responseBody));
    }
}