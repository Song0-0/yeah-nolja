package com.room.yeahnolja.domain.hotel.controller;

import com.room.yeahnolja.domain.hotel.dto.HotelRequestDto;
import com.room.yeahnolja.domain.hotel.dto.HotelResponseDto;
import com.room.yeahnolja.domain.hotel.entity.Hotel;
import com.room.yeahnolja.domain.hotel.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Tag(name = "Hotel Controller")
@RestController
@RequiredArgsConstructor
public class HotelController {
    /**
     * TODO:
     * private final 설정으로 객체 사용을 하고 있으니 생성자 주입방식임을 알 수 있다.
     * 따라서, HotelController 객체가 생성될 때, hotelService에 주입될 수 있도록 설정해줘야한다.
     * 그래서! 직접 생성자를 넣을 수도 있지만,
     * final이나 @NonNull로 선언된 필드만을 파라미터로 갖는 생성자를 자동으로 생성해주는
     *
     * @RequiredArgsConstructor를 사용한다.
     */
    private final HotelService hotelService;

    /**
     * TODO:
     * 아래의 메서드의 경우 boolean으로 하면 jpaRepository자체에 설정되있는 save() 메서드도 boolean 반환타입을 바꿔야하는데
     * 이건 어떻게 ResponseEntity가 적용될 수 있는지..?
     */
    @Operation(summary = "호텔 단건 등록 (jpa 사용)")
    @PostMapping("/hotel/save")
    public ResponseEntity<Void> saveHotel(@RequestBody HotelRequestDto requestDto) {
        try {
            hotelService.saveHotel(requestDto);
            return ResponseEntity.created(URI.create(""))
                    .build();
        } catch (Exception e) {
            return ResponseEntity.ok()
                    .build();
        }
    }

    @Operation(summary = "호텔 단건 수정")
    @PatchMapping("/hotel/{hotelId}")
    public ResponseEntity<Void> modifyHotel(@PathVariable int hotelId, @RequestBody HotelRequestDto requestDto) {
        hotelService.modifyHotel(hotelId, requestDto);
        return ResponseEntity.ok()
                .build();
    }

    @Operation(summary = "호텔 단건 삭제")
    @DeleteMapping("/hotel/{hotelId}")
    public ResponseEntity<Void> removeHotel(@PathVariable int hotelId) {
        hotelService.removeHotel(hotelId);
        return ResponseEntity.ok()
                .build();
    }


    @Operation(summary = "호텔 전체 조회")
    @GetMapping("/hotels")
    public ResponseEntity<List<HotelResponseDto>> getAllHotels() {
        List<HotelResponseDto> allHotels = hotelService.getAllHotels();
        return ResponseEntity.ok()
                .body(allHotels);
    }

    @Operation(summary = "호텔 단건 조회")
    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable int hotelId) {
        Optional<Hotel> hotel = hotelService.getHotel(hotelId);
        return hotel
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "특정 지역에 대한 호텔 조회")
    @GetMapping("/hotels/location")
    public ResponseEntity<List<Hotel>> getHotelsByLocation(@RequestParam String location) {
        List<Hotel> hotelsByLocation = hotelService.getHotelsByLocation(location);
        return new ResponseEntity<>(hotelsByLocation, HttpStatus.OK);
    }

    @Operation(summary = "특정 가격에 대한 호텔 조회")
    @GetMapping("/hotels/price")
    public ResponseEntity<List<Hotel>> getHotelsByPrice(@RequestParam int price) {
        List<Hotel> hotelsByPrice = hotelService.getHotelsByPrice(price);
        return new ResponseEntity<>(hotelsByPrice, HttpStatus.OK);
    }

    @Operation(summary = "특정 호텔명에 대한 호텔 조회")
    @GetMapping("/hotels/name")
    public ResponseEntity<List<Hotel>> getHotelsByName(@RequestParam String name) {
        List<Hotel> hotelsByName = hotelService.getHotelsByName(name);
        return new ResponseEntity<>(hotelsByName, HttpStatus.OK);
    }
}