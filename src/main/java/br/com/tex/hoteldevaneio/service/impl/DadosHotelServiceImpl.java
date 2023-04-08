package br.com.tex.hoteldevaneio.service.impl;

import br.com.tex.hoteldevaneio.model.DadosHotel;
import br.com.tex.hoteldevaneio.repository.DadosHotelRepository;
import br.com.tex.hoteldevaneio.service.DadosHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DadosHotelServiceImpl implements DadosHotelService {

    @Autowired
    private DadosHotelRepository dadosHotelRepository;

    @Transactional
    public DadosHotel cadastra(DadosHotel dadosHotel) {
        return dadosHotelRepository.save(dadosHotel);
    }

    public Optional<DadosHotel> buscarPor(Integer id) {
        return dadosHotelRepository.findById(id);
    }
}
