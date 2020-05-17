package wasteless.server.business.command_controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import wasteless.server.business.command_service.UserCommandService;
import wasteless.server.business.query_service.UserQueryService;
import wasteless.server.exception.ResourceNotFoundException;
import wasteless.server.model.User;
import wasteless.server.presentation.dto.UserDTO;
import wasteless.server.presentation.mapper.UserMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserCommandController {

    private final UserCommandService userService;
    private final UserQueryService userQueryService;
    private final UserMapper userMapper;

    public UserCommandController(UserCommandService userService, UserQueryService userQueryService, UserMapper userMapper) {
        this.userService = userService;
        this.userQueryService = userQueryService;
        this.userMapper = userMapper;
    }

    public ResponseEntity<UserDTO> loginUser(User loginUser) {

        Optional<User> matchingUser = userQueryService.loginUser(loginUser);

        return matchingUser.isPresent() ? ResponseEntity.ok(userMapper.convertToDTO(matchingUser.get())) :
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    public UserDTO createUser(User user) {
        return userMapper.convertToDTO(userService.createUser(user));
    }


    public ResponseEntity<UserDTO> updateUser(Long userId, User userDetails) throws ResourceNotFoundException {


        final User updatedUser = userService.updateUser(userId,userDetails);
        return ResponseEntity.ok(userMapper.convertToDTO(updatedUser));
    }

    public Map<String, Boolean> deleteUser(Long userId)
            throws ResourceNotFoundException {
        userService.deleteUser(userId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
