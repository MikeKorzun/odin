package com.setplex.odin.service;


import com.setplex.odin.entity.User;
import com.setplex.odin.entity.dto.UserCreateRequest;
import com.setplex.odin.entity.dto.UserUpdateRequest;

import java.util.List;

public interface UserService {

    User createUser(UserCreateRequest createRequest);
    User updateUser(UserUpdateRequest updateRequest);
    void deleteUser(int userId);
    List<User> getAllUsers();
    User getUserById(int id);
    User getUserByLogin(String login);

}