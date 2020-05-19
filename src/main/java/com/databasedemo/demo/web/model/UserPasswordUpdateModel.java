package com.databasedemo.demo.web.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

public class UserPasswordUpdateModel {

    private String email;
    private String password;

    public UserPasswordUpdateModel() {
    }

    @Pattern(regexp = "^(.+)@(.+)$")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Length(min = 6, max = 60)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
