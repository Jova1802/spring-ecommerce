package com.proyecto.springecommerce.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// se crea controlador de productos
@Controller
@RequestMapping("/product")
public class ProductoController {

    // se crea objeto que redireccione a la vista del producto
    @GetMapping("")
    public String show() {
        return "productos/show";
    }
}
