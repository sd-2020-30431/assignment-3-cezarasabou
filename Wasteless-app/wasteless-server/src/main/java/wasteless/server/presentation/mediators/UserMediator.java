package wasteless.server.presentation.mediators;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wasteless.server.business.command_service.UserCommandService;
import wasteless.server.exception.ResourceNotFoundException;
import wasteless.server.model.User;
import wasteless.server.presentation.command_controller.UserCommandController;
import wasteless.server.presentation.dto.UserDTO;
import wasteless.server.presentation.query_controller.UserQueryController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

public class UserMediator {

    private final UserQueryController userQueryController;
    private final UserCommandController userCommandController;

    public UserMediator(UserQueryController userQueryController, UserCommandController userCommandController) {
        this.userQueryController = userQueryController;
        this.userCommandController = userCommandController;
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

    @GetMapping("activeUser")
    public UserDTO getActiveUser(){
        return userQueryController.getActiveUser();
    }

    @PostMapping("user/login")
    public ResponseEntity<UserDTO> loginUser(@Valid @RequestBody User loginUser){
        return userCommandController.loginUser(loginUser);
    }

    @PostMapping("/users")
    public UserDTO createUser(@Valid @RequestBody User user){
        return userCommandController.createUser(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable(value = "id") Long userId,
                                              @Valid @RequestBody User userDetails) throws ResourceNotFoundException{
        return userCommandController.updateUser(userId, userDetails);
    }

    @DeleteMapping("/users/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId)
            throws ResourceNotFoundException{
        return userCommandController.deleteUser(userId);
    }
}
