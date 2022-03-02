package com.kantox.models;

import static com.kantox.constants.CodeChallengeConstants.NO_DISCOUNT;

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
    this.discount = NO_DISCOUNT;
    this.discountPrice = product.getPrice();
  }
}
