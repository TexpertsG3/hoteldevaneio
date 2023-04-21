package br.com.tex.hoteldevaneio.model.dto;

import br.com.tex.hoteldevaneio.model.Hotel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HotelListaServicosAdicionaisOutputDTO {

    @Schema(description = "ID do hotel.", example = "1")
    private Integer id;
    @Schema(description = "Nome do hotel.", example = "Devaneio")
    private String nome;
    @Schema(description = "Lista de serviços adicionais do hotel.")
    private List<ServicoAdicionalOutputDTO> servicosAdicionais;

    public HotelListaServicosAdicionaisOutputDTO(Hotel hotel) {
        this.id = hotel.getId();
        this.nome = hotel.getDadosHotelId().getNome();
        this.servicosAdicionais = hotel.getServicosAdicionais().stream().map(servico -> new ServicoAdicionalOutputDTO(servico)).toList();
    }
}
