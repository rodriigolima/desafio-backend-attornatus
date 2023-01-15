package br.com.desafio.attornatus;

import br.com.desafio.attornatus.model.Endereco;
import br.com.desafio.attornatus.model.Pessoa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;


@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	@Scope(value = "prototype")
	Pessoa getPessoa() {
		return new Pessoa();
	}

	@Bean
	@Scope(value = "prototype")
	Endereco getEndereco() {
		return new Endereco();
	}

}


