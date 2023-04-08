package br.com.tex.hoteldevaneio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id")
    private Hotel hotelId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quarto_id")
    private Quarto quartoId;
    @Column(name = "check_in")
    private LocalDate checkIn;
    @Column(name = "check_out")
    private LocalDate checkOut;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hospede_id")
    private Hospede hospedeId;
    @Column(name = "quantidade_adultos")
    private Integer quantidadeAdultos;
    @Column(name = "quantidade_criancas")
    private Integer quantidadeCriancas;
    @Column(name = "total_servicos")
    private BigDecimal totalServicos;
    @Column(name = "total_reserva")
    private BigDecimal totalReserva;

}
