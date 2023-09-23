package com.room.yeahnolja.domain.reservation.dto;

import com.room.yeahnolja.domain.reservation.entity.Room;
import lombok.Getter;

@Getter
public class RoomResponseDto {
    private int id;
    private int hotelId;
    private String hotelName;
    private String type;
    private int people;
    private int price;
    private String information;

    public RoomResponseDto(Room room) {
        this.id = room.getId();
        this.hotelId = room.getHotel().getId();
        this.hotelName = room.getHotel().getName();
        this.type = room.getType();
        this.people = room.getPeople();
        this.price = room.getPrice();
        this.information = room.getInformation();
    }

    public RoomResponseDto(int id, String hotelName, String type, int people, int price, String information) {
        this.id = id;
        this.hotelName = hotelName;
        this.type = type;
        this.people = people;
        this.price = price;
        this.information = information;
    }
}
