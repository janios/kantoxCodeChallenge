package com.kantox.service

import com.google.common.collect.ImmutableList
import com.kantox.models.Item
import com.kantox.models.Product
import spock.lang.Specification
import spock.lang.Unroll

class CashierServiceSpec extends Specification{

    @Unroll
    def "Scenario :Cashier Service -  [#_scenario] "(){

        given:
            def items = _items as Item[]
            def expectedPrice = _expectedPrice
        when:
            def totalPrice = CashierService.getTotalPrice(ImmutableList.of(items))
        then:
            assert  totalPrice == expectedPrice
        where:
         _scenario << [
                 "Without Discount",
                 "Strawberry Discount",
                 "Strawberry Discount not Apply",
                 "Coffee Discount",
                 "Coffee Discount not Apply",
                 "Green Tea Discount",
                 "Green Tea 3 elements",
                 "Green Tea 5 elements",
                 "Test Case 1",
                 "Test Case 2",
                 "Test Case 3",
                 "Test Case 4"
         ]

        _items << [
                [ new Item(Product.CF1),
                  new Item(Product.GR1),
                  new Item(Product.SR1)
                ],[
                  new Item(Product.SR1),
                  new Item(Product.SR1),
                  new Item(Product.SR1)
                ],[
                  new Item(Product.SR1),
                  new Item(Product.SR1)
                ],[
                  new Item(Product.CF1),
                  new Item(Product.CF1),
                  new Item(Product.CF1)
                ],[
                  new Item(Product.CF1),
                  new Item(Product.CF1)
                ],[
                  new Item(Product.GR1),
                  new Item(Product.GR1)
                ], [
                  new Item(Product.GR1),
                  new Item(Product.GR1),
                  new Item(Product.GR1)
                ], [
                  new Item(Product.GR1),
                  new Item(Product.GR1),
                  new Item(Product.GR1),
                  new Item(Product.GR1),
                  new Item(Product.GR1)
                ], [
                  new Item(Product.GR1),
                  new Item(Product.SR1),
                  new Item(Product.GR1),
                  new Item(Product.GR1),
                  new Item(Product.CF1)
                ], [
                  new Item(Product.GR1),
                  new Item(Product.GR1)
                ],[
                  new Item(Product.SR1),
                  new Item(Product.SR1),
                  new Item(Product.GR1),
                  new Item(Product.SR1)
                ], [
                  new Item(Product.GR1),
                  new Item(Product.CF1),
                  new Item(Product.SR1),
                  new Item(Product.CF1),
                  new Item(Product.CF1)
                ]
        ]

        _expectedPrice << [
                BigDecimal.valueOf(19.34).setScale(2),
                BigDecimal.valueOf(13.50).setScale(2),
                BigDecimal.valueOf(10.00).setScale(2),
                BigDecimal.valueOf(22.46).setScale(2),
                BigDecimal.valueOf(22.46).setScale(2),
                BigDecimal.valueOf(3.11).setScale(2),
                BigDecimal.valueOf(6.22).setScale(2),
                BigDecimal.valueOf(9.33).setScale(2),
                BigDecimal.valueOf(22.45).setScale(2),
                BigDecimal.valueOf(3.11).setScale(2),
                BigDecimal.valueOf(16.61).setScale(2),
                BigDecimal.valueOf(30.57).setScale(2)
        ]





    }

}

