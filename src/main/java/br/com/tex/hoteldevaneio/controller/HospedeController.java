package br.com.tex.hoteldevaneio.controller;

import br.com.tex.hoteldevaneio.model.Hospede;
import br.com.tex.hoteldevaneio.model.dto.HospedeInputDTO;
import br.com.tex.hoteldevaneio.model.dto.HospedeOutputDTO;
import br.com.tex.hoteldevaneio.service.impl.HospedeServiceImpl;
import br.com.tex.hoteldevaneio.service.impl.HotelServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/hospede", produces = {"application/json"})
@Tag(name = "Hospede", description = "Endpoints relacionados a hospede.")
public class HospedeController {

    @Autowired
    private HospedeServiceImpl hospedeService;

    @Autowired
    private HotelServiceImpl hotelService;

    @PostMapping
    public ResponseEntity<HospedeOutputDTO> cadastra(@RequestBody @Valid HospedeInputDTO hospedeInputDTO, UriComponentsBuilder uriBuilder) {
        hotelService.buscarReferenciaPor(hospedeInputDTO.getHotelId());

        HospedeOutputDTO hospedeOutputDTO = hospedeService.cadastra(hospedeInputDTO);
        return ResponseEntity
                .created(uriBuilder.path("/hospede/{id}").buildAndExpand(hospedeOutputDTO.getId()).toUri())
                .body(hospedeOutputDTO);
    }

    @GetMapping
    public ResponseEntity<List<HospedeOutputDTO>> listar() {
        List<HospedeOutputDTO> listaHospedes = hospedeService.lista();
        if (listaHospedes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaHospedes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospedeOutputDTO> buscarReferenciaPor(@PathVariable Integer id) {
        Hospede hospedeBuscado = hospedeService.buscarReferenciaPor(id);

        return ResponseEntity.ok(new HospedeOutputDTO(hospedeBuscado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HospedeOutputDTO> altera(@PathVariable Integer id, @RequestBody HospedeInputDTO hospedeInputDTO) {
        Hospede hospedeBuscado = hospedeService.buscarReferenciaPor(id);

        HospedeOutputDTO hospedeOutputDTO = hospedeService.altera(hospedeBuscado, hospedeInputDTO);
        return ResponseEntity
                .ok()
                .body(hospedeOutputDTO);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleta(@PathVariable Integer id) {
        Optional<Hospede> hospedeBuscado = hospedeService.buscarPor(id);
        if (hospedeBuscado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        hospedeService.deleta(hospedeBuscado.get());
        return ResponseEntity.noContent().build();
    }
}
