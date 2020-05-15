package wasteless.server.business.query_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wasteless.server.exception.ResourceNotFoundException;
import wasteless.server.model.User;
import wasteless.server.persistance.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryService {

    private final UserRepository userRepository;

    @Autowired
    public UserQueryService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        return user;
    }
    public User getActiveUser() {
        return userRepository.findFirstByActiveTrue();
    }

    public Optional<User> loginUser(User loginUser){
        List<User> allUsers = userRepository.findAll();

        Optional<User> matchingUser = allUsers
                .stream()
                .filter(user -> user.getEmailAddress().equals(loginUser.getEmailAddress()) &&
                        user.getPassword().equals(loginUser.getPassword()))
                .findFirst();
        if (matchingUser.isPresent()) {
            User user = matchingUser.get();
            user.setActive(true);
            userRepository.save(user);
            return Optional.of(user);
        }
        return Optional.empty();
    }
}
