package com.ForoHub.ForoAPI.controllers;

import com.ForoHub.ForoAPI.domain.users.LoginData;
import com.ForoHub.ForoAPI.domain.users.JWTtokenReponse;
import com.ForoHub.ForoAPI.domain.users.User;
import com.ForoHub.ForoAPI.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class authController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping()
    public ResponseEntity authUserLogin(@RequestBody @Valid LoginData login){
        //encapsula las credenciales para validacion
        Authentication authCredentias = new UsernamePasswordAuthenticationToken(login.username(), login.password());
        //ejecuta la validacion a AuthenticationProvider -> UserDetails para verificar en DB.
        var userAuthenticated = authenticationManager.authenticate(authCredentias);
        var jwToken = tokenService.generateToken((User) userAuthenticated.getPrincipal());
        return ResponseEntity.ok().body(new JWTtokenReponse(jwToken));
    }
}
