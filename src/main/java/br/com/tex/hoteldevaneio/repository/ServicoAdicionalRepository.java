package br.com.tex.hoteldevaneio.repository;

import br.com.tex.hoteldevaneio.model.ServicoAdicional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoAdicionalRepository extends JpaRepository<ServicoAdicional, Integer> {
}
