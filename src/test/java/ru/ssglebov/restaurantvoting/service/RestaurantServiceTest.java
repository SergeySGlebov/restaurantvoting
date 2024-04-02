package ru.ssglebov.restaurantvoting.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.ssglebov.restaurantvoting.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.ssglebov.restaurantvoting.RestaurantTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class RestaurantServiceTest {

    @Autowired
    private RestaurantService service;
    @Test
    public void createMenu() {
        Menu created = service.createMenu(getNewMenu());
        int newId = created.getId();
        Menu newMenu = getNewMenu();
        newMenu.setId(newId);
        MENU_MATCHER.assertMatch(created, newMenu);
        MENU_MATCHER.assertMatch(service.getMenu(newId), newMenu);
    }

    @Test
    public void duplicateDateMenuCreate() {
        assertThrows(DataAccessException.class, () ->
                service.createMenu(new Menu(null, LocalDate.now(), kfc, Arrays.asList(new Dish("duplDish", new BigDecimal("10"))))));
    }

    @Test
    public void getMenu() {
        Menu menu = service.getMenu(KFC_MENU_ID);
        MENU_MATCHER.assertMatch(menu, kfcMenu);
    }

    @Test
    public void getMenuNotFound() {
        assertThrows(IllegalStateException.class, () -> service.getMenu(NOT_FOUND));
    }

    @Test
    public void getMenuOfDay() {
        List<Menu> all = service.getMenuOfDay(LocalDate.now());
        MENU_MATCHER.assertMatch(all, kfcMenu, vitMenu);
    }

    @Test
    public void create() {
        Restaurant created = service.create(getNewRestaurant());
        int newId = created.getId();
        Restaurant newRestaurant = getNewRestaurant();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(service.get(newId), newRestaurant);
    }

    @Test
    public void update() {
        Restaurant updated = getUpdatedRestaurant();
        service.update(updated);
        RESTAURANT_MATCHER.assertMatch(service.get(KFC_ID), getUpdatedRestaurant());
    }

    @Test
    public void delete() {
        service.delete(BURGER_KING_ID);
        assertThrows(IllegalStateException.class, () -> service.get(BURGER_KING_ID));
    }

    @Test
    public void get() {
        Restaurant restaurant = service.get(KFC_ID);
        RESTAURANT_MATCHER.assertMatch(restaurant, kfc);
    }

    @Test
    public void getNotFound() {
        assertThrows(IllegalStateException.class, () -> service.get(NOT_FOUND));
    }

    @Test
    public void getVote() {
        Vote vote = service.getVote(KFC_VOTE_ID);
        VOTE_MATCHER.assertMatch(vote, kfcVote);
    }

    @Test
    public void getVoteNotFound() {
        assertThrows(IllegalStateException.class, () -> service.getVote(NOT_FOUND));
    }

    @Test
    public void createVote() {
        Vote created = service.createVote(getNewVote());
        int newId = created.id();
        Vote newVote = getNewVote();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
        VOTE_MATCHER.assertMatch(service.getVote(newId), newVote);
    }

    @Test
    public void updateVote() {
        Vote updated = getUpdatedVote();
        service.updateVote(updated);
        VOTE_MATCHER.assertMatch(service.getVote(KFC_VOTE_ID), getUpdatedVote());
    }

    @Test
    public void getVoteOfDay() {
        List<Vote> all = service.getVoteOfDay(LocalDate.now());
        VOTE_MATCHER.assertMatch(all, kfcVote);
    }
}