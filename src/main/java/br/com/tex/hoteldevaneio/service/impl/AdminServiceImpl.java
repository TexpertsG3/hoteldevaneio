package br.com.tex.hoteldevaneio.service.impl;

import br.com.tex.hoteldevaneio.model.Admin;
import br.com.tex.hoteldevaneio.model.Contato;
import br.com.tex.hoteldevaneio.model.Endereco;
import br.com.tex.hoteldevaneio.model.Hotel;
import br.com.tex.hoteldevaneio.model.dto.AdminInputDTO;
import br.com.tex.hoteldevaneio.model.dto.AdminOutputDTO;
import br.com.tex.hoteldevaneio.repository.AdminRepository;
import br.com.tex.hoteldevaneio.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ContatoServiceImpl contatoService;

    @Autowired
    private EnderecoServiceImpl enderecoService;

    @Autowired
    private HotelServiceImpl hotelService;

    @Override
    @Transactional
    public AdminOutputDTO cadastra(AdminInputDTO adminInputDTO) {
        Hotel hotel = hotelService.buscarPor(adminInputDTO.getHotelId()).get();

        Contato contato = Contato.cadastroContatoBuilder()
                .email(adminInputDTO.getEmail())
                .telefone(adminInputDTO.getTelefone())
                .celular(adminInputDTO.getCelular())
                .build();
        Contato contatoSalvo = contatoService.cadastra(contato);

        Endereco endereco = Endereco.cadastraEnderecoBuilder()
                .rua(adminInputDTO.getRua())
                .bairro(adminInputDTO.getBairro())
                .numero(adminInputDTO.getNumero())
                .cep(adminInputDTO.getCep())
                .cidade(adminInputDTO.getCidade())
                .uf(adminInputDTO.getUf())
                .complemento(adminInputDTO.getComplemento())
                .build();
        Endereco enderecoSalvo = enderecoService.cadastra(endereco);

        Admin admin = Admin.cadastroAdminBuilder()
                .nome(adminInputDTO.getNome())
                .senha(adminInputDTO.getSenha())
                .contatoId(contatoSalvo)
                .enderecoId(enderecoSalvo)
                .hotelId(hotel)
                .build();

        Admin adminSalvo = adminRepository.save(admin);

        return new AdminOutputDTO(adminSalvo);
    }

    @Override
    public Optional<Admin> buscarPor(Integer id) {
        return adminRepository.findById(id);
    }

    @Override
    public Admin buscarReferenciaPor(Integer id) {
        return adminRepository.getReferenceById(id);
    }

    @Override
    public List<AdminOutputDTO> lista() {
        List<Admin> listaAdmins = adminRepository.findAll();
        return listaAdmins.stream().map(AdminOutputDTO::new).toList();
    }

    @Override
    @Transactional
    public AdminOutputDTO altera(Admin admin, AdminInputDTO adminInputDTO) {
        Hotel hotelBuscado = hotelService.buscarPor(adminInputDTO.getHotelId()).get();

        admin.setNome(adminInputDTO.getNome());
        admin.setSenha(adminInputDTO.getSenha());

        admin.getContatoId().setEmail(adminInputDTO.getEmail());
        admin.getContatoId().setTelefone(adminInputDTO.getTelefone());
        admin.getContatoId().setCelular(adminInputDTO.getCelular());

        admin.getEnderecoId().setRua(adminInputDTO.getRua());
        admin.getEnderecoId().setBairro(adminInputDTO.getBairro());
        admin.getEnderecoId().setNumero(adminInputDTO.getNumero());
        admin.getEnderecoId().setCep(adminInputDTO.getCep());
        admin.getEnderecoId().setCidade(adminInputDTO.getCidade());
        admin.getEnderecoId().setUf(adminInputDTO.getUf());
        admin.getEnderecoId().setComplemento(adminInputDTO.getComplemento());
        admin.setHotelId(hotelBuscado);


        Admin adminSalvo = adminRepository.save(admin);

        return new AdminOutputDTO(adminSalvo);
    }

    @Override
    @Transactional
    public void deleta(Admin admin) {
        adminRepository.deleteById(admin.getId());
    }
}
