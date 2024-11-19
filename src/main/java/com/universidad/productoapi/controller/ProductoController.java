package com.universidad.productoapi.controller;

import com.universidad.productoapi.model.Producto;
import com.universidad.productoapi.service.ProductoService;
import com.universidad.productoapi.exceptions.EntityNotFoundExceptions;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor; // Lombok se encargará de crear el constructor
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/producto")
@Tag(name = "Productos", description = "Gestión de productos")
@RequiredArgsConstructor // Lombok se encarga de crear el constructor con ProductoService
public class ProductoController {

        private final ProductoService productoService; // Se inyecta automáticamente

        @Operation(summary = "Obtener todos los productos", description = "Devuelve una lista de todos los productos registrados")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Operación exitosa")
        })
        @GetMapping
        public List<Producto> obtenerProductos() {
                return productoService.obteneProductos();
        }

        @Operation(summary = "Obtener un producto por ID", description = "Devuelve un producto específico buscado por su ID")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Producto encontrado"),
                        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
        })
        @GetMapping("/{id}")
        public ResponseEntity<Producto> obtenerProducto(@PathVariable Long id) {
                Producto producto = productoService.obtenerPorId(id)
                                .orElseThrow(() -> new EntityNotFoundExceptions("El Producto no existe"));
                return ResponseEntity.ok(producto);
        }

        @Operation(summary = "Crear un nuevo producto", description = "Agrega un nuevo producto al sistema")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Producto creado exitosamente"),
                        @ApiResponse(responseCode = "400", description = "Solicitud inválida")
        })
        @PostMapping
        public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
                Producto nuevoProducto = productoService.crearProducto(producto)
                                .orElseThrow(() -> new EntityNotFoundExceptions("Problemas creando el producto"));
                return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
        }

        @Operation(summary = "Actualizar un producto existente", description = "Actualiza los datos de un producto por su ID")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Producto actualizado correctamente"),
                        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
        })
        @PutMapping("/{id}")
        public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
                Producto productoActualizado = productoService.actualizarProducto(id, producto)
                                .orElseThrow(() -> new EntityNotFoundExceptions("Problemas al actualizar el producto"));
                return ResponseEntity.ok(productoActualizado);
        }

        @Operation(summary = "Eliminar un producto", description = "Elimina un producto existente por su ID")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "204", description = "Producto eliminado correctamente"),
                        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
        })
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
                productoService.eliminarProducto(id);
                return ResponseEntity.noContent().build();
        }
}
