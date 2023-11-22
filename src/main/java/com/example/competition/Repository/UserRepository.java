package com.example.competition.Repository;

import com.example.competition.Entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UserRepository extends CrudRepository <User, Integer>{

    Optional<User> findByLogin(String username);
}
