package com.example.demo.repositories;

import com.example.demo.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.jws.soap.SOAPBinding;

public interface UserRepos  extends MongoRepository<User, String> {
    public User findByUsername(String username);
}

