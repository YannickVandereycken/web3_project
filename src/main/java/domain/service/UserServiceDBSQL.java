package domain.service;

import domain.model.Role;
import domain.model.Team;
import domain.model.User;
import util.DBConnectionService;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

public class UserServiceDBSQL implements UserService {

    private final Connection connection;
    private final String schema;

    public UserServiceDBSQL() {
        this.connection = DBConnectionService.getDbConnection();
        this.schema = DBConnectionService.getSchema();
    }

    @Override
    public void add(User user) {
        String query = String.format("insert into %s.users (first_name, last_name, email, role, team, password) values (?,?,?,?,?,?)", schema);
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getRole().getStringValue());
            statement.setString(5, user.getTeam().getStringValue());
            statement.setString(6, user.hashPassword(user.getPassword()));
            System.out.println(statement);
            statement.execute();
        } catch (SQLException | NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public User get(int id) {
        for (User user : getAllUsers()) {
            if (user != null)
                if (user.getUserid() == id)
                    return user;
        }
        return null;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        String sql = String.format("SELECT * from %s.users order by userid;", schema);
        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                users.add(resultSetToUser(result));
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return users;
    }

    @Override
    public void update(User user) {
        String sql = String.format("UPDATE %s.users set first_name=?, last_name=?, email=?, role=?, team=? where userid=?;", schema);
        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getRole().getStringValue());
            statement.setString(5, user.getTeam().getStringValue());
            statement.setInt(6, user.getUserid());
            statement.execute();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = String.format("DELETE from %s.users where userid=?;", schema);
        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void uniqueEditEmail(String email, int userid) {
        ArrayList<User> users = getAllUsers();
        for (User user : users) {
            if (user != null)
                if (!user.getEmail().equals(get(userid).getEmail()))
                    if (user.getEmail().equals(email)) throw new DbException("Email already used");
        }
    }

    @Override
    public void uniqueEmail(String email) {
        ArrayList<User> users = getAllUsers();
        for (User user : users) {
            if (user != null)
                if (user.getEmail().equals(email)) throw new DbException("Email already used");
        }
    }

    public User resultSetToUser(ResultSet result) throws SQLException {
        int id = result.getInt("userid");
        String firstName = result.getString("first_name").trim();
        String lastName = result.getString("last_name").trim();
        String password = result.getString("password").trim();
        String email = result.getString("email").trim();
        String role = result.getString("role").trim();
        String team = result.getString("team").trim();
        User res = new User(id, email, password, firstName, lastName, Team.valueOf(team.toUpperCase()), Role.valueOf(role.toUpperCase()));
        return res;
    }

    /**
     * Check the connection and reconnect when necessery
     *
     * @return the connection with the db, if there is one
     */
    private Connection getConnection() {
        return this.connection;
    }
}