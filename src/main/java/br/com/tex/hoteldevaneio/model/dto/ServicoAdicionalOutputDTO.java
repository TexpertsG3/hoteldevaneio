package br.com.tex.hoteldevaneio.model.dto;

import br.com.tex.hoteldevaneio.model.ServicoAdicional;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ServicoAdicionalOutputDTO {

    @Schema(description = "ID de cadastro do serviço adicional", example = "1")
    private Integer id;
    @Schema(description = "Nome do serviço adicional.", example = "Passeio nas dunas")
    private String nome;
    @Schema(description = "Preço do serviço adicional.", example = "200.00")
    private BigDecimal preco;
    @Schema(description = "ID do hotel onde foi cadastrado o serviço adicional.", example = "1")
    private Integer hotelId;
    @Schema(description = "Nome do hotel onde foi cadastrado o admin.", example = "Devaneio")
    private String hotelNome;

    public ServicoAdicionalOutputDTO(ServicoAdicional servicoAdicional) {
        this.id = servicoAdicional.getId();
        this.nome = servicoAdicional.getNome();
        this.preco = servicoAdicional.getPreco();
        this.hotelId = servicoAdicional.getHotelId().getId();
        this.hotelNome = servicoAdicional.getHotelId().getDadosHotelId().getNome();
    }

}
