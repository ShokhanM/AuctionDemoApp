package com.example.auction.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@NoArgsConstructor
public class AnnouncementDto {

    private Long id;

    private String title;

    private String description;

    private Long price;

    private Long currentPrice;

}
