package com.example.productlist.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity(name = "products")
@Data
@AllArgsConstructor
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;
  private float weight;
  private float price;

  @Column(name = "idx")
  private int index;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  public Product() {
    this(0L, "", 0.0f, 0.0f, 0, new Category());
  }
}
