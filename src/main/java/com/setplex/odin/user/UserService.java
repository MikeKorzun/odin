package com.setplex.odin.user;

import com.setplex.odin.entity.User;
import com.setplex.odin.user.dto.UserCreateRequest;
import com.setplex.odin.user.dto.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User getUserById(int userId) {
        return userRepository.findOneById(userId);
    }

    public User getUserByLogin(String login) {
        return userRepository.findOneByLogin(login);
    }

    public User createUser(UserCreateRequest createRequest) {
        User user = createWithRequest(createRequest);
        return userRepository.save(user);
    }

    private static User createWithRequest(UserCreateRequest userRequest) {
        User user = new User();
        user.setLogin(userRequest.getLogin());
        user.setPassword(userRequest.getPassword());
        user.setFirstName(userRequest.getFirstname());
        user.setLastName(userRequest.getLastname());
        return user;
    }

    @Transactional
    public User updateUser(UserUpdateRequest updateRequest) {
        User updatedUser = userRepository.findOneById(updateRequest.getId());
        updateWithRequest(updateRequest, updatedUser);
        return userRepository.save(updatedUser);
    }

    private static User updateWithRequest(UserUpdateRequest userRequest, User user) {
        user.setPassword(userRequest.getPassword());
        user.setFirstName(userRequest.getFirstname());
        user.setLastName(userRequest.getLastname());
        return user;
    }

    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }

}