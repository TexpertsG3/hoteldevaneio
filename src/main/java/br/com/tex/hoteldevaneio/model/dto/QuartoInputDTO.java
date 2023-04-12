package br.com.tex.hoteldevaneio.model.dto;

import br.com.tex.hoteldevaneio.model.Hotel;
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

    @NotBlank(message = "O campo nome deve ser preenchido. Não pode ser vazio ou nulo.")
    private String nome;
    @NotBlank(message = "O campo descricao deve ser preenchido. Não pode ser vazio ou nulo.")
    private String descricao;
    @DecimalMin(value = "50.0", message = "Preço mínimo de R$50.00")
    private BigDecimal preco;
    @NotNull(message = "O campo hotel deve ser preenchido. Não pode ser vazio ou nulo.")
    private Hotel hotelId;
}
