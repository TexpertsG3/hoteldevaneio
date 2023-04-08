package br.com.tex.hoteldevaneio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "sobrenome")
    private String sobrenome;
    @Column(name = "cpf")
    private String cpf;
    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargoId;
    @OneToOne
    @JoinColumn(name = "contato_id")
    private Contato contatoId;
    @OneToOne
    @JoinColumn(name = "endereco_id")
    private Endereco enderecoId;
    private BigDecimal salario;
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotelId;

}
