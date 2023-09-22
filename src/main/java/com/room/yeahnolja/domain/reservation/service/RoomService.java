package com.room.yeahnolja.domain.reservation.service;

import com.room.yeahnolja.domain.reservation.dto.RoomResponseDto;
import com.room.yeahnolja.domain.reservation.entity.Room;
import com.room.yeahnolja.domain.reservation.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public List<RoomResponseDto> getRoomsByPrice(int price) {
        List<Room> rooms = roomRepository.findAllByPriceLessThanEqual(price);
        return rooms.stream()
                .map(RoomResponseDto::new)
                .collect(Collectors.toList());
    }
}
