package com.example.auction.service;

import com.example.auction.dto.JwtResponse;
import com.example.auction.dto.UserDto;
import com.sun.istack.NotNull;

public interface AuthService {

    void signup(@NotNull UserDto userDto) throws Exception;

    JwtResponse signin(@NotNull UserDto request);

}
