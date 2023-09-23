package com.room.yeahnolja.domain.reservation.service;

import com.room.yeahnolja.domain.reservation.dto.RoomResponseDto;
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
}
