package com.example.demo.services;

import com.example.demo.dto.UserDto;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepos userRepos;

    public List<UserDto> getAllUsers() {
        List<UserDto> userDtoList = new ArrayList<UserDto>();
        List<User> usersList = userRepos.findAll();
        for (int i = 0; i < usersList.size(); i++) {
            userDtoList.add(convertUserToUserDto(usersList.get(i)));
        }
        return userDtoList;
    }

    public UserDto getUserById(String id) {
        return convertUserToUserDto(Objects.requireNonNull(userRepos.findById(id).orElse(null)));

    }

    public UserDto findByUsername(String username) {
        return convertUserToUserDto(userRepos.findByUsername(username));
    }

    public UserDto postUser(User user) {
        userRepos.save(user);
        return convertUserToUserDto(user);
    }

    public UserDto putUser(String id, User user) {
        user.setId(id);
        return convertUserToUserDto(userRepos.save(user));
    }

    public void deleteUser(String id) {
        userRepos.delete(userRepos.findById(id).get());
    }

    private UserDto convertUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setRole(user.getRole());
        userDto.setSurveysSubmited(user.getSuveysSubmited());
        return userDto;

    }
}