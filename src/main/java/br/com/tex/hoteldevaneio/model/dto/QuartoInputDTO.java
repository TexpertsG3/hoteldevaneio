package br.com.tex.hoteldevaneio.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuartoInputDTO {

    @Schema(description = "Nome do quarto.", example = "Quarto Java Deluxe")
    @NotBlank(message = "O campo nome deve ser preenchido. Não pode ser vazio ou nulo.")
    private String nome;
    @Schema(description = "Descrição do quarto.", example = "Quarto de luxo para casal, com 1 cama king size e 2 camas solteiro.")
    @NotBlank(message = "O campo descricao deve ser preenchido. Não pode ser vazio ou nulo.")
    private String descricao;
    @Schema(description = "Preço do quarto.", example = "50.00")
    @DecimalMin(value = "50.0", message = "Preço mínimo de R$50.00")
    private BigDecimal preco;
    @Schema(description = "ID do hotel onde será cadastrado o quarto.", example = "1")
    @NotNull(message = "O campo hotel deve ser preenchido. Não pode ser vazio ou nulo.")
    private Integer hotelId;
}
