package com.seamew.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/* 同源策略 (Same origin policy) 是一种约定，它是浏览器最核心也最基本的安全功能。同源策略会阻
 * 止一个域的javascript脚本和另外一个域的内容进行交互。所谓同源 (即指在同一个域) 就是两个页面具
 * 有相同的协议 (protocol)，主机 (host) 和端口号 (port) 当一个请求的源主机和目的主机不同域时，
 * 就会产生跨域问题。非同源有下面几个限制：
 * 1. 无法读取非同源网页的 Cookie、LocalStorage 和 IndexedDB
 * 2. 无法接触非同源网页的 DOM
 * 3. 无法向非同源地址发送 AJAX 请求
 * 🌟 跨域只存在于浏览器与服务器之间，服务器与服务器之间不存在跨域
 *
 * 跨域请求的分类:
 * 1. 简单请求: 简单请求指请求方法属于 GET POST HEAD 三种之一，请求头信息不超过以下几个字段:
 *    Accept Accept-Language Content-Language Last-Event-ID Content-Type (data
 *    text/plain application/x-www-form-urlencoded multipart/form-data)；对于简单
 *    请求，在跨域场景中，只需要在请求头中加上 Origin 字段就变成了跨域请求
 * 2. 非简单请求: 只要不满足简单请求条件的请求都是非简单请求，比如 PUT 方法请求，或者 Content-
 *    Type 是 application/json
 *
 * 浏览器对于两种请求类型的不同处理:
 * 1. 发送简单跨域请求: 对于简单请求，浏览器会先在请求头中加上 Origin 字段，然后直接发送请求
 * 2. 发送非简单跨域请求: 对于非简单请求，浏览器会先向服务器发送一个 "预检 (preflight)" 请求，
 *    预检请求的作用是检查请求的源主机、方法、请求头是否被服务器允许，预检请求的方法是 OPTION。
 *    预检请求中的请求头中，Origin 表示源主机，Access-Control-Request-Method 表示请求的
 *    方法，Access-Control-Request-Headers 表示请求的请求头字段。收到服务器对预检请求的响
 *    应后，如果响应的响应头中包含 CORS 相关的字段 (Access-Control-Accept-Origin...)，
 *    则表示服务器允许该主机的跨域请求，接着浏览器就会发送正式的请求。如果响应的响应中不包含任何
 *    与 CORS 相关的字段，浏览器会认为服务器不予许该主机进行跨域访问
 *
 * 预检请求的过期时间:
 * 如果每次发送非简单请求都要发送预检请求，未免有些浪费资源，所以预检请求存在一个过期时间，一次预
 * 检请求发送后，在接下来的一段时间内，只要没有超过预检请求设定的过期时间，那么发送非简单请求前都
 * 无需发送预检请求了。预检请求的过期时间可以在服务器中设定 */

public class CorsFilter implements Filter
{
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        HttpServletResponse resp = (HttpServletResponse) response;
        // 允许跨域访问该服务器的源主机
        resp.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
        // 允许的方法类型
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
        // 允许发送 cookie
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        // 允许发送的请求头
        resp.setHeader("Access-Control-Allow-Headers",
                "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
        resp.setHeader("Message", "hello");
        // 客户端可以访问的响应头
        resp.setHeader("Access-Control-Expose-Headers", "Message");
        // 预检请求过期时间
        resp.setHeader("Access-Control-Max-Age", "3600");
        chain.doFilter(request, resp);
    }
}
