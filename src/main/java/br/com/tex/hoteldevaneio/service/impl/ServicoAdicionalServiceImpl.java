package br.com.tex.hoteldevaneio.service.impl;

import br.com.tex.hoteldevaneio.model.Hotel;
import br.com.tex.hoteldevaneio.model.ServicoAdicional;
import br.com.tex.hoteldevaneio.model.dto.ServicoAdicionalInputDTO;
import br.com.tex.hoteldevaneio.model.dto.ServicoAdicionalOutputDTO;
import br.com.tex.hoteldevaneio.repository.ServicoAdicionalRepository;
import br.com.tex.hoteldevaneio.service.ServicoAdicionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoAdicionalServiceImpl implements ServicoAdicionalService {

    @Autowired
    private ServicoAdicionalRepository servicoAdicionalRepository;

    @Autowired
    private HotelServiceImpl hotelService;

    @Override
    public ServicoAdicionalOutputDTO cadastra(ServicoAdicionalInputDTO servicoAdicionalInputDTO) {
        Hotel hotel = hotelService.buscarPor(servicoAdicionalInputDTO.getHotelId().getId()).get();

        ServicoAdicional servicoAdicional = ServicoAdicional.cadastroServicoAdicionalBuilder()
                .nome(servicoAdicionalInputDTO.getNome())
                .preco(servicoAdicionalInputDTO.getPreco())
                .hotelId(hotel)
                .build();
        ServicoAdicional servicoAdicionalSalvo = servicoAdicionalRepository.save(servicoAdicional);

        return new ServicoAdicionalOutputDTO(servicoAdicionalSalvo);
    }

    @Override
    public Optional<ServicoAdicional> buscarPor(Integer id) {
        return servicoAdicionalRepository.findById(id);
    }

    @Override
    public ServicoAdicional buscarReferenciaPor(Integer id) {
        return servicoAdicionalRepository.getReferenceById(id);
    }

    @Override
    public List<ServicoAdicionalOutputDTO> lista() {
        List<ServicoAdicional> listaServicosAdicionais = servicoAdicionalRepository.findAll();
        return listaServicosAdicionais.stream().map(ServicoAdicionalOutputDTO::new).toList();
    }

    @Override
    public ServicoAdicionalOutputDTO altera(ServicoAdicional servicoAdicional, ServicoAdicionalInputDTO servicoAdicionalInputDTO) {
        return null;
    }

    @Override
    public void deleta(ServicoAdicional servicoAdicional) {

    }
}
