package com.Model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Asignaciones", schema = "gestionpiezas", catalog = "")
public class AsignacionesEntity {
    private int id;
    private int idproveedor;
    private int idpieza;
    private int idproyecto;
    private double cantidad;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "idproveedor", nullable = false)
    public int getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(int idproveedor) {
        this.idproveedor = idproveedor;
    }

    @Basic
    @Column(name = "idpieza", nullable = false)
    public int getIdpieza() {
        return idpieza;
    }

    public void setIdpieza(int idpieza) {
        this.idpieza = idpieza;
    }

    @Basic
    @Column(name = "idproyecto", nullable = false)
    public int getIdproyecto() {
        return idproyecto;
    }

    public void setIdproyecto(int idproyecto) {
        this.idproyecto = idproyecto;
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
        return id == that.id && idproveedor == that.idproveedor && idpieza == that.idpieza && idproyecto == that.idproyecto && Double.compare(that.cantidad, cantidad) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idproveedor, idpieza, idproyecto, cantidad);
    }
}
