package com.room.yeahnolja.domain.hotel.repository;

import com.room.yeahnolja.domain.hotel.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelJpaRepository extends JpaRepository<Hotel, Integer> {
    List<Hotel> findAllByNameContainingAndDelYn(String name, String delYn);
    List<Hotel> findByDelYn(String delYn);
    Optional<Hotel> findByIdAndDelYn(int id, String delYn);
}
