package br.com.desafio.attornatus.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springBlogPessoalOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Projeto Attornatus")
                        .description(" - Projeto Desafio Attornatus -")
                        .license(new License()
                                .name("Attornatus")
                                .url("https://www.attornatus.com.br/"))
                        .contact(new Contact()
                                .name("Rodrigo Lima")
                                .url("https://github.com/rodriigolima")
                                .email("rodrigomoreiralima@hotmail.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("Github")
                        .url("https://github.com/rodriigolima/desafio-backend-attornatus"));
    }
}