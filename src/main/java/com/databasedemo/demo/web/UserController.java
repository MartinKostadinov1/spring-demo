package com.databasedemo.demo.web;

import com.databasedemo.demo.dto.UserDto;
import com.databasedemo.demo.service.UserService;
import com.databasedemo.demo.web.model.UserCreationModel;
import com.databasedemo.demo.web.model.UserLoginModel;
import com.databasedemo.demo.web.model.UserPasswordUpdateModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PutMapping("/change-password")
    public ResponseEntity changeUserPassword(@Valid @RequestBody UserPasswordUpdateModel userPasswordUpdateModel) {
        UserDto userDto = this.modelMapper.map(userPasswordUpdateModel, UserDto.class);
        this.userService.changeUserPassword(userDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLoginModel userLoginModel) {
        UserDto userDto = this.modelMapper.map(userLoginModel, UserDto.class);
        return this.userService.login(userDto) ? ResponseEntity.ok("{\"token\": \"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c\"}") : new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping
    public void createUser(@Valid @RequestBody UserCreationModel userCreationModel) {
        UserDto userDto = this.modelMapper.map(userCreationModel, UserDto.class);
        this.userService.createUser(userDto);
    }
}
