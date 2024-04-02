package ru.ssglebov.restaurantvoting.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.ssglebov.restaurantvoting.model.Menu;
import ru.ssglebov.restaurantvoting.model.Restaurant;
import ru.ssglebov.restaurantvoting.model.Vote;
import ru.ssglebov.restaurantvoting.repository.RestaurantRepository;

import java.time.LocalDate;
import java.util.List;

import static ru.ssglebov.restaurantvoting.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {

    private RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Menu createMenu(Menu menu) {
        Assert.notNull(menu, "menu must not be null");
        return repository.saveMenu(menu);
    }

    public Menu getMenu(int id) {
        return checkNotFoundWithId(repository.getMenu(id), id);
    }

    public List<Menu> getMenuOfDay(LocalDate localDate) {
        return repository.getMenuOfDay(localDate);
    }

    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNotFoundWithId(repository.save(restaurant), restaurant.id());
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public Restaurant get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public Vote getVote(int id) {
        return checkNotFoundWithId(repository.getVote(id), id);
    }

    public Vote createVote(Vote vote) {
        Assert.notNull(vote, "vote must not be null");
        return repository.voting(vote);
    }

    public void updateVote(Vote vote) {
        Assert.notNull(vote, "vote must not be null");
        checkNotFoundWithId(repository.voting(vote), vote.id());
    }

    public List<Vote> getVoteOfDay(LocalDate localDate) {
        return repository.getVoteOfDay(localDate);
    }
}
