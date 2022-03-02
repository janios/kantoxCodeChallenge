package com.kantox.service;

import static org.junit.Assert.assertEquals;

import com.google.common.collect.ImmutableList;
import com.kantox.models.Item;
import com.kantox.models.Product;
import java.math.BigDecimal;
import java.util.List;
import org.junit.Test;

public class CashierServiceTest {

  @Test
  public void cashierServiceWithoutDiscount() {
    List<Item> itemList =
        ImmutableList.of(new Item(Product.CF1), new Item(Product.GR1), new Item(Product.SR1));
    BigDecimal totalPrice = CashierService.getTotalPrice(itemList);
    assertEquals(BigDecimal.valueOf(19.34).setScale(2), totalPrice);
  }

  @Test
  public void cashierServiceStrawberryDiscount() {
    List<Item> itemList =
        ImmutableList.of(new Item(Product.SR1), new Item(Product.SR1), new Item(Product.SR1));
    BigDecimal totalPrice = CashierService.getTotalPrice(itemList);
    assertEquals(BigDecimal.valueOf(13.50).setScale(2), totalPrice);
  }

  @Test
  public void cashierServiceStrawberryDiscountNotApply() {
    List<Item> itemList = ImmutableList.of(new Item(Product.SR1), new Item(Product.SR1));
    BigDecimal totalPrice = CashierService.getTotalPrice(itemList);
    assertEquals(BigDecimal.valueOf(10.00).setScale(2), totalPrice);
  }

  @Test
  public void cashierServiceCoffeeDiscount() {
    List<Item> itemList =
        ImmutableList.of(new Item(Product.CF1), new Item(Product.CF1), new Item(Product.CF1));
    BigDecimal totalPrice = CashierService.getTotalPrice(itemList);
    assertEquals(BigDecimal.valueOf(22.46).setScale(2), totalPrice);
  }

  @Test
  public void cashierServiceCoffeeDiscountNotApply() {
    List<Item> itemList = ImmutableList.of(new Item(Product.CF1), new Item(Product.CF1));
    BigDecimal totalPrice = CashierService.getTotalPrice(itemList);
    assertEquals(BigDecimal.valueOf(22.46).setScale(2), totalPrice);
  }

  @Test
  public void cashierServiceGreeTea() {
    List<Item> itemList = ImmutableList.of(new Item(Product.GR1), new Item(Product.GR1));
    BigDecimal totalPrice = CashierService.getTotalPrice(itemList);
    assertEquals(BigDecimal.valueOf(3.11).setScale(2), totalPrice);
  }

  @Test
  public void cashierServiceGreeTea3Items() {
    List<Item> itemList = ImmutableList.of(new Item(Product.GR1), new Item(Product.GR1), new Item(Product.GR1));
    BigDecimal totalPrice = CashierService.getTotalPrice(itemList);
    assertEquals(BigDecimal.valueOf(6.22).setScale(2), totalPrice);
  }

  @Test
  public void cashierServiceGreeTea5Items() {
    List<Item> itemList = ImmutableList.of(new Item(Product.GR1), new Item(Product.GR1), new Item(Product.GR1), new Item(Product.GR1), new Item(Product.GR1));
    BigDecimal totalPrice = CashierService.getTotalPrice(itemList);
    assertEquals(BigDecimal.valueOf(9.33).setScale(2), totalPrice);
  }

  @Test
  public void cashierServiceTestCases(){
    List<Item> itemList = ImmutableList.of(new Item(Product.GR1), new Item(Product.SR1), new Item(Product.GR1), new Item(Product.GR1), new Item(Product.CF1));
    BigDecimal totalPrice = CashierService.getTotalPrice(itemList);
    assertEquals(BigDecimal.valueOf(22.45).setScale(2), totalPrice);

    itemList = ImmutableList.of(new Item(Product.GR1), new Item(Product.GR1));
    totalPrice = CashierService.getTotalPrice(itemList);
    assertEquals(BigDecimal.valueOf(3.11).setScale(2), totalPrice);

    itemList = ImmutableList.of(new Item(Product.SR1), new Item(Product.SR1), new Item(Product.GR1), new Item(Product.SR1) );
    totalPrice = CashierService.getTotalPrice(itemList);
    assertEquals(BigDecimal.valueOf(16.61).setScale(2), totalPrice);

    itemList = ImmutableList.of(new Item(Product.GR1), new Item(Product.CF1), new Item(Product.SR1), new Item(Product.CF1), new Item(Product.CF1) );
    totalPrice = CashierService.getTotalPrice(itemList);
    assertEquals(BigDecimal.valueOf(30.57).setScale(2), totalPrice);

  }

}
