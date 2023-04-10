package br.com.tex.hoteldevaneio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public Hotel(DadosHotel dadosHotel) {
        this.dadosHotelId = dadosHotel;
    }

    public Hotel(Integer id) {
        this.id = id;
    }

}
