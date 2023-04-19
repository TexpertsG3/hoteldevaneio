package br.com.tex.hoteldevaneio.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioInputDTO {

    @Schema(description = "Nome do funcionário.", example = "Jane")
    @NotBlank(message = "O campo nome deve ser preenchido. Não pode ser vazio ou nulo.")
    private String nome;
    @Schema(description = "Sobrenome do funcionário.", example = "Doe")
    @NotBlank(message = "O campo sobrenome deve ser preenchido. Não pode ser vazio ou nulo.")
    private String sobrenome;
    @Schema(description = "CPF do funcionário.", example = "99988877766")
    @NotBlank(message = "O campo cpf deve ser preenchido. Não pode ser vazio ou nulo.")
    private String cpf;
    @Schema(description = "ID do cargo do funcionário.", example = "1")
    @NotNull(message = "O campo cargoId deve ser preenchido. Não pode ser vazio ou nulo.")
    private Integer cargoId;
    @Schema(description = "Endereço de e-mail do funcionário.", example = "janedoe@hotel.com")
    @Email(message = "E-mail inválido.")
    @NotBlank(message = "O campo email deve ser preenchido. Não pode ser vazio ou nulo.")
    private String email;
    @Schema(description = "Telefone fixo do funcionário.", example = "(99) 9999-9999")
    @NotBlank(message = "O campo telefone deve ser preenchido. Não pode ser vazio ou nulo.")
    private String telefone;
    @Schema(description = "Celular do funcionário.", example = "(99) 99999-9999")
    @NotBlank(message = "O campo celular deve ser preenchido. Não pode ser vazio ou nulo.")
    private String celular;
    @Schema(description = "Rua onde o funcionário reside.", example = "Rua Indefinida")
    @NotBlank(message = "O campo rua deve ser preenchido. Não pode ser vazio ou nulo.")
    private String rua;
    @Schema(description = "Bairro onde está localizado o hotel.", example = "Jd. Indefinido")
    @NotBlank(message = "O campo bairro deve ser preenchido. Não pode ser vazio ou nulo.")
    private String bairro;
    @Schema(description = "Número do endereço.", example = "99")
    @NotNull(message = "O campo número deve ser preenchido. Não pode ser nulo.")
    @Min(value = 1, message = "Número inválido.")
    private Integer numero;
    @Schema(description = "CEP da rua onde está localizado o endereço.", example = "99888000")
    @NotBlank(message = "O campo cep deve ser preenchido. Não pode ser vazio ou nulo.")
    private String cep;
    @Schema(description = "Cidade onde está localizado o endereço.", example = "São Paulo")
    @NotBlank(message = "O campo cidade deve ser preenchido. Não pode ser vazio ou nulo.")
    private String cidade;
    @Schema(description = "UF onde está localizado o endereço.", example = "SP")
    @NotBlank(message = "O campo uf deve ser preenchido. Não pode ser vazio ou nulo.")
    private String uf;
    @Schema(description = "Complemento do endereço.", example = "Condomínio A1")
    @NotBlank(message = "O campo complemento deve ser preenchido. Não pode ser vazio ou nulo.")
    private String complemento;
    @Schema(description = "Salário do funcionário.", example = "3000.00")
    @NotNull(message = "O campo salario deve ser preenchido. Não pode ser vazio ou nulo.")
    private BigDecimal salario;
    @Schema(description = "ID do hotel onde o funcionário será cadastrado.", example = "1")
    @NotNull(message = "O campo hotelId deve ser preenchido. Não pode ser vazio ou nulo.")
    private Integer hotelId;

}
