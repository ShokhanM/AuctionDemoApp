package com.example.auction.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class UserDto{

    private String email;

    private String password;

    private List<AnnouncementDto> announcements;
}