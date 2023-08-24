package com.room.yeahnolja.domain.hotel.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelRequestDto {
    private String name;
    private String type;
    private String address;
    private String phone;
    private String email;
    private int star;
    private String description;
    private int minPrice;
    private int maxPrice;
    private String availabilityId;
    private int facilitiesId;
    private int rooms;
    private int imageId;

    private int id;
}
