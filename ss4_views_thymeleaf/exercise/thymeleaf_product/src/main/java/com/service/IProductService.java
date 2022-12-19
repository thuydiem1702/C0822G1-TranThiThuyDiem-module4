package com.service;

import com.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> getProductList();

    boolean addProduct(Product newProduct);

    Product findProductById(int id);

    boolean updateProduct(Product productUpdate);

    boolean deleteProduct(int idDelete);

    List<Product> searchByName(String productNameSearch);
}
