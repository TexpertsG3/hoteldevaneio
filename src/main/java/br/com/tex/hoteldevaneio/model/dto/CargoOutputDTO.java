package br.com.tex.hoteldevaneio.model.dto;

import br.com.tex.hoteldevaneio.model.Cargo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CargoOutputDTO {

    @Schema(description = "ID de cadastro do cargo.", example = "1")
    private Integer id;
    @Schema(description = "Nome do cargo.", example = "Gerente")
    private String nome;
    @Schema(description = "Descrição do cargo.", example = "Responsável pela gerência geral do hotel.")
    private String descricao;
    @Schema(description = "ID do hotel onde foi cadastrado o cargo.", example = "1")
    private Integer hotelId;
    @Schema(description = "Nome do hotel onde o cargo foi cadastrado.", example = "Devaneio")
    private String nomeHotel;

    public CargoOutputDTO(Cargo cargo) {
        this.id = cargo.getId();
        this.nome = cargo.getNome();
        this.descricao = cargo.getDescricao();
        this.hotelId = cargo.getHotelId().getId();
        this.nomeHotel = cargo.getHotelId().getDadosHotelId().getNome();
    }

}
