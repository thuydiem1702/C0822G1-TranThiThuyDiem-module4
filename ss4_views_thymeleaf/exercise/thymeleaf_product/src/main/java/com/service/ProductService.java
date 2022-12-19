package com.service;

import com.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService {
    static List<Product> productList = new ArrayList<>();

    static {
        productList.add(new Product(1, "Bơ", 100000000, "Oishiii", "VN"));
        productList.add(new Product(2, "Hồng", 10000000, "Oishiii", "VN"));
        productList.add(new Product(3, "Dưa hấu", 10000000, "Oishiii", "VN"));
        productList.add(new Product(4, "Mận", 10000000, "Oishiii", "VN"));
        productList.add(new Product(5, "Nận", 10000000, "Oishiii", "VN"));
    }


    @Override
    public List<Product> getProductList() {
        return productList;
    }

    @Override
    public boolean addProduct(Product newProduct) {

        productList.add(newProduct);

        return true;
    }

    @Override
    public Product findProductById(int idFind) {
        for (Product x : productList) {
            if (x.getId() == idFind) {
                return x;
            }
        }

        return null;
    }

    @Override
    public boolean updateProduct(Product productUpdate) {

        for (Product x : productList) {
            if (x.getId() == productUpdate.getId()) {
                x.setName(productUpdate.getName());
                x.setPrice(productUpdate.getPrice());
                x.setDescription(productUpdate.getDescription());
                x.setManufacturer(productUpdate.getManufacturer());
            }
        }

        return false;
    }

    @Override
    public boolean deleteProduct(int idDelete) {

        productList.removeIf(x -> x.getId() == idDelete);

        return true;
    }

    @Override
    public List<Product> searchByName(String name) {
        List<Product> productsFound = new ArrayList<Product>();

        for (Product x : productList) {
            if (x.getName().contains(name)) {
                productsFound.add(x);
            }
        }

        return productsFound;
    }
}