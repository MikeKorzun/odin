package com.setplex.odin.user;

import com.setplex.odin.controller.exception.InvalidDataException;
import com.setplex.odin.user.dto.UserDTO;
import com.setplex.odin.controller.response.ResponseEntityHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/odin")
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @GetMapping("/users")
    public List<UserDTO> getUsers() {
        return userFacade.getAllUsers();
    }

    @GetMapping("/users/{userId}")
    public UserDTO getUserById(@PathVariable int userId) {
        return userFacade.getUserById(userId);
    }

    @PostMapping("/users")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        userDTO = userFacade.createUser(userDTO);
        return ResponseEntityHelper.createResponseEntityWithBody(userDTO.getId(), userDTO);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable int userId, @RequestBody UserDTO userDTO) {
        if (userId != userDTO.getId()) {
            throw new InvalidDataException(String.format("Id for User %d in URL and in entity %d doesn't match.", userId, userDTO.getId()));
        }
        return ResponseEntityHelper.updateResponseEntityWithBody(userFacade.updateUser(userDTO));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
        userFacade.deleteUser(userId);
        return ResponseEntityHelper.deleteResponseEntity();
    }
}