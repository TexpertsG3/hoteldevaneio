package br.com.tex.hoteldevaneio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "cadastroReservaBuilder")
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
    @ManyToMany
    @JoinTable(
            name = "reserva_servico",
            joinColumns = @JoinColumn(name = "reserva_id"),
            inverseJoinColumns = @JoinColumn(name = "servico_adicional_id"))
    private Set<ServicoAdicional> servicos;
    @Column(name = "total_servicos")
    private BigDecimal totalServicos;
    @Column(name = "total_reserva")
    private BigDecimal totalReserva;

    public Reserva(Quarto quartoId, LocalDate checkIn, LocalDate checkOut, Integer quantidadeAdultos,
                   Integer quantidadeCriancas) {
        this.quartoId = quartoId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.quantidadeAdultos = quantidadeAdultos;
        this.quantidadeCriancas = quantidadeCriancas;
    }

    public Reserva.ReservaBuilder builder(Hotel hotelId, Quarto quartoId, LocalDate checkIn, LocalDate checkOut,
                                        Hospede hospedeId, Integer quantidadeAdultos, Integer quantidadeCriancas,
                                        Set<ServicoAdicional> servicos, BigDecimal totalServicos,
                                        BigDecimal totalReserva) {
        return cadastroReservaBuilder()
                .hotelId(hotelId)
                .quartoId(quartoId)
                .checkIn(checkIn)
                .checkOut(checkOut)
                .hospedeId(hospedeId)
                .quantidadeAdultos(quantidadeAdultos)
                .quantidadeCriancas(quantidadeCriancas)
                .servicos(servicos)
                .totalServicos(totalServicos)
                .totalReserva(totalReserva);
    }

}
