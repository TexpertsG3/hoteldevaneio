package br.com.tex.hoteldevaneio.controller;

import br.com.tex.hoteldevaneio.model.Quarto;
import br.com.tex.hoteldevaneio.model.dto.QuartoInputDTO;
import br.com.tex.hoteldevaneio.model.dto.QuartoOutputDTO;
import br.com.tex.hoteldevaneio.service.impl.HotelServiceImpl;
import br.com.tex.hoteldevaneio.service.impl.QuartoServiceImpl;
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
@RequestMapping(value = "/quarto", produces = {"application/json"})
@Tag(name = "Quarto", description = "Endpoints relacionados a quarto.")
public class QuartoController {

    @Autowired
    private QuartoServiceImpl quartoService;

    @Autowired
    private HotelServiceImpl hotelService;

    @Operation(summary = "Endpoint para o cadastro de um novo quarto.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criado com sucesso.",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = QuartoOutputDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QuartoOutputDTO> cadastra(@RequestBody @Valid QuartoInputDTO quartoInputDTO, UriComponentsBuilder uriBuilder) {
        hotelService.buscarReferenciaPor(quartoInputDTO.getHotelId());

        QuartoOutputDTO servicoAdicionalOutputDTO = quartoService.cadastra(quartoInputDTO);
        return ResponseEntity
                .created(uriBuilder.path("/quarto/{id}").buildAndExpand(servicoAdicionalOutputDTO.getId()).toUri())
                .body(servicoAdicionalOutputDTO);
    }

    @Operation(summary = "Endpoint para listar todos os quartos cadastrados.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok.",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = QuartoOutputDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Não encontrado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<QuartoOutputDTO>> listar() {
        List<QuartoOutputDTO> listaQuartos = quartoService.lista();
        if (listaQuartos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaQuartos);
    }

    @Operation(summary = "Endpoint para buscar um quarto pelo ID.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok.",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = QuartoOutputDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Não encontrado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QuartoOutputDTO> buscarReferenciaPor(@PathVariable Integer id) {
        Quarto quartoBuscado = quartoService.buscarReferenciaPor(id);

        return ResponseEntity.ok(new QuartoOutputDTO(quartoBuscado));
    }

    @Operation(summary = "Endpoint para alterar um quarto buscado pelo ID.", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok.",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = QuartoOutputDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Não encontrado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QuartoOutputDTO> altera(@PathVariable Integer id, @RequestBody QuartoInputDTO quartoInputDTO) {
        Quarto quartoBuscado = quartoService.buscarReferenciaPor(id);
        hotelService.buscarReferenciaPor(quartoInputDTO.getHotelId());

        QuartoOutputDTO quartoOutputDTO = quartoService.altera(quartoBuscado, quartoInputDTO);
        return ResponseEntity
                .ok()
                .body(quartoOutputDTO);

    }

    @Operation(summary = "Endpoint para deletar um quarto buscado pelo ID.", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sem conteúdo.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Não encontrado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleta(@PathVariable Integer id) {
        Optional<Quarto> quartoBuscado = quartoService.buscarPor(id);
        if (quartoBuscado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        quartoService.deleta(quartoBuscado.get());
        return ResponseEntity.noContent().build();
    }
}
