package com.example.completeecommerce.Controller;

import com.example.completeecommerce.Entity.Product;
import com.example.completeecommerce.Entity.ProductImages;
import com.example.completeecommerce.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductService productService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/product/add",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Product  addNewProduct(@RequestPart("product") Product product,
                                  @RequestPart("imageFile")MultipartFile[] file){
       try {
          Set<ProductImages> images= uploadImage(file);
          product.setProductImages(images);
           return productService.addNewProduct(product);

       }catch (IOException e){
           System.out.println("nfhiudshiu1111111111111111111111111111111111111111111111111111111111111111");
           return null;
       }
        }

        public Set<ProductImages> uploadImage(MultipartFile[] multipartFiles) throws IOException {
            Set<ProductImages> productImages=new HashSet<>();
            for (MultipartFile file:multipartFiles) {
                ProductImages productImages1=new ProductImages(
                        file.getOriginalFilename(),
                        file.getContentType(),
                        file.getBytes()
                );
                productImages.add(productImages1);
            }
            return productImages;
        }
        @GetMapping("/getall")
//        @PreAuthorize("hasRole('ADMIN')")
        public List<Product> getAllProducts(){
        return productService.getAllProducts();
        }

        @DeleteMapping("/deleteProductDetails/{productId}")

        @PreAuthorize("hasRole('ADMIN')")
        public void deleteProductDetails(@PathVariable("productId") Long productId){
        productService.deleteProduct(productId);
        }

        @GetMapping("getProductById/{id}")
        public Product getProductById(@PathVariable("id") Long id)
        {
        return productService.getProductById(id);
        }
    }

