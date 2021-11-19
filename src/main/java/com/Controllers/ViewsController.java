package com.Controllers;

import javax.swing.*;
import java.awt.*;

/**
 * Clase genrica para la obtención de objetos de vistas a usar en las vistas generales
 * definidas en el package View
 */
public class ViewsController {

    public enum MyClass {
        Proveedores,
        Piezas,
        Asignaciones,
        Proyectos
    }

    public static MyClass getSelectedModelFromString(String model) {
        MyClass myClass;
        switch (model.toUpperCase()) {
            case "PROVEEDORES", "PROVEEDOR", "PROVEEDORESENTITY" -> {
                myClass = MyClass.Proveedores;
            }
            case "PIEZAS", "PIEZA", "PIEZASENTITY" -> {
                myClass = MyClass.Piezas;
            }
            case "PROYECTOS", "PROYECTO", "PROYECTOSENTITY" -> {
                myClass = MyClass.Proyectos;
            }
            case "ASIGNACIONES", "ASIGNACION", "GESTION", "ASIGNACIONESENTITY", "GESTIONENTITY" -> {
                myClass = MyClass.Asignaciones;
            }
            default -> throw new IllegalStateException("Modelo no reconocido: " + model.toUpperCase());
        }
        return myClass;
    }

    /**
     * Procedimiento que devuelve un JPanel personalizado para las clases deseadas
     * Clases contempladas Piezas, Proveedores y Proyectos.
     * Metodo Sobrecargado para permitir la entrada de Strings o enum Myclass
     *
     * @param clase
     * @return
     */
    public static JPanel getDataPanel(String clase) {
        return getDataPanel(getSelectedModelFromString(clase.toUpperCase()));
    }

    public static JPanel getDataPanel(MyClass clazz) {

        JPanel myDataPanel = new JPanel();

        switch (clazz) {

            /* ref -> https://stackoverflow.com/questions/8545301/put-text-boxes-in-separate-lines */
            case Proveedores -> {
                String[] campos = {"Codigo", "Nombre", "Apellidos", "Direccion"};

                JPanel dataLines = new JPanel(new GridLayout(0, 2));

                for (String campo : campos) {
                    JLabel label = new JLabel(campo + ": ", SwingConstants.RIGHT);
                    label.setMinimumSize(new Dimension(-1, -1));
                    dataLines.add(label);
                    dataLines.add("tb" + campo, new JTextField(20));
                }

                myDataPanel.add(dataLines, BorderLayout.CENTER);
                //ref -> https://stackoverflow.com/questions/5854005/setting-horizontal-and-vertical-margins
                myDataPanel.setBorder(
                        BorderFactory.createEmptyBorder(10, 20, 10, 20));

                return myDataPanel;
            }
            case Piezas -> {
                String[] campos = {"Codigo", "Nombre", "Precio", "Descripcion"};

                JPanel dataLines = new JPanel(new GridLayout(0, 2));

                for (String campo : campos) {
                    JLabel label = new JLabel(campo + ": ", SwingConstants.RIGHT);
                    label.setMinimumSize(new Dimension(-1, -1));
                    dataLines.add(label);
                    dataLines.add("tb" + campo, new JTextField(20));
                }

                myDataPanel.add(dataLines, BorderLayout.CENTER);
                myDataPanel.setBorder(
                        BorderFactory.createEmptyBorder(10, 20, 10, 20));

                return myDataPanel;
            }

            case Proyectos -> {
                String[] campos = {"Codigo", "Nombre", "Ciudad"};

                JPanel dataLines = new JPanel(new GridLayout(0, 2));

                for (String campo : campos) {
                    JLabel label = new JLabel(campo + ": ", SwingConstants.RIGHT);
                    label.setMinimumSize(new Dimension(-1, -1));
                    dataLines.add(label);
                    dataLines.add("tb" + campo, new JTextField(20));
                }

                myDataPanel.add(dataLines, BorderLayout.CENTER);
                myDataPanel.setBorder(
                        BorderFactory.createEmptyBorder(10, 20, 10, 20));

                return myDataPanel;
            }

            case Asignaciones -> {}

            default -> {
            }
        }
        return myDataPanel;
    }
}
