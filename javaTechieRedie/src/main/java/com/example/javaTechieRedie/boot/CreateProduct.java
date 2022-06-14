package com.example.javaTechieRedie.boot;

import com.example.javaTechieRedie.entity.Product;
import com.example.javaTechieRedie.repository.ProductRepo;
import com.example.javaTechieRedie.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(2)
@Slf4j
public class CreateProduct implements CommandLineRunner {

    private final ProductService productService;

    private final ProductRepo productRepo;

    public CreateProduct(ProductService productService, ProductRepo productRepo) {
        this.productService = productService;
        this.productRepo = productRepo;
    }
//    @PostConstruct
//    public void saveAll() {
//        productService.saveAll(List.of(
//                new Product(1, "Product 1", 10, 100),
//                new Product(2, "Product 2", 20, 200),
//                new Product(3, "Product 3", 30, 300),
//                new Product(4, "Product 4", 40, 400),
//                new Product(5, "Product 5", 50, 500)
//        ));
//    }

    @Override
    public void run(String... args) throws Exception {
        // saving one Product
//        productService.save(new Product(6, "Product 1", 10, 100));

        // saving multiple Products
        if (productRepo.count() == 0) {
            productService.saveAll(
                    List.of(
                            new Product(1, "Product 1", 10, 100),
                            new Product(2, "Product 2", 20, 200),
                            new Product(3, "Product 3", 30, 300),
                            new Product(4, "Product 4", 40, 400),
                            new Product(5, "Product 5", 50, 500)
                    )
            );
            log.info(">>>> Create Product");
        }

        // modifying product with id = 2
//        productService.updateProduct(new Product(2, "Product 2", 33, 333));

        // deleting product with id = 4
//        productService.delete(4);

        // retrieving all products
//        productService.findAll().forEachRemaining(System.out::println);

        // retrieving product with id = 100
    }
}
