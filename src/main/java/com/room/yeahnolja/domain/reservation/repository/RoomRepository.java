package com.room.yeahnolja.domain.reservation.repository;

import com.room.yeahnolja.domain.reservation.dto.RoomResponseDto;
import com.room.yeahnolja.domain.reservation.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("SELECT new com.room.yeahnolja.domain.reservation.dto.RoomResponseDto("
            + "r.hotel.id, "
            + "r.hotel.name, "
            + "r.id, "
            + "r.type, "
            + "r.people, "
            + "r.price, "
            + "r.information) "
            + "FROM Room r "
            + "WHERE r.hotel.id = :hotelId AND "
            + "NOT EXISTS (SELECT res FROM Reservation res WHERE res.room.id = r.id AND "
            + "((:checkin <= res.checkOut AND :checkout >= res.checkIn))) "
            + "AND (:price IS NULL OR r.price <= :price) "
            + "AND (:location IS NULL OR r.hotel.address LIKE %:location%)")
    List<RoomResponseDto> findAvailableRooms(
            @Param("hotelId") int hotelId,
            @Param("checkin") LocalDate checkin,
            @Param("checkout") LocalDate checkout,
            @Param("price") Integer price,
            @Param("location") String location
    );
}
