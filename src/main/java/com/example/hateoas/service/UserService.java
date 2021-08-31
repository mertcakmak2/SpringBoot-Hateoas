package com.example.hateoas.service;

import com.example.hateoas.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    List<User> users = new ArrayList<>();

    @PostConstruct
    void addUser(){
        User user1 = User.builder().id(1).name("mert").build();
        User user2 = User.builder().id(2).name("mert2").build();
        User user3 = User.builder().id(3).name("mert3").build();
        User user4 = User.builder().id(4).name("mert4").build();

        users.addAll(Arrays.asList(user1,user2,user3,user4));
    }

    public List<User> findAllUsers(){
        return users;
    }

    public User findUserById(int id){
        return users.stream().filter(u -> u.getId() == id).collect(Collectors.toList()).get(0);
    }

    public List<User> findUserByName(String name){
        List<User> filteredUsers =  users.stream().filter(u -> u.getName().equals(name) ).collect(Collectors.toList());
        return filteredUsers;
    }
}
