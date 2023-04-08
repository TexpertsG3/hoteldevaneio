package br.com.tex.hoteldevaneio.repository;

import br.com.tex.hoteldevaneio.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
}
