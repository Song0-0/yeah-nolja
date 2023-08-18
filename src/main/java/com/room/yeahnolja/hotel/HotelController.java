package com.room.yeahnolja.hotel;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HotelController {
    /**
     * private final 설정으로 객체 사용을 하고 있으니 생성자 주입방식임을 알 수 있다.
     * 따라서, HotelController 객체가 생성될 때, hotelService에 주입될 수 있도록 설정해줘야한다.
     * 그래서! 직접 생성자를 넣을 수도 있지만,
     *  final이나 @NonNull로 선언된 필드만을 파라미터로 갖는 생성자를 자동으로 생성해주는
     *  @RequiredArgsConstructor를 사용한다.
     */
    private final HotelService hotelService;

    @PostMapping("/hotel")
    public void saveHotel(@RequestBody HotelRequestDto requestDto) {
        hotelService.saveHotel(requestDto);
    }
}
