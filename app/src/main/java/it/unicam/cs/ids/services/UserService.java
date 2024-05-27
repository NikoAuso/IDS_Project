package it.unicam.cs.ids.services;

import it.unicam.cs.ids.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {
    private final List<User> userList = new ArrayList<>();

    public User create(User user) {
        userList.add(user);
        return user;
    }

    public User read(int id) {
        Optional<User> User = userList.stream().filter(i -> i.getId() == id).findFirst();
        return User.orElse(null);
    }

    public void update(int id, User user) {
        if (id >= 0 && id < userList.size() && userList.get(id).getId() == id) {
            userList.set(id, user);
        } else {
            throw new IllegalArgumentException("Contest non trovato!");
        }
    }

    public void delete(int id) {
        userList.removeIf(i -> i.getId() == id);
    }

}