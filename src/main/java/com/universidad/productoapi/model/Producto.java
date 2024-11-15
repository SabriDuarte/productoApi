package com.universidad.productoapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.ZonedDateTime;

@Entity
@Data
@Table(name = "crud_product")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Producto {
    // Id auto generado para cada producto
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Validación "Nombre" como obligatorio hasta 100 caracteres
    @NotNull(message = "Debes Agregar un Nombre")
    @Size(min = 1, message = "El nombre debe tener al menos 1 caracter")
    @Column(name = "Nombre")
    private String Nombre;

    // Validacion "Precio" como obligatorio
    @NotNull(message = "Debes agregar un precio")
    @Column(name = "Precio")
    private Integer Precio;

    // Validacion "Stock" como obligatorio
    @NotNull(message = "Debes agregar el Stock")
    @Column(name = "Stock")
    private Integer Stock;

    // Fecha de creación automática
    @CreatedDate
    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    // Fecha de última modificación (automática)
    @LastModifiedDate
    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

}
