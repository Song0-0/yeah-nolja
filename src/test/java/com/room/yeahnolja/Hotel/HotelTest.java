package com.room.yeahnolja.Hotel;

import com.room.yeahnolja.domain.hotel.entity.Hotel;
import com.room.yeahnolja.domain.hotel.repository.HotelJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class HotelTest {
    @Autowired
    private HotelJpaRepository hotelJpaRepository;

    private Hotel createSampleHotel() {
        Hotel hotel = new Hotel();
        hotel.setName("테스트 호텔");
        hotel.setType("테스트 타입");
        hotel.setAddress("테스트 주소");
        hotel.setContact("테스트 연락처");
        hotel.setStar(5);
        hotel.setDescription("테스트 설명");
        hotel.setRooms(100);
        return hotel;
    }

    @Test
    public void 호텔생성() {
        //Given 초기 호텔 정보 생성 및 저장
        Hotel hotel = createSampleHotel();
        //When 호텔 정보 저장
        int saveId = hotelJpaRepository.save(hotel).getId();
        //Then 저장된 정보 검증
        Hotel findHotel = hotelJpaRepository.findById(saveId).get();
        assertEquals(hotel.getId(), findHotel.getId());
    }

    @Test
    public void 호텔수정() {
        // Given 초기 호텔 정보 생성 및 저장
        Hotel hotel = createSampleHotel();
        hotelJpaRepository.save(hotel);
        //When 호텔 정보 수정
        int updateRooms = 400;
        String updateDescription = "업데이트된 설명";
        hotel.setRooms(updateRooms);
        hotel.setDescription(updateDescription);
        //Then 수정된 정보 검증
        Hotel updatedHotel = hotelJpaRepository.findById(hotel.getId()).get();
        assertEquals(updateRooms, updatedHotel.getRooms());
        assertEquals(updateDescription, updatedHotel.getDescription());
    }

    @Test
    public void 호텔삭제() {
        //Given 호텔 정보 생성 및 저장
        Hotel hotel = createSampleHotel();
        hotelJpaRepository.save(hotel);
        //When 호텔 삭제
        hotelJpaRepository.deleteById(hotel.getId());
        //Then 삭제 검증
        assertFalse(hotelJpaRepository.existsById(hotel.getId()));
    }

    @Test
    public void 호텔전체조회() {
        //Given 여러 호텔 정보 생성 및 저장
        Hotel hotel1 = createSampleHotel();
        Hotel hotel2 = createSampleHotel();
        hotelJpaRepository.save(hotel1);
        hotelJpaRepository.save(hotel2);
        //When 전체 호텔 조회
        List<Hotel> hotels = hotelJpaRepository.findAll();
        //Then 조회된 호텔 수 검증
        assertEquals(37, hotels.size());
    }

    @Test
    public void 호텔단건조회() {
        //Given 호텔 정보 생성 및 저장
        Hotel hotel = createSampleHotel();
        hotelJpaRepository.save(hotel);
        //When 단건조회
        Hotel foundHotel = hotelJpaRepository.findById(hotel.getId()).get();
        //Then 조회된 호텔 정보 검증
        assertEquals(hotel.getId(), foundHotel.getId());
    }

    @Test
    public void 호텔명으로조회() {
        //Given 호텔 정보 생성 및 저장
        Hotel hotel = createSampleHotel();
        hotel.setName("쉐라톤");
        hotelJpaRepository.save(hotel);
        //When 호텔명으로 조회
        List<Hotel> foundHotels = hotelJpaRepository.findAllByNameContaining("쉐라톤");
        //Then 조회된 호텔 정보 검증
        assertTrue(foundHotels.stream().anyMatch(h -> h.getName().equals(hotel.getName())));
        long count = foundHotels.stream().filter(h -> h.getName().equals(hotel.getName())).count();
        assertEquals(2, count);
    }

    @Test
    public void 호텔지역명으로조회() {
        //Given 호텔 정보 생성 및 저장
        Hotel hotel = createSampleHotel();
        hotel.setAddress("경주");
        hotelJpaRepository.save(hotel);
        //When 호텔지역명으로 조회
        List<Hotel> foundHotels = hotelJpaRepository.findAllByAddressContaining("경주");
        //Then 조회된 호텔 정보 검증
        assertTrue(foundHotels.stream().anyMatch(h -> h.getAddress().equals(hotel.getAddress())));
        long count = foundHotels.stream().filter(h -> h.getAddress().equals(hotel.getAddress())).count();
        assertEquals(2, count);
    }
}
