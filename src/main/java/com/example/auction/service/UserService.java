package com.example.auction.service;

import com.example.auction.dto.AnnouncementDto;
import com.example.auction.dto.UserDto;
import com.example.auction.model.UserEntity;

public interface UserService {

    UserEntity mapToUserEntity(UserDto dto);

    UserDto mapToUserDto(UserEntity entity);

    void createAnnouncement(AnnouncementDto announcementDto);

}
