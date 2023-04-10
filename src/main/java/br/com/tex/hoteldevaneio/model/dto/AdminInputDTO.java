package br.com.tex.hoteldevaneio.model.dto;

import br.com.tex.hoteldevaneio.model.Hotel;
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

    @NotBlank(message = "O campo nome deve ser preenchido. Não pode ser vazio ou nulo.")
    private String nome;
    @NotBlank(message = "O campo senha deve ser preenchido. Não pode ser vazio ou nulo.")
    private String senha;
    @NotBlank(message = "O campo email deve ser preenchido. Não pode ser vazio ou nulo.")
    @Email(message = "Email inválido.")
    private String email;
    @NotBlank(message = "O campo telefone deve ser preenchido. Não pode ser vazio ou nulo.")
    private String telefone;
    @NotBlank(message = "O campo celular deve ser preenchido. Não pode ser vazio ou nulo.")
    private String celular;
    @NotBlank(message = "O campo rua deve ser preenchido. Não pode ser vazio ou nulo.")
    private String rua;
    @NotBlank(message = "O campo bairro deve ser preenchido. Não pode ser vazio ou nulo.")
    private String bairro;
    @NotNull(message = "O campo número deve ser preenchido. Não pode ser nulo.")
    @Min(value = 1, message = "Número inválido.")
    private Integer numero;
    @NotBlank(message = "O campo cep deve ser preenchido. Não pode ser vazio ou nulo.")
    private String cep;
    @NotBlank(message = "O campo cidade deve ser preenchido. Não pode ser vazio ou nulo.")
    private String cidade;
    @NotBlank(message = "O campo uf deve ser preenchido. Não pode ser vazio ou nulo.")
    private String uf;
    @NotBlank(message = "O campo complemento deve ser preenchido. Não pode ser vazio ou nulo.")
    private String complemento;
    @NotNull(message = "O campo hotelId deve ser preenchido. Não pode ser vazio ou nulo.")
    private Hotel hotelId;
}
