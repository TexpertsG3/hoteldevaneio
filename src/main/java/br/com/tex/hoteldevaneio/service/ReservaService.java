package br.com.tex.hoteldevaneio.service;

import br.com.tex.hoteldevaneio.model.Reserva;
import br.com.tex.hoteldevaneio.model.ServicoAdicional;
import br.com.tex.hoteldevaneio.model.dto.ReservaInputDTO;
import br.com.tex.hoteldevaneio.model.dto.ReservaOutputDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ReservaService {

    ReservaOutputDTO cadastra(ReservaInputDTO reservaInputDTO);
    Optional<Reserva> buscarPor(Integer id);
    Reserva buscarReferenciaPor(Integer id);
    List<ReservaOutputDTO> lista();
    ReservaOutputDTO altera(Reserva reserva, ReservaInputDTO reservaInputDTO);
    void deleta(Reserva reserva);
    BigDecimal calculaValorDiaria(Reserva reserva);
    BigDecimal calculaTotalReserva(Set<ServicoAdicional> servicoAdicional, Reserva reserva);

}
