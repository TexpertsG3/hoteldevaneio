package br.com.tex.hoteldevaneio.model.dto;

import br.com.tex.hoteldevaneio.model.Admin;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdminOutputDTO {

    private Integer id;
    private String nome;
    private String senha;
    private String email;
    private String telefone;
    private String celular;
    private String rua;
    private String bairro;
    private Integer numero;
    private String cep;
    private String cidade;
    private String uf;
    private String complemento;
    private Integer hotelId;
    private String hotelNome;

    public AdminOutputDTO(Admin admin) {
        this.id = admin.getId();
        this.nome = admin.getNome();
        this.senha = admin.getSenha();
        this.email = admin.getContatoId().getEmail();
        this.telefone = admin.getContatoId().getTelefone();
        this.celular = admin.getContatoId().getCelular();
        this.rua = admin.getEnderecoId().getRua();
        this.bairro = admin.getEnderecoId().getBairro();
        this.numero = admin.getEnderecoId().getNumero();
        this.cep = admin.getEnderecoId().getCep();
        this.cidade = admin.getEnderecoId().getCidade();
        this.uf = admin.getEnderecoId().getUf();
        this.complemento = admin.getEnderecoId().getComplemento();
        this.hotelId = admin.getHotelId().getId();
        this.hotelNome = admin.getHotelId().getDadosHotelId().getNome();
    }
}
