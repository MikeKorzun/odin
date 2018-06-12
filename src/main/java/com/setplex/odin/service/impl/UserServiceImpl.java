package com.setplex.odin.service.impl;

import com.setplex.odin.entity.User;
import com.setplex.odin.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Override
    public void addUser(User user) {

    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User gerUserById(int id) {
        return null;
    }

    @Override
    public User getUserByLastName(String lastName) {
        return null;
    }
}
