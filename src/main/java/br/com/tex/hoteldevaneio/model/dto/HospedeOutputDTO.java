package br.com.tex.hoteldevaneio.model.dto;

import br.com.tex.hoteldevaneio.model.Hospede;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HospedeOutputDTO {

    private Integer id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String senha;
    private String email;
    private String telefone;
    private String celular;
    private Integer hotelId;
    private String hotelNome;

    public HospedeOutputDTO(Hospede hospede) {
        this.id = hospede.getId();
        this.nome = hospede.getNome();
        this.sobrenome = hospede.getSobrenome();
        this.cpf = hospede.getCpf();
        this.senha = hospede.getSenha();
        this.email = hospede.getContatoId().getEmail();
        this.telefone = hospede.getContatoId().getTelefone();
        this.celular = hospede.getContatoId().getCelular();
        this.hotelId = hospede.getHotelId().getId();
        this.hotelNome = hospede.getHotelId().getDadosHotelId().getNome();
    }
}
