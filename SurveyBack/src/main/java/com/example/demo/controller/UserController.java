package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.models.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/users")
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("byUsername/{username}")
    public UserDto getUSerByUsername(@PathVariable String username) {
        return userService.findByUsername(username);

    }

    @GetMapping("byId/{id}")
    public UserDto getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @PostMapping()
    public UserDto addUser(@RequestBody User user) {
        return userService.postUser(user);

    }

    @PutMapping("/{id}")
    public UserDto editUser(@PathVariable String id, @RequestBody User user) {
        return userService.putUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }
}