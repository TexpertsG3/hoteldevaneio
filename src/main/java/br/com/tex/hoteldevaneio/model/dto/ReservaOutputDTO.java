package br.com.tex.hoteldevaneio.model.dto;

import br.com.tex.hoteldevaneio.model.Hospede;
import br.com.tex.hoteldevaneio.model.Hotel;
import br.com.tex.hoteldevaneio.model.Quarto;
import br.com.tex.hoteldevaneio.model.Reserva;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReservaOutputDTO {

    private Integer id;
    private Hotel hotelId;
    private Quarto quartoId;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Hospede hospedeId;
    private Integer quantidadeAdultos;
    private Integer quantidadeCriancas;
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
