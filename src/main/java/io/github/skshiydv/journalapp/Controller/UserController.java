package io.github.skshiydv.journalapp.Controller;

import io.github.skshiydv.journalapp.entity.User;

import io.github.skshiydv.journalapp.services.EmailService;
import io.github.skshiydv.journalapp.services.userService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final userService service;
    private final io.github.skshiydv.journalapp.services.quotesService quotesService;
    private final EmailService emailService;

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
    public String getUsers()  {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = service.findByUsername(username);
        String email = user.getEmail();
        emailService.sendEmail(email,"Hi its me","HAHAHAH");


        return "Welcome " + username + "\n" + quotesService.getQuote("happiness").getQuote();
    }
}
