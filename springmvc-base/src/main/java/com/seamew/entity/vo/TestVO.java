package com.seamew.entity.vo;

import com.seamew.entity.po.Address;
import com.seamew.entity.po.Contact;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class TestVO
{
    private String name;
    private String[] hobbies;
    private List<Address> addresses;
    private Map<String, Contact> contacts;
}
