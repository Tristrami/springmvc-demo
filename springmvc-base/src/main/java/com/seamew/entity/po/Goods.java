package com.seamew.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Goods
{
    private Integer id;
    private String name;
    private Double price;
}
