package br.com.tex.hoteldevaneio.model.dto;

import br.com.tex.hoteldevaneio.model.Hospede;
import br.com.tex.hoteldevaneio.model.Hotel;
import br.com.tex.hoteldevaneio.model.Quarto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservaInputDTO {

    private Hotel hotelId;
    private Quarto quartoId;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Hospede hospedeId;
    private Integer quantidadeAdultos;
    private Integer quantidadeCriancas;
    private BigDecimal totalServicos;
    private BigDecimal totalReserva;
}
