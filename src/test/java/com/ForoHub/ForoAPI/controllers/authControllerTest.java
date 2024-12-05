package com.ForoHub.ForoAPI.controllers;

import com.ForoHub.ForoAPI.domain.users.LoginData;
import com.ForoHub.ForoAPI.services.TokenService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class authControllerTest {

    @MockBean
    private TokenService tokenService;

    @Autowired
    private JacksonTester<LoginData> json;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Return 200 and jwtToken when the user was login")
    void authUserLogin() throws Exception {
        LoginData login = new LoginData("adminForo", "foro123");
        var response = mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.write(login).getJson()))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
    }


    @Test
    @DisplayName("Return 403 and jwtToken when the user was not login")
    void authUserLoginNotFound() throws Exception {
        LoginData login = new LoginData("adminForo", "1234");
        var response = mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.write(login).getJson()))
                .andExpect(status().isForbidden())
                .andReturn()
                .getResponse();
    }
}