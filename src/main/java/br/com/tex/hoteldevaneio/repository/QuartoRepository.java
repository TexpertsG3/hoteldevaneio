package br.com.tex.hoteldevaneio.repository;

import br.com.tex.hoteldevaneio.model.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuartoRepository extends JpaRepository<Quarto, Integer> {
}
