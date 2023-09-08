package com.room.yeahnolja.domain.hotel.dto;

import com.room.yeahnolja.domain.hotel.entity.Hotel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelResponseDto {
    private int id;
    private String name;
    private String type;
    private String address;
    private String contact;
    private String email;
    private int star;
    private String description;
    private int minPrice;
    private int maxPrice;
    private int rooms;

    private String regDt;
    private String modDt;

    public HotelResponseDto(Hotel hotelDto) {
        this.id = hotelDto.getId();
        this.name = hotelDto.getName();
        this.type = hotelDto.getType();
        this.address = hotelDto.getAddress();
        this.contact = hotelDto.getContact();
        this.email = hotelDto.getEmail();
        this.star = hotelDto.getStar();
        this.description = hotelDto.getDescription();
        this.minPrice = hotelDto.getMinPrice();
        this.maxPrice = hotelDto.getMaxPrice();
        this.rooms = hotelDto.getRooms();
        this.regDt = String.valueOf(hotelDto.getRegDt());
        this.modDt = String.valueOf(hotelDto.getModDt());
    }
}
