package com.example.eurekaclient1.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class EntityPart {

    private String name;

    private String type;

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
