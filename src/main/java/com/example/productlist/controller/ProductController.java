package com.example.productlist.controller;

import com.example.productlist.model.Product;
import com.example.productlist.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ProductController {
  private final ProductService productService;
  private long latestRequestedId;

  @GetMapping("/product/")
  public String home(Model model) {
    List<Product> productList = productService.getAllProducts();
    model.addAttribute("productList", productList);
    return "product/index";
  }

  @GetMapping("/product/add")
  public String add(Model model) {
    model.addAttribute("product", new Product());
    model.addAttribute("categoryOptions", productService.getCategoryOptions());
    return "product/add";
  }

  @PostMapping("/product/add")
  public String add(@ModelAttribute("product") Product product) {
    productService.addProduct(product);
    return "redirect:/product/";
  }

  @GetMapping("/product/details")
  public String details(@RequestParam("id") long inputId, Model model) {
    model.addAttribute("product", productService.getProductById(inputId));
    return "/product/details";
  }

  @GetMapping(value = {"/product/{productId}/edit"})
  public String edit(Model model, @PathVariable Integer productId) {
    model.addAttribute("product", productService.getProductById(productId));
    System.out.println(productService.getProductById(productId));
    model.addAttribute("categoryOptions", productService.getCategoryOptions());
    latestRequestedId = productId;

    System.out.println(productService.getCategoryOptions());
    return "/product/edit";
  }

  @PostMapping(value = {"/product/edit"})
  public String edit(@ModelAttribute Product product) {
    product.setId(latestRequestedId);
    productService.updateProduct(product);
    latestRequestedId = 0;
    return "redirect:/product/";
  }

  @GetMapping("/product/remove")
  public String remove(@RequestParam("id") long id) {
    productService.deleteProductById(id);
    return "redirect:/product/";
  }
}
