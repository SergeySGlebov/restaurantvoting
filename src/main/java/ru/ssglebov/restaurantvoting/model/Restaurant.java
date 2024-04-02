package ru.ssglebov.restaurantvoting.model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:id")
})
@Entity
@Table(name = "restaurant")
public class Restaurant extends AbstractNamedEntity {

    public static final String DELETE = "Restaurant.delete";

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public Restaurant(String name) {
        this(null, name);
    }

    public Restaurant() {

    }
    public Restaurant(Restaurant r) {
        this(r.getId(), r.getName());
    }
}
