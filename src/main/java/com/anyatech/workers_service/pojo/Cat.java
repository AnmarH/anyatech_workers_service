package com.anyatech.workers_service.pojo;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Cat implements Animal {

    @Override
    public String eats() {
        return "meat";
    }
}
