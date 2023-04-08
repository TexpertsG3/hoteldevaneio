package br.com.tex.hoteldevaneio.service.impl;

import br.com.tex.hoteldevaneio.model.Contato;
import br.com.tex.hoteldevaneio.repository.ContatoRepository;
import br.com.tex.hoteldevaneio.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ContatoServiceImpl implements ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    @Transactional
    public Contato cadastra(Contato contato) {
        return contatoRepository.save(contato);
    }

    public Optional<Contato> buscarPor(Integer id) {
        return contatoRepository.findById(id);
    }

}
