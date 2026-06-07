package com.rahim.store.service;

import com.rahim.store.model.LocalUser;
import com.rahim.store.model.Product;
import com.rahim.store.model.dao.ProductDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }
    public List<Product> getProducts(){
        return productDAO.findAll();
    }
}
