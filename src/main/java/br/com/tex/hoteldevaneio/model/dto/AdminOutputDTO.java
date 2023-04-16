package br.com.tex.hoteldevaneio.model.dto;

import br.com.tex.hoteldevaneio.model.Admin;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdminOutputDTO {

    @Schema(description = "ID de cadastro do admin", example = "1")
    private Integer id;
    @Schema(description = "Nome do admin.", example = "John Doe")
    private String nome;
    @Schema(description = "Senha do admin.", example = "1234Abc")
    private String senha;
    @Schema(description = "E-mail do admin.", example = "admin@hotel.com")
    private String email;
    @Schema(description = "Telefone do admin.", example = "(99) 9999-8888")
    private String telefone;
    @Schema(description = "Celular do admin.", example = "(99) 99999-8888")
    private String celular;
    @Schema(description = "Rua do endereço do admin.", example = "Rua Indefinida")
    private String rua;
    @Schema(description = "Bairro do endereço do admin.", example = "Jd. Indefinido")
    private String bairro;
    @Schema(description = "Número do endereço do admin.", example = "99")
    private Integer numero;
    @Schema(description = "CEP da rua endereço do admin.", example = "99888000")
    private String cep;
    @Schema(description = "Cidade do endereço do admin.", example = "São Paulo")
    private String cidade;
    @Schema(description = "UF do endereço do admin.", example = "SP")
    private String uf;
    @Schema(description = "Complemento do endereço do admin.", example = "Condomínio A1")
    private String complemento;
    @Schema(description = "ID do hotel onde foi cadastrado o admin.", example = "1")
    private Integer hotelId;
    @Schema(description = "Nome do hotel onde foi cadastrado o admin.", example = "Devaneio")
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
