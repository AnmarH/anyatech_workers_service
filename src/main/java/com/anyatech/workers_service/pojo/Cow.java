package com.anyatech.workers_service.pojo;

import lombok.*;

@EqualsAndHashCode
public class Cow implements Animal {

    @Override
    public String eats() {
        return "vegetables";
    }
}
