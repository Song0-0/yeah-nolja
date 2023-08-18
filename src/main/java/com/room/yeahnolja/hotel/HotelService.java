package com.room.yeahnolja.hotel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelMapper hotelMapper;

    public void saveHotel(HotelRequestDto requestDto) {
        hotelMapper.insertHotel(requestDto);
    }
}
