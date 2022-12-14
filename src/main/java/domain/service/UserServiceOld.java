package domain.service;

import domain.model.Role;
import domain.model.Team;
import domain.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserServiceOld {
    private final Map<Integer, User> users = new HashMap<Integer, User>();
    private int userid = 1;    // als je later werkt met externe databank, wordt dit userid automatisch gegenereerd

    public UserServiceOld() {
        User director = new User("director@ucll.be", "t", "Ad", "Director", Team.ALPHA);
        director.setRole(Role.DIRECTOR);
        add(director);
        User boss = new User("a@a.be", "a", "The", "Boss", Team.ALPHA);
        boss.setRole(Role.DIRECTOR);
        add(boss);
        User leader = new User("b@b.be", "b", "A", "Leader", Team.BETA);
        leader.setRole(Role.TEAMLEADER);
        add(leader);
        add(new User("c@c.be", "c", "Good", "Employee", Team.BETA));
    }

    public User get(int userid) {
        return users.get(userid);
    }

    public ArrayList<User> getAllUsers() {
        return new ArrayList<User>(users.values());
    }

    public void add(User user) {
        if (user == null) {
            throw new DbException("No user given");
        }
        if (users.containsKey(user.getUserid())) {
            throw new DbException("User already exists");
        }
        user.setUserid(userid);   // user toevoegen geeft altijd nieuw userid
        users.put(user.getUserid(), user);
        userid++;
    }

    public void update(User user) {
        if (user == null) {
            throw new DbException("No user given");
        }
        if (!users.containsKey(user.getUserid())) {
            throw new DbException("No user found");
        }
        users.put(user.getUserid(), user); // user updaten: userid blijft behouden
    }

    public void delete(int userid) {
        users.remove(userid);   // userid gaat verloren, maar wordt niet ingenomen door eventuele nieuwe user
    }

    public void uniqueEmail(String email) {
        int size = users.size();
        for (int i = 1; i <= size; i++) {
            if (users.get(i) == null) size++;
            if (users.get(i) != null)
                if (users.get(i).getEmail().equals(email)) throw new DbException("Email already used");
        }
    }

    public void uniqueEditEmail(String email, int userid) {
        int size = users.size();
        for (int i = 1; i <= size; i++) {
            if (users.get(i) == null) size++;
            if (users.get(i) != null)
                if (!users.get(i).getEmail().equals(get(userid).getEmail()))
                    if (users.get(i).getEmail().equals(email)) throw new DbException("Email already used");
        }
    }

    public int getNumberOfUsers() {
        return users.size();
    }
}