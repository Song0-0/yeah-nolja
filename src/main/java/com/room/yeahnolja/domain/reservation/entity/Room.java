package com.room.yeahnolja.domain.reservation.entity;

import com.room.yeahnolja.domain.hotel.entity.Hotel;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "room")
@Getter
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private int people;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String information;

    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
        if(reservation.getRoom() != this){
            reservation.setRoom(this);
        }
    }
}
