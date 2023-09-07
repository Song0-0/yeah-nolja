package com.room.yeahnolja.domain.hotel.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hotel")
@Getter
@Setter
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @Column(unique = true)
    private String name;

    @NonNull
    private String type;
    @NonNull
    private String address;
    @NonNull
    private String phone;
    @NonNull
    private int star;
    @NonNull
    private String description;
    @NonNull
    private int minPrice;
    @NonNull
    private int maxPrice;


    private String email;
    private String availabilityId;
    private int facilitiesId;
    private int rooms;
    private int imageId;

    @CreationTimestamp
    private LocalDateTime regDt;

    @UpdateTimestamp
    private LocalDateTime modDt;


    @Override
    public String toString() {
        return name + " " + type + " " + address + " " + phone + " " + star + " " + description + " " + minPrice + " " + maxPrice;
    }
}
