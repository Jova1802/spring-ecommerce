package com.proyecto.springecommerce.controller;


import com.proyecto.springecommerce.model.Producto;
import com.proyecto.springecommerce.model.Usuario;
import com.proyecto.springecommerce.service.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// se crea controlador de productos
@Controller
@RequestMapping("/product")
public class ProductoController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private ProductoService productoService;

    // se crea objeto que redireccione a la vista del producto
    @GetMapping("")
    public String show(Model model) {
        model.addAttribute("productos", productoService.findAll());
        return "productos/show";
    }

    @GetMapping("/create")
    public String create() {

        return "productos/create";
    }

    @PostMapping("/save")
    public String save(Producto producto) {
        LOGGER.info("Este es el objeto producto {}", producto);

        Usuario usuario = new Usuario (1, "", "", "", "", "", "","");
        producto.setUsuario(usuario);

        productoService.save(producto);
        return "redirect:/product";
    }



}
