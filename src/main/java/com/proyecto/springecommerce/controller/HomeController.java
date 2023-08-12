package com.proyecto.springecommerce.controller;

import com.proyecto.springecommerce.model.DetallePedido;
import com.proyecto.springecommerce.model.Pedido;
import com.proyecto.springecommerce.model.Producto;
import com.proyecto.springecommerce.service.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private ProductoService productoService;

    // almacenar los detalles de la orden
    List<DetallePedido> detalle = new ArrayList<DetallePedido>();

    // datos de la orden
    Pedido pedido = new Pedido();

    @GetMapping("")
    public String home(Model model) {

        model.addAttribute("productos", productoService.findAll());
        return "/usuario/home";
    }

    @GetMapping("/productohome/{id}")
    public String productoHome(@PathVariable Integer id, Model model) {

        log.info("Producto {}, id");

        Producto producto = new Producto();

        Optional<Producto> productoOptional = productoService.get(id);
        producto = productoOptional.get();

        model.addAttribute("producto", producto);

        return "/usuario/producto_home";
    }

    @PostMapping("/car")
    public String addCar(@RequestParam Integer id, @RequestParam Integer cantidad, Model model){

        DetallePedido detallePedido = new DetallePedido();
        Producto producto = new Producto();
        double sumaTotal = 0;
        Optional<Producto> optionalProducto = productoService.get(id);

        log.info("Producto añadido: {}", optionalProducto.get());
        log.info("Cantidad: {}", cantidad);

        producto = optionalProducto.get();

        detallePedido.setCantidad(cantidad);
        detallePedido.setPrecio(producto.getPrecio());
        detallePedido.setNombre(producto.getNombre());
        detallePedido.setTotal(producto.getPrecio() * cantidad);
        detallePedido.setProducto(producto);

        // valodar que no se pueda adicionar dos veces el mismo producto

        Integer idProducto = producto.getId();
        boolean ingresado = detalle.stream().anyMatch(p -> p.getProducto().getId() == idProducto);

        if (!ingresado){
            detalle.add(detallePedido);
        }

        sumaTotal = detalle.stream().mapToDouble(dt -> dt.getTotal()).sum(); // Funcion que nos suma el total de todos los productos

        pedido.setTotal(sumaTotal);

        model.addAttribute("car", detalle);
        model.addAttribute("pedido", pedido);

        return "usuario/carrito";
    }

    // quitar producto del carrito
    @GetMapping("delete/car/{id}")
    public String deleteProductoCar(@PathVariable Integer id, Model model){

        List<DetallePedido> pedidosNuevos = new ArrayList<DetallePedido>();

        for(DetallePedido detallePedido: detalle) {

            if(detallePedido.getProducto().getId() != id) {
                pedidosNuevos.add(detallePedido);
            }
        }

        detalle = pedidosNuevos;

        double sumaTotal = 0;

        sumaTotal = detalle.stream().mapToDouble(dt -> dt.getTotal()).sum(); // Funcion que nos suma el total de todos los productos

        pedido.setTotal(sumaTotal);

        model.addAttribute("car", detalle);
        model.addAttribute("pedido", pedido);

        return "usuario/carrito";
    }

    // Metodo para funcionalidad de boton carrito
    @GetMapping("/getCar")
    public String getCar(Model model) {

        model.addAttribute("car", detalle);
        model.addAttribute("pedido", pedido);

        return "/usuario/carrito";

    }

}
