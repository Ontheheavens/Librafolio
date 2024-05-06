package oth.librafolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oth.librafolio.dto.LoginDTO;
import oth.librafolio.dto.UserDTO;
import oth.librafolio.model.Role;
import oth.librafolio.model.User;
import oth.librafolio.repository.UserRepository;

/**
 * @author Ontheheavens
 * @since 03.05.2024
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }

    public User findUser(String username) {
        return userRepository.findByUsername(username);
    }

    public void registerUser(UserDTO userDTO) {
        User user = new User(userDTO.username(),
                userDTO.password(), Role.USER);
        userRepository.save(user);
    }

    public boolean checkLoginUser(LoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.username());
        if (user != null) {
            String password = user.getPassword();
            return password.equals(loginDTO.password());
        }
        return false;
    }

}
