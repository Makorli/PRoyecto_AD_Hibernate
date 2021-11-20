package com.Controllers;

import com.Model.PiezasEntity;
import com.Model.ProveedoresEntity;
import com.Model.ProyectosEntity;
import com.Views.CustomsViews.DinamicJpanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase genrica para la obtención de objetos de vistas a usar en las vistas generales
 * definidas en el package View
 */
public class ViewsController {

    /**
     * Funcion que devuelve un objeto partiendo de los datos contenidos en los TextFields de la vista.
     * @param myCustomPanel
     */
    public static Object getObjectFromMyCustomView(DinamicJpanel myCustomPanel) {
        //Preguntamos el tipo de panel que es
        Object o = null;
        switch (myCustomPanel.getType()){
            case Proveedores -> {
                ProveedoresEntity myEntity = new ProveedoresEntity();
                for (JTextField field: myCustomPanel.getFieldsMap().values()){
                    switch (field.getName()){
                        case "codigo" ->{ myEntity.setCodigo(field.getText()); }
                        case "nombre" ->{ myEntity.setNombre(field.getText()); }
                        case "apellidos" ->{ myEntity.setApellidos(field.getText()); }
                        case "direccion" ->{ myEntity.setDireccion(field.getText()); }
                    }
                }
                o=myEntity;
            }
            case Proyectos -> {
                ProyectosEntity myEntity = new ProyectosEntity();
                for (JTextField field: myCustomPanel.getFieldsMap().values()){
                    switch (field.getName()){
                        case "codigo" ->{ myEntity.setCodigo(field.getText()); }
                        case "nombre" ->{ myEntity.setNombre(field.getText()); }
                        case "ciudad" ->{ myEntity.setCiudad(field.getText()); }
                    }
                }
                o=myEntity;
            }
            case Piezas -> {
                PiezasEntity myEntity = new PiezasEntity();
                for (JTextField field: myCustomPanel.getFieldsMap().values()){
                    switch (field.getName()){
                        case "codigo" ->{ myEntity.setCodigo(field.getText()); }
                        case "nombre" ->{ myEntity.setNombre(field.getText()); }
                        case "precio" ->{ myEntity.setPrecio(Double.parseDouble(field.getText())); }
                        case "descripcion" -> {myEntity.setDescripcion(field.getText());}
                    }
                }
                o=myEntity;
            }
            case Asignaciones -> {}
            default -> {return null;}
        }
        return o;
    }

    public static MyEntitys GetSelectedModelFromString(String model) {
        MyEntitys myEntitys;
        switch (model.toUpperCase()) {
            case "PROVEEDORES", "PROVEEDOR", "PROVEEDORESENTITY" -> {
                myEntitys = MyEntitys.Proveedores;
            }
            case "PIEZAS", "PIEZA", "PIEZASENTITY" -> {
                myEntitys = MyEntitys.Piezas;
            }
            case "PROYECTOS", "PROYECTO", "PROYECTOSENTITY" -> {
                myEntitys = MyEntitys.Proyectos;
            }
            case "ASIGNACIONES", "ASIGNACION", "GESTION", "ASIGNACIONESENTITY", "GESTIONENTITY" -> {
                myEntitys = MyEntitys.Asignaciones;
            }
            default -> throw new IllegalStateException("Modelo no reconocido: " + model.toUpperCase());
        }
        return myEntitys;
    }

    /**
     * Procedimiento que devuelve un JPanel personalizado para las clases deseadas
     * Clases contempladas Piezas, Proveedores y Proyectos.
     * Metodo Sobrecargado para permitir la entrada de Strings o enum Myclass
     *
     * @param clase
     * @return
     */
    public static DinamicJpanel CreateDataPanel(String clase) {
        return CreateDataPanel(GetSelectedModelFromString(clase.toUpperCase()));
    }

    public static DinamicJpanel CreateDataPanel(MyEntitys clazz) {
        return new DinamicJpanel(clazz);
    }

    /**
     * Procedimiento que devuelve una lista de todos los JTextfields contenidos en un contenedor
     */
    public static List<JTextField> GetJTextFieldsFromJPanel(Container myContainer){

        List<JTextField> myList = new ArrayList<>();
        Component[] myComps = myContainer.getComponents();

        for (Component myComp : myComps) {
            if (myComp instanceof JPanel myPanel) {
                GetJTextFieldsFromJPanel(myPanel);
            }
            if (myComp instanceof JTextField myTextField) {
                myList.add(myTextField);
            }
        }
        return myList;
    }

    //Procedimeinto que limpia todos lo JtextFields de un contenedor y los deja vacios.
    public static void ClearAllFields(Container myContainer) {

        // ref --> https://newbedev.com/java-get-jpanel-components
        Component[] myComps = myContainer.getComponents();

        for (Component myComp : myComps) {
            if (myComp instanceof JPanel myPanel) {
                ClearAllFields(myPanel);
            }
            if (myComp instanceof JTextField myTextField) {
                myTextField.setText("");
            }
        }
    }

    public static void TrimAllFields(Container myContainer) {

        // ref --> https://newbedev.com/java-get-jpanel-components
        Component[] myComps = myContainer.getComponents();

        for (Component myComp : myComps) {
            if (myComp instanceof JPanel myPanel) {
                TrimAllFields(myPanel);
            }
            if (myComp instanceof JTextField myTextField) {
                myTextField.setText(myTextField.getText().trim());
            }
        }
    }

    public static void DisableAllFields(Container myContainer) {

        // ref --> https://newbedev.com/java-get-jpanel-components
        Component[] myComps = myContainer.getComponents();

        for (Component myComp : myComps) {
            if (myComp instanceof JPanel myPanel) {
                DisableAllFields(myPanel);
            }
            if (myComp instanceof JTextField myTextField) {
                myTextField.setEnabled(false);
            }
        }
    }

}
