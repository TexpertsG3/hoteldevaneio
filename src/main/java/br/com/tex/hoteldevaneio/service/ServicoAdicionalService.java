package br.com.tex.hoteldevaneio.service;

import br.com.tex.hoteldevaneio.model.ServicoAdicional;
import br.com.tex.hoteldevaneio.model.dto.ServicoAdicionalInputDTO;
import br.com.tex.hoteldevaneio.model.dto.ServicoAdicionalOutputDTO;

import java.util.List;
import java.util.Optional;

public interface ServicoAdicionalService {

    ServicoAdicionalOutputDTO cadastra(ServicoAdicionalInputDTO servicoAdicionalInputDTO);
    Optional<ServicoAdicional> buscarPor(Integer id);
    ServicoAdicional buscarReferenciaPor(Integer id);
    List<ServicoAdicionalOutputDTO> lista();
    ServicoAdicionalOutputDTO altera(ServicoAdicional servicoAdicional, ServicoAdicionalInputDTO servicoAdicionalInputDTO);
    void deleta(ServicoAdicional servicoAdicional);

}
