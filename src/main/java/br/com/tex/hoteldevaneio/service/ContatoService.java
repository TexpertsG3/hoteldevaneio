package br.com.tex.hoteldevaneio.service;

import br.com.tex.hoteldevaneio.model.Contato;

import java.util.Optional;

public interface ContatoService {

    Contato cadastra(Contato contato);
    Optional<Contato> buscarPor(Integer id);

}
