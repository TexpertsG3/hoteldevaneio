package br.com.tex.hoteldevaneio.service.impl;

import br.com.tex.hoteldevaneio.model.Endereco;
import br.com.tex.hoteldevaneio.repository.EnderecoRepository;
import br.com.tex.hoteldevaneio.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Transactional
    public Endereco cadastra(Endereco endereco) {
       return enderecoRepository.save(endereco);
    }

    public Optional<Endereco> buscarPor(Integer id) {
        return enderecoRepository.findById(id);
    }

}
