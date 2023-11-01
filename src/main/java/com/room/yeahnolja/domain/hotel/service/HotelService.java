package com.room.yeahnolja.domain.hotel.service;

import com.room.yeahnolja.config.exception.ResourceNotFoundException;
import com.room.yeahnolja.domain.hotel.dto.HotelCreateRequestDto;
import com.room.yeahnolja.domain.hotel.dto.HotelResponseDto;
import com.room.yeahnolja.domain.hotel.dto.HotelUpdateRequestDto;
import com.room.yeahnolja.domain.hotel.entity.Hotel;
import com.room.yeahnolja.domain.hotel.repository.HotelJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional //더티체킹을 위해 필요.
public class HotelService {
    private final HotelJpaRepository hotelJpaRepository;

    public HotelResponseDto saveHotel(HotelCreateRequestDto createRequestDto) {
        log.info("[서비스] 생성 로직 시작");
        Hotel hotel = new Hotel();
        hotel.setName(createRequestDto.getName());
        hotel.setType(createRequestDto.getType());
        hotel.setAddress(createRequestDto.getAddress());
        hotel.setContact(createRequestDto.getContact());
        hotel.setEmail(createRequestDto.getEmail());
        hotel.setStar(createRequestDto.getStar());
        hotel.setDescription(createRequestDto.getDescription());
        hotel.setRooms(createRequestDto.getRooms());

        Hotel hotelEntity = hotelJpaRepository.save(hotel);
        log.info("[서비스] 생성 로직 끝");
        return new HotelResponseDto(hotelEntity);
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

    public HotelResponseDto modifyHotel(int hotelId, HotelUpdateRequestDto updateRequestDto) {
        log.info("[서비스] 수정 실행");
        Hotel hotel = hotelJpaRepository.findByIdAndDelYn(hotelId, "N")
                .orElseThrow(() -> new ResourceNotFoundException("Hotel with ID " + hotelId + " not found"));

        modifyStringIfNotNull(updateRequestDto.getName(), hotel::setName);
        modifyStringIfNotNull(updateRequestDto.getType(), hotel::setType);
        modifyStringIfNotNull(updateRequestDto.getAddress(), hotel::setAddress);
        modifyStringIfNotNull(updateRequestDto.getContact(), hotel::setContact);
        modifyStringIfNotNull(updateRequestDto.getEmail(), hotel::setEmail);
        modifyStringIfNotNull(updateRequestDto.getDescription(), hotel::setDescription);

        modifyIntIfNotZero(updateRequestDto.getStar(), hotel::setStar);
        modifyIntIfNotZero(updateRequestDto.getRooms(), hotel::setRooms);

//        Hotel update = hotelJpaRepository.update(hotelId, hotel); //JpaRepository를 상속받은 Repository로 할 때는 더티체킹이 되어서 사용할 필요가 없다.
        log.info("[서비스] 수정 종료");
        return new HotelResponseDto(hotel);
    }

    public void removeHotel(int hotelId) {
        log.info("[서비스] 삭제 실행");
        Optional<Hotel> optionalHotel = hotelJpaRepository.findByIdAndDelYn(hotelId, "N");
        Hotel hotel = optionalHotel.orElseThrow(() -> new ResourceNotFoundException("Hotel with ID " + hotelId + " is already deleted or not found"));
        hotel.setDelYn("Y");
        hotelJpaRepository.save(hotel);
        log.info("[서비스] 삭제 종료");
    }

    @Transactional(readOnly = true)
    public List<HotelResponseDto> getAllHotels(LocalDate checkin, LocalDate checkout, String location) {
        log.info("[서비스] 전체조회 실행");

        List<Hotel> allHotels = hotelJpaRepository.findByDelYn("N");

        List<Hotel> availableHotels = allHotels.stream()
                .filter(hotel -> {
                    //지역 필터링
                    if (location != null && !hotel.getAddress().contains(location)) {
                        return false;
                    }
                    //해당 호텔에 예약 가능한 객실이 하나라도 있는지 확인
                    return hotel.getRoomsEntity().stream().anyMatch(room -> {
                        //체크인, 체크아웃 값이 없을 경우, 모든 객실을 예약 가능한 것으로 판단
                        if (checkin == null || checkout == null) {
                            return true;
                        }
                        return room.getReservations().stream().noneMatch(reservation -> (
                                checkin.isBefore(reservation.getCheckOut()) &&
                                        checkout.isAfter(reservation.getCheckIn()
                                        )));
                    });
                })
                .collect(Collectors.toList());

        //DTO 반환
        List<HotelResponseDto> hotelResponseDtos = availableHotels.stream()
                .map(HotelResponseDto::new)
                .collect(Collectors.toList());
        log.info("[서비스] 전체조회 종료");
        return hotelResponseDtos;
    }

    @Transactional(readOnly = true)
    public HotelResponseDto getHotel(int hotelId) {
        log.info("[서비스] 단건조회 실행");
        Hotel hotel = hotelJpaRepository.findByIdAndDelYn(hotelId, "N")
                .orElseThrow(() -> new ResourceNotFoundException("Hotel with ID " + hotelId + " not found"));
        log.info("[서비스] 단건조회 종료");
        return new HotelResponseDto(hotel);
    }

    @Transactional(readOnly = true)
    public List<HotelResponseDto> getHotelsByName(String name) {
        log.info("[서비스] 호텔명으로 조회 실행");
        List<Hotel> allByName = hotelJpaRepository.findAllByNameContainingAndDelYn(name, "N");

        if (allByName.isEmpty()) {
            log.info("[서비스] 호텔명으로 조회 결과 : 0건");
            throw new ResourceNotFoundException("Hotel with " + name + " not found");
        } else {
            log.info("[서비스] 호텔명으로 조회 결과 : {}건", allByName.size() + "건");
            log.info("[서비스] 호텔명으로 조회 종료");
        }
        return allByName.stream()
                .map(hotel -> new HotelResponseDto(hotel))
                .collect(Collectors.toList());
    }
}
