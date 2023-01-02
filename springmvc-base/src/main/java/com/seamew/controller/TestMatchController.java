package com.seamew.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * URL 匹配源码
 * @see org.springframework.web.servlet.handler.AbstractHandlerMethodMapping#addMatchingMappings(java.util.Collection, java.util.List, javax.servlet.http.HttpServletRequest)
 *
 * url 对应方法的匹配信息都存储在 mappingRegistry 中
 * @see org.springframework.web.servlet.handler.AbstractHandlerMethodMapping.mappingRegistry
 *
 * URL 匹配优先级: 如果多个 pattern 都能匹配到相同的 URL, 那么就需要决定哪个 pattern 优先匹配
 * @see org.springframework.util.AntPathMatcher.AntPatternComparator#compare(String, String)
 *
 * 从源码中分析可以发现
 * 1. 完全匹配者, 优先级最高
 * 2. 都是前缀匹配 (/a/**), 匹配路由越长, 优先级越高
 * 3. 前缀匹配优先级，比非前缀的低
 * 4. 需要匹配的数量越少，优先级越高，this.uriVars + this.singleWildcards + (2 * this.doubleWildcards);
 * 5. 路劲越短优先级越高
 * 6. * 越少优先级越高
 * 7. {} 越少优先级越高
 *
 * 匹配方式	                          优先级
 * 全路径匹配，例如：配置路由/a/b/c	      第一优先级
 * 带有{}路径的匹配，例如：/a/{b}/c	      第二优先级
 * 正则匹配，例如：/a/{regex:\d{3}}/c	  第三优先级
 * 带有*路径的匹配，例如：/a/b/*	      第四优先级
 * 带有**路径的匹配，例如：/a/b/**	      第五优先级
 * 仅仅是双通配符：/**	                  最低优先级 */

@Controller
public class TestMatchController
{
    @RequestMapping("/a/**")
    public String handlePrefix1(Model model)
    {
        model.addAttribute("pattern", "/a/**");
        return "test";
    }

    @RequestMapping("/a/b/**")
    public String handlePrefix2(Model model)
    {
        // 同是前缀匹配时选择长度长的 pattern
        model.addAttribute("pattern", "/a/b/**");
        return "test";
    }

    @RequestMapping("/c/d/**")
    public String handlePrefix3(Model model)
    {
        model.addAttribute("pattern", "/c/d/**");
        return "test";
    }

    @RequestMapping("/c/d/*")
    public String handleSingleWildcard1(Model model)
    {
        // 相比前缀匹配, 单通配符优先级更高
        model.addAttribute("pattern", "/c/d/*");
        return "test";
    }

    @RequestMapping("/e/{f}/*")
    public String handleWildcard1(Model model, @PathVariable("f") String pathVariable)
    {
        model.addAttribute("pattern", "/e/*/*");
        model.addAttribute("pathVariable", pathVariable);
        return "test";
    }

    @RequestMapping("/e/f/*")
    public String handleWildcard2(Model model)
    {
        // 非前缀匹配时, 通配符数量越少优先级越高
        model.addAttribute("pattern", "/e/f/*");
        return "test";
    }
}
