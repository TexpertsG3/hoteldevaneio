package br.com.tex.hoteldevaneio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "admin")
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "senha")
	private String senha;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "contato_id")
	private Contato contatoId;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "endereco_id")
	private Endereco enderecoId;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "hotel_id")
	private Hotel hotelId;

}
