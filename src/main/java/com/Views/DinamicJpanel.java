package com.Views;

import com.Controllers.MyEntitys;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//Clase personalizada de JPanel de contenido dinámico según los modelos.
public class DinamicJpanel extends JPanel {

    private MyEntitys type;
    private HashMap<String, HintTextField> fields;
    private HashMap<String,Object> asignacionesFields;
    private JPanel jpDataLines;

    public DinamicJpanel(MyEntitys type) {

        super(new BorderLayout());

        this.type = type;
        fields = new HashMap<>();
        asignacionesFields = new HashMap<>();
        String[] campos = new String[0];

        //Definimos los campos
        switch (type) {
            /* ref -> https://stackoverflow.com/questions/8545301/put-text-boxes-in-separate-lines */
            case Proveedores -> {
                campos = new String[]{"Codigo", "Nombre", "Apellidos", "Direccion"};
            }
            case Piezas -> {
                campos = new String[]{"Codigo", "Nombre", "Precio", "Descripcion"};
            }
            case Proyectos -> {
                campos = new String[]{"Codigo", "Nombre", "Ciudad"};
            }
            case Asignaciones -> {
                //Se monta manualmente en la vista
            }
        }

        //Construimos el panel automaticamente
        switch (type) {
            case Proveedores, Piezas, Proyectos -> {
                //Construimos el panel con las etiquetas y campos definidos.
                this.jpDataLines = new JPanel(new GridLayout(campos.length, 2));
                //JPanel dataLines = new JPanel(new FlowLayout(FlowLayout.CENTER));

                for (String campo : campos) {
                    //Etiqueta
                    JLabel label = new JLabel(campo + ": ", SwingConstants.LEFT);
                    label.setMinimumSize(new Dimension(-1, -1));

                    //TextField
                    HintTextField field = new HintTextField(20);
                    String fieldName = "tb" + campo.toLowerCase();
                    field.setName(fieldName);

                    //Anexamos los objetos al jpanel datalines
                    jpDataLines.add(label);
                    jpDataLines.add(field);

                    //Anexamos el campo al diccionarion de la clase.
                    fields.put(fieldName, field);
                }
                //Añadimos los campos a nuestra clase
                this.add(jpDataLines, BorderLayout.CENTER);

                //Ponemos margen al JPanel.
                //ref -> https://stackoverflow.com/questions/5854005/setting-horizontal-and-vertical-margins
                this.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

            }
        }
    }

    public HashMap<String, HintTextField> getFieldsMap() {
            return fields;
    }

    public HashMap<String, Object> getAsignacionesFieldsMap() {
        return asignacionesFields;
    }

    public MyEntitys getType() {
        return type;
    }

    public List<String> getFieldsNames() {
        if (type == MyEntitys.Asignaciones)
            return new ArrayList<>(this.asignacionesFields.keySet());
        return new ArrayList<>(this.fields.keySet());
    }

    public JPanel getJpDataLines() {
        return jpDataLines;
    }

    public void setType(MyEntitys type) {
        this.type = type;
    }

    public void setJpDataLines(JPanel jpDataLines) {
        this.jpDataLines = jpDataLines;
    }
}
