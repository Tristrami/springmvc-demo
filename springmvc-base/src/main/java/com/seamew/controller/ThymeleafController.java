package com.seamew.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/* Spring 默认的视图解析器是 internalResourceViewResolver，同时支持配置多个试图解析器，
 * 这些视图解析器会被保存为一个解析器链，如果解析器实现了 Ordered 接口，则按照他们的 order
 * 属性进行排序，order 值越小，优先级越高。在解析视图时，Spring 会按照解析器链的顺序来依次
 * 尝试使用解析器来解析视图，直到解析成功。这里我们配置一个第三方的试图解析器 thymeleaf，与
 * 解析 jsp 模版不同，thymeleaf 通常用于解析 html、xhtml 模版，同时 thymeleaf 提供了一
 * 套模版语法。配置的方式如下（详见 springmvc-servlet.xml）:
 * 1. 引入 thymeleaf 和 thymeleaf-spring5 这两个依赖
 * 2. 在容器中注入
 *    org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver
 *    org.thymeleaf.spring5.SpringTemplateEngine
 *    org.thymeleaf.spring5.view.ThymeleafViewResolver
 *    三个bean，并设置相应的属性
 * 🌟 SpringResourceTemplateResolver 和 ThymeleafViewResolver 要设置 charsetEncoding
 *    属性为 UTF-8，否则页面上的中文会乱码
 * 🌟 当在 SpringResourceTemplateResolver 指定了视图的前缀和后缀以后，ThymeleafViewResolver
 *    的 viewName 属性不要设置（如 *.html），否则会找不到视图，因为我们在 handler 方法中返回的
 *    视图名通常没有后缀 */

@Controller
@RequestMapping("thymeleaf")
public class ThymeleafController
{
    @GetMapping("hello")
    public ModelAndView hello(ModelAndView mv)
    {
        mv.setViewName("hello");
        mv.addObject("msg", "Hello thymeleaf");
        return mv;
    }
}
