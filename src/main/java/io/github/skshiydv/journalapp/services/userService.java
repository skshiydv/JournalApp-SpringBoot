package io.github.skshiydv.journalapp.services;

import io.github.skshiydv.journalapp.entity.User;
import io.github.skshiydv.journalapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class userService {
    private final UserRepository userRepository;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public void saveNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);


    } public void save(User user) {
        userRepository.save(user);

    }
    public List<User> findAll() {
        return userRepository.findAll();
    }
    public void delete(ObjectId id) {
        userRepository.deleteById(id);
    }
    public User findById(ObjectId id) {
        if(userRepository.findById(id).isPresent()) {
            return userRepository.findById(id).get();
        }
        return null;
    }
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void deleteByUsername(String username) {
        userRepository.deleteByUsername(username);
    }
}
