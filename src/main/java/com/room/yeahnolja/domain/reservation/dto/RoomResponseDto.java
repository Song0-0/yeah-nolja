package com.room.yeahnolja.domain.reservation.dto;

import com.room.yeahnolja.domain.reservation.entity.Room;
import lombok.Getter;

@Getter
public class RoomResponseDto {
    private int id;
    private int hotelId;
    private String hotelName;
    private String name;
    private String type;
    private int people;
    private int price;
    private String information;

    public RoomResponseDto(Room room) {
        this.id = room.getId();
        this.hotelId = room.getHotel().getId();
        this.hotelName = room.getHotel().getName();
        this.name = room.getName();
        this.type = room.getType();
        this.people = room.getPeople();
        this.price = room.getPrice();
        this.information = room.getInformation();
    }
}
