package br.com.tex.hoteldevaneio.controller;

import br.com.tex.hoteldevaneio.model.Quarto;
import br.com.tex.hoteldevaneio.model.dto.QuartoInputDTO;
import br.com.tex.hoteldevaneio.model.dto.QuartoOutputDTO;
import br.com.tex.hoteldevaneio.service.impl.HotelServiceImpl;
import br.com.tex.hoteldevaneio.service.impl.QuartoServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/quarto")
public class QuartoController {

    @Autowired
    private QuartoServiceImpl quartoService;

    @Autowired
    private HotelServiceImpl hotelService;

    @PostMapping
    public ResponseEntity<QuartoOutputDTO> cadastra(@RequestBody @Valid QuartoInputDTO quartoInputDTO, UriComponentsBuilder uriBuilder) {
        hotelService.buscarReferenciaPor(quartoInputDTO.getHotelId().getId());

        QuartoOutputDTO servicoAdicionalOutputDTO = quartoService.cadastra(quartoInputDTO);
        return ResponseEntity
                .created(uriBuilder.path("/servicoAdicional/{id}").buildAndExpand(servicoAdicionalOutputDTO.getId()).toUri())
                .body(servicoAdicionalOutputDTO);
    }

    @GetMapping
    public ResponseEntity<List<QuartoOutputDTO>> listar() {
        List<QuartoOutputDTO> listaQuartos = quartoService.lista();
        if (listaQuartos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaQuartos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuartoOutputDTO> buscarReferenciaPor(@PathVariable Integer id) {
        Quarto quartoBuscado = quartoService.buscarReferenciaPor(id);

        return ResponseEntity.ok(new QuartoOutputDTO(quartoBuscado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuartoOutputDTO> altera(@PathVariable Integer id, @RequestBody QuartoInputDTO quartoInputDTO) {
        Quarto quartoBuscado = quartoService.buscarReferenciaPor(id);
        hotelService.buscarReferenciaPor(quartoInputDTO.getHotelId().getId());

        QuartoOutputDTO quartoOutputDTO = quartoService.altera(quartoBuscado, quartoInputDTO);
        return ResponseEntity
                .ok()
                .body(quartoOutputDTO);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleta(@PathVariable Integer id) {
        Optional<Quarto> quartoBuscado = quartoService.buscarPor(id);
        if (quartoBuscado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        quartoService.deleta(quartoBuscado.get());
        return ResponseEntity.noContent().build();
    }
}
