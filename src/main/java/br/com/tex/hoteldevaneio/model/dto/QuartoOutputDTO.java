package br.com.tex.hoteldevaneio.model.dto;

import br.com.tex.hoteldevaneio.model.Quarto;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuartoOutputDTO {

    private Integer id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Integer hotelId;
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
