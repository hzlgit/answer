package com.hh.aws.test;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class BCryptTest {
    public static void main(String[] args) {
        String pwd = "111111";
        String encodePwd = BCrypt.hashpw(pwd, BCrypt.gensalt()); // 加密，核心代码
        System.out.println(encodePwd);
        boolean flag = BCrypt.checkpw(pwd, encodePwd); // 验证加密是否正确
        System.out.println(flag);
    }
}
