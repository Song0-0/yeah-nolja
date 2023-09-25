package com.room.yeahnolja.domain.reservation.service;

import com.room.yeahnolja.domain.reservation.dto.RoomResponseDto;
import com.room.yeahnolja.domain.reservation.entity.Reservation;
import com.room.yeahnolja.domain.reservation.entity.Room;
import com.room.yeahnolja.domain.reservation.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
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

    public RoomResponseDto getReservationPreInfo(int roomId, LocalDate checkin, LocalDate checkout) {
        //1. 객실 존재 여부 확인
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 객실입니다."));

        //2. 예약 가능한지 확인
        Optional<Room> optionalAvailableRoom = roomRepository.findAvailableRoomByIdAndDate(roomId, checkin, checkout);
        if (!optionalAvailableRoom.isPresent()) {
            throw new IllegalArgumentException("해당 날짜에는 예약이 불가능합니다.");
        }

        //체크인과 체크아웃의 차이 계산하여 숙박 일수 구함
        long between = ChronoUnit.DAYS.between(checkin, checkout);
        //1박 객실 요금 * 숙박일수
        int totalPayment = (int) (room.getPrice() * between);

        return new RoomResponseDto(
                room.getHotel().getName(),
                room.getType(),
                checkin,
                checkout,
                totalPayment
        );
    }
}
