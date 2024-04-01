package ru.ssglebov.restaurantvoting.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ssglebov.restaurantvoting.model.Menu;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Repository
public class MenuRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Menu save(Menu menu) {
        if (menu.isNew()) {
            em.persist(menu);
            return menu;
        } else {
            return em.merge(menu);
        }
    }

    public Menu get(int id) {
        return em.find(Menu.class, id);
    }

    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Menu.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    public List<Menu> getMenuOfDay(LocalDate localDate) {
        return em.createNamedQuery(Menu.GET_MENU_OF_DAY, Menu.class)
                .setParameter(1, localDate)
                .getResultList();
    }
}
