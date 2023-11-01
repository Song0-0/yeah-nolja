package com.room.yeahnolja.domain.reservation.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class RoomResponseDto {
    private int hotelId;
    private String hotelName;
    private int id;
    private String type;
    private int people;
    private int price;
    private String information;
    private List<String> reservationNotices;

    private LocalDate checkin;
    private LocalDate checkout;
    private int payment;

    public RoomResponseDto(int hotelId, String hotelName, int id, String type, int people, int price, String information) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.id = id;
        this.type = type;
        this.people = people;
        this.price = price;
        this.information = information;
    }

    public RoomResponseDto(String type, int people, int price, String information, List<String> reservationNotices) {
        this.type = type;
        this.people = people;
        this.price = price;
        this.information = information;
        this.reservationNotices = reservationNotices;
    }

    public RoomResponseDto(String hotelName, String type, LocalDate checkin, LocalDate checkout, int payment) {
        this.hotelName = hotelName;
        this.type = type;
        this.checkin = checkin;
        this.checkout = checkout;
        this.payment = payment;
    }
}
