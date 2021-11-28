package com.Controllers;

import com.Model.AsignacionesEntity;
import com.Model.PiezasEntity;
import com.Model.ProveedoresEntity;
import com.Model.ProyectosEntity;
import com.Views.DinamicJpanel;
import com.Views.HintTextField;

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
     *
     * @param myCustomPanel
     */
    public static Object getObjectFromMyCustomView(DinamicJpanel myCustomPanel) {
        //Preguntamos el tipo de panel que es
        Object o = null;
        switch (myCustomPanel.getType()) {
            case Proveedores -> {
                ProveedoresEntity myEntity = new ProveedoresEntity();
                for (JTextField field : myCustomPanel.getFieldsMap().values()) {
                    switch (field.getName()) {
                        case "codigo" -> {
                            myEntity.setCodigo(field.getText());
                        }
                        case "nombre" -> {
                            myEntity.setNombre(field.getText());
                        }
                        case "apellidos" -> {
                            myEntity.setApellidos(field.getText());
                        }
                        case "direccion" -> {
                            myEntity.setDireccion(field.getText());
                        }
                    }
                }
                o = myEntity;
            }
            case Proyectos -> {
                ProyectosEntity myEntity = new ProyectosEntity();
                for (JTextField field : myCustomPanel.getFieldsMap().values()) {
                    switch (field.getName()) {
                        case "codigo" -> {
                            myEntity.setCodigo(field.getText());
                        }
                        case "nombre" -> {
                            myEntity.setNombre(field.getText());
                        }
                        case "ciudad" -> {
                            myEntity.setCiudad(field.getText());
                        }
                    }
                }
                o = myEntity;
            }
            case Piezas -> {
                PiezasEntity myEntity = new PiezasEntity();
                for (JTextField field : myCustomPanel.getFieldsMap().values()) {
                    switch (field.getName()) {
                        case "codigo" -> {
                            myEntity.setCodigo(field.getText());
                        }
                        case "nombre" -> {
                            myEntity.setNombre(field.getText());
                        }
                        case "precio" -> {
                            myEntity.setPrecio(Double.parseDouble(field.getText()));
                        }
                        case "descripcion" -> {
                            myEntity.setDescripcion(field.getText());
                        }
                    }
                }
                o = myEntity;
            }
            case Asignaciones -> {
            }
            default -> {
                return null;
            }
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
     * Funcion que devuelve un objeto del tipo defindio en el dinamic panel
     *
     * @param dinamicJpanel con los JTextFields que contines los datos
     * @return Objeto o null en caso de no ser posible la converisión.
     */
    public static Object GetObjectFromPanel(DinamicJpanel dinamicJpanel) {
        //Leemos la clase de panel dinamico que estamos manejando.
        MyEntitys myEntity = dinamicJpanel.getType();

        //Rellenamos los campos de los Textfield contenidos
        switch (myEntity) {
            //nomenclatura de JTextfields --> tb+campo
            case Proveedores -> {
                //campos = new String[]{"Codigo", "Nombre", "Apellidos", "Direccion"};
                try {
                    ProveedoresEntity p = new ProveedoresEntity();
                    p.setCodigo(dinamicJpanel.getFieldsMap().get("tbcodigo").getText());
                    p.setNombre(dinamicJpanel.getFieldsMap().get("tbnombre").getText());
                    p.setApellidos(dinamicJpanel.getFieldsMap().get("tbapellidos").getText());
                    p.setDireccion(dinamicJpanel.getFieldsMap().get("tbdireccion").getText());
                } catch (Exception e) {
                    System.out.println("Error en la generación de objeto");
                }

            }
            case Piezas -> {
                // campos = new String[]{"Codigo", "Nombre", "Precio", "Descripcion"};
                try {
                    PiezasEntity p = new PiezasEntity();
                    p.setCodigo(dinamicJpanel.getFieldsMap().get("tbcodigo").getText());
                    p.setNombre(dinamicJpanel.getFieldsMap().get("tbnombre").getText());
                    p.setPrecio(Double.parseDouble(dinamicJpanel.getFieldsMap().get("tbprecio").getText()));
                    p.setDescripcion(dinamicJpanel.getFieldsMap().get("tbdescripcion").getText());
                } catch (Exception e) {
                    System.out.println("Error en la generación de objeto");
                }
            }
            case Proyectos -> {
                // campos = new String[]{"Codigo", "Nombre", "Ciudad"};
                try {
                    ProyectosEntity p = new ProyectosEntity();
                    p.setCodigo(dinamicJpanel.getFieldsMap().get("tbcodigo").getText());
                    p.setNombre(dinamicJpanel.getFieldsMap().get("tbnombre").getText());
                    p.setCiudad(dinamicJpanel.getFieldsMap().get("tbciudad").getText());
                } catch (Exception e) {
                    System.out.println("Error en la generación de objeto");
                }

            }
            case Asignaciones -> {
                try {
                    AsignacionesEntity p = new AsignacionesEntity();
                } catch (Exception e) {
                    System.out.println("Error en la generación de objeto");
                }
                return true;
            }
        }
        return false;
    }

    /*** Funcion que establece los datos definidos en un objeto dentro en un DinamicPanel
     * El objeto debe ser spoportado por el dinamic panel
     * @param o Objeto a insertar en un dinamic panel
     * @param dinamicJpanel con los datos del objeto
     * @return boolean si se ha podido realizar la conexión.
     */
    public static boolean setObjectToDinamicPanel(Object o, DinamicJpanel dinamicJpanel) {
        //Leemos la clase de panel dinamico que estamos manejando.
        MyEntitys myEntity = dinamicJpanel.getType();

        //Rellenamos los campos de los Textfield contenidos
        switch (myEntity) {
            //nomenclatura de JTextfields --> tb+campo
            case Proveedores -> {
                //campos = new String[]{"Codigo", "Nombre", "Apellidos", "Direccion"};
                if (o instanceof ProveedoresEntity p) {
                    dinamicJpanel.getFieldsMap().get("tbcodigo").setText(p.getCodigo());
                    dinamicJpanel.getFieldsMap().get("tbnombre").setText(p.getNombre());
                    dinamicJpanel.getFieldsMap().get("tbapellidos").setText(p.getApellidos());
                    dinamicJpanel.getFieldsMap().get("tbdireccion").setText(p.getDireccion());
                    dinamicJpanel.getJpDataLines().repaint();
                }
            }
            case Piezas -> {
                // campos = new String[]{"Codigo", "Nombre", "Precio", "Descripcion"};
                if (o instanceof PiezasEntity p) {
                    dinamicJpanel.getFieldsMap().get("tbcodigo").setText(p.getCodigo());
                    dinamicJpanel.getFieldsMap().get("tbnombre").setText(p.getNombre());
                    dinamicJpanel.getFieldsMap().get("tbprecio").setText(String.valueOf(p.getPrecio()));
                    dinamicJpanel.getFieldsMap().get("tbdescripcion").setText(p.getDescripcion());
                    dinamicJpanel.getJpDataLines().repaint();
                }
            }
            case Proyectos -> {
                // campos = new String[]{"Codigo", "Nombre", "Ciudad"};
                if (o instanceof ProyectosEntity p) {
                    dinamicJpanel.getFieldsMap().get("tbcodigo").setText(p.getCodigo());
                    dinamicJpanel.getFieldsMap().get("tbnombre").setText(p.getNombre());
                    dinamicJpanel.getFieldsMap().get("tbciudad").setText(p.getCiudad());
                    dinamicJpanel.getJpDataLines().repaint();
                }
            }
            case Asignaciones -> {
                if (o instanceof AsignacionesEntity) {
                    AsignacionesEntity p = (AsignacionesEntity) o;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Procedimiento que devuelve una lista de todos los JTextfields contenidos en un contenedor
     */
    public static List<JTextField> GetJTextFieldsFromJPanel(Container myContainer) {

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
            if (myComp instanceof HintTextField myHintTextField) {
                myHintTextField.setText("");
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
            if (myComp instanceof HintTextField myHintTextField) {
                myHintTextField.setText(myHintTextField.getText().trim());
            }
        }
    }

    public static void DisableAllFields(Container myContainer) {
        setEnableAllFields(myContainer,false);
    }

    public static void EnableAllFields(Container myContainer){
        setEnableAllFields(myContainer,true);
    }

    private static void setEnableAllFields(Container myContainer, boolean state){
        Component[] myComps = myContainer.getComponents();

        for (Component myComp : myComps) {
            if (myComp instanceof JPanel myPanel) {
                setEnableAllFields(myPanel,state);
            }
            if (myComp instanceof JTextField myTextField) {
                myTextField.setEnabled(state);
            }
            if (myComp instanceof HintTextField myHintTextField) {
                myHintTextField.setEnabled(state);
            }
        }
    }


}
