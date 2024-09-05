package io.github.skshiydv.journalapp.Controller;

import io.github.skshiydv.journalapp.entity.User;
import io.github.skshiydv.journalapp.services.userService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
public class publicController {
    private final userService service;

    public publicController(userService service) {
        this.service = service;
    }
    @PostMapping
    public String addUser(@RequestBody User user) {
        service.saveNewUser(user);
        return "success";
    }
}
