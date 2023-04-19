package br.com.tex.hoteldevaneio.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CargoInputDTO {

    @Schema(description = "Nome do cargo.", example = "Gerente")
    @NotBlank(message = "O campo nome deve ser preenchido. Não pode ser vazio ou nulo.")
    private String nome;
    @Schema(description = "Descrição do cargo.", example = "Responsável pela gerência geral do hotel.")
    @NotBlank(message = "O campo descricao deve ser preenchido. Não pode ser vazio ou nulo.")
    private String descricao;
    @Schema(description = "ID do hotel onde será cadastrado o cargo.", example = "1")
    @NotNull(message = "O hotelId nome deve ser preenchido. Não pode ser vazio ou nulo.")
    @Min(1)
    private Integer hotelId;

}
