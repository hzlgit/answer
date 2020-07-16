package com.hh.aws.web;

import com.hh.aws.model.User;
import com.hh.aws.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/user")
public class UserController {
    @Resource
    UserService userService;

    @GetMapping("/info")
    public String getUserInfo() {
        return "user info";
    }

    @RequestMapping("/index")
    public String index() {
        return "redirect:/list";
    }

    @RequestMapping("/list")
    public  String list(Model model) {
        List<User> users=userService.getUserList();
        model.addAttribute("users", users);
        return "user/list";
    }
}
