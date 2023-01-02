package com.seamew.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class CustomObjectMapper extends ObjectMapper
{
    public CustomObjectMapper()
    {
        super();
        // 去掉默认的时间戳格式
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        // 设置为东八区
        setTimeZone(TimeZone.getTimeZone("GMT+8"));
        // 设置日期转换 yyyy-MM-dd HH:mm:ss
        setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // 设置输入:禁止把POJO中值为null的字段映射到json字符串中
        configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        // 空值不序列化
        setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 反序列化时，属性不存在的兼容处理
        getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 序列化枚举是以 toString() 来输出，默认 false，即默认以 name() 来输出
        configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        // 注册 JavaTimeModule 来支持 jdk8 日期的序列化和反序列化，如 LocalDate
        registerModule(new JavaTimeModule());
    }
}
