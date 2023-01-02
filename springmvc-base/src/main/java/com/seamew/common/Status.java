package com.seamew.common;

public enum Status
{
    OK(200, "成功"),

    REDIRECT(300, "重定向"),

    NOT_FOUND(400, "未找到"),

    ERROR(500, "错误");

    private Integer code;
    private String message;

    Status(Integer code, String message)
    {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString()
    {
        return "Status{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
