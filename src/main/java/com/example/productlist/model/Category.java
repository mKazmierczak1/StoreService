package com.example.productlist.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity(name = "categories")
@Data
@AllArgsConstructor
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;
  private String code;

  @OneToMany(mappedBy = "category")
  private List<Product> products;

  public Category() {
    this(0L, "", "", new ArrayList<>());
  }

  @Override
  public String toString() {
    return "Category(id=" + id + ", name=" + name + ", code=" + code + ")";
  }
}
