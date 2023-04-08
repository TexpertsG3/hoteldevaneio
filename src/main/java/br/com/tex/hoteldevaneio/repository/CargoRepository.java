package br.com.tex.hoteldevaneio.repository;

import br.com.tex.hoteldevaneio.model.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Integer> {
}
