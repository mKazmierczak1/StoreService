package com.example.productlist.controller;

import com.example.productlist.model.Category;
import com.example.productlist.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class CategoryController {
  private final CategoryService categoryService;
  private long latestRequestedId;

  @GetMapping("/category")
  public String home(Model model) {
    List<Category> categoryList = categoryService.getAllCategories();
    model.addAttribute("categoryList", categoryList);
    return "category/index";
  }

  @GetMapping("/category/add")
  public String add(Model model) {
    model.addAttribute("category", new Category());
    return "category/add";
  }

  @PostMapping("/category/add")
  public String add(@ModelAttribute("category") Category category) {
    categoryService.addCategory(category);
    return "redirect:/category/";
  }

  @GetMapping(value = {"/category/{categoryId}/edit"})
  public String edit(Model model, @PathVariable Integer categoryId) {
    model.addAttribute("category", categoryService.getCategoryById(categoryId));
    latestRequestedId = categoryId;
    return "/category/edit";
  }

  @PostMapping(value = {"/category/edit"})
  public String edit(Model model, @ModelAttribute Category category) {
    category.setId(latestRequestedId);
    categoryService.updateCategory(category);
    latestRequestedId = 0;
    return "redirect:/category/";
  }

  @GetMapping("/category/remove")
  public String remove(@RequestParam("id") long id) {
    categoryService.deleteCategoryById(id);
    return "redirect:/category/";
  }
}
