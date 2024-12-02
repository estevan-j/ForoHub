package com.ForoHub.ForoAPI.repositories;

import com.ForoHub.ForoAPI.domain.users.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Check the username was found in the DB")
    void findByUsernameFound() {
        String username = "tester";
        saveUserOnTestDB(username);
        UserDetails userDetails = userRepository.findByUsername(username);
        assertEquals(username, userDetails.getUsername());
    }

    @Test
    @DisplayName("Check the username was not found in the DB")
    void findByUsernameNotFound() {
        String username = "tester";
        saveUserOnTestDB(username);
        UserDetails userDetails = userRepository.findByUsername(username+"34");
        assertNull(userDetails);
    }

    private void saveUserOnTestDB(String username){
        User user = new User();
        user.setUsername(username);
        user.setPassword("password23");
        userRepository.save(user);
    }
}