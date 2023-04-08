package br.com.tex.hoteldevaneio.service;

import br.com.tex.hoteldevaneio.model.Hotel;
import br.com.tex.hoteldevaneio.model.dto.HotelInputDTO;
import br.com.tex.hoteldevaneio.model.dto.HotelOutputDTO;

import java.util.List;
import java.util.Optional;


public interface HotelService {

    HotelOutputDTO cadastra(HotelInputDTO hotelInputDTO);
    Optional<Hotel> buscarPor(Integer id);
    Hotel buscarReferenciaPor(Integer id);
    List<HotelOutputDTO> lista();
    HotelOutputDTO altera(Hotel hotel, HotelInputDTO hotelInputDTO);
    void deleta(Hotel hotel);

}
