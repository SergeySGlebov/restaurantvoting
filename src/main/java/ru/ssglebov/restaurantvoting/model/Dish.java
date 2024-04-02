package ru.ssglebov.restaurantvoting.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Dish extends AbstractNamedEntity{

    @NotNull
    @Column(name = "price")
    private BigDecimal price;

    public Dish(String name, BigDecimal price) {
        this(null, name, price);
    }

    public Dish(Integer id, String name, BigDecimal price) {
        super(id, name);
        this.price = price;
    }

    public Dish() {

    }

}
