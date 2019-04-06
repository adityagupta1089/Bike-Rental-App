package com.csl456.bikerentalapp.db;

import com.csl456.bikerentalapp.core.User;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDAO extends AbstractDAO<User> {

    public UserDAO(SessionFactory factory) {
        super(factory);
    }

    public User create(User user) {
        return persist(user);
    }

    @SuppressWarnings("unchecked")
    public List<User> findUsersByUsernameAndPassword(String username, String password) {
        return list(namedQuery("User.findByUserNameAndPassword").setParameter("username", username)
                                                                .setParameter("password", password));
    }
}
