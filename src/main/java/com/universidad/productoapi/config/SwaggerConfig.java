package com.universidad.productoapi.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

        @Bean
        public OpenAPI customOpenAPI() {
                return new OpenAPI()
                                .components(new Components())
                                .info(new Info()
                                                .title("Producto API")
                                                .description("API para gestionar productos")
                                                .version("1.0.0")
                                                .contact(new Contact()
                                                                .name("Equipo Universidad")
                                                                .email("soporte@universidad.com")
                                                                .url("https://universidad.com"))
                                                .license(new License()
                                                                .name("MIT")
                                                                .url("https://opensource.org/licenses/MIT")));
        }
}
