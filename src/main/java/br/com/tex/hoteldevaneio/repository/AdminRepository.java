package br.com.tex.hoteldevaneio.repository;

import br.com.tex.hoteldevaneio.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
}
