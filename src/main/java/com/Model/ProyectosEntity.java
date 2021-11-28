package com.Model;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Proyectos", schema = "gestionpiezas", catalog = "")
public class ProyectosEntity {
    private int id;
    private String codigo;
    private String nombre;
    private String ciudad;
    private Collection<AsignacionesEntity> asignacionesById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "codigo", nullable = false, length = 6)
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Basic
    @Column(name = "nombre", nullable = false, length = 40)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "ciudad", nullable = false, length = 40)
    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProyectosEntity that = (ProyectosEntity) o;
        return id == that.id && Objects.equals(codigo, that.codigo) && Objects.equals(nombre, that.nombre) && Objects.equals(ciudad, that.ciudad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigo, nombre, ciudad);
    }

    @OneToMany(mappedBy = "proyectosByIdproyecto")
    public Collection<AsignacionesEntity> getAsignacionesById() {
        return asignacionesById;
    }

    public void setAsignacionesById(Collection<AsignacionesEntity> asignacionesById) {
        this.asignacionesById = asignacionesById;
    }
}
