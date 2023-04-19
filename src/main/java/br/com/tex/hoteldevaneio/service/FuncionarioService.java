package br.com.tex.hoteldevaneio.service;

import br.com.tex.hoteldevaneio.model.Funcionario;
import br.com.tex.hoteldevaneio.model.dto.FuncionarioInputDTO;
import br.com.tex.hoteldevaneio.model.dto.FuncionarioOutputDTO;

import java.util.List;
import java.util.Optional;

public interface FuncionarioService {

    FuncionarioOutputDTO cadastra(FuncionarioInputDTO funcionarioInputDTO);
    Optional<Funcionario> buscarPor(Integer id);
    Funcionario buscarReferenciaPor(Integer id);
    List<FuncionarioOutputDTO> lista();
    FuncionarioOutputDTO altera(Funcionario funcionario, FuncionarioInputDTO funcionarioInputDTO);
    void deleta(Funcionario funcionario);

}
