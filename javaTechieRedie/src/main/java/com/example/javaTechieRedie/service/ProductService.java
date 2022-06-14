package com.example.javaTechieRedie.service;

import com.example.javaTechieRedie.entity.Product;
import com.example.javaTechieRedie.repository.ProductRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductService {
    private final ProductRepo productRepo;
//    private final RedisTemplate<String, Product> redisTemplate;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public void save(Product product) {
        productRepo.save(product);
    }

    public Optional<Product> findById(int id) {
        return productRepo.findById(id);
    }

    public Product findByName(String name) {
        log.info(">>>> findByName: {}", name);
        return productRepo.findFirstByName(name);
    }

//    public List<String> findNameList() {
//        List<Product> productList = (List<Product>) productRepo.findAll();
//        return productList.stream().map(p -> p.getName()).collect(Collectors.toList());
//    }

    public Iterator<Product> findAll() {
        return productRepo.findAll().iterator();
    }

    public void updateProduct(Product product) {
        productRepo.save(product);
    }

    public void delete(int id) {
        productRepo.deleteById(id);
    }

    public void saveAll(List<Product> products) {
        productRepo.saveAll(products);
    }
}
