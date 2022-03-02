package com.kantox.models;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Item {

  private Product product;
  private String discount;
  private BigDecimal discountPrice;

  public Item(Product product) {
    this.product = product;
    this.discount = "None";
    this.discountPrice = product.getPrice();
  }
}
