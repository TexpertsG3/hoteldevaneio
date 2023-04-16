package br.com.tex.hoteldevaneio.controller;

import br.com.tex.hoteldevaneio.model.Reserva;
import br.com.tex.hoteldevaneio.model.dto.ReservaInputDTO;
import br.com.tex.hoteldevaneio.model.dto.ReservaOutputDTO;
import br.com.tex.hoteldevaneio.service.impl.HospedeServiceImpl;
import br.com.tex.hoteldevaneio.service.impl.HotelServiceImpl;
import br.com.tex.hoteldevaneio.service.impl.QuartoServiceImpl;
import br.com.tex.hoteldevaneio.service.impl.ReservaServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @Operation(summary = "Endpoint para o cadastro de uma nova reserva.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criado com sucesso.",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ReservaOutputDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservaOutputDTO> cadastra(@RequestBody @Valid ReservaInputDTO reservaInputDTO, UriComponentsBuilder uriBuilder) {
        hotelService.buscarReferenciaPor(reservaInputDTO.getHotelId());
        quartoService.buscarReferenciaPor(reservaInputDTO.getQuartoId());
        hospedeService.buscarReferenciaPor(reservaInputDTO.getHospedeId());

        ReservaOutputDTO reservaOutputDTO = reservaService.cadastra(reservaInputDTO);
        return ResponseEntity
                .created(uriBuilder.path("/reserva/{id}").buildAndExpand(reservaOutputDTO.getId()).toUri())
                .body(reservaOutputDTO);
    }

    @Operation(summary = "Endpoint para listar todos as reservas cadastradas.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok.",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ReservaOutputDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Não encontrado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReservaOutputDTO>> listar() {
        List<ReservaOutputDTO> listaReservas = reservaService.lista();
        if (listaReservas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaReservas);
    }

    @Operation(summary = "Endpoint para buscar uma reserva pelo ID.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok.",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ReservaOutputDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Não encontrado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservaOutputDTO> buscarReferenciaPor(@PathVariable Integer id) {
        Reserva reservaBuscada = reservaService.buscarReferenciaPor(id);

        return ResponseEntity.ok(new ReservaOutputDTO(reservaBuscada));
    }

    @Operation(summary = "Endpoint para alterar uma reserva buscada pelo ID.", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok.",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ReservaOutputDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Não encontrado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservaOutputDTO> altera(@PathVariable Integer id, @RequestBody ReservaInputDTO reservaInputDTO) {
        Reserva reservaBuscada = reservaService.buscarReferenciaPor(id);
        hotelService.buscarReferenciaPor(reservaInputDTO.getHotelId());

        ReservaOutputDTO reservaOutputDTO = reservaService.altera(reservaBuscada, reservaInputDTO);
        return ResponseEntity
                .ok()
                .body(reservaOutputDTO);

    }

    @Operation(summary = "Endpoint para deletar uma reserva buscada pelo ID.", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sem conteúdo.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Não encontrado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleta(@PathVariable Integer id) {
        Optional<Reserva> reservaBuscada = reservaService.buscarPor(id);
        if (reservaBuscada.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        reservaService.deleta(reservaBuscada.get());
        return ResponseEntity.noContent().build();
    }
}
