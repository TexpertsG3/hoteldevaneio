package br.com.tex.hoteldevaneio.model.dto;

import br.com.tex.hoteldevaneio.model.Hotel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HotelOutputDTO {

    @Schema(description = "ID do hotel.", example = "1")
    private Integer id;
    @Schema(description = "Nome do hotel.", example = "Devaneio")
    private String nome;
    @Schema(description = "CNPJ do hotel.", example = "99789521000185, sem '.' ou '/'")
    private String cnpj;
    @Schema(description = "Endereço de e-mail do hotel.", example = "devaneio@hotel.com")
    private String email;
    @Schema(description = "Telefone fixo do hotel.", example = "(99) 9999-9999")
    private String telefone;
    @Schema(description = "Celular do hotel.", example = "(99) 99999-9999")
    private String celular;
    @Schema(description = "Rua onde está localizado o hotel.", example = "Rua Indefinida")
    private String rua;
    @Schema(description = "Bairro onde está localizado o hotel.", example = "Jd. Indefinido")
    private String bairro;
    @Schema(description = "Número do hotel.", example = "99")
    private Integer numero;
    @Schema(description = "CEP da rua onde está localizado o hotel.", example = "99888000")
    private String cep;
    @Schema(description = "Cidade onde está localizado o hotel.", example = "São Paulo")
    private String cidade;
    @Schema(description = "UF onde está localizado o hotel.", example = "SP")
    private String uf;
    @Schema(description = "Complemento do hotel.", example = "Condomínio A1")
    private String complemento;

    public HotelOutputDTO(Hotel hotel) {
        this.id = hotel.getId();
        this.nome = hotel.getDadosHotelId().getNome();
        this.cnpj = hotel.getDadosHotelId().getCnpj();
        this.email = hotel.getDadosHotelId().getContatoId().getEmail();
        this.telefone = hotel.getDadosHotelId().getContatoId().getTelefone();
        this.celular = hotel.getDadosHotelId().getContatoId().getCelular();
        this.rua = hotel.getDadosHotelId().getEnderecoId().getRua();
        this.bairro = hotel.getDadosHotelId().getEnderecoId().getBairro();
        this.numero = hotel.getDadosHotelId().getEnderecoId().getNumero();
        this.cep = hotel.getDadosHotelId().getEnderecoId().getCep();
        this.cidade = hotel.getDadosHotelId().getEnderecoId().getCidade();
        this.uf = hotel.getDadosHotelId().getEnderecoId().getUf();
        this.complemento = hotel.getDadosHotelId().getEnderecoId().getComplemento();
    }

}
