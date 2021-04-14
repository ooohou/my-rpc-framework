package org.example.dto;
import lombok.Builder;
import lombok.Data;
import java.io.Serializable;

//用于发送的rpc请求
@Data
@Builder
public class RpcRequest implements Serializable {
    private static final long serialVersionUID = 715745410605631233L;
    //接口名
    String interfaceName;
    //方法名
    String methodName;
    //传入参数
    Object[] parameters;
    Class<?>[] paramTypes;
}
