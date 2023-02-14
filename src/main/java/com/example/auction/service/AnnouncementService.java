package com.example.auction.service;

import com.example.auction.dto.AnnouncementDto;
import com.example.auction.model.AnnouncementEntity;

import java.util.List;

public interface AnnouncementService {

    List<AnnouncementDto> findAll();

    AnnouncementDto mapToAnnouncementDto(AnnouncementEntity entity);

    AnnouncementEntity mapToAnnouncementEntity(AnnouncementDto dto);

    boolean bet(Long id, Long price);

}
