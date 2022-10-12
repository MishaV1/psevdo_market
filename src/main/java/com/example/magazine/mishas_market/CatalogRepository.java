package com.example.magazine.mishas_market;

import com.example.DB.RepositoryForDB;
import com.example.models.Catalog;
import com.example.models.Item;
import com.example.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class CatalogRepository implements RepositoryForDB {

    @Autowired
    JdbcTemplate jdbcTemplate;

    RowMapper<Item> ROW_MAPPER = (ResultSet resultSet, int rowNum) ->{
        return new Catalog(resultSet.getInt("id"), resultSet.getString("name"));
    } ;

    RowMapper<Item> ROW_MAPPER_FOR_CATALOG = (ResultSet resultSet, int rowNum) ->{
        return new Product(resultSet.getString("name"), resultSet.getInt("price"), resultSet.getInt("catalog_id"));
    };

    RowMapper<Integer> ROW_MAPPER_FOR_PRICE = (ResultSet resultSet, int rowNum) ->{
        return resultSet.getInt("sum_of_price");
    };

    @Override
    public Item findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM catalog WHERE id = ?", ROW_MAPPER, id);
    }

    @Override
    public List<Item> findThemAll() {
        return jdbcTemplate.query("SELECT * FROM catalog", ROW_MAPPER);
    }

    @Override
    public Item findByName(String name) {
        return jdbcTemplate.queryForObject("SELECT * FROM catalog WHERE name = ?", ROW_MAPPER, name);
    }

    @Override
    public int getPriceById(int id) {
        return jdbcTemplate.queryForObject("SELECT SUM(price) as sum_of_price FROM product WHERE catalog_id = ?", ROW_MAPPER_FOR_PRICE, id).intValue();
    }

    @Override
    public void deleteById(int id) {
        Object[] args = new Object[]{id};
        jdbcTemplate.update("DELETE FROM catalog WHERE id = ?", args);
    }

    public List<Item> getAllProductOfCategory(int idofcatalog){
        return  jdbcTemplate.query("SELECT * FROM product WHERE catalog_id = ?", ROW_MAPPER_FOR_CATALOG, idofcatalog);
    }

    public void createNew(int id, String name) {
        Object[] args = new Object[]{id, name};
        jdbcTemplate.update("INSERT INTO catalog (id, name) VALUES (?, ?)", args);
    }
}
