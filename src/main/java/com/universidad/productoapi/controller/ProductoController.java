package com.universidad.productoapi.controller;

import com.universidad.productoapi.model.Producto;
import com.universidad.productoapi.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador para manejar las operaciones relacionadas con la gestión de
 * productos.
 */
@Slf4j
@RestController
@Tag(name = "Productos", description = "Operaciones relacionadas con la gestión de productos.")
@RequiredArgsConstructor
@RequestMapping("/api/producto")
public class ProductoController {
        private final ProductoService productoService;

        /**
         * Obtiene una lista de todos los productos.
         *
         * return Lista de productos.
         */
        @Operation(summary = "Obtener todos los productos.", description = "Retorna una lista de todos los productos registrados.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Lista de productos obtenida correctamente.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))),
                        @ApiResponse(responseCode = "500", description = "Error interno del servidor.", content = @Content)
        })
        @GetMapping
        public ResponseEntity<List<Producto>> obteneProductos() {
                List<Producto> producto = productoService.obteneProductos();
                log.info("Se obtuvo una lista de {} productos.", producto.size());
                return ResponseEntity.ok(producto);
        }

        /**
         * Obtiene un producto por su ID.
         *
         * param id ID del producto a obtener.
         * return Detalles del producto.
         */
        @Operation(summary = "Obtener un producto por su ID.", description = "Retorna un producto obtenido por su ID.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Producto obtenido correctamente.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))),
                        @ApiResponse(responseCode = "404", description = "No se encontró el producto."),
                        @ApiResponse(responseCode = "500", description = "Error interno del servidor.", content = @Content)
        })
        @GetMapping("/{id}")
        public ResponseEntity<Optional<Producto>> getProducto(
                        @Parameter(description = "ID del producto a obtener.", required = true, example = "1") @PathVariable Long id) {
                Optional<Producto> producto = productoService.obtenerPorId(id);

                if (producto.isPresent()) {
                        log.info("Se obtuvo el producto {}.", producto.get().getNombre());
                        return ResponseEntity.ok(producto);
                } else {
                        return ResponseEntity.notFound().build();
                }
        }

        /**
         * Agrega un producto.
         *
         * param product El producto a agregar.
         * return El estado de la operacion.
         */
        @Operation(summary = "Agrega un producto.", description = "Retorna el estado de la operacion.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Producto agregado correctamente.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))),
                        @ApiResponse(responseCode = "500", description = "Error interno del servidor.", content = @Content)
        })
        @PostMapping
        public ResponseEntity<Optional<Producto>> createProducto(
                        @Parameter(description = "Datos del producto a crear.", required = true) @RequestBody Producto producto) {
                try {
                        Optional<Producto> nuevoProducto = productoService.crearProducto(producto);

                        if (nuevoProducto.isPresent()) {
                                log.info("Se agrego el producto {}.", nuevoProducto.get().getNombre());
                                return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
                        } else {
                                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                        }
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
        }

        /**
         * Modifica un producto.
         *
         * param id El ID del producto a modificar.
         * param product Los detalles producto a modificar.
         * return El estado de la operacion.
         */
        @Operation(summary = "Modifica un producto.", description = "Retorna el estado de la operacion.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Producto modificado correctamente.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))),
                        @ApiResponse(responseCode = "500", description = "Error interno del servidor.", content = @Content)
        })
        @PutMapping("/{id}")
        public ResponseEntity<Optional<Producto>> actualizarProducto(
                        @Parameter(description = "ID del producto a modificar.", required = true, example = "1") @PathVariable Long id,
                        @Parameter(description = "Detalles del producto a modificar.", required = true) @RequestBody Producto producto) {
                Optional<Producto> actualizarProducto = productoService.actualizarProducto(id, producto);

                if (actualizarProducto.isPresent()) {
                        log.info("Se modifico el producto {}.", actualizarProducto.get().getNombre());
                        return ResponseEntity.ok(actualizarProducto);
                } else {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }
        }

        /**
         * Elimina un producto.
         *
         * param id El ID del producto a eliminar.
         * return El estado de la operacion.
         */
        @Operation(summary = "Elimina un producto.", description = "Retorna el estado de la operacion.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Producto eliminado correctamente.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))),
                        @ApiResponse(responseCode = "500", description = "Error interno del servidor.", content = @Content)
        })
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> eliminarProducto(
                        @Parameter(description = "ID del producto a eliminar.", required = true, example = "1") @PathVariable Long id) {
                log.info("Se elimino el producto con ID {}.", id);
                productoService.eliminarProducto(id);
                return ResponseEntity.noContent().build();
        }
}
