package br.com.tex.hoteldevaneio.model.dto;

import br.com.tex.hoteldevaneio.model.ServicoAdicional;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ServicoAdicionalOutputDTO {

    private Integer id;
    private String nome;
    private BigDecimal preco;
    private Integer hotelId;
    private String hotelNome;

    public ServicoAdicionalOutputDTO(ServicoAdicional servicoAdicional) {
        this.id = servicoAdicional.getId();
        this.nome = servicoAdicional.getNome();
        this.preco = servicoAdicional.getPreco();
        this.hotelId = servicoAdicional.getHotelId().getId();
        this.hotelNome = servicoAdicional.getHotelId().getDadosHotelId().getNome();
    }

}
