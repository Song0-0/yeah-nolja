package com.room.yeahnolja.domain.hotel.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "hotel")
@Getter
@Setter
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @Column(unique = true)
    private String name;

    @NonNull
    private String type;
    @NonNull
    private String address;
    @NonNull
    private String phone;
    @NonNull
    private int star;
    @NonNull
    private String description;
    @NonNull
    private int min_price;
    @NonNull
    private int max_price;


    private String email;
    private String availability_id;
    private int facilities_id;
    private int rooms;
    private int image_id;

    private String reg_dt;
    private String mod_dt;


    @Override
    public String toString() {
        return name + " " + type + " " + address + " " + phone + " " + star + " " + description + " " + min_price + " " + max_price;
    }
}
