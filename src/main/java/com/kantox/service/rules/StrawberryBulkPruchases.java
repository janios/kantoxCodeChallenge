package com.kantox.service.rules;

import static com.kantox.constants.CodeChallengeConstants.*;
import static java.util.stream.Collectors.groupingBy;

import com.kantox.models.Item;
import com.kantox.models.Product;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

@Slf4j
@Rule(name = STRAWBERRIES_BULK_PURCHASES)
public class StrawberryBulkPruchases {

  @Condition
  public boolean bulkPurchases(@Fact(FACT_ITEMS) List<Item> itemList) {
    Map<Product, Long> map =
        itemList.stream().collect(groupingBy(Item::getProduct, Collectors.counting()));
    return map.containsKey(Product.SR1)
        && map.get(Product.SR1) >= STRAWBERRIES_BULK_PURCHASE_MINIMUM;
  }

  @Action
  public void applyDiscount(@Fact(FACT_ITEMS) List<Item> itemList) {
    itemList.stream()
        .filter(e -> e.getProduct() == Product.SR1)
        .forEach(
            e -> {
              e.setDiscountPrice(STRAWBERRIES_DISCOUNT_PRICE);
              e.setDiscount(STRAWBERRIES_BULK_PURCHASES);
            });
  }
}
