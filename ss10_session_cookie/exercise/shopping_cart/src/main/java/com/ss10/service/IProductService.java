package com.ss10.service;

import com.ss10.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> findAll();
    Optional<Product> findById(int id);
    void save(Product product);
}
