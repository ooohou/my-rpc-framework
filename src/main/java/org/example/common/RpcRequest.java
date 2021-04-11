package org.example.common;

import java.io.Serializable;

//用于发送的rpc请求
public class RpcRequest implements Serializable {
    //接口名
    String Interface;
    //方法名
    String MethodName;
    //传入参数
    Object[] parameters;
}
