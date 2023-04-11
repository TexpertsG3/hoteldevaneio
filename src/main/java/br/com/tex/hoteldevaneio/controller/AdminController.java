package br.com.tex.hoteldevaneio.controller;

import br.com.tex.hoteldevaneio.model.Admin;
import br.com.tex.hoteldevaneio.model.dto.AdminInputDTO;
import br.com.tex.hoteldevaneio.model.dto.AdminOutputDTO;
import br.com.tex.hoteldevaneio.service.impl.AdminServiceImpl;
import br.com.tex.hoteldevaneio.service.impl.HotelServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private HotelServiceImpl hotelService;

    @PostMapping
    public ResponseEntity<AdminOutputDTO> cadastra(@RequestBody @Valid AdminInputDTO adminInputDTO, UriComponentsBuilder uriBuilder) {
        hotelService.buscarReferenciaPor(adminInputDTO.getHotelId().getId());

        AdminOutputDTO adminOutputDTO = adminService.cadastra(adminInputDTO);
        return ResponseEntity
                .created(uriBuilder.path("/admin/{id}").buildAndExpand(adminOutputDTO.getId()).toUri())
                .body(adminOutputDTO);
    }

    @GetMapping
    public ResponseEntity<List<AdminOutputDTO>> listar() {
        List<AdminOutputDTO> listaAdmins = adminService.lista();
        if (listaAdmins.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaAdmins);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminOutputDTO> buscarReferenciaPor(@PathVariable Integer id) {
        Admin adminBuscado = adminService.buscarReferenciaPor(id);

        return ResponseEntity.ok(new AdminOutputDTO(adminBuscado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminOutputDTO> altera(@PathVariable Integer id, @RequestBody AdminInputDTO adminInputDTO) {
        Admin adminBuscado = adminService.buscarReferenciaPor(id);
        hotelService.buscarReferenciaPor(adminInputDTO.getHotelId().getId());

        AdminOutputDTO adminOutputDTO = adminService.altera(adminBuscado, adminInputDTO);
        return ResponseEntity
                .ok()
                .body(adminOutputDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleta(@PathVariable Integer id) {
        Optional<Admin> adminBuscado = adminService.buscarPor(id);
        if (adminBuscado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        adminService.deleta(adminBuscado.get());
        return ResponseEntity.noContent().build();
    }
}
