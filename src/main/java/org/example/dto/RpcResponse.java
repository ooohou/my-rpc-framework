package org.example.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class RpcResponse<T> implements Serializable {
    //这是一个相应类，应该有响应码，响应信息，响应数据和一些响应的方法。
    //响应码和响应信息都可以封装为枚举类
    private static final long serialVersionUID = 1233234234325435546L;
    private static final int SUCCESS = 200;
    private static final int FAIL = -1;
    //相应码
    private Integer code;
    //相应信息
    private String message;
    //相应数据
    private T data;

    public static <T> RpcResponse<T> success(T data){
        RpcResponse<T> rpcResponse = new RpcResponse<>();
        rpcResponse.code = SUCCESS;
        rpcResponse.message = "调用方法成功";
        rpcResponse.data = data;
        return rpcResponse;
    }

    public static <T>RpcResponse<T> fail(T data){
        RpcResponse rpcResponse = new RpcResponse<>();
        rpcResponse.code = FAIL;
        rpcResponse.message = "调用方法失败";
        return rpcResponse;
    }
}
