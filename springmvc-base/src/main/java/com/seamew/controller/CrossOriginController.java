package com.seamew.controller;

import com.seamew.common.ResponseResult;
import org.springframework.web.bind.annotation.*;

/* 在 @CrossOrigin 这个注解中我们可以设置以下内容:
 * 1. origin: 允许访问的源主机
 * 2. methods: 允许的请求方法
 * 3. allowedHeaders: 允许的请求头
 * 4. exposedHeaders: 要暴露给客户端的响应头
 * 5. maxAge: 预检请求的过期时间
 * 6. allowCredentials: 是否允许客户端发送如 cookie 这样的验证信息
 * @CrossOrigin 可以用于类上，表示其中的所有 handler 方法都允许跨域；也可单独用于某个 handler
 * 方法上，表示只有该方法支持跨域
 *
 * 除了使用 @CrossOrigin 注解来配置跨域，我们还可以通过 xml 的方式，使用 <mvc:cors/> 标签来
 * 配置跨域，详见 springmvc-servlet.xml */

@RestController
@RequestMapping("cors")
@CrossOrigin
(
    origins = {"http://127.0.0.1:5500"},
    methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.OPTIONS,
        RequestMethod.DELETE,
        RequestMethod.PUT
    },
    maxAge = 10
)
public class CrossOriginController
{
    @GetMapping
    public ResponseResult handleGetRequest()
    {
        return ResponseResult.success();
    }

    @PostMapping
    public ResponseResult handlePostRequest()
    {
        return ResponseResult.success();
    }

    @PutMapping
    public ResponseResult handlePutMethod()
    {
        return ResponseResult.success();
    }

    @DeleteMapping
    public ResponseResult handleDeleteMethod()
    {
        return ResponseResult.success();
    }
}
