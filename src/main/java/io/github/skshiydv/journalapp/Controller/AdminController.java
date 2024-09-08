package io.github.skshiydv.journalapp.Controller;

import io.github.skshiydv.journalapp.entity.User;
import io.github.skshiydv.journalapp.services.userService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final userService service;

    public AdminController(userService service) {
        this.service = service;
    }
    @GetMapping("/all-users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> all= service.findAll();
        if(all!=null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping("/add-new-admin")
    public ResponseEntity<String> addNewAdmin(@RequestBody User user) {
        if(user!=null) {
            service.saveAdmin(user);
            return new ResponseEntity<>("successfully added admin", HttpStatus.OK);
        }
        return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
    }
}
