package com.room.yeahnolja.domain.reservation.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(nullable = false)
    private int people;

    @Column(nullable = false)
    private int payment;

    @CreationTimestamp
    private LocalDateTime reservationDate;

    @Column(nullable = false)
    private LocalDate checkIn;

    @Column(nullable = false)
    private LocalDate checkOut;

    @Column(nullable = false)
    private String notice;

    @Column(nullable = false)
    private String delYn;

    private String cancelDate;

    public void setRoom(Room room) {
        this.room = room;
    }
    public void setDelYn(String delYn) {
        this.delYn = delYn;
    }
    public void setCancelDate(String cancelDate) {
        this.cancelDate = cancelDate;
    }

}
