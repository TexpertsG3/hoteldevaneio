package br.com.tex.hoteldevaneio.service;

import br.com.tex.hoteldevaneio.model.ServicoAdicional;
import br.com.tex.hoteldevaneio.model.dto.ServicoAdicionalInputDTO;
import br.com.tex.hoteldevaneio.model.dto.ServicoAdicionalOutputDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ServicoAdicionalService {

    ServicoAdicionalOutputDTO cadastra(ServicoAdicionalInputDTO servicoAdicionalInputDTO);
    Optional<ServicoAdicional> buscarPor(Integer id);
    ServicoAdicional buscarReferenciaPor(Integer id);
    List<ServicoAdicionalOutputDTO> lista();
    ServicoAdicionalOutputDTO altera(ServicoAdicional servicoAdicional, ServicoAdicionalInputDTO servicoAdicionalInputDTO);
    void deleta(ServicoAdicional servicoAdicional);
    Set<ServicoAdicional> buscarServicosPorIds(Set<Integer> ids);
    BigDecimal somaServicos(Set<ServicoAdicional> servicos);

}
