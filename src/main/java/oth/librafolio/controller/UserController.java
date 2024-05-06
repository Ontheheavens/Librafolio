package oth.librafolio.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import oth.librafolio.dto.LoginDTO;
import oth.librafolio.dto.UserDTO;
import oth.librafolio.model.User;
import oth.librafolio.service.UserService;

/**
 * @author Ontheheavens
 * @since 03.05.2024
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService inputUserService) {
        this.userService = inputUserService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String username) {
        User user = userService.findUser(username);
        if (user == null) {
            ResponseEntity.HeadersBuilder<?> headersBuilder = ResponseEntity.notFound();
            return headersBuilder.build();
        } else {
            UserDTO userDTO = new UserDTO(user.getUsername(), user.getPassword());
            return ResponseEntity.ok(userDTO);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
        try {
            userService.registerUser(userDTO);
        } catch (Exception e) {
            ResponseEntity.BodyBuilder status = ResponseEntity.status(HttpStatus.BAD_REQUEST);
            return status.body(e.getMessage());
        }
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDTO) {
        if (userService.checkLoginUser(loginDTO)) {
            return ResponseEntity.ok("Login successful");
        } else {
            ResponseEntity.BodyBuilder status = ResponseEntity.status(HttpStatus.UNAUTHORIZED);
            return status.body("Invalid username or password");
        }
    }
}
