package br.com.tex.hoteldevaneio.service;

import br.com.tex.hoteldevaneio.model.Endereco;

import java.util.Optional;

public interface EnderecoService {

    Endereco cadastra(Endereco endereco);
    Optional<Endereco> buscarPor(Integer id);

}
