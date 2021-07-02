package iPublisher;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class PublicUser {

    //User Properties
    private StringProperty name;
    private StringProperty password;
    private StringProperty username;

    //Constructor
    public PublicUser(StringProperty name, StringProperty password, StringProperty username, IntegerProperty userId) {
        this.name = name;
        this.password = password;
        this.username = username;
        this.userId = userId;
    }

    //Getters and Setters
    private IntegerProperty userId;

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public int getUserId() {
        return userId.get();
    }

    public IntegerProperty userIdProperty() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId.set(userId);
    }

}
