package com.example.productlist.service;

import com.example.productlist.model.Category;
import com.example.productlist.repository.CategoryRepository;
import com.example.productlist.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;

  public List<Category> getAllCategories() {
    return categoryRepository.findAll();
  }

  public void addCategory(Category category) {
    categoryRepository.save(category);
  }

  public Category getCategoryById(long id) {
    return categoryRepository.findById(id).orElse(null);
  }

  public void updateCategory(Category category) {
    categoryRepository.save(category);
  }

  public void deleteCategoryById(long id) {
    productRepository.deleteAll(getCategoryById(id).getProducts());
    categoryRepository.deleteById(id);
  }
}
