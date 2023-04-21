package br.com.tex.hoteldevaneio.model.dto;

import br.com.tex.hoteldevaneio.model.Hotel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HotelListaCargosOutputDTO {

    @Schema(description = "ID do hotel.", example = "1")
    private Integer id;
    @Schema(description = "Nome do hotel.", example = "Devaneio")
    private String nome;
    @Schema(description = "Lista de cargos do hotel.")
    private List<CargoOutputDTO> cargos;

    public HotelListaCargosOutputDTO(Hotel hotel) {
        this.id = hotel.getId();
        this.nome = hotel.getDadosHotelId().getNome();
        this.cargos = hotel.getCargos().stream().map(cargo -> new CargoOutputDTO(cargo)).toList();
    }
}
