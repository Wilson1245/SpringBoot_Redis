package com.example.javaTechieRedie.controllers;


import com.example.javaTechieRedie.entity.Product;
import com.example.javaTechieRedie.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Iterator<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Product> findById(@PathVariable int id) {
        return productService.findById(id);
    }

    @GetMapping("/name")
    public Product findByName(@RequestParam(defaultValue = "") String name) {
        if (name.isEmpty()) {
            return null;
        }
        return productService.findByName(name);
    }

    @PostMapping
    public Product save(@RequestBody Product product) {
        return product;
    }

    @PostMapping("/all")
    public String saveAll(@RequestBody List<Product> products) {
        productService.saveAll(products);
        return "save all success";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, @RequestBody Product product) {
        product.setId(id);
        return "update success";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        productService.delete(id);
        return "delete success";
    }
}
