package br.com.tex.hoteldevaneio.service.impl;

import br.com.tex.hoteldevaneio.model.Hotel;
import br.com.tex.hoteldevaneio.model.Quarto;
import br.com.tex.hoteldevaneio.model.dto.QuartoInputDTO;
import br.com.tex.hoteldevaneio.model.dto.QuartoOutputDTO;
import br.com.tex.hoteldevaneio.repository.QuartoRepository;
import br.com.tex.hoteldevaneio.service.QuartoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class QuartoServiceImpl implements QuartoService {

    @Autowired
    private QuartoRepository quartoRepository;

    @Autowired
    private HotelServiceImpl hotelService;

    @Override
    @Transactional
    public QuartoOutputDTO cadastra(QuartoInputDTO quartoInputDTO) {
        Hotel hotel = hotelService.buscarPor(quartoInputDTO.getHotelId()).get();

        Quarto quarto = Quarto.cadastroQuartoBuilder()
                .nome(quartoInputDTO.getNome())
                .descricao(quartoInputDTO.getDescricao())
                .preco(quartoInputDTO.getPreco())
                .hotelId(hotel)
                .build();
        Quarto quartoSalvo = quartoRepository.save(quarto);

        return new QuartoOutputDTO(quartoSalvo);
    }

    @Override
    public Optional<Quarto> buscarPor(Integer id) {
        return quartoRepository.findById(id);
    }

    @Override
    public Quarto buscarReferenciaPor(Integer id) {
        return quartoRepository.getReferenceById(id);
    }

    @Override
    public List<QuartoOutputDTO> lista() {
        List<Quarto> listaQuartos = quartoRepository.findAll();
        return listaQuartos.stream().map(QuartoOutputDTO::new).toList();
    }

    @Override
    @Transactional
    public QuartoOutputDTO altera(Quarto quarto, QuartoInputDTO quartoInputDTO) {
        Hotel hotelBuscado = hotelService.buscarPor(quartoInputDTO.getHotelId()).get();

        quarto.setNome(quartoInputDTO.getNome());
        quarto.setDescricao(quartoInputDTO.getDescricao());
        quarto.setPreco(quartoInputDTO.getPreco());
        quarto.setHotelId(hotelBuscado);

        Quarto quartoSalvo = quartoRepository.save(quarto);

        return new QuartoOutputDTO(quartoSalvo);
    }

    @Override
    @Transactional
    public void deleta(Quarto quarto) {
        quartoRepository.deleteById(quarto.getId());
    }
}
