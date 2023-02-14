package com.example.auction.controller;

import com.example.auction.dto.AnnouncementDto;
import com.example.auction.dto.UserDto;
import com.example.auction.service.AnnouncementService;
import com.example.auction.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/create-announcement")
    @ApiOperation(value = "Create announcement")
    public ResponseEntity<HttpStatus> createAnnouncement(@RequestBody AnnouncementDto dto){
        userService.createAnnouncement(dto);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }




}
