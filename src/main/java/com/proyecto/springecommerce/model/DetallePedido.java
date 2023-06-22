package com.proyecto.springecommerce.model;

import jakarta.persistence.*;

// entity clase detalle de pedidos y creacion de tabla detalles
@Entity
@Table(name = "detalles")
public class DetallePedido {

    // atributos clase Detalles pedido
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private double cantidad;
    private double precio;
    private double total;

    // un pedido solo tiene un detalle de producto y el detalle de producto solo tiene un pedido, relacion uno a uno
    @OneToOne
    private Pedido pedido;

    // un producto puede tener un detalle de pedido pero un detalle puede tener varios productos, relacion muchos a uno
    @ManyToOne
    private Producto producto;

    //----------------------------------------------------------------constructor vacio--------------------------------
    public DetallePedido() {
    }

    //----------------------------------------------------------------constructor----------------------------------------------------------------
    public DetallePedido(int id, String nombre, double cantidad, double precio, double total) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;
    }

    //----------------------------------------------------------------getters and setters----------------------------------------------------------------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    //----------------------------------------------------------------to string----------------------------------------------------------------
    @Override
    public String toString() {
        return "DetallePedido{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                ", total=" + total +
                '}';
    }
}
