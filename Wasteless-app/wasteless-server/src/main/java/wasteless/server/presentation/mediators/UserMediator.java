package wasteless.server.presentation.mediators;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wasteless.server.exception.ResourceNotFoundException;
import wasteless.server.model.User;
import wasteless.server.business.command_handler.UserCommandHandler;
import wasteless.server.presentation.dto.UserDTO;
import wasteless.server.business.query_handler.UserQueryHandler;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
public class UserMediator {

    private final UserQueryHandler userQueryController;
    private final UserCommandHandler userCommandHandler;

    public UserMediator(UserQueryHandler userQueryController, UserCommandHandler userCommandHandler) {
        this.userQueryController = userQueryController;
        this.userCommandHandler = userCommandHandler;
    }

    @GetMapping("/users")
    public List<UserDTO> getAllUsers(){
        return userQueryController.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(value = "id") Long userId)
            throws ResourceNotFoundException{
        return userQueryController.getUserById(userId);
    }

    @GetMapping("/activeUser")
    public UserDTO getActiveUser(){
        return userQueryController.getActiveUser();
    }

    @PostMapping("user/login")
    public ResponseEntity<UserDTO> loginUser(@Valid @RequestBody User loginUser){
        return userCommandHandler.loginUser(loginUser);
    }

    @PostMapping("/users")
    public UserDTO createUser(@Valid @RequestBody User user){
        return userCommandHandler.createUser(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable(value = "id") Long userId,
                                              @Valid @RequestBody User userDetails) throws ResourceNotFoundException{
        return userCommandHandler.updateUser(userId, userDetails);
    }

    @DeleteMapping("/users/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId)
            throws ResourceNotFoundException{
        return userCommandHandler.deleteUser(userId);
    }
}
