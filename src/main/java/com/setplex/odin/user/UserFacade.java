package com.setplex.odin.user;

import com.setplex.odin.entity.User;
import com.setplex.odin.user.dto.UserCreateRequest;
import com.setplex.odin.user.dto.UserDTO;
import com.setplex.odin.user.dto.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;

    public UserDTO createUser(UserDTO userDTO) {
        UserCreateRequest user = transformToUserCreateRequest(userDTO);
        return transform(userService.createUser(user));
    }

    public UserDTO updateUser(UserDTO userDTO) {
        return transform(userService.updateUser(transformToUserUpdateRequest(userDTO)));
    }

    public void deleteUser(int userId) {
        userService.deleteUser(userId);
    }

    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(UserFacade::transform)
                .collect(toList());
    }

    public UserDTO getUserById(int userId) {
        return transform(userService.getUserById(userId));
    }

    /*public UserDTO getUserByLogin(String login) {
        return transform(userService.getUserByLogin(login));
    }*/

    private static UserDTO transform(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .login(user.getLogin())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    private static UserCreateRequest transformToUserCreateRequest(UserDTO userDTO) {
        UserCreateRequest request = new UserCreateRequest();
        request.setFirstname(userDTO.getFirstName());
        request.setLastname(userDTO.getLastName());
        request.setPassword(userDTO.getPassword());
        request.setLogin(userDTO.getLogin());
        return request;
    }

    private static UserUpdateRequest transformToUserUpdateRequest(UserDTO userDTO) {
        UserUpdateRequest request = new UserUpdateRequest();
        request.setId(userDTO.getId());
        request.setFirstname(userDTO.getFirstName());
        request.setLastname(userDTO.getLastName());
        request.setPassword(userDTO.getPassword());
        return request;
    }
}