package com.project.productservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    //get all products
    @RequestMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    //get a single product by id
    @RequestMapping("/products/{id}")
    public Product getProductByIdProd(@PathVariable Long id){
        return productService.getProductById(id);
    }

    //Post request to add product
    @RequestMapping(method = RequestMethod.POST, value = "/products")
    public void addProduct(@RequestBody Product product){
        productService.addProduct(product);
    }

    //Put request to update client
    @RequestMapping(method = RequestMethod.PUT,value = "/products/{id}")
    public void updateProduct(@RequestBody Product  product, @PathVariable Long id){
        productService.updateProduct(id,product);
    }

    //Delete request to delete a product
    @RequestMapping(method = RequestMethod.DELETE,value = "/products/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }

}
