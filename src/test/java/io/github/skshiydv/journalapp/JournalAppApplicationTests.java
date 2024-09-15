package io.github.skshiydv.journalapp;
import static org.junit.jupiter.api.Assertions.*;

import io.github.skshiydv.journalapp.entity.User;
import io.github.skshiydv.journalapp.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JournalAppApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {
    }



}
