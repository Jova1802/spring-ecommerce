package com.proyecto.springecommerce.model;

import jakarta.persistence.*;

import java.util.Date;

import static org.yaml.snakeyaml.nodes.NodeId.mapping;

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String numero;
    private Date fechaCreacion;
    private Date fechaRecepcion;
    private double total;

    @ManyToOne
    private Usuario usuario;

    @OneToOne(mappedBy = "pedido")
    private DetallePedido detalle;

    public Pedido() {
    }

    public Pedido(Integer id, String numero, Date fechaCreacion, Date fechaRecepcion, double total) {
        this.id = id;
        this.numero = numero;
        this.fechaCreacion = fechaCreacion;
        this.fechaRecepcion = fechaRecepcion;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public DetallePedido getDetalle() {
        return detalle;
    }

    public void setDetalle(DetallePedido detalle) {
        this.detalle = detalle;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", fechaRecepcion=" + fechaRecepcion +
                ", total=" + total +
                '}';
    }
}
