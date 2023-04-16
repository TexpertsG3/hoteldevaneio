package br.com.tex.hoteldevaneio.model.dto;

import br.com.tex.hoteldevaneio.model.Hospede;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HospedeOutputDTO {

    @Schema(description = "ID de cadastro do hospede", example = "1")
    private Integer id;
    @Schema(description = "Nome do hospede.", example = "John")
    private String nome;
    @Schema(description = "Sobrenome do hospede.", example = "Doe")
    private String sobrenome;
    @Schema(description = "CPF do hospede.", example = "99988877766")
    private String cpf;
    @Schema(description = "Senha do hospede.", example = "1234Abc")
    private String senha;
    @Schema(description = "E-mail do hospede.", example = "johndoe@hotel.com")
    private String email;
    @Schema(description = "Telefone do hospede.", example = "(99) 9999-9999")
    private String telefone;
    @Schema(description = "Celular do hospede.", example = "(99) 99999-9999")
    private String celular;
    @Schema(description = "ID do hotel onde foi cadastrado o hospede.", example = "1")
    private Integer hotelId;
    @Schema(description = "Nome do hotel onde foi cadastrado o admin.", example = "Devaneio")
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
