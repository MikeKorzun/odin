package com.setplex.odin.service.impl;

import com.setplex.odin.entity.User;
import com.setplex.odin.entity.dto.UserCreateRequest;
import com.setplex.odin.entity.dto.UserUpdateRequest;
import com.setplex.odin.repository.UserRepository;
import com.setplex.odin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(UserCreateRequest createRequest) {
        User user = createWithRequest(createRequest);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(UserUpdateRequest updateRequest) {
        User updatedUser = userRepository.findOneById(updateRequest.getId());
        updateWithRequest(updateRequest, updatedUser);
        return userRepository.save(updatedUser);
    }

    @Override
    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User getUserById(int userId) {
        return userRepository.findOneById(userId);
    }

    @Override
    public User getUserByLogin(String login) {
        return userRepository.findOneByLogin(login);
    }


    private static User createWithRequest(UserCreateRequest userRequest) {
        User user = new User();
        user.setLogin(userRequest.getLogin());
        user.setPassword(userRequest.getPassword());
        user.setFirstName(userRequest.getFirstname());
        user.setLastName(userRequest.getLastname());
        return user;
    }

    private static User updateWithRequest(UserUpdateRequest userRequest, User user) {
        user.setPassword(userRequest.getPassword());
        user.setFirstName(userRequest.getFirstname());
        user.setLastName(userRequest.getLastname());
        return user;
    }
}
