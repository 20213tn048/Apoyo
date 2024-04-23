package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.annotation.PostConstruct;
import java.awt.*;
import java.net.URI;

@SpringBootApplication
@Slf4j
public class DemoApplication {

	private final Environment env;

	public DemoApplication(Environment env) {
		this.env = env;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@PostConstruct
	public void init() {
		openSwagger();
	}

	@Bean
	public OpenAPI customApi() {
		return new OpenAPI()
			.info(new Info()
				.title("Examen Extraordinario")
				.version("1.0.0")
				.description(
					"Lista de servicios que se utilizarán para esta evaluación"
				));
	}

	private void openSwagger() {
		String url = UriComponentsBuilder.fromHttpUrl("http://localhost")
			.port(env.getProperty("server.port","8080"))
			.path("/doc/swagger-ui.html")
			.toUriString();
		
			browse(url);
	}

	private static void browse(String url) {
		try{
			URI uri = new URI(url);
			if(Desktop.isDesktopSupported() && Desktop.getDesktop(). isSupported(Desktop.Action.BROWSE)) {
				Desktop.getDesktop().browse(uri);
			} else {
				log.info("Swagger URL: {}",url);
			}
		} catch(Exception e) {
			log.error("Error al abrir Swagger en el buscador", e);
		}
	}

}