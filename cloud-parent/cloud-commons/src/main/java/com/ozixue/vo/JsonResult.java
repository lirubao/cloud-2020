package com.ozixue.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JsonResult<T> {

    private Integer status;
    private String message;
    private T data;

    public JsonResult(Integer status, String message) {
        this(status, message, null);
    }
}
