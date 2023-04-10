package br.com.tex.hoteldevaneio.controller;

import br.com.tex.hoteldevaneio.model.Hotel;
import br.com.tex.hoteldevaneio.model.dto.HotelInputDTO;
import br.com.tex.hoteldevaneio.model.dto.HotelOutputDTO;
import br.com.tex.hoteldevaneio.service.impl.HotelServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelServiceImpl hotelService;

    @PostMapping
    public ResponseEntity<HotelOutputDTO> cadastra(@RequestBody @Valid HotelInputDTO hotelInputDTO, UriComponentsBuilder uriBuilder) {
        HotelOutputDTO hotelOutputDTO = hotelService.cadastra(hotelInputDTO);
        return ResponseEntity
                .created(uriBuilder.path("/hotel/{id}").buildAndExpand(hotelOutputDTO.getId()).toUri())
                .body(hotelOutputDTO);
    }

    @GetMapping
    public ResponseEntity<List<HotelOutputDTO>> listar() {
        List<HotelOutputDTO> listaHoteis = hotelService.lista();
        if (listaHoteis.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaHoteis);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelOutputDTO> buscarReferenciaPor(@PathVariable Integer id) {
        Hotel hotelBuscado = hotelService.buscarReferenciaPor(id);

        return ResponseEntity.ok(new HotelOutputDTO(hotelBuscado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelOutputDTO> altera(@PathVariable Integer id, @RequestBody HotelInputDTO hotelInputDTO) {
        Hotel hotelBuscado = hotelService.buscarReferenciaPor(id);

        HotelOutputDTO hotelOutputDTO = hotelService.altera(hotelBuscado, hotelInputDTO);
        return ResponseEntity
                .ok()
                .body(hotelOutputDTO);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleta(@PathVariable Integer id) {
        Optional<Hotel> hotelBuscado = hotelService.buscarPor(id);
        if (hotelBuscado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        hotelService.deleta(hotelBuscado.get());
        return ResponseEntity.noContent().build();
    }
}
