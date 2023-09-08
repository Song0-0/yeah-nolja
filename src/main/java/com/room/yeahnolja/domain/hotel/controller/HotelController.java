package com.room.yeahnolja.domain.hotel.controller;

import com.room.yeahnolja.domain.hotel.dto.HotelRequestDto;
import com.room.yeahnolja.domain.hotel.dto.HotelResponseDto;
import com.room.yeahnolja.domain.hotel.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Tag(name = "Hotel Controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/hotels")
@Slf4j
public class HotelController {

    private final HotelService hotelService;

    @Operation(summary = "호텔 단건 등록")
    @PostMapping("/save")
    public ResponseEntity<Void> saveHotel(@RequestBody HotelRequestDto requestDto) {
        log.info("[컨트롤러] 등록 시, 호텔명 : {}", requestDto.getName());
        HotelResponseDto hotelResponseDto = hotelService.saveHotel(requestDto);
        int id = hotelResponseDto.getId();
        URI location = URI.create("/hotels/" + id);
        log.info("[컨트롤러] 등록 후, 호텔명 : {}", hotelResponseDto.getName());
        return ResponseEntity.created(location)
                .build();

    }

    @Operation(summary = "호텔 단건 수정")
    @PatchMapping("/{hotelId}")
    public ResponseEntity<Void> modifyHotel(@PathVariable int hotelId, @RequestBody HotelRequestDto requestDto) {
        log.info("[컨트롤러] 수정 시 받은 ID : {}", hotelId);
        hotelService.modifyHotel(hotelId, requestDto);
        log.info("[컨트롤러] 수정 완료");
        return ResponseEntity.ok()
                .build();
    }

    @Operation(summary = "호텔 단건 삭제")
    @DeleteMapping("/{hotelId}")
    public ResponseEntity<Void> removeHotel(@PathVariable int hotelId) {
        log.info("[컨트롤러] 삭제 시 받은 ID : {}", hotelId);
        hotelService.removeHotel(hotelId);
        log.info("[컨트롤러] 삭제 완료");
        return ResponseEntity.ok()
                .build();
    }


    @Operation(summary = "호텔 전체 조회")
    @GetMapping("")
    public ResponseEntity<List<HotelResponseDto>> getAllHotels() {
        log.info("[컨트롤러] 전체조회 시작");
        List<HotelResponseDto> allHotels = hotelService.getAllHotels();
        log.info("[컨트롤러] 전체조회 결과 : {}", allHotels);
        return ResponseEntity.ok()
                .body(allHotels);
    }

    @Operation(summary = "호텔 단건 조회")
    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelResponseDto> getHotel(@PathVariable int hotelId) {
        log.info("[컨트롤러] 단건조회 시 받은 ID : {}", hotelId);
        HotelResponseDto hotel = hotelService.getHotel(hotelId);
        log.info("[컨트롤러] 단건조회 완료");
        return ResponseEntity.ok()
                .body(hotel);
    }

    @Operation(summary = "특정 지역에 대한 호텔 조회")
    @GetMapping("/location")
    public ResponseEntity<List<HotelResponseDto>> getHotelsByLocation(@RequestParam String location) {
        log.info("[컨트롤러] 검색용 지역 데이터 : {}", location);
        List<HotelResponseDto> hotelsByLocation = hotelService.getHotelsByLocation(location);
        log.info("[컨트롤러] 특정지역 조회 완료");
        return ResponseEntity.ok()
                .body(hotelsByLocation);
    }

    @Operation(summary = "특정 가격에 대한 호텔 조회")
    @GetMapping("/price")
    public ResponseEntity<List<HotelResponseDto>> getHotelsByPrice(@RequestParam int price) {
        log.info("[컨트롤러] 검색용 가격 데이터 : {}", price);
        List<HotelResponseDto> hotelsByPrice = hotelService.getHotelsByPrice(price);
        log.info("[컨트롤러] 특정가격 조회 완료");
        return ResponseEntity.ok()
                .body(hotelsByPrice);
    }

    @Operation(summary = "특정 호텔명에 대한 호텔 조회")
    @GetMapping("/name")
    public ResponseEntity<List<HotelResponseDto>> getHotelsByName(@RequestParam String name) {
        log.info("[컨트롤러] 검색용 호텥명 데이터 : {}", name);
        List<HotelResponseDto> hotelsByName = hotelService.getHotelsByName(name);
        log.info("[컨트롤러] 특정 호텔명 조회 완료");
        return ResponseEntity.ok()
                .body(hotelsByName);
    }
}