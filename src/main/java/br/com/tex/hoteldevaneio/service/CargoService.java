package br.com.tex.hoteldevaneio.service;

import br.com.tex.hoteldevaneio.model.Cargo;
import br.com.tex.hoteldevaneio.model.dto.CargoInputDTO;
import br.com.tex.hoteldevaneio.model.dto.CargoOutputDTO;

import java.util.List;
import java.util.Optional;

public interface CargoService {

    CargoOutputDTO cadastra(CargoInputDTO cargoInputDTO);
    Optional<Cargo> buscarPor(Integer id);
    Cargo buscarReferenciaPor(Integer id);
    List<CargoOutputDTO> lista();
    CargoOutputDTO altera(Cargo cargo, CargoInputDTO cargoInputDTO);
    void deleta(Cargo cargo);

}
