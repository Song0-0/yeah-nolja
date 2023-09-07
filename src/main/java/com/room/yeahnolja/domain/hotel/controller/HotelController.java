package com.room.yeahnolja.domain.hotel.controller;

import com.room.yeahnolja.domain.hotel.dto.HotelRequestDto;
import com.room.yeahnolja.domain.hotel.dto.HotelResponseDto;
import com.room.yeahnolja.domain.hotel.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Tag(name = "Hotel Controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/hotels")
public class HotelController {

    private final HotelService hotelService;

    @Operation(summary = "호텔 단건 등록 (jpa 사용)")
    @PostMapping("/save")
    public ResponseEntity<Void> saveHotel(@RequestBody HotelRequestDto requestDto) {
        HotelResponseDto hotelResponseDto = hotelService.saveHotel(requestDto);
        int id = hotelResponseDto.getId();
        URI location = URI.create("/hotels/" + id);
        return ResponseEntity.created(location)
                .build();
    }

    @Operation(summary = "호텔 단건 수정")
    @PatchMapping("/{hotelId}")
    public ResponseEntity<Void> modifyHotel(@PathVariable int hotelId, @RequestBody HotelRequestDto requestDto) {
        hotelService.modifyHotel(hotelId, requestDto);
        return ResponseEntity.ok()
                .build();
    }

    @Operation(summary = "호텔 단건 삭제")
    @DeleteMapping("/{hotelId}")
    public ResponseEntity<Void> removeHotel(@PathVariable int hotelId) {
        hotelService.removeHotel(hotelId);
        return ResponseEntity.ok()
                .build();
    }


    @Operation(summary = "호텔 전체 조회")
    @GetMapping("")
    public ResponseEntity<List<HotelResponseDto>> getAllHotels() {
        List<HotelResponseDto> allHotels = hotelService.getAllHotels();
        return ResponseEntity.ok()
                .body(allHotels);
    }

    @Operation(summary = "호텔 단건 조회")
    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelResponseDto> getHotel(@PathVariable int hotelId) {
        HotelResponseDto hotel = hotelService.getHotel(hotelId);
        return ResponseEntity.ok()
                .body(hotel);
    }

    @Operation(summary = "특정 지역에 대한 호텔 조회")
    @GetMapping("/location")
    public ResponseEntity<List<HotelResponseDto>> getHotelsByLocation(@RequestParam String location) {
        List<HotelResponseDto> hotelsByLocation = hotelService.getHotelsByLocation(location);
        return ResponseEntity.ok()
                .body(hotelsByLocation);
    }

    @Operation(summary = "특정 가격에 대한 호텔 조회")
    @GetMapping("/price")
    public ResponseEntity<List<HotelResponseDto>> getHotelsByPrice(@RequestParam int price) {
        List<HotelResponseDto> hotelsByPrice = hotelService.getHotelsByPrice(price);
        return ResponseEntity.ok()
                .body(hotelsByPrice);
    }

    @Operation(summary = "특정 호텔명에 대한 호텔 조회")
    @GetMapping("/name")
    public ResponseEntity<List<HotelResponseDto>> getHotelsByName(@RequestParam String name) {
        List<HotelResponseDto> hotelsByName = hotelService.getHotelsByName(name);
        return ResponseEntity.ok()
                .body(hotelsByName);
    }
}