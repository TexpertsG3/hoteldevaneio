package br.com.tex.hoteldevaneio.model.dto;

import br.com.tex.hoteldevaneio.model.Quarto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuartoOutputDTO {

    @Schema(description = "ID de cadastro do admin", example = "1")
    private Integer id;
    @Schema(description = "Nome do quarto.", example = "Quarto Java Deluxe")
    private String nome;
    @Schema(description = "Descrição do quarto.", example = "Quarto de luxo para casal, com 1 cama king size e 2 camas solteiro.")
    private String descricao;
    @Schema(description = "Preço do quarto.", example = "50.00")
    private BigDecimal preco;
    @Schema(description = "ID do hotel onde foi cadastrado o quarto.", example = "1")
    private Integer hotelId;
    @Schema(description = "Nome do hotel onde foi cadastrado o admin.", example = "Devaneio")
    private String hotelNome;

    public QuartoOutputDTO(Quarto quarto) {
        this.id = quarto.getId();
        this.nome = quarto.getNome();
        this.descricao = quarto.getDescricao();
        this.preco = quarto.getPreco();
        this.hotelId = quarto.getHotelId().getId();
        this.hotelNome = quarto.getHotelId().getDadosHotelId().getNome();
    }
}
