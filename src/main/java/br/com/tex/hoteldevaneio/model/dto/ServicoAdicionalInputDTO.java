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
public class ServicoAdicionalInputDTO {

    @Schema(description = "Nome do serviço adicional.", example = "Passeio nas dunas")
    @NotBlank(message = "O campo nome deve ser preenchido. Não pode ser vazio ou nulo.")
    private String nome;
    @Schema(description = "Preço do serviço adicional.", example = "200.00")
    @DecimalMin(value = "50.0", message = "Preço mínimo de R$50.00")
    private BigDecimal preco;
    @Schema(description = "ID do hotel onde será cadastrado o serviço adicional.", example = "1")
    @NotNull(message = "O campo hotelId deve ser preenchido. Não pode ser vazio ou nulo.")
    private Integer hotelId;

}
