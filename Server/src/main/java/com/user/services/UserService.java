package com.user.services;

import com.user.models.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserService {

    @GetMapping("/user")
    public User get() {
        return new User("Joe","joe@email.com");
    }



}
