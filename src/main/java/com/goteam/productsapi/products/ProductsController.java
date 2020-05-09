package com.goteam.productsapi.products;

import com.goteam.productsapi.products.models.Product;
import com.goteam.productsapi.products.models.ProductRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@RestController
public class ProductsController {

    @Autowired
    ProductsService productService;

    @GetMapping(value = "/products/{id}")
    @ResponseBody
    public Product get(@PathVariable("id") String id){

        return productService.retrieveById(id);
    }

    @PostMapping(value = "/products")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Product post(@RequestBody @Valid ProductRequestDTO request){
        return productService.create(request);
    }
}
