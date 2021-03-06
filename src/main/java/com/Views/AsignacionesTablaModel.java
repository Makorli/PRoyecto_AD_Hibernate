package com.Views;

import javax.swing.table.AbstractTableModel;

import com.Controllers.PiezasDAO;
import com.Controllers.ProveedoresDAO;
import com.Controllers.ProyectosDAO;
import com.Model.AsignacionesEntity;
import com.Model.PiezasEntity;
import com.Model.ProveedoresEntity;
import com.Model.ProyectosEntity;

import java.util.List;

public class AsignacionesTablaModel extends AbstractTableModel {

    //CONTROLADORES
    ProveedoresDAO ctProv;
    ProyectosDAO ctProy;
    PiezasDAO ctPieza;

    //COLUMNAS
    private final String[] columnas = {
            "ID",               //POS 0
            "PROVEEDOR",        //POS 1
            "PROYECTO",         //POS 2
            "PIEZA",            //POS 3
            "CANTIDAD",         //POS 4
    };

    //LISTA DE ASIGNACIONES
    private List<AsignacionesEntity> asignacionesEntityList;

    public AsignacionesTablaModel(List<AsignacionesEntity> asignacionesList) {
        asignacionesEntityList = asignacionesList;
        ctProv = new ProveedoresDAO();
        ctProy = new ProyectosDAO();
        ctPieza = new PiezasDAO();
    }

    @Override
    public int getRowCount() { return asignacionesEntityList.size(); }

    @Override
    public int getColumnCount() { return columnas.length; }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //Extraemos la asignacion
        AsignacionesEntity asignacion = asignacionesEntityList.get(rowIndex);

        //Establecemos los valores de las columnas
        switch (columnIndex) {
            //ID Asignacion
            case 0 -> { return asignacion.getId();}
            //NOMBRE APELLIDOS DE PROVEEDOR
            case 1 -> {
                ProveedoresEntity proveedor = ctProv.getById(asignacion.getIdproveedor());
                return String.format("%s %s", proveedor.getNombre(), proveedor.getApellidos());
            }
            //PROYECTO Y CIUDAD
            case 2 -> {
                ProyectosEntity proyecto = ctProy.getById(asignacion.getIdproyecto());
                return String.format("%s %s", proyecto.getNombre(), proyecto.getCiudad());
            }
            //NOMBRE PIEZA
            case 3 -> {
                PiezasEntity pieza= ctPieza.getById(asignacion.getIdpieza());
                return String.format ("%s",pieza.getNombre());
            }
            case 4 ->{
                    return asignacion.getCantidad();
            }
        };
        return null;
    }

    @Override
    public String getColumnName(int column) { return columnas[column]; }

}

