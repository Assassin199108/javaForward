package com.wangwei.basic.dto;

import java.io.Serializable;

public class AnimalDTO implements Serializable {

    private static final long serialVersionUID = 1321771686821175407L;

    private String name = "dog";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
