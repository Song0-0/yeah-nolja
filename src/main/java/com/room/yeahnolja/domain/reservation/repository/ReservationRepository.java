package com.room.yeahnolja.domain.reservation.repository;

import com.room.yeahnolja.domain.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Optional<Reservation> findByIdAndDelYn(int id, String delYn);

}
