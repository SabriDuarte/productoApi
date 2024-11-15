package com.universidad.productoapi.service;

import com.universidad.productoapi.model.Producto;
import com.universidad.productoapi.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
// Declaracion de la clase y anotaciones
public class ProductoServiceImpl implements ProductoService {

    // inyeccion de dependencia
    private final ProductoRepository productoRepository;

    // Traer todos los productos
    @Override
    public List<Producto> obteneProductos() {
        return productoRepository.findAll();
    }

    // Traer producto por su ID
    @Override
    public Optional<Producto> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }

    // Crear un Producto
    @Override
    public Optional<Producto> crearProducto(Producto producto) {
        Producto productoParaGuardar = Producto
                .builder()
                .Nombre(producto.getNombre())
                .Precio(producto.getPrecio())
                .Stock(producto.getStock())
                .createdAt(ZonedDateTime.now()) // Fecha de creación
                .updatedAt(ZonedDateTime.now()) // Fecha de Actualización
                .build();
        return Optional.of(productoRepository.save(productoParaGuardar));
    }

    // Actualizar un producto existente
    @Override
    public Optional<Producto> actualizarProducto(Long id, Producto producto) {
        Optional<Producto> productoViejo = productoRepository.findById(id);
        if (productoViejo.isPresent()) {
            Producto productoParaActualizar = productoViejo.get();
            productoParaActualizar.setNombre(producto.getNombre());
            productoParaActualizar.setPrecio(producto.getPrecio());
            productoParaActualizar.setStock(producto.getStock());
            productoParaActualizar.setUpdatedAt(ZonedDateTime.now()); // Actualiza la fecha de modificación
            return Optional.of(productoRepository.save(productoParaActualizar));
        } else {
            return Optional.empty(); // Devolver vacío si no encuentra el producto
        }
    }

    // Eliminar un producto
    @Override
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

}