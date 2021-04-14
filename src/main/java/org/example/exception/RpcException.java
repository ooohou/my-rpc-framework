package org.example.exception;

import org.example.enumeration.RpcErrorMessageEnum;

//自定义一个异常类，主要是为了让用户知道发生什么错误了
public class RpcException extends RuntimeException {
    public RpcException(RpcErrorMessageEnum rpcErrorMessageEnum, String detail){
        super(rpcErrorMessageEnum.getMsg() + ":" + detail);
    }

    public RpcException(String message, Throwable cause){
        super(message, cause);
    }

    public RpcException(RpcErrorMessageEnum rpcErrorMessageEnum){
        super(rpcErrorMessageEnum.getMsg());
    }
}
