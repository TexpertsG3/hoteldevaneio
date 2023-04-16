package br.com.tex.hoteldevaneio.controller;

import br.com.tex.hoteldevaneio.model.Reserva;
import br.com.tex.hoteldevaneio.model.dto.ReservaInputDTO;
import br.com.tex.hoteldevaneio.model.dto.ReservaOutputDTO;
import br.com.tex.hoteldevaneio.service.impl.HospedeServiceImpl;
import br.com.tex.hoteldevaneio.service.impl.HotelServiceImpl;
import br.com.tex.hoteldevaneio.service.impl.QuartoServiceImpl;
import br.com.tex.hoteldevaneio.service.impl.ReservaServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/reserva", produces = {"application/json"})
@Tag(name = "Reserva", description = "Endpoints relacionados a reserva.")
public class ReservaController {

    @Autowired
    private ReservaServiceImpl reservaService;

    @Autowired
    private HotelServiceImpl hotelService;

    @Autowired
    private QuartoServiceImpl quartoService;

    @Autowired
    private HospedeServiceImpl hospedeService;

    @PostMapping
    public ResponseEntity<ReservaOutputDTO> cadastra(@RequestBody @Valid ReservaInputDTO reservaInputDTO, UriComponentsBuilder uriBuilder) {
        hotelService.buscarReferenciaPor(reservaInputDTO.getHotelId());
        quartoService.buscarReferenciaPor(reservaInputDTO.getQuartoId());
        hospedeService.buscarReferenciaPor(reservaInputDTO.getHospedeId());

        ReservaOutputDTO reservaOutputDTO = reservaService.cadastra(reservaInputDTO);
        return ResponseEntity
                .created(uriBuilder.path("/reserva/{id}").buildAndExpand(reservaOutputDTO.getId()).toUri())
                .body(reservaOutputDTO);
    }

    @GetMapping
    public ResponseEntity<List<ReservaOutputDTO>> listar() {
        List<ReservaOutputDTO> listaReservas = reservaService.lista();
        if (listaReservas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaReservas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaOutputDTO> buscarReferenciaPor(@PathVariable Integer id) {
        Reserva reservaBuscada = reservaService.buscarReferenciaPor(id);

        return ResponseEntity.ok(new ReservaOutputDTO(reservaBuscada));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaOutputDTO> altera(@PathVariable Integer id, @RequestBody ReservaInputDTO reservaInputDTO) {
        Reserva reservaBuscada = reservaService.buscarReferenciaPor(id);
        hotelService.buscarReferenciaPor(reservaInputDTO.getHotelId());

        ReservaOutputDTO reservaOutputDTO = reservaService.altera(reservaBuscada, reservaInputDTO);
        return ResponseEntity
                .ok()
                .body(reservaOutputDTO);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleta(@PathVariable Integer id) {
        Optional<Reserva> reservaBuscada = reservaService.buscarPor(id);
        if (reservaBuscada.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        reservaService.deleta(reservaBuscada.get());
        return ResponseEntity.noContent().build();
    }
}
