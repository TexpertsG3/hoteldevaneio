package br.com.tex.hoteldevaneio.controller;

import br.com.tex.hoteldevaneio.model.Hotel;
import br.com.tex.hoteldevaneio.model.dto.HotelInputDTO;
import br.com.tex.hoteldevaneio.model.dto.HotelOutputDTO;
import br.com.tex.hoteldevaneio.service.impl.HotelServiceImpl;
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
@RequestMapping(value = "/hotel", produces = {"application/json"})
@Tag(name = "Hotel", description = "Endpoints relacionados a hotel.")
public class HotelController {

    @Autowired
    private HotelServiceImpl hotelService;

    @Operation(summary = "Endpoint para o cadastro de um novo hotel.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criado com sucesso.",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = HotelOutputDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HotelOutputDTO> cadastra(@RequestBody @Valid HotelInputDTO hotelInputDTO, UriComponentsBuilder uriBuilder) {
        HotelOutputDTO hotelOutputDTO = hotelService.cadastra(hotelInputDTO);
        return ResponseEntity
                .created(uriBuilder.path("/hotel/{id}").buildAndExpand(hotelOutputDTO.getId()).toUri())
                .body(hotelOutputDTO);
    }

    @Operation(summary = "Endpoint para listar todos os hotéis cadastrados.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok.",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = HotelOutputDTO.class)) }),
            @ApiResponse(responseCode = "204", description = "Sem conteúdo.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Não encontrado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<HotelOutputDTO>> listar() {
        List<HotelOutputDTO> listaHoteis = hotelService.lista();
        if (listaHoteis.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaHoteis);
    }

    @Operation(summary = "Endpoint para buscar um hotel pelo ID.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok.",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = HotelOutputDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Não encontrado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HotelOutputDTO> buscarReferenciaPor(@PathVariable Integer id) {
        Hotel hotelBuscado = hotelService.buscarReferenciaPor(id);

        return ResponseEntity.ok(new HotelOutputDTO(hotelBuscado));
    }

    @Operation(summary = "Endpoint para alterar um hotel buscado pelo ID.", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok.",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = HotelOutputDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Não encontrado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HotelOutputDTO> altera(@PathVariable Integer id, @RequestBody HotelInputDTO hotelInputDTO) {
        Hotel hotelBuscado = hotelService.buscarReferenciaPor(id);

        HotelOutputDTO hotelOutputDTO = hotelService.altera(hotelBuscado, hotelInputDTO);
        return ResponseEntity
                .ok()
                .body(hotelOutputDTO);

    }

    @Operation(summary = "Endpoint para deletar um hotel buscado pelo ID.", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sem conteúdo.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Não encontrado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleta(@PathVariable Integer id) {
        Optional<Hotel> hotelBuscado = hotelService.buscarPor(id);
        if (hotelBuscado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        hotelService.deleta(hotelBuscado.get());
        return ResponseEntity.noContent().build();
    }
}
