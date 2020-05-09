package com.goteam.productsapi.products;

import com.goteam.productsapi.products.models.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductsRepository extends CrudRepository<Product,String> {
    List<Product> findByName(String name);
}
