package com.example.auction.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Table(name = "announcement")
@Entity
public class AnnouncementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "status")
    private boolean status = true;

    @Column(name = "cowntdown")
    private Date countdown;

    @Column(name = "price")
    private Long price;

    @Column(name = "current_price")
    private Long currentPrice;

    @Column(name = "candidate_id")
    private Long candidate;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserEntity owner;


}
