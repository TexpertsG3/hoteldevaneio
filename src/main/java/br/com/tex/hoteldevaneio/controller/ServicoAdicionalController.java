package br.com.tex.hoteldevaneio.controller;

import br.com.tex.hoteldevaneio.model.ServicoAdicional;
import br.com.tex.hoteldevaneio.model.dto.ServicoAdicionalInputDTO;
import br.com.tex.hoteldevaneio.model.dto.ServicoAdicionalOutputDTO;
import br.com.tex.hoteldevaneio.service.impl.HotelServiceImpl;
import br.com.tex.hoteldevaneio.service.impl.ServicoAdicionalServiceImpl;
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
@RequestMapping(value = "/servicoAdicional", produces = {"application/json"})
@Tag(name = "Serviço Adicional", description = "Endpoints relacionados a serviço adicional.")
public class ServicoAdicionalController {

    @Autowired
    private ServicoAdicionalServiceImpl servicoAdicionalService;

    @Autowired
    private HotelServiceImpl hotelService;

    @Operation(summary = "Endpoint para o cadastro de um novo serviço adicional.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criado com sucesso.",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ServicoAdicionalOutputDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServicoAdicionalOutputDTO> cadastra(@RequestBody @Valid ServicoAdicionalInputDTO servicoAdicionalInputDTO, UriComponentsBuilder uriBuilder) {
        hotelService.buscarReferenciaPor(servicoAdicionalInputDTO.getHotelId());

        ServicoAdicionalOutputDTO servicoAdicionalOutputDTO = servicoAdicionalService.cadastra(servicoAdicionalInputDTO);
        return ResponseEntity
                .created(uriBuilder.path("/servicoAdicional/{id}").buildAndExpand(servicoAdicionalOutputDTO.getId()).toUri())
                .body(servicoAdicionalOutputDTO);
    }

    @Operation(summary = "Endpoint para listar todos os serviços adicionais cadastrados.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok.",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ServicoAdicionalOutputDTO.class)) }),
            @ApiResponse(responseCode = "204", description = "Sem conteúdo.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Não encontrado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ServicoAdicionalOutputDTO>> listar() {
        List<ServicoAdicionalOutputDTO> listaServicosAdicionais = servicoAdicionalService.lista();
        if (listaServicosAdicionais.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaServicosAdicionais);
    }

    @Operation(summary = "Endpoint para buscar um serviço adicional pelo ID.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok.",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ServicoAdicionalOutputDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Não encontrado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServicoAdicionalOutputDTO> buscarReferenciaPor(@PathVariable Integer id) {
        ServicoAdicional servicoAdicionalBuscado = servicoAdicionalService.buscarReferenciaPor(id);

        return ResponseEntity.ok(new ServicoAdicionalOutputDTO(servicoAdicionalBuscado));
    }

    @Operation(summary = "Endpoint para alterar um serviço adicional pelo ID.", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok.",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ServicoAdicionalOutputDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Não encontrado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServicoAdicionalOutputDTO> altera(@PathVariable Integer id, @RequestBody ServicoAdicionalInputDTO servicoAdicionalInputDTO) {
        ServicoAdicional servicoAdicionalBuscado = servicoAdicionalService.buscarReferenciaPor(id);
        hotelService.buscarReferenciaPor(servicoAdicionalInputDTO.getHotelId());

        ServicoAdicionalOutputDTO servicoAdicionalOutputDTO = servicoAdicionalService.altera(servicoAdicionalBuscado, servicoAdicionalInputDTO);
        return ResponseEntity
                .ok()
                .body(servicoAdicionalOutputDTO);

    }

    @Operation(summary = "Endpoint para deletar um serviço adicional buscado pelo ID.", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sem conteúdo.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Não encontrado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleta(@PathVariable Integer id) {
        Optional<ServicoAdicional> servicoAdicionalBuscado = servicoAdicionalService.buscarPor(id);
        if (servicoAdicionalBuscado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        servicoAdicionalService.deleta(servicoAdicionalBuscado.get());
        return ResponseEntity.noContent().build();
    }

}
