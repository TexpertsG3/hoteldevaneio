package br.com.tex.hoteldevaneio.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "cadastroContatoBuilder")
@Entity
@Table(name = "contato")
public class Contato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "email")
	private String email;
	@Column(name = "telefone")
	private String telefone;
	@Column(name = "celular")
	private String celular;

	public ContatoBuilder builder(String email, String telefone, String celular) {
		return cadastroContatoBuilder()
				.email(email)
				.telefone(telefone)
				.celular(celular);
	}

}
