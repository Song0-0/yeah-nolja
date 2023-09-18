package com.room.yeahnolja.domain.hotel.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@Table(name = "hotel")
@Getter
@Setter
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String contact;
    @Column(nullable = false)
    private int star;
    @Column(nullable = false)
    private String description;
    private String email;
    private int rooms;
    @CreationTimestamp
    private LocalDateTime regDt;
    @UpdateTimestamp
    private LocalDateTime modDt;
    private String delYn;

    public Hotel() {
        this.delYn = "N";
    }
}
