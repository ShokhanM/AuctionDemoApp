package com.example.auction.service.impl;

import com.example.auction.dto.AnnouncementDto;
import com.example.auction.dto.UserDto;
import com.example.auction.model.AnnouncementEntity;
import com.example.auction.model.UserEntity;
import com.example.auction.repository.AnnouncementRepository;
import com.example.auction.repository.UserRepository;
import com.example.auction.service.AnnouncementService;
import com.example.auction.service.UserService;
import com.example.auction.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AnnouncementRepository announcementRepository;
    private final AnnouncementService announcementService;

    //TODO implement MapStruct
    @Override
    public UserEntity mapToUserEntity(UserDto dto) {
        UserEntity entity = new UserEntity();
        entity.setEmail(dto.getEmail());
        return entity;
    }

    //TODO implement MapStruct
    @Override
    public UserDto mapToUserDto(UserEntity entity) {
        UserDto dto = new UserDto();
        dto.setEmail(entity.getEmail());
        List<AnnouncementDto> announcements  = entity.getAnnouncements().stream()
                .map(announcementService::mapToAnnouncementDto).toList();
        dto.setAnnouncements(announcements);
        return dto;
    }


    @Override
    public void createAnnouncement(AnnouncementDto announcementDto) {
        AnnouncementEntity announcement = announcementService.mapToAnnouncementEntity(announcementDto);
        announcement.setOwner(getCurrentUser());
        announcement.setCurrentPrice(announcementDto.getPrice());
        announcementRepository.save(announcement);
    }

    private UserEntity getCurrentUser(){
        UserDetailsImpl userDetails = SecurityUtils.getAuthUser();
        return userRepository.findByEmail(userDetails.getEmail()).orElseThrow(
                ()-> new EntityNotFoundException("User not found")
        );
    }



}
