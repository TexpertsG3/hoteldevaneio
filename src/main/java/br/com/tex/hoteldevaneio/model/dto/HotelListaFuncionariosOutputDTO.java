package br.com.tex.hoteldevaneio.model.dto;

import br.com.tex.hoteldevaneio.model.Hotel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HotelListaFuncionariosOutputDTO {

    @Schema(description = "ID do hotel.", example = "1")
    private Integer id;
    @Schema(description = "Nome do hotel.", example = "Devaneio")
    private String nome;
    @Schema(description = "Lista de funcionários do hotel.")
    private List<FuncionarioOutputDTO> funcionarios;

    public HotelListaFuncionariosOutputDTO(Hotel hotel) {
        this.id = hotel.getId();
        this.nome = hotel.getDadosHotelId().getNome();
        this.funcionarios = hotel.getFuncionarios().stream().map(funcionario -> new FuncionarioOutputDTO(funcionario)).toList();
    }
}
