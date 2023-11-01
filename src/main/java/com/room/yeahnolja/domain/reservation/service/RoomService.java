package com.room.yeahnolja.domain.reservation.service;

import com.room.yeahnolja.domain.reservation.dto.ReservationResponseDto;
import com.room.yeahnolja.domain.reservation.dto.RoomResponseDto;
import com.room.yeahnolja.domain.reservation.entity.Reservation;
import com.room.yeahnolja.domain.reservation.entity.Room;
import com.room.yeahnolja.domain.reservation.repository.ReservationRepository;
import com.room.yeahnolja.domain.reservation.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

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

    private Room validateRoomAndAvailability(int roomId, LocalDate checkin, LocalDate checkout) {
        //객실 존재 여부 확인
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 객실입니다."));

        //예약 가능한지 확인
        Optional<Room> optionalAvailableRoom = roomRepository.findAvailableRoomByIdAndDate(roomId, checkin, checkout);
        if (!optionalAvailableRoom.isPresent()) {
            throw new IllegalArgumentException("해당 날짜에는 예약이 불가능합니다.");
        }
        return room;
    }

    private int calculatePayment(int roomId, LocalDate checkin, LocalDate checkout) {
        Room room = validateRoomAndAvailability(roomId, checkin, checkout);
        //체크인과 체크아웃의 차이 계산하여 숙박 일수 구함
        long between = ChronoUnit.DAYS.between(checkin, checkout);
        //1박 객실 요금 * 숙박일수
        int totalPayment = (int) (room.getPrice() * between);

        return totalPayment;
    }

    public RoomResponseDto getReservationPreInfo(int roomId, LocalDate checkin, LocalDate checkout) {
        Room room = validateRoomAndAvailability(roomId, checkin, checkout);
        int total = calculatePayment(roomId, checkin, checkout);

        return new RoomResponseDto(
                room.getHotel().getName(),
                room.getType(),
                checkin,
                checkout,
                total
        );
    }

    public void saveReservation(int roomId, int people, LocalDate checkin, LocalDate checkout) {
        Room room = validateRoomAndAvailability(roomId, checkin, checkout);
        int total = calculatePayment(roomId, checkin, checkout);

        //예약 저장
        Reservation reservation = Reservation.builder()
                .room(room)
                .people(people)
                .payment(total)
                .checkIn(checkin)
                .checkOut(checkout)
                .build();
        room.addReservation(reservation);

        roomRepository.save(room);
    }


    public void cancelReservation(int reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 예약입니다."));
        reservation.setDelYn("Y");
        reservation.setCancelDate(LocalDateTime.now().toString());
        roomRepository.save(reservation.getRoom());
    }

    public ReservationResponseDto getReservationDetails(int reservationId) {

        Reservation reservation = reservationRepository.findByIdAndDelYn(reservationId, "N").orElseThrow(() -> new IllegalArgumentException("존재하지 않는 예약입니다."));

        return new ReservationResponseDto(
                reservation.getRoom().getHotel().getName(),
                reservation.getCheckIn(),
                reservation.getCheckOut(),
                reservation.getRoom().getType(),
                reservation.getPayment()
        );
    }
}
