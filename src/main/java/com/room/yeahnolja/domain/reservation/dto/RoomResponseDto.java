package com.room.yeahnolja.domain.reservation.dto;

import com.room.yeahnolja.domain.reservation.entity.Room;
import lombok.Getter;

@Getter
public class RoomResponseDto {
    private int hotelId;
    private String hotelName;
    private int id;
    private String type;
    private int people;
    private int price;
    private String information;

    public RoomResponseDto(Room room) {
        this.hotelId = room.getHotel().getId();
        this.hotelName = room.getHotel().getName();
        this.id = room.getId();
        this.type = room.getType();
        this.people = room.getPeople();
        this.price = room.getPrice();
        this.information = room.getInformation();
    }

    public RoomResponseDto(int hotelId, String hotelName, int id, String type, int people, int price, String information) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.id = id;
        this.type = type;
        this.people = people;
        this.price = price;
        this.information = information;
    }
}
