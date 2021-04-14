package org.example.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum RpcErrorMessageEnum {
    SERVICE_INVOCATION_FAILURE("服务调用失败"),
    SERVICE_CAN_NOT_BE_FOUND("没有找到指定服务"),
    SERVICE_NOT_IMPLEMENT_ANY_INTERFACE("注册的服务没有实现任何接口");
    private String msg;
    private RpcErrorMessageEnum(String msg){this.msg = msg;}
}
