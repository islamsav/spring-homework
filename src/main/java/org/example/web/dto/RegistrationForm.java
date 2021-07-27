package org.example.web.dto;

public class RegistrationForm {

    private String username;
    private String password;
    private Integer id;

    public RegistrationForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public RegistrationForm() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RegistrationForm{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
