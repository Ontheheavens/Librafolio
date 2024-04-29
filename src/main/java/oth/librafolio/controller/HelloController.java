package oth.librafolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import oth.librafolio.model.Message;
import oth.librafolio.repository.MessageRepository;

/**
 * @author Ontheheavens
 * @since 05.12.2023
 */
@RestController
@RequestMapping("/api")
public class HelloController {

    private final MessageRepository messageRepository;

    @Autowired
    public HelloController(MessageRepository repository) {
        this.messageRepository = repository;
    }

    @ResponseBody
    @GetMapping("/hello")
    public Message sayHello() {
        return messageRepository.findRandomMessage();
    }

}
