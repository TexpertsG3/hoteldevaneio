package br.com.tex.hoteldevaneio.controller;

import br.com.tex.hoteldevaneio.model.Hotel;
import br.com.tex.hoteldevaneio.model.dto.*;
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

    @Operation(summary = "Endpoint para listar todos os admins cadastrados em um hotel específico.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok.",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = HotelListaAdminsOutputDTO.class)) }),
            @ApiResponse(responseCode = "204", description = "Sem conteúdo.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Não encontrado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @GetMapping(value = "/{id}/admins", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HotelListaAdminsOutputDTO> buscaAdminsPorHotel(@PathVariable Integer id) {
        Hotel hotelBuscado = hotelService.buscarReferenciaPor(id);

        return ResponseEntity.ok(new HotelListaAdminsOutputDTO(hotelBuscado));
    }

    @Operation(summary = "Endpoint para listar todos os cargos cadastrados em um hotel específico.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok.",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = HotelListaCargosOutputDTO.class)) }),
            @ApiResponse(responseCode = "204", description = "Sem conteúdo.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Não encontrado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @GetMapping(value = "/{id}/cargos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HotelListaCargosOutputDTO> buscaCargosPorHotel(@PathVariable Integer id) {
        Hotel hotelBuscado = hotelService.buscarReferenciaPor(id);

        return ResponseEntity.ok(new HotelListaCargosOutputDTO(hotelBuscado));
    }

    @Operation(summary = "Endpoint para listar todos os funcionários cadastrados em um hotel específico.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok.",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = HotelListaFuncionariosOutputDTO.class)) }),
            @ApiResponse(responseCode = "204", description = "Sem conteúdo.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Não encontrado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @GetMapping(value = "/{id}/funcionarios", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HotelListaFuncionariosOutputDTO> buscaFuncionariosPorHotel(@PathVariable Integer id) {
        Hotel hotelBuscado = hotelService.buscarReferenciaPor(id);

        return ResponseEntity.ok(new HotelListaFuncionariosOutputDTO(hotelBuscado));
    }

    @Operation(summary = "Endpoint para listar todos os hóspedes cadastrados em um hotel específico.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok.",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = HotelListaHospedesOutputDTO.class)) }),
            @ApiResponse(responseCode = "204", description = "Sem conteúdo.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Não encontrado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @GetMapping(value = "/{id}/hospedes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HotelListaHospedesOutputDTO> buscaHospedesPorHotel(@PathVariable Integer id) {
        Hotel hotelBuscado = hotelService.buscarReferenciaPor(id);

        return ResponseEntity.ok(new HotelListaHospedesOutputDTO(hotelBuscado));
    }

    @Operation(summary = "Endpoint para listar todos os quartos cadastrados em um hotel específico.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok.",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = HotelListaQuartosOutputDTO.class)) }),
            @ApiResponse(responseCode = "204", description = "Sem conteúdo.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Não encontrado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @GetMapping(value = "/{id}/quartos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HotelListaQuartosOutputDTO> buscaQuartosPorHotel(@PathVariable Integer id) {
        Hotel hotelBuscado = hotelService.buscarReferenciaPor(id);

        return ResponseEntity.ok(new HotelListaQuartosOutputDTO(hotelBuscado));
    }

    @Operation(summary = "Endpoint para listar todos as reservas cadastradas em um hotel específico.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok.",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = HotelListaReservasOutputDTO.class)) }),
            @ApiResponse(responseCode = "204", description = "Sem conteúdo.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Não encontrado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @GetMapping(value = "/{id}/reservas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HotelListaReservasOutputDTO> buscaReservasPorHotel(@PathVariable Integer id) {
        Hotel hotelBuscado = hotelService.buscarReferenciaPor(id);

        return ResponseEntity.ok(new HotelListaReservasOutputDTO(hotelBuscado));
    }

    @Operation(summary = "Endpoint para listar todos os serviços adicionais cadastrados em um hotel específico.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok.",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = HotelListaServicosAdicionaisOutputDTO.class)) }),
            @ApiResponse(responseCode = "204", description = "Sem conteúdo.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Não encontrado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @GetMapping(value = "/{id}/servicosAdicionais", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HotelListaServicosAdicionaisOutputDTO> buscaServicosAdicionaisPorHotel(@PathVariable Integer id) {
        Hotel hotelBuscado = hotelService.buscarReferenciaPor(id);

        return ResponseEntity.ok(new HotelListaServicosAdicionaisOutputDTO(hotelBuscado));
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
