package com.Views;

import com.Controllers.MyEntitys;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//Clase personalizada de JPanel de contenido dinámico según los modelos.
public class DinamicJpanel extends JPanel {

    private final MyEntitys type;
    private final HashMap<String, HintTextField> fields;
    private final JPanel jpDataLines;

    public DinamicJpanel(MyEntitys type) {
        //super(new GridLayout(0, 1));
        super(new BorderLayout());

        this.type = type;
        fields = new HashMap<>();
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
            }
            default -> {
            }
        }

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

    public HashMap<String, HintTextField> getFieldsMap() {
        return fields;
    }

    public MyEntitys getType() {
        return type;
    }

    public List<String> getFieldsNames() {
        return new ArrayList<>(this.fields.keySet());
    }

    public JPanel getJpDataLines() {
        return jpDataLines;
    }

}
