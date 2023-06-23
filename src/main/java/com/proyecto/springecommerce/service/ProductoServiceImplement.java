package com.proyecto.springecommerce.service;

import com.proyecto.springecommerce.model.Producto;
import com.proyecto.springecommerce.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductoServiceImplement implements ProductoService{

    // se crea un objeto para el CRUD
    @Autowired
    private ProductoRepository productoRepository;

    // se crean los metodos para el CRUD
    @Override
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Optional<Producto> get(int id) {
        return productoRepository.findById(id);
    }

    @Override
    public void update(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public void delete(int id) {
        productoRepository.deleteById(id);
    }
}
