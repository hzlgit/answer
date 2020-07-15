package com.hh.aws.config;

import com.hh.aws.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;
    private final CorsFilter corsFilter;
    private final JwtAuthenticationEntryPoint authenticationErrorHandler;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Autowired
    private AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler;
    @Autowired
    private AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler;
    @Autowired
    private UserModelDetailsService userModelDetailsService;

    public WebSecurityConfig(
            TokenProvider tokenProvider,
            CorsFilter corsFilter,
            JwtAuthenticationEntryPoint authenticationErrorHandler,
            JwtAccessDeniedHandler jwtAccessDeniedHandler
    ) {
        this.tokenProvider = tokenProvider;
        this.corsFilter = corsFilter;
        this.authenticationErrorHandler = authenticationErrorHandler;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }



    // Configure BCrypt password encoder =====================================================================

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // Configure security settings ===========================================================================

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

//        httpSecurity
//                // security 默认 csrf 是开启的，我们使用了 token ，这个也没有什么必要了
//                .csrf().disable()
////                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
//                // 前后端分离是 STATELESS，故 session 使用该策略,使用 JWT，关闭token
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//
//
//                .and()
//                .httpBasic()
//                .authenticationEntryPoint(authenticationErrorHandler)
//
//
//                .and()
//                .authorizeRequests()
//
//
//                .anyRequest()
//                .access("@rbacauthorityservice.hasPermission(request,authentication)") // RBAC 动态 url 认证
//
////                .and()
////                .formLogin()  //开启登录
////                .successHandler(ajaxAuthenticationSuccessHandler) // 登录成功
////                .failureHandler(ajaxAuthenticationFailureHandler) // 登录失败
////                .permitAll()
//
//                .and()
//                .logout()
//                .permitAll()
//
//
//                .and()
//                .apply(securityConfigurerAdapter());
//
//        httpSecurity.exceptionHandling().accessDeniedHandler(jwtAccessDeniedHandler);
//        httpSecurity.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.authorizeRequests()
                .anyRequest()
                .permitAll()
                .and()
                .logout()
                .permitAll();

    }

    private JWTConfigurer securityConfigurerAdapter() {
        return new JWTConfigurer(tokenProvider);
    }
}