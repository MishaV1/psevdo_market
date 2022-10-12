package com.example.magazine.mishas_market;

import com.example.models.Catalog;
import com.example.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("market/catalog")
public class CatalogController {

    @Autowired
    CatalogRepository catalogRepository;

    @GetMapping("/{id}")
    Item findById(@PathVariable("id") int id){
        return catalogRepository.findById(id);
    }

    @GetMapping("/all")
    List<Item> findAll(){
        return catalogRepository.findThemAll();
    }

    @GetMapping("/name/{name}")
    Item findByName(@PathVariable("name") String name){
        return catalogRepository.findByName(name);
    }

    @GetMapping("/price/{id}")
    int getAllPriceOfCatalogProd(@PathVariable("id") int id){
        return catalogRepository.getPriceById(id);
    }

    @GetMapping("/productsofcatalog/{id}")
    List<Item> getAllProductsOfCatalog(@PathVariable("id") int id){
        return catalogRepository.getAllProductOfCategory(id);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteCatalog(@PathVariable("id") int id){
        try{
            catalogRepository.deleteById(id);
            return new ResponseEntity<>("Catalog was deleted", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Cannot delete catalog", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    ResponseEntity<String> createCatalog(@RequestBody Catalog catalog){
        try{
            catalogRepository.createNew(catalog.getId(), catalog.getName());
            return new ResponseEntity<>("Catalog was created", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Cannot create catalog", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
