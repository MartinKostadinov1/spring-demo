package com.databasedemo.demo.service.impl;

import com.databasedemo.demo.dto.UserDto;
import com.databasedemo.demo.entity.User;
import com.databasedemo.demo.repository.UserRepository;
import com.databasedemo.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createUser(UserDto user) {
        User newUser = this.modelMapper.map(user, User.class);

        this.userRepository.saveAndFlush(newUser);
    }

    @Override
    public void changeUserPassword(UserDto user) {
        User dbUser = this.userRepository.findByEmail(user.getEmail()).orElse(null);

        if(dbUser != null) {
            dbUser.setPassword(user.getPassword());
            this.userRepository.saveAndFlush(dbUser);
            return;
        }

        throw new IllegalArgumentException();
    }

    @Transactional
    @Override
    public boolean login(UserDto user) {
        User dbUser = this.userRepository.findByEmail(user.getEmail()).orElse(null);

        if(dbUser != null) {
            return dbUser.getPassword().equals(user.getPassword());
//            return user.getPassword().equals(dbUser.getPassword());
        }

       return false;
    }
}
