package com.room.yeahnolja.domain.reservation.controller;

import com.room.yeahnolja.domain.reservation.dto.RoomResponseDto;
import com.room.yeahnolja.domain.reservation.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final RoomService roomService;

    @Operation(summary = "특정 금액에 따른 객실 조회")
    @GetMapping("/reservation/price")
    public ResponseEntity<List<RoomResponseDto>> getRoomsByPrice(@RequestParam int price) {
        List<RoomResponseDto> rooms = roomService.getRoomsByPrice(price);
        return ResponseEntity.ok()
                .body(rooms);
    }
}
