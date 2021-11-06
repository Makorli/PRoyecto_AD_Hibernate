package com.Model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Asignaciones", schema = "gestionpiezas", catalog = "")
public class AsignacionesEntity {
    private int id;
    private double cantidad;
    private ProveedoresEntity proveedoresByIdproveedor;
    private PiezasEntity piezasByIdpieza;
    private ProyectosEntity proyectosByIdproyecto;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "cantidad", nullable = false, precision = 0)
    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AsignacionesEntity that = (AsignacionesEntity) o;
        return id == that.id && Double.compare(that.cantidad, cantidad) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cantidad);
    }

    @ManyToOne
    @JoinColumn(name = "idproveedor", referencedColumnName = "id", nullable = false)
    public ProveedoresEntity getProveedoresByIdproveedor() {
        return proveedoresByIdproveedor;
    }

    public void setProveedoresByIdproveedor(ProveedoresEntity proveedoresByIdproveedor) {
        this.proveedoresByIdproveedor = proveedoresByIdproveedor;
    }

    @ManyToOne
    @JoinColumn(name = "idpieza", referencedColumnName = "id", nullable = false)
    public PiezasEntity getPiezasByIdpieza() {
        return piezasByIdpieza;
    }

    public void setPiezasByIdpieza(PiezasEntity piezasByIdpieza) {
        this.piezasByIdpieza = piezasByIdpieza;
    }

    @ManyToOne
    @JoinColumn(name = "idproyecto", referencedColumnName = "id", nullable = false)
    public ProyectosEntity getProyectosByIdproyecto() {
        return proyectosByIdproyecto;
    }

    public void setProyectosByIdproyecto(ProyectosEntity proyectosByIdproyecto) {
        this.proyectosByIdproyecto = proyectosByIdproyecto;
    }
}
