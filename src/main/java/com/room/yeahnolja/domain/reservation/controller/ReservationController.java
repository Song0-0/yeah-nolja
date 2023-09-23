package com.room.yeahnolja.domain.reservation.controller;

import com.room.yeahnolja.domain.reservation.dto.RoomResponseDto;
import com.room.yeahnolja.domain.reservation.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final RoomService roomService;

    @Operation(summary = "특정 호텔에 대한 예약 가능한 객실 조회")
    @GetMapping("/reservation/rooms/{hotelId}")
    public ResponseEntity<List<RoomResponseDto>> getAvailableRooms(
            @PathVariable int hotelId,
            @RequestParam("checkin") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkin,
            @RequestParam("checkout") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkout,
            @RequestParam(value = "price", required = false) Integer price,
            @RequestParam(value = "location", required = false) String location
    ) {
        List<RoomResponseDto> availableRooms = roomService.getAvailableRooms(hotelId, checkin, checkout, price, location);
        return ResponseEntity.ok(availableRooms);
    }


    @Operation(summary = "객실 단건 조회")
    @GetMapping("/reservation/room/{roomId}")
    public ResponseEntity<RoomResponseDto> getRoom(@PathVariable int roomId) {
        RoomResponseDto room = roomService.getRoom(roomId);
        return ResponseEntity.ok(room);
    }
}
