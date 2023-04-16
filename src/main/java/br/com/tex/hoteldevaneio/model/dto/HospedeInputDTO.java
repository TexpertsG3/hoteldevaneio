package br.com.tex.hoteldevaneio.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
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
public class HospedeInputDTO {

    @Schema(description = "Nome do hospede.", example = "John")
    @NotBlank(message = "O campo nome deve ser preenchido. Não pode ser vazio ou nulo.")
    private String nome;
    @Schema(description = "Sobrenome do hospede.", example = "Doe")
    @NotBlank(message = "O campo sobrenome deve ser preenchido. Não pode ser vazio ou nulo.")
    private String sobrenome;
    @Schema(description = "CPF do hospede.", example = "99988877766")
    @NotBlank(message = "O campo cpf deve ser preenchido. Não pode ser vazio ou nulo. Não é permitido cpf duplicado.")
    private String cpf;
    @Schema(description = "Senha do hospede.", example = "1234Abc")
    @NotBlank(message = "O campo senha deve ser preenchido. Não pode ser vazio ou nulo.")
    private String senha;
    @Schema(description = "E-mail do hospede.", example = "johndoe@hotel.com")
    @NotBlank(message = "O campo email deve ser preenchido. Não pode ser vazio ou nulo.")
    @Email(message = "E-mail inválido.")
    private String email;
    @Schema(description = "Telefone do hospede.", example = "(99) 9999-9999")
    @NotBlank(message = "O campo telefone deve ser preenchido. Não pode ser vazio ou nulo.")
    private String telefone;
    @Schema(description = "Celular do hospede.", example = "(99) 99999-9999")
    @NotBlank(message = "O campo celular deve ser preenchido. Não pode ser vazio ou nulo.")
    private String celular;
    @Schema(description = "ID do hotel onde será cadastrado o hospede.", example = "1")
    @NotNull(message = "O campo hotelId deve ser preenchido. Não pode ser vazio ou nulo.")
    private Integer hotelId;
}
