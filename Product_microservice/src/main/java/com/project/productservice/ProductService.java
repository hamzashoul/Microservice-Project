package com.project.productservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        List<Product> products =new ArrayList<>();
        productRepository.findAll()
                .forEach(products::add);
        return products;
    }

    public Product getProductById(Long id){
        return productRepository.findProductById(id);
    }

    public void addProduct(Product product){
        productRepository.save(product);
    }

    public void updateProduct(Long id,Product product) {
        product = getProductById(id);
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}