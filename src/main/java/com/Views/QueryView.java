package com.Views;

import javax.swing.*;
import java.awt.*;

public class QueryView {
    private JPanel JPGeneral;
    private JTextField tfBusqueda;
    private JButton btBuscar;
    private JComboBox cbSelector;
    private JPanel JPBusqueda;
    private JPanel JPDatosQuery;
    private JPanel JPSelector;
    private JLabel lbDescripcion;

    //Variables de tipo de busqueda
    private String claseBusqueda;
    private String campoBusqueda;
    //CONSTRUCTORES


    public QueryView() {
    }

    public QueryView(String clase, String campo) {
        this.claseBusqueda = clase;
        this.campoBusqueda = campo;
        EventQueue.invokeLater(this::buildDataPanel);
    }

    public void buildDataPanel(){

        switch (claseBusqueda.toUpperCase()){

            /* ref -> https://stackoverflow.com/questions/8545301/put-text-boxes-in-separate-lines */
            case "PROVEEDORES", "PROVEEDOR" -> {

                //Campos a mostrar de proveedores. (Codigo, Nombre, Apellidos, Dirección)
                JTextField codigo = new JTextField(10);
                JTextField nombre = new JTextField(10);
                JTextField apellidos = new JTextField(10);
                JTextField direccion = new JTextField(10);

                this.JPDatosQuery = new JPanel();

                JPanel labels = new JPanel(new GridLayout(0,1));
                JPanel data = new JPanel(new GridLayout(0,1));
                this.JPDatosQuery.add(labels, BorderLayout.WEST);
                this.JPDatosQuery.add(data, BorderLayout.CENTER);

                labels.add(new JLabel("Codigo: "));
                data.add(codigo);
                labels.add(new JLabel("Nombre: "));
                data.add(nombre);
                labels.add(new JLabel("Apellidos: "));
                data.add(apellidos);
                labels.add(new JLabel("Dirección: "));
                data.add(direccion);

                //JPDatosQuery.add(submit, BorderLayout.SOUTH);
            }

            case "PIEZAS", "PIEZA" -> {}

            case "PROYECTOS", "PROYECTO" -> {}

            default -> {}
        }
    }

    public JPanel getJPGeneral() {
        return JPGeneral;
    }
}
