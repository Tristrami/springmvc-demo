package com.seamew.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ResponseResult
{
    private Status status;
    private Object data;

    public static ResponseResult success(Object data)
    {
        return builder()
                .status(Status.OK)
                .data(data)
                .build();
    }

    public static ResponseResult success()
    {
        return builder()
                .status(Status.OK)
                .build();
    }

    public static ResponseResult fail()
    {
        return builder()
                .status(Status.ERROR)
                .build();
    }
}
