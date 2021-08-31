package com.example.hateoas.model.controller;

import com.example.hateoas.model.User;
import com.example.hateoas.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public CollectionModel<User> findAllUsers(){
        List<User> users = userService.findAllUsers();

        for (User user : users) {
            Link byIdLink = linkTo(methodOn(UserController.class).findUserById(user.getId())).withRel("findUserById").withType("GET");
            Link byNameLink = linkTo(methodOn(UserController.class).findUserByName(user.getName())).withRel("findUserByName").withType("GET");
            user.add(byIdLink, byNameLink);
        }

        Link link = linkTo(UserController.class).withSelfRel();
        CollectionModel<User> result = CollectionModel.of(users, link);
        return result;
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable int id){
        User user = userService.findUserById(id);
        return user;
    }

    @GetMapping("/name/{name}")
    public List<User> findUserByName(@PathVariable String name){
        return userService.findUserByName(name);
    }

}
