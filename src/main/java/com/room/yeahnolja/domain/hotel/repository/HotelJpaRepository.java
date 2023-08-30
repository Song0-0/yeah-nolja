package com.room.yeahnolja.domain.hotel.repository;

import com.room.yeahnolja.domain.hotel.dto.HotelResponseDto;
import com.room.yeahnolja.domain.hotel.entity.Hotel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class HotelJpaRepository implements HotelRepository {

    private final EntityManager em;

    public HotelJpaRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    /**
     * TODO:
     * No EntityManager with actual transaction available for current thread - cannot reliably process 'persist' call 오류는 보통 트랜잭션이 시작되지 않았을 때 발생합니다.
     * 이런 오류가나서 @Transactional 을 해줬다..
     */
    public Hotel save(Hotel hotel) {
        em.persist(hotel);
        return hotel;
    }

    @Override
    @Transactional
    public HotelResponseDto update(int id, String name, String type, String address, String phone, int star, String description, int min_price, int max_price) {
        Hotel hotel = em.find(Hotel.class, id);
        if (hotel != null) {
            hotel.setName(name);
            hotel.setType(type);
            hotel.setAddress(address);
            hotel.setPhone(phone);
            hotel.setStar(star);
            hotel.setDescription(description);
            hotel.setMin_price(min_price);
            hotel.setMax_price(max_price);
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(int id) {
        Hotel hotel = em.find(Hotel.class, id);
        if (hotel != null) {
            em.remove(hotel);
        }
    }

    @Override
    public List<Hotel> findAll() {
        return em.createQuery("select h from Hotel h", Hotel.class)
                .getResultList();
    }

    @Override
    public Optional<Hotel> findById(int id) {
        Hotel hotel = em.find(Hotel.class, id);
        return Optional.ofNullable(hotel);
    }

    @Override
    public Optional<Hotel> findAllByPrice(int price) {
        List<Hotel> result = em.createQuery("select h from Hotel " + "h where h.min_price <= :price and h.max_price >= :price")
                .setParameter("price", price)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public Optional<Hotel> findAllByName(String name) {
        List<Hotel> result = em.createQuery("select h from Hotel " + "h where h.name = :name", Hotel.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public Optional<Hotel> findAllByLocation(String address) {
        List<Hotel> result = em.createQuery("select h from Hotel " + "h where h.address = :address", Hotel.class)
                .setParameter("address", address)
                .getResultList();
        return result.stream().findAny();
    }
}
