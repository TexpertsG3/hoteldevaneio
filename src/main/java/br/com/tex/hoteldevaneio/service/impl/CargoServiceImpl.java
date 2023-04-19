package br.com.tex.hoteldevaneio.service.impl;

import br.com.tex.hoteldevaneio.model.Cargo;
import br.com.tex.hoteldevaneio.model.Hotel;
import br.com.tex.hoteldevaneio.model.dto.CargoInputDTO;
import br.com.tex.hoteldevaneio.model.dto.CargoOutputDTO;
import br.com.tex.hoteldevaneio.repository.CargoRepository;
import br.com.tex.hoteldevaneio.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CargoServiceImpl implements CargoService {

    @Autowired
    CargoRepository cargoRepository;

    @Autowired
    HotelServiceImpl hotelService;

    @Override
    @Transactional
    public CargoOutputDTO cadastra(CargoInputDTO cargoInputDTO) {
        Hotel hotel = hotelService.buscarPor(cargoInputDTO.getHotelId()).get();

        Cargo cargo = Cargo.cadastroCargoBuilder()
                .nome(cargoInputDTO.getNome())
                .descricao(cargoInputDTO.getDescricao())
                .hotelId(hotel)
                .build();

        Cargo cargoSalvo = cargoRepository.save(cargo);

        return new CargoOutputDTO(cargoSalvo);
    }

    @Override
    public Optional<Cargo> buscarPor(Integer id) {
        return cargoRepository.findById(id);
    }

    @Override
    public Cargo buscarReferenciaPor(Integer id) {
        return cargoRepository.getReferenceById(id);
    }

    @Override
    public List<CargoOutputDTO> lista() {
        List<Cargo> listaCargos = cargoRepository.findAll();
        return listaCargos.stream().map(CargoOutputDTO::new).toList();
    }

    @Override
    @Transactional
    public CargoOutputDTO altera(Cargo cargo, CargoInputDTO cargoInputDTO) {
        Hotel hotelBuscado = hotelService.buscarPor(cargoInputDTO.getHotelId()).get();

        cargo.setNome(cargoInputDTO.getNome());
        cargo.setDescricao(cargoInputDTO.getDescricao());
        cargo.setHotelId(hotelBuscado);

        Cargo cargoSalvo = cargoRepository.save(cargo);

        return new CargoOutputDTO(cargoSalvo);
    }

    @Override
    @Transactional
    public void deleta(Cargo cargo) {
        cargoRepository.deleteById(cargo.getId());
    }

}
