package oth.librafolio.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import oth.librafolio.dto.roles.UserDTO;
import oth.librafolio.model.roles.Role;
import oth.librafolio.model.roles.User;
import oth.librafolio.service.UserService;

import java.util.logging.Logger;

/**
 * @author Ontheheavens
 * @since 09.05.2024
 */
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private AutoCloseable openedMocks;

    @BeforeEach
    void setup() {
        openedMocks = MockitoAnnotations.openMocks(this);

    }

    @AfterEach
    void cleanup() throws Exception {
        openedMocks.close();
    }

    @Test
    void testGetUser() {
        // Mock data
        String username = "testUser";
        String testPassword = "testPassword";
        User user = new User(username, testPassword, Role.USER);
        UserDTO userDTO = new UserDTO(username, testPassword);

        // Mock the behavior of the userService
        Mockito.when(userService.findUser(username)).thenReturn(user);

        // Call the method to test
        ResponseEntity<UserDTO> response = userController.getUser(username);

        // Verify the result
        Assertions.assertEquals(ResponseEntity.ok(userDTO), response,
                "Testing getUser() failed.");

        Logger.getLogger("UserControllerTest").info("Test getUser() passed.");
    }

}
