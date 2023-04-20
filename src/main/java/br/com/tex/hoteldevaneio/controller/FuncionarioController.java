package br.com.tex.hoteldevaneio.controller;

import br.com.tex.hoteldevaneio.model.Funcionario;
import br.com.tex.hoteldevaneio.model.dto.FuncionarioInputDTO;
import br.com.tex.hoteldevaneio.model.dto.FuncionarioOutputDTO;
import br.com.tex.hoteldevaneio.service.impl.FuncionarioServiceImpl;
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
@RequestMapping(value = "/funcionario", produces = {"application/json"})
@Tag(name = "Funcionário", description = "Endpoints relacionados a funcionário.")
public class FuncionarioController {

    @Autowired
    private FuncionarioServiceImpl funcionarioService;

    @Autowired
    private HotelServiceImpl hotelService;

    @Operation(summary = "Endpoint para o cadastro de um novo funcionário.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criado com sucesso.",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = FuncionarioOutputDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FuncionarioOutputDTO> cadastra(@RequestBody @Valid FuncionarioInputDTO funcionarioInputDTO, UriComponentsBuilder uriBuilder) {
        hotelService.buscarReferenciaPor(funcionarioInputDTO.getHotelId());

        FuncionarioOutputDTO funcionarioOutputDTO = funcionarioService.cadastra(funcionarioInputDTO);
        return ResponseEntity
                .created(uriBuilder.path("/funcionario/{id}").buildAndExpand(funcionarioOutputDTO.getId()).toUri())
                .body(funcionarioOutputDTO);
    }

    @Operation(summary = "Endpoint para listar todos os quartos cadastrados.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok.",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = FuncionarioOutputDTO.class)) }),
            @ApiResponse(responseCode = "204", description = "Sem conteúdo.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Não encontrado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FuncionarioOutputDTO>> listar() {
        List<FuncionarioOutputDTO> listaFuncionarios = funcionarioService.lista();
        if (listaFuncionarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaFuncionarios);
    }

    @Operation(summary = "Endpoint para buscar um quarto pelo ID.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok.",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = FuncionarioOutputDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Não encontrado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FuncionarioOutputDTO> buscarReferenciaPor(@PathVariable Integer id) {
        Funcionario funcionarioBuscado = funcionarioService.buscarReferenciaPor(id);

        return ResponseEntity.ok(new FuncionarioOutputDTO(funcionarioBuscado));
    }

    @Operation(summary = "Endpoint para alterar um quarto buscado pelo ID.", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok.",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = FuncionarioOutputDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Não encontrado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FuncionarioOutputDTO> altera(@PathVariable Integer id, @RequestBody FuncionarioInputDTO funcionarioInputDTO) {
        Funcionario funcionarioBuscado = funcionarioService.buscarReferenciaPor(id);
        hotelService.buscarReferenciaPor(funcionarioInputDTO.getHotelId());

        FuncionarioOutputDTO funcionarioOutputDTO = funcionarioService.altera(funcionarioBuscado, funcionarioInputDTO);
        return ResponseEntity
                .ok()
                .body(funcionarioOutputDTO);

    }

    @Operation(summary = "Endpoint para deletar um quarto buscado pelo ID.", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sem conteúdo.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Não encontrado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleta(@PathVariable Integer id) {
        Optional<Funcionario> funcionarioBuscado = funcionarioService.buscarPor(id);
        if (funcionarioBuscado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        funcionarioService.deleta(funcionarioBuscado.get());
        return ResponseEntity.noContent().build();
    }
}
