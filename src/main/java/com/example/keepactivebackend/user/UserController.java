package com.example.keepactivebackend.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final UserService userService;

//    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//     public List<User> getUsers(){
//        return userService.getUsers();
//     }

    @PostMapping(path="signup")
    public User signUpUser (@RequestBody User user){
        return userService.signUpUser(user);
    }

}
