package com.room.yeahnolja.domain.reservation.service;

import com.room.yeahnolja.domain.reservation.dto.RoomResponseDto;
import com.room.yeahnolja.domain.reservation.entity.Reservation;
import com.room.yeahnolja.domain.reservation.entity.Room;
import com.room.yeahnolja.domain.reservation.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public List<RoomResponseDto> getAvailableRooms(int hotelId, LocalDate checkin, LocalDate checkout, Integer price, String location) {
        List<RoomResponseDto> rooms = roomRepository.findAvailableRooms(hotelId, checkin, checkout, price, location);
        return rooms;
    }

    public RoomResponseDto getRoom(int roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 객실입니다."));

        List<String> reservationNotices = room.getReservations()
                .stream()
                .map(Reservation::getNotice)
                .collect(Collectors.toList());
        return new RoomResponseDto(
                room.getType(),
                room.getPeople(),
                room.getPrice(),
                room.getInformation(),
                reservationNotices
        );
    }
}
