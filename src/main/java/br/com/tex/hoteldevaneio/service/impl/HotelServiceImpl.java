package br.com.tex.hoteldevaneio.service.impl;

import br.com.tex.hoteldevaneio.model.Contato;
import br.com.tex.hoteldevaneio.model.DadosHotel;
import br.com.tex.hoteldevaneio.model.Endereco;
import br.com.tex.hoteldevaneio.model.Hotel;
import br.com.tex.hoteldevaneio.model.dto.HotelInputDTO;
import br.com.tex.hoteldevaneio.model.dto.HotelOutputDTO;
import br.com.tex.hoteldevaneio.repository.HotelRepository;
import br.com.tex.hoteldevaneio.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ContatoServiceImpl contatoService;

    @Autowired
    private EnderecoServiceImpl enderecoService;

    @Autowired
    private DadosHotelServiceImpl dadosHotelService;

    @Transactional
    public HotelOutputDTO cadastra(HotelInputDTO hotelInputDTO) {
        Contato contato = Contato.cadastroContatoBuilder()
                .email(hotelInputDTO.getEmail())
                .telefone(hotelInputDTO.getTelefone())
                .celular(hotelInputDTO.getCelular())
                .build();
        Contato contatoSalvo = contatoService.cadastra(contato);

        Endereco endereco = Endereco.cadastraEnderecoBuilder()
                .rua(hotelInputDTO.getRua())
                .bairro(hotelInputDTO.getBairro())
                .numero(hotelInputDTO.getNumero())
                .cep(hotelInputDTO.getCep())
                .cidade(hotelInputDTO.getCidade())
                .uf(hotelInputDTO.getUf())
                .complemento(hotelInputDTO.getComplemento())
                .build();
        Endereco enderecoSalvo = enderecoService.cadastra(endereco);

        DadosHotel dadosHotel = new DadosHotel(hotelInputDTO.getNome(), hotelInputDTO.getCnpj(), contatoSalvo, enderecoSalvo);
        DadosHotel dadosHotelSalvo = dadosHotelService.cadastra(dadosHotel);

        Hotel hotel = new Hotel(dadosHotelSalvo);

        Hotel hotelSalvo = hotelRepository.save(hotel);

        return new HotelOutputDTO(hotelSalvo);
    }

    public Optional<Hotel> buscarPor(Integer id) {
        return hotelRepository.findById(id);
    }

    public Hotel buscarReferenciaPor(Integer id) {
        return hotelRepository.getReferenceById(id);
    }

    public List<HotelOutputDTO> lista() {
        List<Hotel> listaHoteis = hotelRepository.findAll();
        return listaHoteis.stream().map(HotelOutputDTO::new).toList();
    }

    @Transactional
    public HotelOutputDTO altera(Hotel hotel, HotelInputDTO hotelInputDTO) {
        Contato contato = contatoService.buscarPor(hotel.getDadosHotelId().getContatoId().getId()).get();
        Endereco endereco = enderecoService.buscarPor(hotel.getDadosHotelId().getEnderecoId().getId()).get();
        DadosHotel dadosHotel = dadosHotelService.buscarPor(hotel.getDadosHotelId().getId()).get();

        contato.setEmail(hotelInputDTO.getEmail());
        contato.setTelefone(hotelInputDTO.getTelefone());
        contato.setCelular(hotelInputDTO.getCelular());

        endereco.setRua(hotelInputDTO.getRua());
        endereco.setBairro(hotelInputDTO.getBairro());
        endereco.setNumero(hotelInputDTO.getNumero());
        endereco.setCep(hotelInputDTO.getCep());
        endereco.setCidade(hotelInputDTO.getCidade());
        endereco.setUf(hotelInputDTO.getUf());
        endereco.setComplemento(hotelInputDTO.getComplemento());

        dadosHotel.setNome(hotelInputDTO.getNome());
        dadosHotel.setCnpj(hotelInputDTO.getCnpj());

        Hotel hotelSalvo = hotelRepository.save(hotel);

    return new HotelOutputDTO(hotelSalvo);
    }

    @Transactional
    public void deleta(Hotel hotel) {
        hotelRepository.deleteById(hotel.getId());
    }
}
