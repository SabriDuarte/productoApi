package com.universidad.productoapi.controller;

import com.universidad.productoapi.model.Producto;
import com.universidad.productoapi.service.ProductoService;
import com.universidad.productoapi.exceptions.EntityNotFoundExceptions;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/producto")
@RequiredArgsConstructor
public class ProductoController {
    private final ProductoService productoService;

    // Obtener todos los Productos
    @GetMapping
    public List<Producto> obtenerProductos() {
        return productoService.obteneProductos();// 200 ok
    }

    // Obtener Productos por id
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable Long id) {
        // Busca el producto, si no lo encuentra tira una excepción 404
        Producto producto = productoService.obtenerPorId(id)
                .orElseThrow(() -> new EntityNotFoundExceptions("El Producto no existe"));// 404 Not Found
        return ResponseEntity.ok(producto);// 200 ok
    }

    // Crear un nuevo Usuario
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.crearProducto(producto)
                // Si hay algun error lanza una excepción
                .orElseThrow(() -> new EntityNotFoundExceptions("Problemas creando el producto"));
        // Si el producto se crea correctamente, responde con 201 CREATED
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }

    // Actualizar un Usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id,
            @RequestBody Producto producto) {
        Producto productoActualizado = productoService.actualizarProducto(id, producto)
                // Si hay algun error en la actualización lanza ena excepción
                .orElseThrow(() -> new EntityNotFoundExceptions("Problemas al actualizar el producto"));
        // Si el producto se actualiza correctamente, responde con 200 OK
        return ResponseEntity.ok(productoActualizado);
    }

    // Endpoint para eliminar un producto por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build(); // 204 NO CONTENT
    }
}
