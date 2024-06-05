package it.unicam.cs.ids.observer;

import it.unicam.cs.ids.model.Users;

public interface Observer {
    default void update(Users user, String message){
        System.out.println("Notifica inviata a " + user.getUsername() + " - " + message);
    };
}