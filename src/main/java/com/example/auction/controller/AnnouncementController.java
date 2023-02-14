package com.example.auction.controller;


import com.example.auction.dto.AnnouncementDto;
import com.example.auction.model.AnnouncementEntity;
import com.example.auction.service.AnnouncementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/announcements")
public class AnnouncementController {

    private final AnnouncementService announcementService;


    @ApiOperation(value = "Show all announcements")
    @GetMapping("")
    public ResponseEntity<List<AnnouncementDto>> findAll(){
        return ResponseEntity.ok(announcementService.findAll());
    }

    @ApiOperation(value = "Bet on an announcement")
    @PostMapping("/bet")
    public ResponseEntity<HttpStatus> bet(@RequestParam("id") Long id, @RequestParam("price") Long price) {
        if (announcementService.bet(id, price)) {
            return ResponseEntity.ok(HttpStatus.ACCEPTED);
        } else {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
    }

}
