package br.com.tex.hoteldevaneio.repository;

import br.com.tex.hoteldevaneio.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Integer> {
}
