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
    private Integer hotelId;
    private Integer quartoId;
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate checkIn;
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate checkOut;
    private Integer hospedeId;
    private Integer quantidadeAdultos;
    private Integer quantidadeCriancas;
    private Set<Integer> servicosIds;
    private BigDecimal totalServicos;
    private BigDecimal totalReserva;

    public ReservaOutputDTO(Reserva reserva) {
        this.id = reserva.getId();
        this.hotelId = reserva.getHotelId().getId();
        this.quartoId = reserva.getQuartoId().getId();
        this.checkIn = reserva.getCheckIn();
        this.checkOut = reserva.getCheckOut();
        this.hospedeId = reserva.getHospedeId().getId();
        this.quantidadeAdultos = reserva.getQuantidadeAdultos();
        this.quantidadeCriancas = reserva.getQuantidadeCriancas();
        this.totalServicos = reserva.getTotalServicos();
        this.totalReserva = reserva.getTotalReserva();
    }
}
