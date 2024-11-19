package com.universidad.productoapi.service;

import com.universidad.productoapi.model.Producto;
import com.universidad.productoapi.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // Lombok se encarga de inyectar el ProductoRepository en el constructor
public class ProductoServiceImpl implements ProductoService {

    private static final Logger logger = LogManager.getLogger(ProductoServiceImpl.class);
    private final ProductoRepository productoRepository;

    @Override
    public List<Producto> obteneProductos() {
        logger.info("Obteniendo todos los productos...");
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> obtenerPorId(Long id) {
        logger.info("Buscando producto con ID: {}", id);
        return productoRepository.findById(id);
    }

    @Override
    public Optional<Producto> crearProducto(Producto producto) {
        logger.info("Creando un nuevo producto: {}", producto);

        // Usamos el patrón Builder para crear el Producto
        Producto productoParaGuardar = Producto
                .builder() // Usamos el Builder de Lombok
                .nombre(producto.getNombre()) // Los métodos getter generados por Lombok
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .createdAt(ZonedDateTime.now()) // Fecha de creación
                .updatedAt(ZonedDateTime.now()) // Fecha de actualización
                .build();

        Producto guardado = productoRepository.save(productoParaGuardar);
        logger.info("Producto creado con éxito: {}", guardado);
        return Optional.of(guardado);
    }

    @Override
    public Optional<Producto> actualizarProducto(Long id, Producto producto) {
        logger.info("Actualizando producto con ID: {}", id);
        Optional<Producto> productoViejo = productoRepository.findById(id);

        if (productoViejo.isPresent()) {
            Producto productoParaActualizar = productoViejo.get();
            logger.info("Producto antes de actualizar: {}", productoParaActualizar);

            // Actualizamos el producto con los nuevos valores
            productoParaActualizar.setNombre(producto.getNombre());
            productoParaActualizar.setPrecio(producto.getPrecio());
            productoParaActualizar.setStock(producto.getStock());
            productoParaActualizar.setUpdatedAt(ZonedDateTime.now()); // Actualizamos la fecha de modificación

            Producto actualizado = productoRepository.save(productoParaActualizar);
            logger.info("Producto actualizado: {}", actualizado);
            return Optional.of(actualizado);
        } else {
            logger.warn("El producto con ID: {} no existe.", id);
            return Optional.empty();
        }
    }

    @Override
    public void eliminarProducto(Long id) {
        logger.warn("Eliminando producto con ID: {}", id);
        productoRepository.deleteById(id); // Eliminamos el producto por ID
        logger.info("Producto con ID: {} eliminado correctamente.", id);
    }
}
