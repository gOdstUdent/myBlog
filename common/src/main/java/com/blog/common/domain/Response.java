package com.blog.common.domain;

import lombok.Builder;
import lombok.Data;

/**
 * Created by zhanghao on 2017/2/22.
 * 响应结果对象
 */
@SuppressWarnings("unused")
@Builder
@Data
public class Response<T> {
    /*
    * 状态
    * */
    private ResponseStatus status;

    /*
     * 业务状态码
     * */
    private String businessCode;
    /*
    * 响应状态信息
    * */
    private String message;

    /*
    * 响应结果
    * */
    private T data;

    public Response() {
    }

    public Response(ResponseStatus status, String businessCode, String message) {
        this(status, businessCode, message, null);
    }

    public Response(ResponseStatus status, String businessCode, String message, T data) {
        this.status = status;
        this.businessCode = businessCode;
        this.message = message;
        this.data = data;
    }

    public Response(ResponseStatus status) {
        this(status, "", "", null);
    }
}
