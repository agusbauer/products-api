package com.goteam.productsapi.products;

import com.goteam.productsapi.products.models.Product;
import com.goteam.productsapi.products.models.ProductMapper;
import com.goteam.productsapi.products.models.ProductRequestDTO;
import com.goteam.productsapi.configuration.BasicConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductsService {

    @Autowired
    private BasicConfiguration basicConf;

    @Autowired
    private ProductsRepository repository;

    public Product create(ProductRequestDTO requestDTO){

        Product newProduct = ProductMapper.requestToProduct(requestDTO);
        return repository.save(newProduct);
    }

    public Product retrieveById(String id){

        return repository.findById(id).orElse(null);
    }

}
