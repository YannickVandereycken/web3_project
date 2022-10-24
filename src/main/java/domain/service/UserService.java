package domain.service;

import domain.model.User;

import java.util.ArrayList;

public interface UserService {

    void add(User user);

    User get(int id);

    ArrayList<User> getAllUsers();

    void update(User user);

    void uniqueEditEmail(String email, int userid);

    void uniqueEmail(String email);

    void delete(int id);
}
