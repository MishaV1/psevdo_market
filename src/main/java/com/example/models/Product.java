package com.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product implements Item {
    private String name;
    private int price;
    private int idofcatalog;


    @Override
    public void getItem() {
        System.out.println(name);
    }
}
