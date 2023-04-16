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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminInputDTO {

    @Schema(description = "Nome do admin.", example = "John Doe")
    @NotBlank(message = "O campo nome deve ser preenchido. Não pode ser vazio ou nulo.")
    private String nome;
    @Schema(description = "Senha do admin.", example = "1234Abc")
    @NotBlank(message = "O campo senha deve ser preenchido. Não pode ser vazio ou nulo.")
    private String senha;
    @Schema(description = "E-mail do admin.", example = "admin@hotel.com")
    @NotBlank(message = "O campo email deve ser preenchido. Não pode ser vazio ou nulo.")
    @Email(message = "E-mail inválido.")
    private String email;
    @Schema(description = "Telefone do admin.", example = "(99) 9999-8888")
    @NotBlank(message = "O campo telefone deve ser preenchido. Não pode ser vazio ou nulo.")
    private String telefone;
    @Schema(description = "Celular do admin.", example = "(99) 99999-8888")
    @NotBlank(message = "O campo celular deve ser preenchido. Não pode ser vazio ou nulo.")
    private String celular;
    @NotBlank(message = "O campo rua deve ser preenchido. Não pode ser vazio ou nulo.")
    @Schema(description = "Rua do endereço do admin.", example = "Rua Indefinida")
    private String rua;
    @Schema(description = "Bairro do endereço do admin.", example = "Jd. Indefinido")
    @NotBlank(message = "O campo bairro deve ser preenchido. Não pode ser vazio ou nulo.")
    private String bairro;
    @Schema(description = "Número do endereço do admin.", example = "99")
    @NotNull(message = "O campo número deve ser preenchido. Não pode ser nulo.")
    @Min(value = 1, message = "Número inválido.")
    private Integer numero;
    @Schema(description = "CEP da rua endereço do admin.", example = "99888000")
    @NotBlank(message = "O campo cep deve ser preenchido. Não pode ser vazio ou nulo.")
    private String cep;
    @Schema(description = "Cidade do endereço do admin.", example = "São Paulo")
    @NotBlank(message = "O campo cidade deve ser preenchido. Não pode ser vazio ou nulo.")
    private String cidade;
    @Schema(description = "UF do endereço do admin.", example = "SP")
    @NotBlank(message = "O campo uf deve ser preenchido. Não pode ser vazio ou nulo.")
    private String uf;
    @Schema(description = "Complemento do endereço do admin.", example = "Condomínio A1")
    @NotBlank(message = "O campo complemento deve ser preenchido. Não pode ser vazio ou nulo.")
    private String complemento;
    @Schema(description = "ID do hotel onde será cadastrado o admin.", example = "1")
    @NotNull(message = "O campo hotelId deve ser preenchido. Não pode ser vazio ou nulo.")
    private Integer hotelId;
}
