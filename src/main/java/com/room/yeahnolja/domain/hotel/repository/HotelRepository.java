package com.room.yeahnolja.domain.hotel.repository;

import com.room.yeahnolja.domain.hotel.entity.Hotel;

import java.util.List;
import java.util.Optional;

public interface HotelRepository {
    Hotel save(Hotel hotel);
    Hotel update(int id, Hotel hotel);
    void delete(int id);
    List<Hotel> findAll();
    Optional<Hotel> findById(int id);
    List<Hotel>  findAllByLocation(String address);
    List<Hotel> findAllByPrice(int price);
    List<Hotel> findAllByName(String name);
}
