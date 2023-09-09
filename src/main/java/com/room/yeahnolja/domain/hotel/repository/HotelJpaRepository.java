package com.room.yeahnolja.domain.hotel.repository;

import com.room.yeahnolja.domain.hotel.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelJpaRepository extends JpaRepository<Hotel, Integer> {
    List<Hotel> findAllByAddressContaining(String address);

    List<Hotel> findAllByNameContaining(String name);

}
