package br.com.tex.hoteldevaneio.controller;

import br.com.tex.hoteldevaneio.model.Cargo;
import br.com.tex.hoteldevaneio.model.dto.CargoInputDTO;
import br.com.tex.hoteldevaneio.model.dto.CargoOutputDTO;
import br.com.tex.hoteldevaneio.service.impl.CargoServiceImpl;
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
@RequestMapping(value = "/cargo", produces = {"application/json"})
@Tag(name = "Cargo", description = "Endpoints relacionados a cargo.")
public class CargoController {

    @Autowired
    private CargoServiceImpl cargoService;

    @Autowired
    private HotelServiceImpl hotelService;

    @Operation(summary = "Endpoint para o cadastro de um novo cargo.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criado com sucesso.",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CargoOutputDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CargoOutputDTO> cadastra(@RequestBody @Valid CargoInputDTO cargoInputDTO, UriComponentsBuilder uriBuilder) {
        hotelService.buscarReferenciaPor(cargoInputDTO.getHotelId());

        CargoOutputDTO cargoOutputDTO = cargoService.cadastra(cargoInputDTO);
        return ResponseEntity
                .created(uriBuilder.path("/cargo/{id}").buildAndExpand(cargoOutputDTO.getId()).toUri())
                .body(cargoOutputDTO);
    }

    @Operation(summary = "Endpoint para listar todos os cargos cadastrados.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok.",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CargoOutputDTO.class)) }),
            @ApiResponse(responseCode = "204", description = "Sem conteúdo.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Não encontrado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CargoOutputDTO>> listar() {
        List<CargoOutputDTO> listaCargos = cargoService.lista();
        if (listaCargos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaCargos);
    }

    @Operation(summary = "Endpoint para buscar um cargo pelo ID.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok.",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CargoOutputDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Não encontrado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CargoOutputDTO> buscarReferenciaPor(@PathVariable Integer id) {
        Cargo cargoBuscado = cargoService.buscarReferenciaPor(id);

        return ResponseEntity.ok(new CargoOutputDTO(cargoBuscado));
    }

    @Operation(summary = "Endpoint para alterar um cargo buscado pelo ID.", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok.",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CargoOutputDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Não encontrado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CargoOutputDTO> altera(@PathVariable Integer id, @RequestBody CargoInputDTO cargoInputDTO) {
        Cargo cargoBuscado = cargoService.buscarReferenciaPor(id);
        hotelService.buscarReferenciaPor(cargoInputDTO.getHotelId());

        CargoOutputDTO cargoOutputDTO = cargoService.altera(cargoBuscado, cargoInputDTO);
        return ResponseEntity
                .ok()
                .body(cargoOutputDTO);

    }

    @Operation(summary = "Endpoint para deletar um cargo buscado pelo ID.", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sem conteúdo.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Não encontrado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleta(@PathVariable Integer id) {
        Optional<Cargo> cargoBuscado = cargoService.buscarPor(id);
        if (cargoBuscado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        cargoService.deleta(cargoBuscado.get());
        return ResponseEntity.noContent().build();
    }
}
