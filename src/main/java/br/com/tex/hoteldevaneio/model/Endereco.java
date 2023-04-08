package br.com.tex.hoteldevaneio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "cadastraEnderecoBuilder")
@Entity
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "rua")
    private String rua;
    @Column(name = "bairro")
    private String bairro;
    @Column(name = "numero")
    private Integer numero;
    @Column(name = "cep")
    private String cep;
    @Column(name = "cidade")
    private String cidade;
    @Column(name = "uf")
    private String uf;
    @Column(name = "complemento")
    private String complemento;

    public EnderecoBuilder builder(String rua, String bairro, Integer numero, String cep,
                                   String cidade, String uf, String complemento) {
        return cadastraEnderecoBuilder()
                .rua(rua)
                .bairro(bairro)
                .numero(numero)
                .cep(cep)
                .cidade(cidade)
                .uf(uf)
                .complemento(complemento);
    }

}
