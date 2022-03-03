package com.kantox.service.rules;

import static com.kantox.constants.CodeChallengeConstants.*;
import static java.util.stream.Collectors.groupingBy;

import com.kantox.models.Item;
import com.kantox.models.Product;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

@Slf4j
@Rule(name = COFFEE_DISCOUNT_STRING)
public class CoffeeDiscount {

  @Condition
  public boolean coffee3OrMore(@Fact(FACT_ITEMS) List<Item> itemList) {
    Map<Product, Long> map =
        itemList.stream().collect(groupingBy(Item::getProduct, Collectors.counting()));
    return map.containsKey(Product.CF1) && map.get(Product.CF1) >= COFFEE_DISCOUNT_MINIMUM;
  }

  @Action
  public void applyDiscount(@Fact(FACT_ITEMS) List<Item> itemList) {
    itemList.stream()
        .filter(e -> e.getProduct() == Product.CF1)
        .forEach(
            e -> {
              e.setDiscountPrice(
                  e.getDiscountPrice()
                      .multiply(COFFEE_DISCOUNT)
                      .setScale(3, RoundingMode.HALF_DOWN));
              e.setDiscount(COFFEE_DISCOUNT_STRING);
            });
  }
}
