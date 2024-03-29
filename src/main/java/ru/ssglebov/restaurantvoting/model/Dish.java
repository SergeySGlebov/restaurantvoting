package ru.ssglebov.restaurantvoting.model;

import java.math.BigDecimal;

public class Dish extends AbstractNamedEntity{

    private BigDecimal price;

    public Dish(String name, BigDecimal price) {
        this(null, name, price);
    }

    public Dish(Integer id, String name, BigDecimal price) {
        super(id, name);
        this.price = price;
    }
}
