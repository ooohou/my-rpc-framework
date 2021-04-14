package org.example.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
//需要进行传输的实体类
@Getter
@Setter
public class Hello implements Serializable {
    String name;
    String description;

    public Hello(String name, String description){
        this.name = name;
        this.description = description;
    }
}
