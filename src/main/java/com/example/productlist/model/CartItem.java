package com.example.productlist.model;

import lombok.Data;

@Data
public class CartItem {
  private final Product product;
  private int amount = 1;

  public void increaseAmount() {
    amount++;
  }

  public void decreaseAmount() {
    if (amount > 1) {
      amount--;
    }
  }
}
