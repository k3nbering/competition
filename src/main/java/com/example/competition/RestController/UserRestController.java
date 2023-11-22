package com.example.competition.RestController;

import com.example.competition.Entity.User;
import com.example.competition.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    UserRepository userRepository;
    @RequestMapping("/index")
    public Iterable<User> index(){
        Iterable<User> users = userRepository.findAll();
        return users;
    }
}
