package com.seamew.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController
{
    @RequestMapping("/hello1")
    public ModelAndView handleHello1()
    {
        // 数据和视图绑定
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", "hello1");
        mv.setViewName("hello");
        return mv;
    }

    @RequestMapping("/hello2")
    public String handleHello2(Model model)
    {
        // 先设置数据
        model.addAttribute("msg", "hello2");
        // 返回视图文件的名称
        return "hello";
    }

    @RequestMapping("/hello3")
    public String handleHello3(Model model)
    {
        // 请求重定向
        return "redirect:https://www.baidu.com";
    }

    @RequestMapping("/hello4")
    public String handleHello4(Model model)
    {
        model.addAttribute("msg", "hello4");
        // 请求转发, forward 关键字可省略
        return "forward:hello";
    }
}
