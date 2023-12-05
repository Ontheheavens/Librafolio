package oth.librafolio.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import oth.librafolio.model.Message;

/**
 * @author Ontheheavens
 * @since 05.12.2023
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public Message sayHello() {
        return new Message("Hello, World!");
    }
}
