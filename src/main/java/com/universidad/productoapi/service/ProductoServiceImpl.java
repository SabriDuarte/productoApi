package com.universidad.productoapi.service;

import com.universidad.productoapi.model.Producto;
import com.universidad.productoapi.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {
    private final ProductoRepository productoRepository;

    @Override
    public List<Producto> obteneProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public Optional<Producto> crearProducto(Producto producto) {
        Producto productoParaGuardar = Producto
                .builder()
                .reference(UUID.randomUUID().toString())
                .nombre(producto.getNombre())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .isActive(true)
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .build();
        return Optional.of(productoRepository.save(productoParaGuardar));
    }

    @Override
    public Optional<Producto> actualizarProducto(Long id, Producto producto) {
        Optional<Producto> productoViejo = productoRepository.findById(id);
        if (productoViejo.isPresent()) {
            Producto productoParaActualizar = productoViejo.get();
            productoParaActualizar.setNombre(producto.getNombre());
            productoParaActualizar.setPrecio(producto.getPrecio());
            productoParaActualizar.setStock(producto.getStock());
            productoParaActualizar.setUpdatedAt(ZonedDateTime.now());
            return Optional.of(productoRepository.save(productoParaActualizar));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}