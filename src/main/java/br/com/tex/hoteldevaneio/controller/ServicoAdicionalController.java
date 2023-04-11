package br.com.tex.hoteldevaneio.controller;

import br.com.tex.hoteldevaneio.model.ServicoAdicional;
import br.com.tex.hoteldevaneio.model.dto.ServicoAdicionalInputDTO;
import br.com.tex.hoteldevaneio.model.dto.ServicoAdicionalOutputDTO;
import br.com.tex.hoteldevaneio.service.impl.HotelServiceImpl;
import br.com.tex.hoteldevaneio.service.impl.ServicoAdicionalServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/servicoAdicional")
public class ServicoAdicionalController {

    @Autowired
    private ServicoAdicionalServiceImpl servicoAdicionalService;

    @Autowired
    private HotelServiceImpl hotelService;

    @PostMapping
    public ResponseEntity<ServicoAdicionalOutputDTO> cadastra(@RequestBody @Valid ServicoAdicionalInputDTO servicoAdicionalInputDTO, UriComponentsBuilder uriBuilder) {
        hotelService.buscarReferenciaPor(servicoAdicionalInputDTO.getHotelId().getId());

        ServicoAdicionalOutputDTO servicoAdicionalOutputDTO = servicoAdicionalService.cadastra(servicoAdicionalInputDTO);
        return ResponseEntity
                .created(uriBuilder.path("/servicoAdicional/{id}").buildAndExpand(servicoAdicionalOutputDTO.getId()).toUri())
                .body(servicoAdicionalOutputDTO);
    }

    @GetMapping
    public ResponseEntity<List<ServicoAdicionalOutputDTO>> listar() {
        List<ServicoAdicionalOutputDTO> listaServicosAdicionais = servicoAdicionalService.lista();
        if (listaServicosAdicionais.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaServicosAdicionais);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoAdicionalOutputDTO> buscarReferenciaPor(@PathVariable Integer id) {
        ServicoAdicional servicoAdicionalBuscado = servicoAdicionalService.buscarReferenciaPor(id);

        return ResponseEntity.ok(new ServicoAdicionalOutputDTO(servicoAdicionalBuscado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoAdicionalOutputDTO> altera(@PathVariable Integer id, @RequestBody ServicoAdicionalInputDTO servicoAdicionalInputDTO) {
        ServicoAdicional servicoAdicionalBuscado = servicoAdicionalService.buscarReferenciaPor(id);
        hotelService.buscarReferenciaPor(servicoAdicionalInputDTO.getHotelId().getId());

        ServicoAdicionalOutputDTO servicoAdicionalOutputDTO = servicoAdicionalService.altera(servicoAdicionalBuscado, servicoAdicionalInputDTO);
        return ResponseEntity
                .ok()
                .body(servicoAdicionalOutputDTO);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleta(@PathVariable Integer id) {
        Optional<ServicoAdicional> servicoAdicionalBuscado = servicoAdicionalService.buscarPor(id);
        if (servicoAdicionalBuscado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        servicoAdicionalService.deleta(servicoAdicionalBuscado.get());
        return ResponseEntity.noContent().build();
    }

}
