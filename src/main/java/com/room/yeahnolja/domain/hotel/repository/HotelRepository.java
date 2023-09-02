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
    List<Hotel> findAllByPrice(int price);
    List<Hotel> findAllByName(String name);
    List<Hotel>  findAllByLocation(String address);

//    //JPA
//    List<Hotel> findByNameContaining(String name);
//
//    //MAP DB
//    void save(Hotel hotel);
//    Collection<Hotel> findAll();
//    void findAllById(int id);
//    void update(int id, String name, String type, String address, String phone, int star, String description, int min_price, int max_price);
//    void delete(int id);
//    void findAllByPrice(int price);
//    void findAllByName(String name);
//    void findAllByLocation(String address);
//
//    //MAPPER
//    boolean save(HotelRequestDto requestDto);
//    List<HotelResponseDto> findAll();
//    HotelResponseDto findAllById(int hotelId);
//    HotelResponseDto update(HotelRequestDto requestDto);
//    void delete(int hotelId);
//    List<HotelResponseDto> findAllByLocation(String location);
//    List<HotelResponseDto> findAllByPrice(int price);

}
