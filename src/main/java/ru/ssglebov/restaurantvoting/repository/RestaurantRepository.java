package ru.ssglebov.restaurantvoting.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ssglebov.restaurantvoting.model.Menu;
import ru.ssglebov.restaurantvoting.model.Restaurant;
import ru.ssglebov.restaurantvoting.model.Vote;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class RestaurantRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Restaurant save(Restaurant restaurant) {
        if (restaurant.isNew()) {
            em.persist(restaurant);
            return restaurant;
        } else {
            return em.merge(restaurant);
        }
    }

    public Restaurant get(int id) {
        return em.find(Restaurant.class, id);
    }

    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Restaurant.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Transactional
    public Vote voting(Vote vote) {
        if (vote.isNew()) {
            em.persist(vote);
            return vote;
        } else {
            return em.merge(vote);
        }
    }

    public Vote getVote(int id) {
        return em.find(Vote.class, id);
    }

    public List<Vote> getVoteOfDay(LocalDate localDate) {
        return em.createNamedQuery(Vote.GET_VOTE_OF_DAY, Vote.class)
                .setParameter(1, localDate)
                .getResultList();
    }

    @Transactional
    public Menu saveMenu(Menu menu) {
        if (menu.isNew()) {
            em.persist(menu);
            return menu;
        } else {
            return em.merge(menu);
        }
    }

    public Menu getMenu(int id) {
        return em.find(Menu.class, id);
    }

    public List<Menu> getMenuOfDay(LocalDate localDate) {
        return em.createNamedQuery(Menu.GET_MENU_OF_DAY, Menu.class)
                .setParameter(1, localDate)
                .getResultList();
    }
}
