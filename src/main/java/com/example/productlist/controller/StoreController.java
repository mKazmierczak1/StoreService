package com.example.productlist.controller;

import com.example.productlist.model.Product;
import com.example.productlist.service.ProductService;
import com.example.productlist.service.StoreService;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class StoreController {
  private final ProductService productService;
  private final StoreService storeService;

  @GetMapping("/store/")
  public String home(HttpServletResponse response, Model model) {
    List<Product> productList = productService.getAllProducts();
    model.addAttribute("productList", productList);

    return "store/index";
  }

  @GetMapping(value = {"/cart/{itemId}/add"})
  public String addToCart(HttpServletResponse response, @PathVariable Long itemId) {
    ResponseCookie resCookie =
        ResponseCookie.from("product." + itemId, itemId.toString())
            .httpOnly(true)
            .sameSite("None")
            .secure(true)
            .path("/")
            .maxAge(Math.toIntExact(300))
            .build();
    response.addHeader("Set-Cookie", resCookie.toString());

    return "redirect:/store/";
  }

  @GetMapping("/cart/")
  public String cart(HttpServletRequest request, Model model) {
    var ids =
        Arrays.stream(request.getCookies())
            .filter(cookie -> cookie.getName().contains("product"))
            .map(Cookie::getValue)
            .map(Long::parseLong)
            .toList();
    var items = storeService.getProductById(ids);

    model.addAttribute("productList", items);

    return "store/cart";
  }

  @GetMapping("/cart/{itemId}/increase")
  public String increaseAmount(@PathVariable Long itemId) {
    storeService.increaseAmountForId(itemId);
    return "redirect:/cart/";
  }

  @GetMapping("/cart/{itemId}/decrease")
  public String decreaseAmount(@PathVariable Long itemId) {
    storeService.decreaseAmountForId(itemId);
    return "redirect:/cart/";
  }

  @GetMapping(value = {"/cart/{itemId}/remove"})
  public String removeFromCart(HttpServletResponse response, @PathVariable Long itemId) {
    ResponseCookie resCookie =
        ResponseCookie.from("product." + itemId, itemId.toString())
            .httpOnly(true)
            .sameSite("None")
            .secure(true)
            .path("/")
            .maxAge(Math.toIntExact(0))
            .build();
    response.addHeader("Set-Cookie", resCookie.toString());

    return "redirect:/cart/";
  }
}
