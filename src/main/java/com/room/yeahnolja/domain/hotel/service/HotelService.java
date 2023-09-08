package com.room.yeahnolja.domain.hotel.service;

import com.room.yeahnolja.domain.hotel.dto.HotelRequestDto;
import com.room.yeahnolja.domain.hotel.dto.HotelResponseDto;
import com.room.yeahnolja.domain.hotel.entity.Hotel;
import com.room.yeahnolja.domain.hotel.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class HotelService {

    //    private final HotelMapper hotelMapper;
//    private final HotelJpaRepository hotelJpaRepository;
    private final HotelRepository hotelRepository;


    public HotelResponseDto saveHotel(HotelRequestDto requestDto) {
        log.info("[서비스] 생성 로직 시작");
        Hotel hotel = new Hotel();
        hotel.setName(requestDto.getName());
        hotel.setType(requestDto.getType());
        hotel.setAddress(requestDto.getAddress());
        hotel.setContact(requestDto.getContact());
        hotel.setEmail(requestDto.getEmail());
        hotel.setStar(requestDto.getStar());
        hotel.setDescription(requestDto.getDescription());
        hotel.setMinPrice(requestDto.getMinPrice());
        hotel.setMaxPrice(requestDto.getMaxPrice());
        hotel.setRooms(requestDto.getRooms());

        Hotel hotelDto = hotelRepository.save(hotel);
        log.info("[서비스] 생성 로직 끝");
        return new HotelResponseDto(hotelDto);
    }

    private void modifyStringIfNotNull(String value, Consumer<String> modifier) {
        log.info("[서비스] 수정 modifyStringIfNotNull 실행");
        if (StringUtils.hasText(value)) {
            modifier.accept(value);
        }
        log.info("[서비스] 수정 modifyStringIfNotNull 종료");
    }

    private void modifyIntIfNotZero(int value, Consumer<Integer> modifier) {
        log.info("[서비스] 수정 modifyIntIfNotZero 실행");
        if (value != 0) {
            modifier.accept(value);
        }
        log.info("[서비스] 수정 modifyIntIfNotZero 종료");
    }

    public HotelResponseDto modifyHotel(int hotelId, HotelRequestDto requestDto) {
        log.info("[서비스] 수정 실행");
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new EntityNotFoundException("Hotel not found with id " + hotelId));

        modifyStringIfNotNull(requestDto.getName(), hotel::setName);
        modifyStringIfNotNull(requestDto.getType(), hotel::setType);
        modifyStringIfNotNull(requestDto.getAddress(), hotel::setAddress);
        modifyStringIfNotNull(requestDto.getContact(), hotel::setContact);
        modifyStringIfNotNull(requestDto.getEmail(), hotel::setEmail);
        modifyStringIfNotNull(requestDto.getDescription(), hotel::setDescription);

        modifyIntIfNotZero(requestDto.getStar(), hotel::setStar);
        modifyIntIfNotZero(requestDto.getMinPrice(), hotel::setMinPrice);
        modifyIntIfNotZero(requestDto.getMaxPrice(), hotel::setMaxPrice);
        modifyIntIfNotZero(requestDto.getRooms(), hotel::setRooms);

        Hotel update = hotelRepository.update(hotelId, hotel);
        log.info("[서비스] 수정 종료");
        return new HotelResponseDto(update);
    }

    public void removeHotel(int hotelId) {
        log.info("[서비스] 삭제 실행");
        hotelRepository.delete(hotelId);
        log.info("[서비스] 삭제 종료");
    }

    public List<HotelResponseDto> getAllHotels() {
        log.info("[서비스] 전체조회 실행");
        List<Hotel> allHotels = hotelRepository.findAll();
        List<HotelResponseDto> allHotelResponseDto = allHotels.stream()
                .map(hotel -> new HotelResponseDto(hotel))
                .collect(Collectors.toList());
        log.info("[서비스] 전체조회 종료");
        return allHotelResponseDto;
    }

    public HotelResponseDto getHotel(int hotelId) {
        log.info("[서비스] 단건조회 실행");
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new EntityNotFoundException("Hotel not found with id " + hotelId));
        log.info("[서비스] 단건조회 종료");
        return new HotelResponseDto(hotel);
    }

    public List<HotelResponseDto> getHotelsByLocation(String location) {
        log.info("[서비스] 지역명으로 조회 실행");
        List<Hotel> allByLocation = hotelRepository.findAllByLocation(location);
        log.info("[서비스] 지역명으로 조회 종료");
        return allByLocation.stream()
                .map(HotelResponseDto::new)
                .collect(Collectors.toList());
    }


    public List<HotelResponseDto> getHotelsByPrice(int price) {
        log.info("[서비스] 가격으로 조회 실행");
        List<Hotel> allByPrice = hotelRepository.findAllByPrice(price);
        log.info("[서비스] 가격으로 조회 종료");
        return allByPrice.stream()
                .map(HotelResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<HotelResponseDto> getHotelsByName(String name) {
        log.info("[서비스] 호텔명으로 조회 실행");
        List<Hotel> allByName = hotelRepository.findAllByName(name);
        log.info("[서비스] 호텔명으로 조회 종료");
        return allByName.stream()
                .map(hotel -> new HotelResponseDto(hotel))
                .collect(Collectors.toList());
    }
}
