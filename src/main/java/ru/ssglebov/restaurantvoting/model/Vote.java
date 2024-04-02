package ru.ssglebov.restaurantvoting.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NamedQueries({
        @NamedQuery(name = Vote.GET_VOTE_OF_DAY, query = "SELECT v FROM Vote v WHERE v.date=?1")
})
@Entity
@Table(name = "vote")
public class Vote extends AbstractBaseEntity {

    public static final String GET_VOTE_OF_DAY = "Vote.getVoteOfDay";

    @NotNull
    @Column(name = "date")
    private LocalDate date;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @JoinColumn(name = "restaurant_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Restaurant restaurant;

    public Vote(LocalDate date, User user, Restaurant restaurant) {
        this(null, date, user, restaurant);
    }

    public Vote(Integer id, LocalDate date, User user, Restaurant restaurant) {
        super(id);
        this.date = date;
        this.user = user;
        this.restaurant = restaurant;
    }

    public Vote() {

    }

    public Vote(Vote v) {
        this(v.getId(), v.getDate(), v.getUser(), v.getRestaurant());
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
