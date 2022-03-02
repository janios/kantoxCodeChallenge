package com.kantox.service.rules;

import static com.kantox.constants.CodeChallengeConstants.*;
import static java.util.stream.Collectors.groupingBy;

import com.kantox.models.Item;
import com.kantox.models.Product;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

@Slf4j
@Rule(name = GREEN_TEA_STRING)
public class GreenTeaBuyOneGetOneFree {

  @Condition
  public boolean discountGreenTea(@Fact(FACT_ITEMS) List<Item> itemList) {
    Map<Product, Long> map =
        itemList.stream().collect(groupingBy(Item::getProduct, Collectors.counting()));
    return map.containsKey(Product.GR1) && map.get(Product.GR1) >= 2;
  }

  @Action
  public void applyDiscount(@Fact(FACT_ITEMS) List<Item> itemList) {
    List<Item> greenTeaList =
        itemList.stream().filter(e -> e.getProduct() == Product.GR1).collect(Collectors.toList());
    int count = 1;
    for (Item greenItem : greenTeaList) {
      if (count % 2 == 0) {
        greenItem.setDiscount(GREEN_TEA_STRING);
        greenItem.setDiscountPrice(BigDecimal.ZERO);
      }
      count++;
    }
  }
}
