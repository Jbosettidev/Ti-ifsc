package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Ponto de entrada da aplicação Spring Boot.
 * <p>
 * {@code @SpringBootApplication} agrupa três coisas: configuração automática
 * ({@code @EnableAutoConfiguration}), varredura de componentes neste pacote e abaixo
 * ({@code @ComponentScan}), e suporte a configuração tipo MVC/REST
 * ({@code @SpringBootConfiguration}). A partir daqui o Spring sobe o servidor embutido,
 * o contexto de injeção de dependência e registra controllers, services e repositórios.
 */
@SpringBootApplication
public class TiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TiApplication.class, args);
	}

}