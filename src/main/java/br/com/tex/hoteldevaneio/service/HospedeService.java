package br.com.tex.hoteldevaneio.service;

import br.com.tex.hoteldevaneio.model.Hospede;
import br.com.tex.hoteldevaneio.model.dto.HospedeInputDTO;
import br.com.tex.hoteldevaneio.model.dto.HospedeOutputDTO;

import java.util.List;
import java.util.Optional;

public interface HospedeService {

    HospedeOutputDTO cadastra(HospedeInputDTO hospedeInputDTO);
    Optional<Hospede> buscarPor(Integer id);
    Hospede buscarReferenciaPor(Integer id);
    List<HospedeOutputDTO> lista();
    HospedeOutputDTO altera(Hospede hospede, HospedeInputDTO hospedeInputDTO);
    void deleta(Hospede hospede);

}
