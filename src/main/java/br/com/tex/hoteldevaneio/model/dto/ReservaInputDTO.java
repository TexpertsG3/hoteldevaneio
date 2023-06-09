package br.com.tex.hoteldevaneio.model.dto;

import br.com.tex.hoteldevaneio.annotation.CheckData;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@CheckData
public class ReservaInputDTO {

    @Schema(description = "ID do hotel onde será cadastrada a reserva.", example = "1")
    @NotNull(message = "O campo hotelId deve ser preenchido. Não pode ser vazio ou nulo.")
    private Integer hotelId;
    @Schema(description = "ID do quarto onde será cadastrada a reserva.", example = "1")
    @NotNull(message = "O campo quartoId deve ser preenchido. Não pode ser vazio ou nulo.")
    private Integer quartoId;
    @Schema(description = "Data de check-in da reserva.", pattern = "dd/MM/yyyy" , example = "16/12/2025")
    @NotNull(message = "O campo checkIn deve ser preenchido. Não pode ser vazio ou nulo.")
    private String checkIn;
    @Schema(description = "Data de check-out da reserva.", pattern = "dd/MM/yyyy" , example = "17/12/2025")
    @NotNull(message = "O campo checkOut deve ser preenchido. Não pode ser vazio ou nulo.")
    private String checkOut;
    @Schema(description = "ID do hospede para quem será cadastrada a reserva.", example = "1")
    @NotNull(message = "O campo hospedeId deve ser preenchido. Não pode ser vazio ou nulo.")
    private Integer hospedeId;
    @Schema(description = "Quantidade de adultos.", example = "1")
    @NotNull(message = "O campo quantidadeAdultos deve ser preenchido. Não pode ser vazio ou nulo.")
    @Min(1)
    private Integer quantidadeAdultos;
    @Schema(description = "Quantidade de crianças.", example = "1")
    @NotNull(message = "O campo quantidadeCriancas deve ser preenchido. Não pode ser vazio ou nulo.")
    @Min(0)
    private Integer quantidadeCriancas;
    @Schema(description = "IDs dos serviços adicionais que serão cadastrados na reserva.", example = "[1,3,5]")
    @NotNull(message = "O campo servicosIds deve ser preenchido. Não pode ser vazio ou nulo.")
    private Set<Integer> servicosIds;
}
