package br.com.tex.hoteldevaneio.repository;

import br.com.tex.hoteldevaneio.model.DadosHotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DadosHotelRepository extends JpaRepository<DadosHotel, Integer> {
}
