package com.kantox.service;

import static com.kantox.constants.CodeChallengeConstants.*;

import com.kantox.models.Item;
import com.kantox.service.rules.CoffeeDiscount;
import com.kantox.service.rules.GreenTeaBuyOneGetOneFree;
import com.kantox.service.rules.StrawberryBulkPruchases;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;

public class CashierService {

  private static Rules getRules() {
    Rules rules = new Rules();
    rules.register(new StrawberryBulkPruchases());
    rules.register(new CoffeeDiscount());
    rules.register(new GreenTeaBuyOneGetOneFree());
    return rules;
  }

  public static BigDecimal getTotalPrice(List<Item> itemList) {
    RulesEngine rulesEngine = new DefaultRulesEngine();
    Facts fact = new Facts();
    fact.put(FACT_ITEMS, itemList);
    rulesEngine.fire(getRules(), fact);

    return itemList.stream()
        .map(e -> e.getDiscountPrice())
        .reduce(BigDecimal.ZERO, BigDecimal::add)
        .setScale(2, RoundingMode.HALF_DOWN);
  }
}
