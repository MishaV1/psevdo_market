package com.example.magazine.mishas_market;

import com.example.models.Item;
import com.example.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/market/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/{id}")
    Item findById(@PathVariable("id") int id){
        return productRepository.findById(id);
    }

    @GetMapping("/all")
    List<Item> findAll(){
        return productRepository.findThemAll();
    }

    @GetMapping("/byName/{name}")
    Item findByName(@PathVariable("name") String name){return  productRepository.findByName(name);}

    @GetMapping("/price/{id}")
    int getPriceById(@PathVariable("id") int id){
        return productRepository.getPriceById(id);
    }


    @DeleteMapping(value = "delete/{id:\\d+}")
    @ResponseBody
    public ResponseEntity<String> deleteProduct(@PathVariable("id") int id){
        try{
            productRepository.deleteById(id);
            return new ResponseEntity<>("Product was deleted", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Cannot delete product", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/create")
    public ResponseEntity<String> creatingNewProduct(@RequestBody Product product){
        productRepository.createNew(product.getName(), product.getPrice(), product.getIdofcatalog());
        return new ResponseEntity<>("Product was created", HttpStatus.CREATED);

    }

}
