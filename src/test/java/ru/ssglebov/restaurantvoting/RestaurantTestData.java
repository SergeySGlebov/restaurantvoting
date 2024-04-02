package ru.ssglebov.restaurantvoting;

import ru.ssglebov.restaurantvoting.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import static ru.ssglebov.restaurantvoting.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator();
    public static final MatcherFactory.Matcher<Menu> MENU_MATCHER = MatcherFactory.usingIgnoringFieldsComparator("dishes.id");
    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator("user");

    public static final int KFC_ID = START_SEQ + 3;
    public static final int VIT_ID = START_SEQ + 4;
    public static final int BURGER_KING_ID = START_SEQ + 5;
    public static final int KFC_MENU_ID = START_SEQ + 6;
    public static final int VIT_MENU_ID = START_SEQ + 7;
    public static final int KFC_VOTE_ID = START_SEQ + 15;
    public static final int NOT_FOUND = 10;

    public static final Restaurant kfc = new Restaurant(KFC_ID, "KFC");
    public static final Restaurant vit = new Restaurant(VIT_ID, "ViT");
    public static final Restaurant burgerKing = new Restaurant(BURGER_KING_ID, "Burger King");
    public static final Menu kfcMenu = new Menu(KFC_MENU_ID, LocalDate.now(), kfc, Arrays.asList(
            new Dish(START_SEQ+8, "Twister", new BigDecimal("198.57")),
            new Dish(START_SEQ+9, "Chicken Strips", new BigDecimal("234.00")),
            new Dish(START_SEQ+10, "Milkshake", new BigDecimal("150.00"))
    ));

    public static final Menu vitMenu = new Menu(VIT_MENU_ID, LocalDate.now(), vit, Arrays.asList(
            new Dish(START_SEQ+11, "Big Mac", new BigDecimal("199.00")),
            new Dish(START_SEQ+12, "French fries", new BigDecimal("80.00")),
            new Dish(START_SEQ+13, "Coca-cola", new BigDecimal("111.00")),
            new Dish(START_SEQ+14, "Cherry pie", new BigDecimal("69.00"))
    ));

    public static final Vote kfcVote = new Vote(KFC_VOTE_ID, LocalDate.now(), UserTestData.user, kfc);

    public static Restaurant getNewRestaurant() {
        return new Restaurant("New restaurant");
    }

    public static Menu getNewMenu() {
        return new Menu(LocalDate.now(), burgerKing, Arrays.asList(
                new Dish("new dish", new BigDecimal("111.11"))
        ));
    }

    public static Vote getNewVote() {
        return new Vote(LocalDate.now(), UserTestData.admin, burgerKing);
    }

    public static Restaurant getUpdatedRestaurant() {
        Restaurant updated = new Restaurant(kfc);
        updated.setName("Rostic's");
        return updated;
    }

    public static Vote getUpdatedVote() {
        Vote updated = new Vote(kfcVote);
        updated.setRestaurant(burgerKing);
        return updated;
    }
}
