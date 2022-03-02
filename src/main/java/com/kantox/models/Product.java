package com.kantox.models;

import java.math.BigDecimal;

public enum Product {
  GR1("Green tea", BigDecimal.valueOf(3.11)),
  SR1("Strawberries", BigDecimal.valueOf(5)),
  CF1("Coffee", BigDecimal.valueOf(11.23));

  private String description;
  private BigDecimal price;

  private Product(String description, BigDecimal price) {
    this.description = description;
    this.price = price;
  }

  public String getDescription() {
    return description;
  }

  public BigDecimal getPrice() {
    return price;
  }
}
