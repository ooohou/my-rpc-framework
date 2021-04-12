package org.example.common;

import java.io.Serializable;
//需要进行传输的实体类
public class Hello implements Serializable {
    String name;
    String description;

    public Hello(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
