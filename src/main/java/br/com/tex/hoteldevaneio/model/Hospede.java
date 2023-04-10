package br.com.tex.hoteldevaneio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "cadastroHospedeBuilder")
@Entity
@Table(name = "hospede")
public class Hospede {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "sobrenome")
    private String sobrenome;
    @Column(name = "cpf", unique = true)
    private String cpf;
    @Column(name = "senha")
    private String senha;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "contato_id")
    private Contato contatoId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id")
    private Hotel hotelId;

    public Hospede.HospedeBuilder builder(String nome, String sobrenome, String cpf,
                                          String senha, Contato contatoId, Hotel hotelId) {
        return cadastroHospedeBuilder()
                .nome(nome)
                .sobrenome(sobrenome)
                .cpf(cpf)
                .senha(senha)
                .contatoId(contatoId)
                .hotelId(hotelId);
    }

}
