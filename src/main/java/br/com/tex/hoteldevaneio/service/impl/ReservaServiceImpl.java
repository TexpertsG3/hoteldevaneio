package br.com.tex.hoteldevaneio.service.impl;

import br.com.tex.hoteldevaneio.model.Reserva;
import br.com.tex.hoteldevaneio.model.dto.ReservaInputDTO;
import br.com.tex.hoteldevaneio.model.dto.ReservaOutputDTO;
import br.com.tex.hoteldevaneio.service.ReservaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaServiceImpl implements ReservaService {
    @Override
    public ReservaOutputDTO cadastra(ReservaInputDTO reservaInputDTO) {
        return null;
    }

    @Override
    public Optional<Reserva> buscarPor(Integer id) {
        return Optional.empty();
    }

    @Override
    public Reserva buscarReferenciaPor(Integer id) {
        return null;
    }

    @Override
    public List<ReservaOutputDTO> lista() {
        return null;
    }

    @Override
    public ReservaOutputDTO altera(Reserva reserva, ReservaInputDTO reservaInputDTO) {
        return null;
    }

    @Override
    public void deleta(Reserva reserva) {

    }
}
