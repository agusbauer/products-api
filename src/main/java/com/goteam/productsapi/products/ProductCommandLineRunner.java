package com.goteam.productsapi.products;

import com.goteam.productsapi.products.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class ProductCommandLineRunner implements CommandLineRunner {

    @Autowired
    ProductsRepository repository;

    @Override
    public void run(String... args) throws Exception {

        Product product = new Product.Builder("test_1", "Wine").build();
        Product product2 = new Product.Builder("test_2", "Pizza").build();
        repository.save(product);
        repository.save(product2);
    }
}
