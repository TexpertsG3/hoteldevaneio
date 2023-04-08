package br.com.tex.hoteldevaneio.model.dto.erro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErroOutputDTO {

    private String campo;
    private String descricao;

}
