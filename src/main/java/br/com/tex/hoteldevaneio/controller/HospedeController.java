package br.com.tex.hoteldevaneio.controller;

import br.com.tex.hoteldevaneio.model.Hospede;
import br.com.tex.hoteldevaneio.model.dto.HospedeInputDTO;
import br.com.tex.hoteldevaneio.model.dto.HospedeOutputDTO;
import br.com.tex.hoteldevaneio.service.impl.HospedeServiceImpl;
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
@RequestMapping(value = "/hospede", produces = {"application/json"})
@Tag(name = "Hospede", description = "Endpoints relacionados a hospede.")
public class HospedeController {

    @Autowired
    private HospedeServiceImpl hospedeService;

    @Autowired
    private HotelServiceImpl hotelService;

    @Operation(summary = "Endpoint para o cadastro de um novo hospede.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criado com sucesso.",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = HospedeOutputDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HospedeOutputDTO> cadastra(@RequestBody @Valid HospedeInputDTO hospedeInputDTO, UriComponentsBuilder uriBuilder) {
        hotelService.buscarReferenciaPor(hospedeInputDTO.getHotelId());

        HospedeOutputDTO hospedeOutputDTO = hospedeService.cadastra(hospedeInputDTO);
        return ResponseEntity
                .created(uriBuilder.path("/hospede/{id}").buildAndExpand(hospedeOutputDTO.getId()).toUri())
                .body(hospedeOutputDTO);
    }

    @Operation(summary = "Endpoint para listar todos os hospedes cadastrados.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok.",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = HospedeOutputDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Não encontrado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<HospedeOutputDTO>> listar() {
        List<HospedeOutputDTO> listaHospedes = hospedeService.lista();
        if (listaHospedes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaHospedes);
    }

    @Operation(summary = "Endpoint para buscar um hospede pelo ID.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok.",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = HospedeOutputDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Não encontrado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HospedeOutputDTO> buscarReferenciaPor(@PathVariable Integer id) {
        Hospede hospedeBuscado = hospedeService.buscarReferenciaPor(id);

        return ResponseEntity.ok(new HospedeOutputDTO(hospedeBuscado));
    }

    @Operation(summary = "Endpoint para alterar um hospede buscado pelo ID.", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok.",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = HospedeOutputDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Não encontrado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HospedeOutputDTO> altera(@PathVariable Integer id, @RequestBody HospedeInputDTO hospedeInputDTO) {
        Hospede hospedeBuscado = hospedeService.buscarReferenciaPor(id);

        HospedeOutputDTO hospedeOutputDTO = hospedeService.altera(hospedeBuscado, hospedeInputDTO);
        return ResponseEntity
                .ok()
                .body(hospedeOutputDTO);

    }

    @Operation(summary = "Endpoint para deletar um hospede buscado pelo ID.", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sem conteúdo.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Não encontrado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleta(@PathVariable Integer id) {
        Optional<Hospede> hospedeBuscado = hospedeService.buscarPor(id);
        if (hospedeBuscado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        hospedeService.deleta(hospedeBuscado.get());
        return ResponseEntity.noContent().build();
    }
}
