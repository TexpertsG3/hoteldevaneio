package br.com.tex.hoteldevaneio.repository;

import br.com.tex.hoteldevaneio.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
}
