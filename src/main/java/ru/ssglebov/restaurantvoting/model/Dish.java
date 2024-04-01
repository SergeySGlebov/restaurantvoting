package ru.ssglebov.restaurantvoting.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Embeddable
public class Dish {

    @NotNull
    private String name;

    @NotBlank
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.EAGER)
    private Menu menu;

    public Dish(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public Dish() {

    }


}
