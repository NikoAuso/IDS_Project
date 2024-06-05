package it.unicam.cs.ids.observer;

import it.unicam.cs.ids.model.Users;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
    private final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(Users user, String message) {
        for (Observer observer : observers) {
            observer.update(user, message);
        }
    }
}
