package br.com.tex.hoteldevaneio.model.dto;

import br.com.tex.hoteldevaneio.model.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReservaOutputDTO {

    private Integer id;
    private Hotel hotelId;
    private Quarto quartoId;
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate checkIn;
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate checkOut;
    private Hospede hospedeId;
    private Integer quantidadeAdultos;
    private Integer quantidadeCriancas;
    private Set<ServicoAdicional> servicos;
    private BigDecimal totalServicos;
    private BigDecimal totalReserva;

    public ReservaOutputDTO(Reserva reserva) {
        this.id = reserva.getId();
        this.hotelId = reserva.getHotelId();
        this.quartoId = reserva.getQuartoId();
        this.checkIn = reserva.getCheckIn();
        this.checkOut = reserva.getCheckOut();
        this.hospedeId = reserva.getHospedeId();
        this.quantidadeAdultos = reserva.getQuantidadeAdultos();
        this.quantidadeCriancas = reserva.getQuantidadeCriancas();
        this.totalServicos = reserva.getTotalServicos();
        this.totalReserva = reserva.getTotalReserva();
    }
}
