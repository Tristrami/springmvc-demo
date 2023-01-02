package com.seamew.controller;

import com.seamew.common.ResponseResult;
import com.seamew.entity.po.Goods;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/* 前端工程会发送跨域请求访问这个 controller 中的 api
 * 解决跨域问题的核心在于服务器需要允许发送请求的主机对服务器中的资源进行跨域访问，
 * 解决方式就是服务器需要在响应头中设置跨域请求响应的首部响应的首部信息，详见 CorsFilter
 * 除了自己写 Filter 意外，我们也可以使用 springmvc 为我们提供的解决方案，那就是
 * 使用 @CrossOrigin 注解 */

@RestController
@RequestMapping("api")
public class CrossOriginGoodsController
{
    @GetMapping("goods")
    public ResponseResult getAllGoods()
    {
        return ResponseResult.success(List.of(
                new Goods(1, "棒棒糖", 5d),
                new Goods(2, "牛仔裤", 45d)));
    }

    @PostMapping("goods")
    public ResponseResult insertGoods(@RequestBody Goods goods)
    {
        // ...
        return ResponseResult.success(goods);
    }

    @PutMapping("goods/{id}")
    public ResponseResult updateGoodsById(@PathVariable("id") Integer id, @RequestBody Goods goods)
    {
        // ...
        return ResponseResult.success(goods);
    }

    @DeleteMapping("goods/{id}")
    public ResponseResult deleteGoodsById(@PathVariable("id") Integer id)
    {
        // ...
        return ResponseResult.success();
    }
}
