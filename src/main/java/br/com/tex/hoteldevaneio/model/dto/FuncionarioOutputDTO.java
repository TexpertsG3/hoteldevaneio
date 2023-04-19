package br.com.tex.hoteldevaneio.model.dto;

import br.com.tex.hoteldevaneio.model.Funcionario;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FuncionarioOutputDTO {

    @Schema(description = "ID de cadastro do funcionário.", example = "1")
    private Integer id;
    @Schema(description = "Nome do funcionário.", example = "Jane")
    private String nome;
    @Schema(description = "Sobrenome do funcionário.", example = "Doe")
    private String sobrenome;
    @Schema(description = "CPF do funcionário.", example = "99988877766")
    private String cpf;
    @Schema(description = "ID do cargo do funcionário.", example = "1")
    private Integer cargoId;
    @Schema(description = "Nome do cargo do funcionário.", example = "Gerente")
    private String nomeCargo;
    @Schema(description = "Endereço de e-mail do funcionário.", example = "janedoe@hotel.com")
    private String email;
    @Schema(description = "Telefone fixo do funcionário.", example = "(99) 9999-9999")
    private String telefone;
    @Schema(description = "Celular do funcionário.", example = "(99) 99999-9999")
    private String celular;
    @Schema(description = "Rua onde o funcionário reside.", example = "Rua Indefinida")
    private String rua;
    @Schema(description = "Bairro onde está localizado o hotel.", example = "Jd. Indefinido")
    private String bairro;
    @Schema(description = "Número do endereço.", example = "99")
    private Integer numero;
    @Schema(description = "CEP da rua onde está localizado o endereço.", example = "99888000")
    private String cep;
    @Schema(description = "Cidade onde está localizado o endereço.", example = "São Paulo")
    private String cidade;
    @Schema(description = "UF onde está localizado o endereço.", example = "SP")
    private String uf;
    @Schema(description = "Complemento do endereço.", example = "Condomínio A1")
    private String complemento;
    @Schema(description = "Salário do funcionário.", example = "3000.00")
    private BigDecimal salario;
    @Schema(description = "ID do hotel onde o funcionário foi cadastrado.", example = "1")
    private Integer hotelId;
    @Schema(description = "Nome do hotel onde o funcionário foi cadastrado.", example = "Devaneio")
    private String nomeHotel;

    public FuncionarioOutputDTO(Funcionario funcionario) {
        this.id = funcionario.getId();
        this.nome = funcionario.getNome();
        this.sobrenome = funcionario.getSobrenome();
        this.cpf = funcionario.getCpf();
        this.cargoId = funcionario.getCargoId().getId();
        this.nomeCargo = funcionario.getCargoId().getNome();
        this.email = funcionario.getContatoId().getEmail();
        this.telefone = funcionario.getContatoId().getTelefone();
        this.celular = funcionario.getContatoId().getCelular();
        this.rua = funcionario.getEnderecoId().getRua();
        this.bairro = funcionario.getEnderecoId().getBairro();
        this.numero = funcionario.getEnderecoId().getNumero();
        this.cep = funcionario.getEnderecoId().getCep();
        this.cidade = funcionario.getEnderecoId().getCidade();
        this.uf = funcionario.getEnderecoId().getUf();
        this.complemento = funcionario.getEnderecoId().getComplemento();
        this.salario = funcionario.getSalario();
        this.hotelId = funcionario.getHotelId().getId();
        this.nomeHotel = funcionario.getHotelId().getDadosHotelId().getNome();
    }

}
