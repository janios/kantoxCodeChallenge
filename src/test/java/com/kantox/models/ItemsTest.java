package com.kantox.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ItemsTest {
  @Test
  public void createProduct() {
    Item item = new Item(Product.CF1);
    assertEquals(item.getProduct(), Product.CF1);
    assertEquals(item.getDiscountPrice(), Product.CF1.getPrice());
  }
}
