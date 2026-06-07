package com.rahim.store.api.Product;



import com.rahim.store.model.LocalUser;
import com.rahim.store.model.Product;
import com.rahim.store.model.WebOrder;
import com.rahim.store.service.OrderService;
import com.rahim.store.service.ProductService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public List<Product> getProducts(){
        return productService.getProducts();
    }
}
