package br.com.tex.hoteldevaneio.model.dto;

import br.com.tex.hoteldevaneio.model.Reserva;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "ID de cadastro da reserva", example = "1")
    private Integer id;
    @Schema(description = "ID do hotel onde será cadastrada a reserva.", example = "1")
    private Integer hotelId;
    @Schema(description = "ID do quarto onde será cadastrada a reserva.", example = "1")
    private Integer quartoId;
    @Schema(description = "Data de check-in da reserva.", example = "16/12/2025")
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate checkIn;
    @Schema(description = "Data de check-out da reserva.", example = "17/12/2025")
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate checkOut;
    @Schema(description = "ID do hospede para quem será cadastrada a reserva.", example = "1")
    private Integer hospedeId;
    @Schema(description = "Quantidade de adultos.", example = "1")
    private Integer quantidadeAdultos;
    @Schema(description = "Quantidade de crianças.", example = "1")
    private Integer quantidadeCriancas;
    @Schema(description = "IDs dos serviços adicionais que foram cadastrados na reserva.", example = "1,2,3,4")
    private Set<Integer> servicosIds;
    @Schema(description = "Total dos serviços adicionais que foram cadastrados na reserva.", example = "500.00")
    private BigDecimal totalServicos;
    @Schema(description = "Total da reserva que foi cadastrada.", example = "1200.00")
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
