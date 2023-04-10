package br.com.tex.hoteldevaneio.model.dto;

import br.com.tex.hoteldevaneio.model.Hotel;
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

    @NotBlank(message = "O campo nome deve ser preenchido. Não pode ser vazio ou nulo.")
    private String nome;
    @NotBlank(message = "O campo sobrenome deve ser preenchido. Não pode ser vazio ou nulo.")
    private String sobrenome;
    @NotBlank(message = "O campo cpf deve ser preenchido. Não pode ser vazio ou nulo. Não é permitido cpf duplicado.")
    private String cpf;
    @NotBlank(message = "O campo senha deve ser preenchido. Não pode ser vazio ou nulo.")
    private String senha;
    @NotBlank(message = "O campo email deve ser preenchido. Não pode ser vazio ou nulo.")
    @Email(message = "Email inválido.")
    private String email;
    @NotBlank(message = "O campo telefone deve ser preenchido. Não pode ser vazio ou nulo.")
    private String telefone;
    @NotBlank(message = "O campo celular deve ser preenchido. Não pode ser vazio ou nulo.")
    private String celular;
    @NotNull(message = "O campo hotelId deve ser preenchido. Não pode ser vazio ou nulo.")
    private Hotel hotelId;
}
