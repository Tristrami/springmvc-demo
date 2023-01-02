package com.seamew.controller;

import com.seamew.common.ResponseResult;
import com.seamew.entity.po.Goods;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 遵循 RESTful 风格
@RestController
@RequestMapping("goods")
public class GoodsController
{
    @GetMapping("{id}")
    public ResponseResult getGoodsById(@PathVariable("id") Integer id)
    {
        return ResponseResult.success(new Goods(id, "棒棒糖", 5d));
    }

    @GetMapping
    public ResponseResult getAllGoods()
    {
        return ResponseResult.success(List.of(
                new Goods(1, "棒棒糖", 5d),
                new Goods(2, "牛仔裤", 45d)));
    }

    @PostMapping
    public ResponseResult insertGoods(@RequestBody Goods goods)
    {
        // ...
        return ResponseResult.success(goods);
    }

    @PutMapping("{id}")
    public ResponseResult updateGoodsById(@PathVariable("id") Integer id, @RequestBody Goods goods)
    {
        // ...
        return ResponseResult.success(goods);
    }

    @DeleteMapping("{id}")
    public ResponseResult deleteGoodsById(@PathVariable("id") Integer id)
    {
        // ...
        return ResponseResult.success();
    }
}
