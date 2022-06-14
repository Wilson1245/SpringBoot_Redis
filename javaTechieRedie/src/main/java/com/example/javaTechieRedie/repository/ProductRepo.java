package com.example.javaTechieRedie.repository;

import com.example.javaTechieRedie.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProductRepo extends CrudRepository<Product, Integer> {

    Product findFirstByName(String name);

//    private final String hashReference = "product";
//
//
//    private final RedisTemplate<String, Product> redisTemplate;
//
//    public ProductRepo(RedisTemplate<String, Product> redisTemplate) {
//        this.redisTemplate = redisTemplate;
//    }

//    @Override
//    public void save(Product product) {
//        redisTemplate.opsForHash().putIfAbsent(hashReference, product.getId(), product);
//    }
//
//    @Override
//    public Product findById(int id) {
//        return (Product) redisTemplate.opsForHash().get(hashReference, id);
//    }
//
//    @Override
//    public void updateProduct(Product product) {
//        redisTemplate.opsForHash().put(hashReference, product.getId(), product);
//    }
//
//    @Override
//    public Map<Object, Object> findAll() {
//        return redisTemplate.opsForHash().entries(hashReference);
//    }
//
//    @Override
//    public void delete(int id) {
//        redisTemplate.opsForHash().delete(hashReference, id);
//    }
//
//    @Override
//    public void saveAll(Map<Integer, Product> map) {
//        redisTemplate.opsForHash().putAll(hashReference, map);
//    }
}
