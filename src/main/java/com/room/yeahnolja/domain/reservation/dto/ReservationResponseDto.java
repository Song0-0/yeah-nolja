package com.room.yeahnolja.domain.reservation.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ReservationResponseDto {
    private String hotelName;
    private LocalDate checkin;
    private LocalDate checkout;
    private String type;
    private int payment;

    public ReservationResponseDto(String name, LocalDate checkIn, LocalDate checkOut, String type, int payment) {
        this.hotelName = name;
        this.checkin = checkIn;
        this.checkout = checkOut;
        this.type = type;
        this.payment = payment;
    }
}
