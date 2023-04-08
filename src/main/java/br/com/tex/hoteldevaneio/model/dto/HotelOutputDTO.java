package br.com.tex.hoteldevaneio.model.dto;

import br.com.tex.hoteldevaneio.model.Hotel;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HotelOutputDTO {

    private Integer id;
    private String nome;
    private String cnpj;
    private String email;
    private String telefone;
    private String celular;
    private String rua;
    private String bairro;
    private Integer numero;
    private String cep;
    private String cidade;
    private String uf;
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
