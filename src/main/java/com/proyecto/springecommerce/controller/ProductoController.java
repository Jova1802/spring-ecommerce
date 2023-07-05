package com.proyecto.springecommerce.controller;


import com.proyecto.springecommerce.model.Producto;
import com.proyecto.springecommerce.model.Usuario;
import com.proyecto.springecommerce.service.ProductoService;
import com.proyecto.springecommerce.service.UploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

// se crea controlador de productos
@Controller
@RequestMapping("/product")
public class ProductoController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private ProductoService productoService;

    @Autowired
    private UploadFileService upload;

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
    public String save(Producto producto, @RequestParam("img") MultipartFile file) throws IOException {
        LOGGER.info("Este es el objeto producto {}", producto);

        Usuario usuario = new Usuario (1, "", "", "", "", "", "","");
        producto.setUsuario(usuario);

        // guardado de imagen de producto
        if(producto.getId() == Integer.parseInt(null)){ // cuando se crea un producto este trae un ID null
            String nombreImagen = upload.saveImage(file);
            producto.setImagen(nombreImagen);
        }else{

        }
        productoService.save(producto);
        return "redirect:/product";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model){
        Producto producto = new Producto();
        Optional<Producto> optionalProducto = productoService.get(id);
        producto = optionalProducto.get();

        LOGGER.info("Producto encontrado: {}", producto);

        model.addAttribute("producto", producto);

        return "productos/edit";
    }

    @PostMapping("/update")
    public String update(Producto producto,  @RequestParam("img") MultipartFile file) throws IOException {

        if(file.isEmpty()){  // cuando editamos un producto pero no cambiamos la imagen
            Producto p = new Producto();
            p = productoService.get(producto.getId()).get();
            producto.setImagen(p.getImagen());
        }else { // esto pasa cuando se edita la imagen

            Producto p = new Producto();
            p = productoService.get(producto.getId()).get();

            // eliminar cuando no sea la imagen por defecto
            if(!p.getImagen().equals("default.jpg")){
                upload.deleteImage(p.getImagen());
            }

            String nombreImagen = upload.saveImage(file);
            producto.setImagen(nombreImagen);
        }

        productoService.update(producto);
        return "redirect:/product";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id){

        Producto p = new Producto();
        p = productoService.get(id).get();

        // eliminar cuando no sea la imagen por defecto
        if(!p.getImagen().equals("default.jpg")){
            upload.deleteImage(p.getImagen());
        }

        productoService.delete(id);
        return "redirect:/product";
    }
}
