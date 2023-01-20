package com.example.productlist.model;

public record CategoryOption(long id, String name) {

  public static CategoryOption fromCategory(Category category) {
    return new CategoryOption(category.getId(), category.getName());
  }
}
