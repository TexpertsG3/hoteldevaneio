package br.com.tex.hoteldevaneio.service.impl;

import br.com.tex.hoteldevaneio.model.*;
import br.com.tex.hoteldevaneio.model.dto.FuncionarioInputDTO;
import br.com.tex.hoteldevaneio.model.dto.FuncionarioOutputDTO;
import br.com.tex.hoteldevaneio.repository.FuncionarioRepository;
import br.com.tex.hoteldevaneio.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    HotelServiceImpl hotelService;

    @Autowired
    ContatoServiceImpl contatoService;

    @Autowired
    EnderecoServiceImpl enderecoService;

    @Autowired
    CargoServiceImpl cargoService;

    @Override
    @Transactional
    public FuncionarioOutputDTO cadastra(FuncionarioInputDTO funcionarioInputDTO) {
        Hotel hotel = hotelService.buscarPor(funcionarioInputDTO.getHotelId()).get();
        Cargo cargo = cargoService.buscarPor(funcionarioInputDTO.getCargoId()).get();

        Contato contato = Contato.cadastroContatoBuilder()
                .email(funcionarioInputDTO.getEmail())
                .telefone(funcionarioInputDTO.getTelefone())
                .celular(funcionarioInputDTO.getCelular())
                .build();
        Contato contatoSalvo = contatoService.cadastra(contato);

        Endereco endereco = Endereco.cadastraEnderecoBuilder()
                .rua(funcionarioInputDTO.getRua())
                .bairro(funcionarioInputDTO.getBairro())
                .numero(funcionarioInputDTO.getNumero())
                .cep(funcionarioInputDTO.getCep())
                .cidade(funcionarioInputDTO.getCidade())
                .uf(funcionarioInputDTO.getUf())
                .complemento(funcionarioInputDTO.getComplemento())
                .build();
        Endereco enderecoSalvo = enderecoService.cadastra(endereco);

        Funcionario funcionario = Funcionario.cadastroFuncionarioBuilder()
                .nome(funcionarioInputDTO.getNome())
                .sobrenome(funcionarioInputDTO.getSobrenome())
                .cpf(funcionarioInputDTO.getCpf())
                .cargoId(cargo)
                .contatoId(contatoSalvo)
                .enderecoId(enderecoSalvo)
                .salario(funcionarioInputDTO.getSalario())
                .hotelId(hotel)
                .build();

        Funcionario funcionarioSalvo = funcionarioRepository.save(funcionario);

        return new FuncionarioOutputDTO(funcionarioSalvo);
    }

    @Override
    public Optional<Funcionario> buscarPor(Integer id) {
        return funcionarioRepository.findById(id);
    }

    @Override
    public Funcionario buscarReferenciaPor(Integer id) {
        return funcionarioRepository.getReferenceById(id);
    }

    @Override
    public List<FuncionarioOutputDTO> lista() {
        List<Funcionario> listaFuncionarios = funcionarioRepository.findAll();
        return listaFuncionarios.stream().map(FuncionarioOutputDTO::new).toList();
    }

    @Override
    @Transactional
    public FuncionarioOutputDTO altera(Funcionario funcionario, FuncionarioInputDTO funcionarioInputDTO) {
        Hotel hotelBuscado = hotelService.buscarPor(funcionarioInputDTO.getHotelId()).get();
        Cargo cargoBuscado = cargoService.buscarPor(funcionarioInputDTO.getCargoId()).get();

        funcionario.setNome(funcionarioInputDTO.getNome());
        funcionario.setSobrenome(funcionarioInputDTO.getSobrenome());
        funcionario.setCpf(funcionarioInputDTO.getCpf());
        funcionario.setCargoId(cargoBuscado);
        funcionario.getContatoId().setEmail(funcionarioInputDTO.getEmail());
        funcionario.getContatoId().setTelefone(funcionarioInputDTO.getTelefone());
        funcionario.getContatoId().setCelular(funcionarioInputDTO.getCelular());
        funcionario.getEnderecoId().setRua(funcionarioInputDTO.getRua());
        funcionario.getEnderecoId().setBairro(funcionarioInputDTO.getBairro());
        funcionario.getEnderecoId().setNumero(funcionarioInputDTO.getNumero());
        funcionario.getEnderecoId().setCep(funcionarioInputDTO.getCep());
        funcionario.getEnderecoId().setCidade(funcionarioInputDTO.getCidade());
        funcionario.getEnderecoId().setUf(funcionarioInputDTO.getUf());
        funcionario.getEnderecoId().setComplemento(funcionarioInputDTO.getComplemento());
        funcionario.setSalario(funcionarioInputDTO.getSalario());
        funcionario.setHotelId(hotelBuscado);

        Funcionario funcionarioSalvo = funcionarioRepository.save(funcionario);

        return new FuncionarioOutputDTO(funcionarioSalvo);
    }

    @Override
    @Transactional
    public void deleta(Funcionario funcionario) {
        funcionarioRepository.deleteById(funcionario.getId());
    }
}
