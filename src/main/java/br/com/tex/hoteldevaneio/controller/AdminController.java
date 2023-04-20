package br.com.tex.hoteldevaneio.controller;

import br.com.tex.hoteldevaneio.model.Admin;
import br.com.tex.hoteldevaneio.model.dto.AdminInputDTO;
import br.com.tex.hoteldevaneio.model.dto.AdminOutputDTO;
import br.com.tex.hoteldevaneio.service.impl.AdminServiceImpl;
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
@RequestMapping(value = "/admin", produces = {"application/json"})
@Tag(name = "Admin", description = "Endpoints relacionados a admin.")
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private HotelServiceImpl hotelService;

    @Operation(summary = "Endpoint para o cadastro de um novo admin.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criado com sucesso.",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AdminOutputDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdminOutputDTO> cadastra(@RequestBody @Valid AdminInputDTO adminInputDTO, UriComponentsBuilder uriBuilder) {
        hotelService.buscarReferenciaPor(adminInputDTO.getHotelId());

        AdminOutputDTO adminOutputDTO = adminService.cadastra(adminInputDTO);
        return ResponseEntity
                .created(uriBuilder.path("/admin/{id}").buildAndExpand(adminOutputDTO.getId()).toUri())
                .body(adminOutputDTO);
    }

    @Operation(summary = "Endpoint para listar todos os admins cadastrados.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok.",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AdminOutputDTO.class)) }),
            @ApiResponse(responseCode = "204", description = "Sem conteúdo.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Não encontrado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AdminOutputDTO>> listar() {
        List<AdminOutputDTO> listaAdmins = adminService.lista();
        if (listaAdmins.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaAdmins);
    }

    @Operation(summary = "Endpoint para buscar um admin pelo ID.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok.",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AdminOutputDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Não encontrado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdminOutputDTO> buscarReferenciaPor(@PathVariable Integer id) {
        Admin adminBuscado = adminService.buscarReferenciaPor(id);

        return ResponseEntity.ok(new AdminOutputDTO(adminBuscado));
    }

    @Operation(summary = "Endpoint para alterar um admin buscado pelo ID.", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok.",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AdminOutputDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Não encontrado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdminOutputDTO> altera(@PathVariable Integer id, @RequestBody AdminInputDTO adminInputDTO) {
        Admin adminBuscado = adminService.buscarReferenciaPor(id);
        hotelService.buscarReferenciaPor(adminInputDTO.getHotelId());

        AdminOutputDTO adminOutputDTO = adminService.altera(adminBuscado, adminInputDTO);
        return ResponseEntity
                .ok()
                .body(adminOutputDTO);
    }

    @Operation(summary = "Endpoint para deletar um admin buscado pelo ID.", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sem conteúdo.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Não encontrado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor.", content = @Content),
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleta(@PathVariable Integer id) {
        Optional<Admin> adminBuscado = adminService.buscarPor(id);
        if (adminBuscado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        adminService.deleta(adminBuscado.get());
        return ResponseEntity.noContent().build();
    }
}
