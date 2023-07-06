package com.proyecto.springecommerce.service;

import com.proyecto.springecommerce.model.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    // se definen servicios de interface ----------------------------------------------------------------
    public Producto save(Producto producto);
    public Optional<Producto> get(Integer id);
    public void update(Producto producto);
    public void delete(Integer id);
    public List<Producto> findAll();
}
