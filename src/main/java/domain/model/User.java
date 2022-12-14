package domain.model;

import domain.service.DbException;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private int userid;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Team team;
    private Role role;

    public User(String email, String password, String firstName, String lastName, Team team) {
        setEmail(email);
        try {
            setPassword(password);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new DbException(e.getMessage());
        }
        setFirstName(firstName);
        setLastName(lastName);
        setTeam(team);
        setRole(Role.EMPLOYEE);
    }

    public User(int userid, String email, String password, String firstName, String lastName, Team team) {
        this(email, password, firstName, lastName, team);
        this.setUserid(userid);
    }

    public User(int userid, String email, String password, String firstName, String lastName, Team team, Role role) {
        this(email, password, firstName, lastName, team);
        setRole(role);
        this.setUserid(userid);
    }

    public User() {
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setEmail(String email) {
        if (email.isEmpty()) {
            throw new IllegalArgumentException("No email given");
        }
        String USERID_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern p = Pattern.compile(USERID_PATTERN);
        Matcher m = p.matcher(email);
        if (!m.matches()) {
            throw new IllegalArgumentException("Email not valid");
        }
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isCorrectPassword(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return getPassword().equals(hashPassword(password));
    }

    public void setPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (password.isEmpty())
            throw new IllegalArgumentException("No password given");
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName.isEmpty()) {
            throw new IllegalArgumentException("No firstname given");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName.isEmpty()) {
            throw new IllegalArgumentException("No last name given");
        }
        this.lastName = lastName;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setTeam(String team) {
        try {
            this.team = Team.valueOf(team.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DomainException("There is no team with value " + team);
        }
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + ": " + getUserid() + ", " + getEmail() + ", " + getRole() + ", " + getTeam();
    }

    public String hashPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //create MessageDigest
        MessageDigest crypt = MessageDigest.getInstance("SHA-512");
        //reset
        crypt.reset();
        byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
        crypt.update(passwordBytes);
        //digest
        byte[] digest = crypt.digest();
        //convert to String
        BigInteger digestAsBigInteger = new BigInteger(1, digest);
        //return hashed password
        return digestAsBigInteger.toString(16);
    }
}