package com.hh.aws.security;

import com.alibaba.fastjson.JSON;
import com.hh.aws.bean.ResponseData;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AjaxAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResponseData responseBody = new ResponseData();

        responseBody.setCode("400");
        responseBody.setMsg("登录失败!");

        httpServletResponse.getWriter().write(JSON.toJSONString(responseBody));
    }
}