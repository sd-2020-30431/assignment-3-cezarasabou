package wasteless.server.business.query_handler;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import wasteless.server.business.query_service.UserQueryService;
import wasteless.server.exception.ResourceNotFoundException;
import wasteless.server.model.User;
import wasteless.server.presentation.dto.UserDTO;
import wasteless.server.presentation.mapper.UserMapper;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class UserQueryHandler {


    private final UserQueryService userService;
    private final UserMapper userMapper;

    public UserQueryHandler(UserQueryService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers()
                .stream()
                .map(userMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    public ResponseEntity<UserDTO> getUserById(Long userId)
            throws ResourceNotFoundException {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok().body(userMapper.convertToDTO(user));
    }

    public UserDTO getActiveUser() {
        User user = userService.getActiveUser();
        return userMapper.convertToDTO(user);
    }
}
