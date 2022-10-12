package com.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Catalog implements Item{
    private int id;
    private String name;

    @Override
    public void getItem() {
        System.out.println(name);
    }


}
