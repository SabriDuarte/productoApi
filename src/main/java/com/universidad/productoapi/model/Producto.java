package com.universidad.productoapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Data // Lombok se encarga de los getters, setters, toString, equals, hashCode, y
      // constructor
@Table(name = "crud_product")
@NoArgsConstructor // Constructor vacío
@AllArgsConstructor // Constructor con todos los parámetros
@Builder // Permite la creación del producto usando el patrón builder
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Debes agregar un nombre")
    @Size(min = 1, message = "El nombre debe tener al menos 1 caracter")
    @Column(name = "Nombre", nullable = false)
    private String nombre;

    @NotNull(message = "Debes agregar un precio")
    @Digits(integer = 10, fraction = 2, message = "El precio debe ser válido con hasta 10 dígitos enteros y 2 decimales")
    @Column(name = "Precio", nullable = false)
    private BigDecimal precio;

    @NotNull(message = "Debes agregar el stock")
    @Column(name = "Stock", nullable = false)
    private Integer stock;

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    private ZonedDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private ZonedDateTime updatedAt;
}
