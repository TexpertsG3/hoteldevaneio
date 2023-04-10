package br.com.tex.hoteldevaneio.service.impl;

import br.com.tex.hoteldevaneio.model.Contato;
import br.com.tex.hoteldevaneio.model.Hospede;
import br.com.tex.hoteldevaneio.model.Hotel;
import br.com.tex.hoteldevaneio.model.dto.HospedeInputDTO;
import br.com.tex.hoteldevaneio.model.dto.HospedeOutputDTO;
import br.com.tex.hoteldevaneio.repository.HospedeRepository;
import br.com.tex.hoteldevaneio.service.HospedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HospedeServiceImpl implements HospedeService {

    @Autowired
    private HospedeRepository hospedeRepository;

    @Autowired
    private ContatoServiceImpl contatoService;

    @Autowired
    private HotelServiceImpl hotelService;

    @Override
    @Transactional
    public HospedeOutputDTO cadastra(HospedeInputDTO hospedeInputDTO) {
        Contato contato = Contato.cadastroContatoBuilder()
                .email(hospedeInputDTO.getEmail())
                .telefone(hospedeInputDTO.getTelefone())
                .celular(hospedeInputDTO.getCelular())
                .build();
        Contato contatoSalvo = contatoService.cadastra(contato);

        Hotel hotel = hotelService.buscarPor(hospedeInputDTO.getHotelId().getId()).get();

        Hospede hospede = Hospede.cadastroHospedeBuilder()
                .nome(hospedeInputDTO.getNome())
                .sobrenome(hospedeInputDTO.getSobrenome())
                .cpf(hospedeInputDTO.getCpf())
                .senha(hospedeInputDTO.getSenha())
                .contatoId(contatoSalvo)
                .hotelId(hotel)
                .build();

        Hospede hospedeSalvo = hospedeRepository.save(hospede);

        return new HospedeOutputDTO(hospedeSalvo);
    }

    @Override
    public Optional<Hospede> buscarPor(Integer id) {
        return hospedeRepository.findById(id);
    }

    @Override
    public Hospede buscarReferenciaPor(Integer id) {
        return hospedeRepository.getReferenceById(id);
    }

    @Override
    public List<HospedeOutputDTO> lista() {
        List<Hospede> listaHospedes = hospedeRepository.findAll();
        return listaHospedes.stream().map(HospedeOutputDTO::new).toList();
    }

    @Override
    @Transactional
    public HospedeOutputDTO altera(Hospede hospede, HospedeInputDTO hospedeInputDTO) {
        hospede.setNome(hospedeInputDTO.getNome());
        hospede.setSobrenome(hospedeInputDTO.getSobrenome());
        hospede.setCpf(hospedeInputDTO.getCpf());
        hospede.setSenha(hospedeInputDTO.getSenha());
        hospede.getContatoId().setEmail(hospedeInputDTO.getEmail());
        hospede.getContatoId().setTelefone(hospedeInputDTO.getTelefone());
        hospede.getContatoId().setCelular(hospedeInputDTO.getCelular());

        Hospede hospedeSalvo = hospedeRepository.save(hospede);

        return new HospedeOutputDTO(hospedeSalvo);
    }

    @Override
    @Transactional
    public void deleta(Hospede hospede) {
        hospedeRepository.deleteById(hospede.getId());
    }
}
