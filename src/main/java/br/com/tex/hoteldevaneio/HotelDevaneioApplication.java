package br.com.tex.hoteldevaneio;

import br.com.tex.hoteldevaneio.configuration.WebConfig;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({WebConfig.class})
@OpenAPIDefinition(info = @Info(title = "Devaneio API", version = "1.0.0", description = "API de sistema de gerenciamento de hotéis."))
public class HotelDevaneioApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelDevaneioApplication.class, args);
	}

}
