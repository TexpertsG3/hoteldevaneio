package br.com.tex.hoteldevaneio.service;

import br.com.tex.hoteldevaneio.model.Admin;
import br.com.tex.hoteldevaneio.model.dto.AdminInputDTO;
import br.com.tex.hoteldevaneio.model.dto.AdminOutputDTO;

import java.util.List;
import java.util.Optional;

public interface AdminService {

    AdminOutputDTO cadastra(AdminInputDTO adminInputDTO);
    Optional<Admin> buscarPor(Integer id);
    Admin buscarReferenciaPor(Integer id);
    List<AdminOutputDTO> lista();
    AdminOutputDTO altera(Admin admin, AdminInputDTO adminInputDTO);
    void deleta(Admin admin);

}
