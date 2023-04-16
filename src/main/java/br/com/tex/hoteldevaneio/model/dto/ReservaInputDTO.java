package br.com.tex.hoteldevaneio.model.dto;

import br.com.tex.hoteldevaneio.annotation.CheckData;
import br.com.tex.hoteldevaneio.model.Hospede;
import br.com.tex.hoteldevaneio.model.Hotel;
import br.com.tex.hoteldevaneio.model.Quarto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@CheckData
public class ReservaInputDTO {

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
}
