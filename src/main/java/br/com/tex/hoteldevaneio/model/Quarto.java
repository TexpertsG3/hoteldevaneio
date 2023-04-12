package br.com.tex.hoteldevaneio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "cadastroQuartoBuilder")
@Entity
@Table(name = "quarto")
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "preco")
    private BigDecimal preco;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id")
    private Hotel hotelId;

    public Quarto.QuartoBuilder builder(String nome, String descricao, BigDecimal preco,
                                          Hotel hotelId) {
        return cadastroQuartoBuilder()
                .nome(nome)
                .descricao(descricao)
                .preco(preco)
                .hotelId(hotelId);
    }

}
