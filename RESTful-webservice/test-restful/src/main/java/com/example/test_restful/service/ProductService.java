package com.example.test_restful.service;

import java.util.List;
import java.util.Optional;
import com.example.test_restful.model.Product;
import com.example.test_restful.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() { return productRepository.findAll(); }
    public Optional<Product> getProductById(Long id) { return productRepository.findById(id); }

    @Transactional
    public Product createProduct(Product product) { return productRepository.save(product); }

    public Product updateProduct(Product product) { return productRepository.save(product); }

    public void deleteProduct(Long id) { productRepository.deleteById(id); }
}