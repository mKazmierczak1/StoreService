package com.example.productlist.service;

import com.example.productlist.model.CategoryOption;
import com.example.productlist.model.Product;
import com.example.productlist.repository.CategoryRepository;
import com.example.productlist.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  public void addProduct(Product product) {
    productRepository.save(product);
  }

  public Product getProductById(long id) {
    return productRepository.findById(id).orElse(null);
  }

  public List<CategoryOption> getCategoryOptions() {
    return categoryRepository.findAll().stream().map(CategoryOption::fromCategory).toList();
  }

  public void updateProduct(Product product) {
    productRepository.save(product);
  }

  public void deleteProductById(long id) {
    productRepository.deleteById(id);
  }
}
