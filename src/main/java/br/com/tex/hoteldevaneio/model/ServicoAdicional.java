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
@Builder(builderMethodName = "cadastroServicoAdicionalBuilder")
@Entity
@Table(name = "servico_adicional")
public class ServicoAdicional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "preco")
    private BigDecimal preco;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id")
    private Hotel hotelId;

    public ServicoAdicional.ServicoAdicionalBuilder builder(String nome, BigDecimal preco, Hotel hotelId) {

        return cadastroServicoAdicionalBuilder()
                .nome(nome)
                .preco(preco)
                .hotelId(hotelId);
    }

}
