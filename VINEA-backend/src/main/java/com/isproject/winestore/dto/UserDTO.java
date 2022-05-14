package com.isproject.winestore.dto;

import java.time.LocalDateTime;

public class UserDTO {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String token;
    private LocalDateTime createdAccount;
    private boolean isAdmin;

    public UserDTO(Long id, String name, String surname, String email, String password, String token,
                   LocalDateTime createdAccount, boolean isAdmin) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.token = token;
        this.createdAccount = createdAccount;
        this.isAdmin = isAdmin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getCreatedAccount() {
        return createdAccount;
    }

    public void setCreatedAccount(LocalDateTime createdAccount) {
        this.createdAccount = createdAccount;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
