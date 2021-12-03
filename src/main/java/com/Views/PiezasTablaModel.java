package com.Views;

import com.Controllers.AsignacionesDAO;
import com.Controllers.PiezasDAO;
import com.Controllers.ProveedoresDAO;
import com.Controllers.ProyectosDAO;
import com.Model.AsignacionesEntity;
import com.Model.PiezasEntity;
import com.Model.ProveedoresEntity;
import com.Model.ProyectosEntity;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PiezasTablaModel extends AbstractTableModel {

    //CONTROLADORES
    AsignacionesDAO ctAsignaciones;
    ProveedoresDAO ctProv;
    ProyectosDAO ctProy;
    PiezasDAO ctPieza;

    //COLUMNAS
    private final String[] columnas = {
            "PROYECTO",               //POS 0
            "PROVEEDOR",        //POS 1
            "CANTIDAD",         //POS 2
    };

    //LISTA DE ASIGNACIONES
    private final List<AsignacionesEntity> asignacionesEntityList;

    public PiezasTablaModel(PiezasEntity piezasEntity) {
        ctAsignaciones = new AsignacionesDAO();
        ctProv = new ProveedoresDAO();
        ctProy = new ProyectosDAO();
        ctPieza = new PiezasDAO();

        asignacionesEntityList = ctAsignaciones.getByPart(piezasEntity.getId());

    }

    @Override
    public int getRowCount() { return asignacionesEntityList.size(); }

    @Override
    public int getColumnCount() { return columnas.length; }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //Extraemos la asignacion
        AsignacionesEntity asignacion = asignacionesEntityList.get(rowIndex);

        //Extraemos los objetos asociados a la asignacion.
        ProveedoresEntity proveedor = ctProv.getById(asignacion.getIdproveedor());
        ProyectosEntity proyecto = ctProy.getById(asignacion.getIdproyecto());

        //Establecemos los valores de las columnas
        return switch (columnIndex) {
            //PROYECTO Y CIUDAD
            case 0 ->
            String.format ("%s %s",proyecto.getNombre(),proyecto.getCiudad());

            //NOMBRE APELLIDOS DE PROVEEDOR
            case 1 -> String.format ("%s %s",proveedor.getNombre(),proveedor.getApellidos());

            //CANTIDAD
            case 2 -> String.format ("%.2f",asignacion.getCantidad());
            default -> "";

        };
    }

    @Override
    public String getColumnName(int column) { return columnas[column]; }

}
