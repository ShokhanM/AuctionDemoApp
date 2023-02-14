package com.example.auction.controller;

import com.example.auction.dto.JwtResponse;
import com.example.auction.dto.UserDto;
import com.example.auction.service.AuthService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private final AuthService authService;

    @PostMapping("/signin")
    @ApiOperation(value = "User authorization")
    public ResponseEntity<JwtResponse> authUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(authService.signin(userDto));

    }

    @PostMapping("/signup")
    @ApiOperation(value = "User registration")
    public ResponseEntity<HttpStatus> registerUser(@RequestBody UserDto userDto) {
        try {
            authService.signup(userDto);
            return ResponseEntity.ok(HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
    }

}