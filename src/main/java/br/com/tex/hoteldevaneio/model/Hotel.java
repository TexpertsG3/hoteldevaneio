package br.com.tex.hoteldevaneio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "dados_hotel_id")
    private DadosHotel dadosHotelId;
    @OneToMany(mappedBy = "hotelId")
    private List<Admin> admins = new ArrayList<>();
    @OneToMany(mappedBy = "hotelId")
    private List<Cargo> cargos = new ArrayList<>();
    @OneToMany(mappedBy = "hotelId")
    private List<Funcionario> funcionarios = new ArrayList<>();
    @OneToMany(mappedBy = "hotelId")
    private List<Hospede> hospedes = new ArrayList<>();
    @OneToMany(mappedBy = "hotelId")
    private List<Quarto> quartos = new ArrayList<>();
    @OneToMany(mappedBy = "hotelId")
    private List<Reserva> reservas = new ArrayList<>();
    @OneToMany(mappedBy = "hotelId")
    private List<ServicoAdicional> servicosAdicionais = new ArrayList<>();

    public Hotel(DadosHotel dadosHotel) {
        this.dadosHotelId = dadosHotel;
    }

    public Hotel(Integer id) {
        this.id = id;
    }

}
