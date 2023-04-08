package br.com.tex.hoteldevaneio.repository;

import br.com.tex.hoteldevaneio.model.Hospede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospedeRepository extends JpaRepository<Hospede, Integer> {
}
