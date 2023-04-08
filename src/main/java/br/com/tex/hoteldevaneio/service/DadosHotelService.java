package br.com.tex.hoteldevaneio.service;

import br.com.tex.hoteldevaneio.model.DadosHotel;

import java.util.Optional;

public interface DadosHotelService {

    DadosHotel cadastra(DadosHotel dadosHotel);
    Optional<DadosHotel> buscarPor(Integer id);

}
