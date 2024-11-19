package com.universidad.productoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.universidad.productoapi") // Asegúrate de que todos los paquetes sean
                                                                         // escaneados
@EnableJpaRepositories("com.universidad.productoapi.repository") // Asegúrate de que los repositorios sean escaneados
@EnableJpaAuditing // Asegúrate de habilitar la auditoría de JPA
public class ProductoapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductoapiApplication.class, args);
    }
}
