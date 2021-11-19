package com.Model;

public class Proveedor extends ProveedoresEntity {

    //Creamos los constructores
    public Proveedor(int id, String codigo, String nombre, String apellidos, String direccion) {
        super.setId(id);
        super.setCodigo(codigo);
        super.setNombre(nombre);
        super.setApellidos(apellidos);
        super.setDireccion(direccion);
    }

    public Proveedor(String codigo, String nombre, String apellidos, String direccion) {
        super.setCodigo(codigo);
        super.setNombre(nombre);
        super.setApellidos(apellidos);
        super.setDireccion(direccion);
    }
}
