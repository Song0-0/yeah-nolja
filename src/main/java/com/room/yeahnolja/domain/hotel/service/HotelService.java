package com.room.yeahnolja.domain.hotel.service;

import com.room.yeahnolja.config.exception.ResourceNotFoundException;
import com.room.yeahnolja.domain.hotel.dto.HotelRequestDto;
import com.room.yeahnolja.domain.hotel.dto.HotelResponseDto;
import com.room.yeahnolja.domain.hotel.entity.Hotel;
import com.room.yeahnolja.domain.hotel.repository.HotelJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional //더티체킹을 위해 필요.
public class HotelService {

    //    private final HotelMapper hotelMapper;
    private final HotelJpaRepository hotelJpaRepository;
//    private final HotelRepository hotelRepository;


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
        hotel.setRooms(requestDto.getRooms());

        Hotel hotelDto = hotelJpaRepository.save(hotel);
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
        Hotel hotel = hotelJpaRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel with ID " + hotelId + " not found"));

        modifyStringIfNotNull(requestDto.getName(), hotel::setName);
        modifyStringIfNotNull(requestDto.getType(), hotel::setType);
        modifyStringIfNotNull(requestDto.getAddress(), hotel::setAddress);
        modifyStringIfNotNull(requestDto.getContact(), hotel::setContact);
        modifyStringIfNotNull(requestDto.getEmail(), hotel::setEmail);
        modifyStringIfNotNull(requestDto.getDescription(), hotel::setDescription);

        modifyIntIfNotZero(requestDto.getStar(), hotel::setStar);
        modifyIntIfNotZero(requestDto.getRooms(), hotel::setRooms);

//        Hotel update = hotelJpaRepository.update(hotelId, hotel); //JpaRepository를 상속받은 Repository로 할 때는 더티체킹이 되어서 사용할 필요가 없다.
        log.info("[서비스] 수정 종료");
        return new HotelResponseDto(hotel);
    }

    public void removeHotel(int hotelId) {
        log.info("[서비스] 삭제 실행");
        hotelJpaRepository.deleteById(hotelId);
        log.info("[서비스] 삭제 종료");
    }

    public List<HotelResponseDto> getAllHotels() {
        log.info("[서비스] 전체조회 실행");
        List<Hotel> allHotels = hotelJpaRepository.findAll();
        List<HotelResponseDto> allHotelResponseDto = allHotels.stream()
                .map(hotel -> new HotelResponseDto(hotel))
                .collect(Collectors.toList());
        log.info("[서비스] 전체조회 종료");
        return allHotelResponseDto;
    }

    public HotelResponseDto getHotel(int hotelId) {
        log.info("[서비스] 단건조회 실행");
        Hotel hotel = hotelJpaRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel with ID " + hotelId + " not found"));
        log.info("[서비스] 단건조회 종료");
        return new HotelResponseDto(hotel);
    }

    public List<HotelResponseDto> getHotelsByLocation(String location) {
        log.info("[서비스] 지역명으로 조회 실행");
        List<Hotel> allByLocation = hotelJpaRepository.findAllByAddressContaining(location);

        if (allByLocation.isEmpty()) {
            log.info("[서비스] 지역명으로 조회 결과 : 0건");
            throw new ResourceNotFoundException("Hotel with " + location + " not found");
        } else {
            log.info("[서비스] 지역명으로 조회 결과 : {}건", allByLocation.size() + "건");
            log.info("[서비스] 지역명으로 조회 종료");
        }
        return allByLocation.stream()
                .map(HotelResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<HotelResponseDto> getHotelsByName(String name) {
        log.info("[서비스] 호텔명으로 조회 실행");
        List<Hotel> allByName = hotelJpaRepository.findAllByNameContaining(name);

        if (allByName.isEmpty()) {
            log.info("[서비스] 지역명으로 조회 결과 : 0건");
            throw new ResourceNotFoundException("Hotel with " + name + " not found");
        } else {
            log.info("[서비스] 지역명으로 조회 결과 : {}건", allByName.size() + "건");
            log.info("[서비스] 호텔명으로 조회 종료");
        }
        return allByName.stream()
                .map(hotel -> new HotelResponseDto(hotel))
                .collect(Collectors.toList());
    }
}
