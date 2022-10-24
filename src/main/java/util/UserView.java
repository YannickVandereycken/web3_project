package util;

import util.Secret;

import java.sql.*;
import java.util.Properties;

public class UserView {
    public static void main(String[] args) {
        // constants for your project
        String url = "jdbc:postgresql://databanken.ucll.be:62223/2TX36";
        // replace 'web3' by your own schema name, e.g. groep102
        String schema = "groep2_13";

        // set properties for db connection
        Properties properties = new Properties();

        try {
            Class.forName("util.Secret");
            Secret.setPass(properties);
        } catch (ClassNotFoundException e) {
            System.out.println("Class Secret with credentials not found");
        }

        properties.setProperty("ssl", "true");
        properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
        properties.setProperty("sslmode", "require");

        //open the db connection
        try (Connection connection = DriverManager.getConnection(url, properties)) {
            // add a user
            String query = String.format("insert into %s.users (userid, first_name, last_name, email, role, team, password) values (?,?,?,?,?,?,?)", schema);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, 5);
            preparedStatement.setString(2, "mama");
            preparedStatement.setString(3, "se");
            preparedStatement.setString(4, "m@m.be");
            preparedStatement.setString(5, "employee");
            preparedStatement.setString(6, "ALPHA");
            preparedStatement.setString(7, "m");
            preparedStatement.execute();

            //get all users
            query = String.format("SELECT * from %s.users order by userid;", schema);
            PreparedStatement statementInsert = connection.prepareStatement(query);
            ResultSet resultSet = statementInsert.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("userid");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String role = resultSet.getString("role");
                String team = resultSet.getString("team");
//                User user = new User(id, email, password, firstName,lastName, Team.valueOf(team));
//                System.out.println(user.toString());
                System.out.println(firstName);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Connection no succes");
        }
    }
}
