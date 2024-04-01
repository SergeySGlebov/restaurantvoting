package ru.ssglebov.restaurantvoting.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = Menu.DELETE, query = "DELETE FROM Menu m WHERE m.id=:id"),
        @NamedQuery(name = Menu.GET_MENU_OF_DAY, query = "SELECT m FROM Menu m WHERE m.date=?1")
})
@Entity
@Table(name = "menu")
public class Menu extends AbstractBaseEntity {

    public static final String DELETE = "Menu.delete";
    public static final String GET_MENU_OF_DAY = "Menu.getMenuOfDay";

    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDate date;

    @JoinColumn(name = "restaurant_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Restaurant restaurant;

    @CollectionTable(name = "dish", joinColumns = @JoinColumn(name = "menu_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"menu_id", "name"}, name = "menu_date_idx")})
    @ElementCollection(fetch = FetchType.EAGER)
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

    public Menu() {

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
