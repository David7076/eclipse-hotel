package com.eclipsehotel.reservations.springdoc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Api Eclipse Hotel")
                        .version("1.0")
                        .description("Documentação da API, contendo funcionalidades de reserva de quartos")
                        .contact(new Contact()
                                .name("Desenvolvedor - David dos Santos Lima")
                                .email("david.santos@dev.com")));
    }
}
