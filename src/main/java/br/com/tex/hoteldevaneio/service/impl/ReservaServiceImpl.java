package br.com.tex.hoteldevaneio.service.impl;

import br.com.tex.hoteldevaneio.model.*;
import br.com.tex.hoteldevaneio.model.dto.HospedeOutputDTO;
import br.com.tex.hoteldevaneio.model.dto.ReservaInputDTO;
import br.com.tex.hoteldevaneio.model.dto.ReservaOutputDTO;
import br.com.tex.hoteldevaneio.repository.ReservaRepository;
import br.com.tex.hoteldevaneio.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private HotelServiceImpl hotelService;

    @Autowired
    private QuartoServiceImpl quartoService;

    @Autowired
    private HospedeServiceImpl hospedeService;

    @Autowired
    private ServicoAdicionalServiceImpl servicoAdicionalService;

    @Override
    @Transactional
    public ReservaOutputDTO cadastra(ReservaInputDTO reservaInputDTO) {
        Hotel hotel = hotelService.buscarPor(reservaInputDTO.getHotelId()).get();
        Quarto quarto = quartoService.buscarPor(reservaInputDTO.getQuartoId()).get();
        Hospede hospede = hospedeService.buscarPor(reservaInputDTO.getHospedeId()).get();

        Set<ServicoAdicional> servicos = servicoAdicionalService.buscarServicosPorIds(reservaInputDTO.getServicosIds());

        BigDecimal valorTotalServicos = servicoAdicionalService.somaServicos(servicos);

        Reserva reservaTemp = new Reserva(quarto, reservaInputDTO.getCheckIn(), reservaInputDTO.getCheckOut(),
                reservaInputDTO.getQuantidadeAdultos(), reservaInputDTO.getQuantidadeCriancas());

        BigDecimal valorTotalReserva = calculaTotalReserva(servicos, reservaTemp);

        Reserva reserva = Reserva.cadastroReservaBuilder()
                .hotelId(hotel)
                .quartoId(quarto)
                .checkIn(reservaInputDTO.getCheckIn())
                .checkOut(reservaInputDTO.getCheckOut())
                .hospedeId(hospede)
                .quantidadeAdultos(reservaInputDTO.getQuantidadeAdultos())
                .quantidadeCriancas(reservaInputDTO.getQuantidadeCriancas())
                .servicos(servicos)
                .totalServicos(valorTotalServicos)
                .totalReserva(valorTotalReserva)
                .build();
        Reserva reservaSalva = reservaRepository.save(reserva);

        return new ReservaOutputDTO(reservaSalva);
    }

    @Override
    public Optional<Reserva> buscarPor(Integer id) {
        return reservaRepository.findById(id);
    }

    @Override
    public Reserva buscarReferenciaPor(Integer id) {
        return reservaRepository.getReferenceById(id);
    }

    @Override
    public List<ReservaOutputDTO> lista() {
        List<Reserva> listaReservas = reservaRepository.findAll();
        return listaReservas.stream().map(ReservaOutputDTO::new).toList();
    }

    @Override
    public ReservaOutputDTO altera(Reserva reserva, ReservaInputDTO reservaInputDTO) {
        return null;
    }

    @Override
    public void deleta(Reserva reserva) {
        reservaRepository.deleteById(reserva.getId());
    }

    public BigDecimal calculaValorDiaria(Reserva reserva) {
        BigDecimal totalDiaria = BigDecimal.ZERO;
        Integer totalAdultos = reserva.getQuantidadeAdultos();
        if (totalAdultos > 2) {
            BigDecimal temp = new BigDecimal(totalAdultos-2).multiply(BigDecimal.valueOf(10.00));
            totalDiaria = totalDiaria.add(temp);
        }

        Integer dias = Integer.valueOf(reserva.getCheckOut().getDayOfYear() - reserva.getCheckIn().getDayOfYear());

        totalDiaria = totalDiaria.add(reserva.getQuartoId().getPreco());
        totalDiaria = totalDiaria.multiply(new BigDecimal(dias));

        return totalDiaria;
    }

    public BigDecimal calculaTotalReserva(Set<ServicoAdicional> servicoAdicional, Reserva reserva) {
        BigDecimal valorTotalDiaria = calculaValorDiaria(reserva);
        BigDecimal valorTotalServicos = servicoAdicionalService.somaServicos(servicoAdicional);

        BigDecimal total = BigDecimal.ZERO;
        total = total.add(valorTotalDiaria).add(valorTotalServicos);

        return total;
    }
}
