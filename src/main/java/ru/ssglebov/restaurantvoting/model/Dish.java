package ru.ssglebov.restaurantvoting.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Dish {

    private String name;
    private BigDecimal price;

    public Dish(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return Objects.equals(name, dish.name) && Objects.equals(price, dish.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
