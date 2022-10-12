package com.example.DB;

import com.example.models.Item;
import com.example.models.Product;

import java.util.List;

public interface RepositoryForDB {

    Item findById(int id);

    List<Item> findThemAll();

    Item findByName(String name);

    int getPriceById(int id);

    void deleteById(int id);



}
