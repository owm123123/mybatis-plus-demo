package com.example.mybatisplusdemo.user.controller;

import com.example.mybatisplusdemo.user.repository.UserRepository;
import com.example.mybatisplusdemo.user.repository.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public boolean createUser(@RequestBody UserEntity userEntity) {
        return userRepository.insert(userEntity) > 0;
    }

    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable("id") Integer id) {
        return userRepository.selectById(id);
    }

    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userRepository.selectList(null);
    }

    @PutMapping
    public boolean updateUser(@RequestBody UserEntity userEntity) {
        return userRepository.updateById(userEntity) > 0;
    }

    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable("id") String id) {
        return userRepository.deleteById(id) > 0;
    }
}
