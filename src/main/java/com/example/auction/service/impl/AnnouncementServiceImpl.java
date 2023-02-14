package com.example.auction.service.impl;

import com.example.auction.dto.AnnouncementDto;
import com.example.auction.model.AnnouncementEntity;
import com.example.auction.model.UserEntity;
import com.example.auction.repository.AnnouncementRepository;
import com.example.auction.repository.UserRepository;
import com.example.auction.service.AnnouncementService;
import com.example.auction.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final UserRepository userRepository;

    @Override
    public List<AnnouncementDto> findAll() {
        return announcementRepository.findAll().stream().map(this::mapToAnnouncementDto).collect(Collectors.toList());
    }


    //TODO implement MapStruct
    @Override
    public AnnouncementDto mapToAnnouncementDto(AnnouncementEntity entity) {
        AnnouncementDto dto = new AnnouncementDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setCurrentPrice(entity.getCurrentPrice());
        dto.setPrice(entity.getPrice());
        return dto;
    }

    //TODO implement MapStruct
    @Override
    public AnnouncementEntity mapToAnnouncementEntity(AnnouncementDto dto) {
        AnnouncementEntity entity = new AnnouncementEntity();
        entity.setId(dto.getId());
        entity.setDescription(dto.getDescription());
        entity.setTitle(dto.getTitle());
        entity.setPrice(dto.getPrice());
        entity.setCurrentPrice(dto.getCurrentPrice());
        return entity;
    }

    @Override
    @Transactional
    public boolean bet(Long id, Long price) {
        AnnouncementEntity announcementEntity = announcementRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("announcement not found")
        );
        UserEntity currentCandidate = userRepository.findById(getCurrentUser().getId())
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
        if (announcementEntity.getCurrentPrice() > price || Objects.equals(announcementEntity.getCurrentPrice(), price)) {
            return false;
        }
        if (timeExpired(announcementEntity)) {
            log.info("announcement " + announcementEntity.getTitle() + " time expired");
            log.info("Announcement with title: " + announcementEntity.getTitle()
                    + " has been bought by: " + currentCandidate.getEmail());
            System.out.println("Announcement has been sold " + announcementEntity.getOwner().getEmail());
            announcementEntity.setOwner(currentCandidate);
            announcementEntity.setStatus(false);
            System.out.println("Announcements is your " + currentCandidate.getEmail());
            return false;
        } else {
            log.info("price increased " + price + " in announcement" + announcementEntity.getTitle());
            System.out.println(currentCandidate.getEmail() + "Вашу цену сбили"); // SEND TO EMAIL
            announcementEntity.setCurrentPrice(price);
            announcementEntity.setCandidate(getCurrentUser().getId());
            return true;
        }
    }

    private UserEntity getCurrentUser(){
        UserDetailsImpl userDetails = SecurityUtils.getAuthUser();
        return userRepository.findByEmail(userDetails.getEmail()).orElseThrow(
                ()-> new EntityNotFoundException("User not found")
        );
    }

    public boolean timeExpired(AnnouncementEntity announcementEntity) {

        return announcementEntity.getCountdown().getTime() + Calendar.HOUR >
                LocalDateTime.now().getHour();

    }

}
