package com.csl456.bikerentalapp.db;

import java.util.List;

import org.hibernate.SessionFactory;

import com.csl456.bikerentalapp.core.Person;
import com.csl456.bikerentalapp.core.User;

import io.dropwizard.hibernate.AbstractDAO;

public class UserDAO extends AbstractDAO<User> {

    public UserDAO(SessionFactory factory) {
        super(factory);
    }

    public User create(User user) {
        return persist(user);
    }
    
    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        return list(namedQuery("User.findAll"));
    }

    @SuppressWarnings("unchecked")
    public User findUsersByUsernameAndPassword(String username, String password) {
        return uniqueResult(namedQuery("User.findByUserNameAndPassword").setParameter("username", username)
                                                                .setParameter("password", password));
    }
}
