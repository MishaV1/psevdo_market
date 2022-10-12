package com.example.magazine.mishas_market;

import com.example.DB.RepositoryForDB;
import com.example.models.Item;
import com.example.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class ProductRepository implements RepositoryForDB {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    RowMapper<Item> ROW_MAPPER = (ResultSet resultSet, int rowNum) ->{
        return new Product(resultSet.getString("name"), resultSet.getInt("price"), resultSet.getInt("catalog_id"));
    };

    RowMapper<Item> ROW_MAPPER_FOR_CATALOG = (ResultSet resultSet, int idofcatalog) ->{
        if(idofcatalog == resultSet.getInt("catalog_id")){
            return new Product(resultSet.getString("name"), resultSet.getInt("price"), resultSet.getInt("catalog_id"));
        }
        return null;
    };

    RowMapper<Integer> ROW_MAPPER_FOR_INT = (ResultSet resultSet, int rowNum) ->{
        return resultSet.getInt("price");
    };

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Item findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM product WHERE id = ?", ROW_MAPPER, id);
    }


    @Override
    public List<Item> findThemAll() {
        return jdbcTemplate.query("SELECT * FROM product", ROW_MAPPER);
    }

    @Override
    public Item findByName(String name) {
        return jdbcTemplate.queryForObject("SELECT * FROM product WHERE name = ?", ROW_MAPPER, name);
    }

    @Override
    public int getPriceById(int id) {
        return jdbcTemplate.queryForObject("SELECT price FROM product WHERE id = ?", ROW_MAPPER_FOR_INT, id).intValue();
    }

    @Override
    public void deleteById(int id) {
        Object[] args = new Object[]{id};
        jdbcTemplate.update("DELETE FROM product WHERE id = ?", args);
    }

    public void createNew(String name, int price, int idOfCatalog) {
        Object[] args = new Object[]{name, price, idOfCatalog};
        jdbcTemplate.update("INSERT INTO product (name,price, catalog_id) VALUES (?, ?, ?)", args);
    }
}
