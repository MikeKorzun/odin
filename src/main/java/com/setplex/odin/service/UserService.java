package com.setplex.odin.service;


import com.setplex.odin.entity.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    void deleteUser(User user);
    List<User> getAllUsers();
    User gerUserById(int id);
    User getUserByLastName(String lastName);
}
