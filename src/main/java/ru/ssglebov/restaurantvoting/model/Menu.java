package ru.ssglebov.restaurantvoting.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Menu extends AbstractBaseEntity {
    private LocalDate date;

    private Restaurant restaurant;

    private List<Dish> dishes;

    public Menu(LocalDate date, Restaurant restaurant, List<Dish> dishes) {
        this(null, date, restaurant, dishes);
    }

    public Menu(Integer id, LocalDate date, Restaurant restaurant, List<Dish> dishes) {
        super(id);
        this.date = date;
        this.restaurant = restaurant;
        this.dishes = dishes;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void addDish(String dish, BigDecimal price) {
        dishes.add(new Dish(dish, price));
    }
}
