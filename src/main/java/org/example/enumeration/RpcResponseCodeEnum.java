package org.example.enumeration;

import lombok.Getter;

@Getter
public enum RpcResponseCodeEnum {
    SUCCESS(200, "调用方法成功"),
    FAIL(500, "调用方法失败"),
    METHOD_NOT_FOUND(500,"未找到指定方法"),
    CLASS_NOT_FOUND(500, "未找到指定类");
    int code;
    String msg;
    RpcResponseCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
