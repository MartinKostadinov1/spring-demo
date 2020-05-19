package com.databasedemo.demo.repository;

import com.databasedemo.demo.entity.*;
import com.databasedemo.demo.entity.enums.UserRole;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    //JPQL - for more fun - can be done with jpa method - don't worry :)
    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmail(String email);
}
