package oth.librafolio.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import oth.librafolio.model.Message;

import java.security.SecureRandom;
import java.util.random.RandomGenerator;

/**
 * @author Ontheheavens
 * @since 05.12.2023
 */
@RestController
@RequestMapping("/api")
public class HelloController {

    @ResponseBody
    @GetMapping("/hello")
    public Message sayHello() {
        RandomGenerator random = new SecureRandom();
        int number = random.nextInt(10);
        return new Message("Hello, World! Random number: " + number);
    }

}
