package com.example.completeecommerce.Services;

import com.example.completeecommerce.Entity.Product;
import com.example.completeecommerce.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public Product addNewProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> getAllProducts(){
         return productRepository.findAll();
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    public Product getProductById(Long id){
       return productRepository.findById(id).get();

    }

}
