package br.com.tex.hoteldevaneio.model.dto;

import br.com.tex.hoteldevaneio.model.Reserva;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReservaOutputDTO {

    @JsonIgnore
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Schema(description = "ID de cadastro da reserva", example = "1")
    private Integer id;
    @Schema(description = "ID do hotel onde foi cadastrada a reserva.", example = "1")
    private Integer hotelId;
    @Schema(description = "Nome do hotel onde foi cadastrada a reserva.", example = "Devaneio")
    private String nomeHotel;
    @Schema(description = "ID do quarto onde foi cadastrada a reserva.", example = "1")
    private Integer quartoId;
    @Schema(description = "Nome do quarto onde foi cadastrada a reserva.", example = "Java Deluxe")
    private String nomeQuarto;
    @Schema(description = "Data de check-in da reserva.", pattern = "dd/MM/yyyy" , example = "16/12/2025")
    private String checkIn;
    @Schema(description = "Data de check-out da reserva.", pattern = "dd/MM/yyyy" , example = "17/12/2025")
    private String checkOut;
    @Schema(description = "ID do hospede para quem foi cadastrada a reserva.", example = "1")
    private Integer hospedeId;
    @Schema(description = "Nome do hospede para quem foi cadastrada a reserva.", example = "John Doe")
    private String nomeHospede;
    @Schema(description = "Quantidade de adultos.", example = "1")
    private Integer quantidadeAdultos;
    @Schema(description = "Quantidade de crianças.", example = "1")
    private Integer quantidadeCriancas;
    @Schema(description = "Nomes e preços dos serviços adicionais que foram cadastrados na reserva.", example = "[Passeio nas dunas 200.00, Passeio na cidade 100.00, Passeio Genérico, 50.00]")
    private Set<String> servicos;
    @Schema(description = "Total dos serviços adicionais que foram cadastrados na reserva.", example = "500.00")
    private BigDecimal totalServicos;
    @Schema(description = "Total da reserva que foi cadastrada.", example = "1200.00")
    private BigDecimal totalReserva;

    public ReservaOutputDTO(Reserva reserva) {
        this.id = reserva.getId();
        this.hotelId = reserva.getHotelId().getId();
        this.nomeHotel = reserva.getHotelId().getDadosHotelId().getNome();
        this.quartoId = reserva.getQuartoId().getId();
        this.nomeQuarto = reserva.getQuartoId().getNome();
        this.checkIn = (reserva.getCheckIn()).format(formatter);
        this.checkOut = (reserva.getCheckOut()).format(formatter);
        this.hospedeId = reserva.getHospedeId().getId();
        this.nomeHospede = reserva.getHospedeId().getNome() + " " + reserva.getHospedeId().getSobrenome();
        this.quantidadeAdultos = reserva.getQuantidadeAdultos();
        this.quantidadeCriancas = reserva.getQuantidadeCriancas();
        this.servicos = reserva.getServicos().stream().map(s -> s.getNome() + " " + s.getPreco()).collect(Collectors.toSet());
        this.totalServicos = reserva.getTotalServicos();
        this.totalReserva = reserva.getTotalReserva();
    }
}
