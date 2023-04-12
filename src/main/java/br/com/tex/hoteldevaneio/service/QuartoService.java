package br.com.tex.hoteldevaneio.service;

import br.com.tex.hoteldevaneio.model.Quarto;
import br.com.tex.hoteldevaneio.model.ServicoAdicional;
import br.com.tex.hoteldevaneio.model.dto.QuartoInputDTO;
import br.com.tex.hoteldevaneio.model.dto.QuartoOutputDTO;
import br.com.tex.hoteldevaneio.model.dto.ServicoAdicionalInputDTO;
import br.com.tex.hoteldevaneio.model.dto.ServicoAdicionalOutputDTO;

import java.util.List;
import java.util.Optional;

public interface QuartoService {

    QuartoOutputDTO cadastra(QuartoInputDTO quartoInputDTO);
    Optional<Quarto> buscarPor(Integer id);
    Quarto buscarReferenciaPor(Integer id);
    List<QuartoOutputDTO> lista();
    QuartoOutputDTO altera(Quarto quarto, QuartoInputDTO quartoInputDTO);
    void deleta(Quarto quarto);

}
