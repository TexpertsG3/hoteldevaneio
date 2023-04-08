package br.com.tex.hoteldevaneio.repository;

import br.com.tex.hoteldevaneio.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
}
