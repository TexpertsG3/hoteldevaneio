package br.com.tex.hoteldevaneio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "cadastroCargoBuilder")
@Entity
@Table(name = "cargo")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "descricao")
    private String descricao;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id")
    private Hotel hotelId;

    public Cargo.CargoBuilder builder(String nome, String descricao, Hotel hotelId) {
        return cadastroCargoBuilder()
                .nome(nome)
                .descricao(descricao)
                .hotelId(hotelId);
    }

}
