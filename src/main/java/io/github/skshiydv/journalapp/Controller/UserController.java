package io.github.skshiydv.journalapp.Controller;

import io.github.skshiydv.journalapp.entity.User;

import io.github.skshiydv.journalapp.services.userService;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final userService service;
    private final io.github.skshiydv.journalapp.services.quotesService quotesService;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        if (!authentication.isAuthenticated()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        User user1 = service.findByUsername(username);
            user1.setUsername(user.getUsername());
            user1.setPassword(user.getPassword());
            service.saveNewUser(user1);
            return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping
    public String deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        service.deleteByUsername(username);
        return "delete success";
    }
    @GetMapping
    public String getUsers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return "Welcome " + username + "\n" + quotesService.getQuote("happiness").getQuote();
    }
}
