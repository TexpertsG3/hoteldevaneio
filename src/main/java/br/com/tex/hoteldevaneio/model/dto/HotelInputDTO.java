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
public class HotelInputDTO {

    @Schema(description = "Nome do hotel.", example = "Devaneio")
    @NotBlank(message = "O campo nome deve ser preenchido. Não pode ser vazio ou nulo.")
    private String nome;
    @Schema(description = "CNPJ do hotel.", example = "99789521000185, sem '.' ou '/'")
    @NotBlank(message = "O campo CNPJ deve ser preenchido. Não pode ser vazio ou nulo.")
    private String cnpj;
    @Schema(description = "Endereço de e-mail do hotel.", example = "devaneio@hotel.com")
    @Email(message = "E-mail inválido.")
    @NotBlank(message = "O campo email deve ser preenchido. Não pode ser vazio ou nulo.")
    private String email;
    @Schema(description = "Telefone fixo do hotel.", example = "(99) 9999-9999")
    @NotBlank(message = "O campo telefone deve ser preenchido. Não pode ser vazio ou nulo.")
    private String telefone;
    @Schema(description = "Celular do hotel.", example = "(99) 99999-9999")
    @NotBlank(message = "O campo celular deve ser preenchido. Não pode ser vazio ou nulo.")
    private String celular;
    @Schema(description = "Rua onde está localizado o hotel.", example = "Rua Indefinida")
    @NotBlank(message = "O campo rua deve ser preenchido. Não pode ser vazio ou nulo.")
    private String rua;
    @Schema(description = "Bairro onde está localizado o hotel.", example = "Jd. Indefinido")
    @NotBlank(message = "O campo bairro deve ser preenchido. Não pode ser vazio ou nulo.")
    private String bairro;
    @Schema(description = "Número do hotel.", example = "99")
    @NotNull(message = "O campo número deve ser preenchido. Não pode ser nulo.")
    @Min(value = 1, message = "Número inválido.")
    private Integer numero;
    @Schema(description = "CEP da rua onde está localizado o hotel.", example = "99888000")
    @NotBlank(message = "O campo cep deve ser preenchido. Não pode ser vazio ou nulo.")
    private String cep;
    @Schema(description = "Cidade onde está localizado o hotel.", example = "São Paulo")
    @NotBlank(message = "O campo cidade deve ser preenchido. Não pode ser vazio ou nulo.")
    private String cidade;
    @Schema(description = "UF onde está localizado o hotel.", example = "SP")
    @NotBlank(message = "O campo uf deve ser preenchido. Não pode ser vazio ou nulo.")
    private String uf;
    @Schema(description = "Complemento do hotel.", example = "Condomínio A1")
    @NotBlank(message = "O campo complemento deve ser preenchido. Não pode ser vazio ou nulo.")
    private String complemento;

}
