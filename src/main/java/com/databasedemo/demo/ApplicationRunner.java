package com.databasedemo.demo;

import com.databasedemo.demo.entity.*;
import com.databasedemo.demo.entity.enums.*;
import com.databasedemo.demo.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.stereotype.*;

@Component
public class ApplicationRunner implements CommandLineRunner {

    private final UserRepository userRepository;

    @Autowired
    public ApplicationRunner(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setEmail("admincho@pesho-blog.com");
        user.setUsername("admincho");
        user.setPassword("admincho123");
        user.setRole(UserRole.USER);

        this.userRepository.saveAndFlush(user);

    }
}
