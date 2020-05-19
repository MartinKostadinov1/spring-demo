package com.databasedemo.demo.service;

import com.databasedemo.demo.dto.UserDto;

public interface UserService {

    void createUser(UserDto user);

    void changeUserPassword(UserDto user);

    boolean login(UserDto user);
}
