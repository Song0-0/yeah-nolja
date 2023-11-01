package com.room.yeahnolja.domain.hotel.repository;

import com.room.yeahnolja.domain.hotel.entity.Hotel;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.*;

//@Repository
//@Primary
public class HotelMapRepository implements HotelRepository {
    private static int id = 1;

    private Map<Integer, Hotel> db = new HashMap<>();

    @PostConstruct
    void init() {
        db.put(id, Hotel.builder()
                .id(1)
                .name("힐튼1")
                .type("관광호텔업")
                .address("서울시")
                .contact("010-4564-4564")
                .email("hilton@hiilton.com")
                .star(5)
                .description("힐튼 설명")
                .rooms(250)
                .regDt(LocalDateTime.of(2023, 9, 3, 10, 20, 30))
                .modDt(LocalDateTime.of(2023, 9, 6, 10, 20, 30))
                .build());
        id++;

        db.put(id, Hotel.builder()
                .id(2)
                .name("경원재 앰배서더 인천")
                .type("한국전통호텔업")
                .address("인천광역시")
                .contact("010-4564-4564")
                .email("incheonhotel@hotel.com")
                .star(5)
                .description("경원재 설명")
                .rooms(300)
                .regDt(LocalDateTime.of(2023, 9, 2, 10, 20, 30))
                .modDt(LocalDateTime.of(2023, 9, 5, 10, 20, 30))
                .build());
        id++;
    }


    public Hotel save(Hotel hotel) {
        hotel.setId(id);
        db.put(id, hotel);
        id++;
        return hotel;
    }

    public Hotel update(int id, Hotel hotel) {
        Hotel hotelEntity = db.put(id, hotel);
        return hotelEntity;
    }

    public void deleteById(int id) {
        db.remove(id);
    }

    public List<Hotel> findAll() {
        List<Hotel> values = new ArrayList<>(db.values());
        return values;
    }

    public Optional<Hotel> findById(int id) {
        Hotel hotel = db.get(id);
        return Optional.ofNullable(hotel);
    }


    public List<Hotel> findAllByNameContaining(String name) {
        List<Hotel> hotels = new ArrayList<>();
        for (Map.Entry<Integer, Hotel> entrySet : db.entrySet()) {
            if (entrySet.getValue().getName().contains(name)) {
                hotels.add(entrySet.getValue());
            }
        }
        return hotels;
    }

    public List<Hotel> findAllByAddressContaining(String address) {
        List<Hotel> hotels = new ArrayList<>();
        for (Map.Entry<Integer, Hotel> entrySet : db.entrySet()) {
            if (entrySet.getValue().getAddress().contains(address)) {
                hotels.add(entrySet.getValue());
            }
        }
        return hotels;
    }
}
