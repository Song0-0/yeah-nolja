package com.room.yeahnolja.domain.reservation.repository;

import com.room.yeahnolja.domain.reservation.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findAllByPriceLessThanEqual(int price);
}
