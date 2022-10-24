package domain.service;

import domain.model.User;

import java.util.ArrayList;

public class AppService {
    private UserService userService = new UserServiceDBSQL();

    public void add(User user) {
        userService.add(user);
    }

    public User get(int id) {
        return userService.get(id);
    }

    public ArrayList<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public void update(User user) {
        userService.update(user);
    }

    public void uniqueEditEmail(String email, int userid) {
        userService.uniqueEditEmail(email, userid);
    }

    public void uniqueEmail(String email) {
        userService.uniqueEmail(email);
    }

    public void delete(int id) {
        userService.delete(id);
    }
}