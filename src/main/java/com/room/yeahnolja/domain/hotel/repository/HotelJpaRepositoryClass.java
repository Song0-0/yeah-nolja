package com.room.yeahnolja.domain.hotel.repository;

import com.room.yeahnolja.domain.hotel.entity.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

//@Repository
public class HotelJpaRepositoryClass implements HotelRepository {
    private final EntityManager em;
    private static final Logger log = LoggerFactory.getLogger(HotelJpaRepositoryClass.class);

    public HotelJpaRepositoryClass(EntityManager em) {
        log.info("HotelJpaRepositoryClass 생성자");
        this.em = em;
    }

    @Override
    @Transactional
    public Hotel save(Hotel hotel) {
        log.info("[레파지토리] 생성 시작");
        em.persist(hotel);
        log.info("[레파지토리] 생성 종료");
        return hotel;
    }

    @Override
    @Transactional
    public Hotel update(int id, Hotel hotel) {
        log.info("[레파지토리] 수정 시작");
        em.persist(hotel);
        Hotel hotelEntity = em.find(Hotel.class, id);
        log.info("[레파지토리] 수정 종료");
        return hotelEntity;
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        log.info("[레파지토리] 삭제 시작");
        Hotel hotel = em.find(Hotel.class, id);
        if (hotel != null) {
            em.remove(hotel);
        }
        log.info("[레파지토리] 삭제 종료");
    }

    @Override
    public List<Hotel> findAll() {
        log.info("[레파지토리] 전체조회 시작과 종료");
        return em.createQuery("select h from Hotel h", Hotel.class)
                .getResultList();
    }

    @Override
    public Optional<Hotel> findById(int id) {
        log.info("[레파지토리] 단건조회 시작");
        Hotel hotel = em.find(Hotel.class, id);
        log.info("[레파지토리] 단건조회 종료");
        return Optional.ofNullable(hotel);
    }

    @Override
    public List<Hotel> findAllByAddressContaining(String address) {
        log.info("[레파지토리] 특정 지역으로 조회 시작");
        List<Hotel> result = em.createQuery("select h from Hotel h where h.address like :address", Hotel.class)
                .setParameter("address", "%" + address + "%")
                .getResultList();
        log.info("[레파지토리] 특정 지역으로 조회 종료");
        return result;
    }

    @Override
    public List<Hotel> findAllByNameContaining(String name) {
        log.info("[레파지토리] 특정명으로 조회 시작");
        List<Hotel> result = em.createQuery("select h from Hotel " + "h where h.name like :name", Hotel.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
        log.info("[레파지토리] 특정명으로 조회 끝");
        return result;
    }

}
