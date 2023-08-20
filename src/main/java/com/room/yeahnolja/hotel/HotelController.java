package com.room.yeahnolja.hotel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Hotel Controller")
@RestController
@RequiredArgsConstructor
public class HotelController {
    /**
     * private final 설정으로 객체 사용을 하고 있으니 생성자 주입방식임을 알 수 있다.
     * 따라서, HotelController 객체가 생성될 때, hotelService에 주입될 수 있도록 설정해줘야한다.
     * 그래서! 직접 생성자를 넣을 수도 있지만,
     * final이나 @NonNull로 선언된 필드만을 파라미터로 갖는 생성자를 자동으로 생성해주는
     *
     * @RequiredArgsConstructor를 사용한다.
     */
    private final HotelService hotelService;

    @Operation(summary = "호텔 단건 등록")
    @PostMapping("/hotel")
    public void saveHotel(@RequestBody HotelRequestDto requestDto) {
        hotelService.saveHotel(requestDto);
    }

    @Operation(summary = "호텔 전체 조회")
    @GetMapping("/hotels")
    public List<HotelResponseDto> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @Operation(summary = "호텔 단건 조회")
    @GetMapping("/hotels/{hotelId}")
    public HotelResponseDto getHotel(@PathVariable int hotelId) {
        return hotelService.getHotel(hotelId);
    }

    @Operation(summary = "특정 지역에 대한 호텔 조회")
    @GetMapping("/hotels/location")
    public List<HotelResponseDto> getHotelsByLocation(@RequestParam String location) {
        return hotelService.getHotelsByLocation(location);
    }

    @Operation(summary = "특정 가격에 대한 호텔 조회")
    @GetMapping("/hotels/price")
    public List<HotelResponseDto> getHotelsByPrice(@RequestParam int price) {
        return hotelService.getHotelsByPrice(price);
    }

    @Operation(summary = "호텔 단건 수정")
    @PutMapping("/hotel/{hotelId}")
    public void modifyHotel(@PathVariable int hotelId, @RequestBody HotelRequestDto requestDto) {
        hotelService.modifyHotel(hotelId, requestDto);
    }
}
