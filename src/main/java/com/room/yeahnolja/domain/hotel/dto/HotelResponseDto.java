package com.room.yeahnolja.domain.hotel.dto;

import com.room.yeahnolja.domain.hotel.entity.Hotel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class HotelResponseDto {
    private int id;
    private String name;
    private String type;
    private String address;
    private String contact;
    private String email;
    private int star;
    private String description;
    private int rooms;

    private String regDt;
    private String modDt;

    public HotelResponseDto(Hotel hotel) {
        this.id = hotel.getId();
        this.name = hotel.getName();
        this.type = hotel.getType();
        this.address = hotel.getAddress();
        this.contact = hotel.getContact();
        this.email = hotel.getEmail();
        this.star = hotel.getStar();
        this.description = hotel.getDescription();
        this.rooms = hotel.getRooms();
        this.regDt = String.valueOf(hotel.getRegDt());
        this.modDt = String.valueOf(hotel.getModDt());
    }
}
