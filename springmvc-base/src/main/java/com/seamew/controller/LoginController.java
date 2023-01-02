package com.seamew.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController
{
    @GetMapping("toLogin")
    public String toLogin()
    {
        return "login";
    }

    @PostMapping("login")
    public String login(@RequestParam("username") String username, HttpServletRequest req, Model model)
    {
        HttpSession session = req.getSession();
        session.setAttribute("username", username);
        model.addAttribute("msg", "登录成功");
        return "success";
    }
}
