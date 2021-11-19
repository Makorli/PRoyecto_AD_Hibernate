package com.Views;

import com.Controllers.GenericDAO;
import com.Controllers.ViewsController;
import com.Model.Proveedor;
import com.Model.ProveedoresEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Locale;

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

    //VAriable para el controlador de la clase seleccionada


    //CONSTRUCTORES
    public QueryView(String clase, String campo) {
        this.claseBusqueda =clase;
        this.campoBusqueda = campo;
        this.lbDescripcion.setText(
                String.format("Escribe %s o una parte", this.campoBusqueda.toUpperCase())
        );
        initListeners();
    }

    //Procedimiento que inicializa los listeners del panel
    public void initListeners(){
        ViewsController.MyClass myClass = ViewsController.getSelectedModelFromString(this.claseBusqueda);
        tfBusqueda.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                switch (myClass){
                    case Proveedores -> {
                        //{"Codigo", "Nombre", "Apellidos", "Direccion"}
                        GenericDAO myController = new GenericDAO(ProveedoresEntity.class);
                        ProveedoresEntity prov = new ProveedoresEntity(1,"sd","Adrian","Theone","Castillo");
                        myController.create(prov);
                    }
                    case Piezas -> {
                        //{"Codigo", "Nombre", "Precio", "Descripcion"}
                    }
                    case Proyectos -> {
                        //{"Codigo", "Nombre", "Ciudad"}
                    }
                    case Asignaciones -> {}
                }
            }
        });
    }

    public JPanel getJPGeneral() {
        return JPGeneral;
    }

    private void createUIComponents() {
        //Construimos la ventana de visualziacion de objetos dinamicamente dependiendo
        // de la clase de busqueda elegida.
        //Cada clase tiene sus campos
        // Proveedor --> {"Codigo", "Nombre", "Apellidos", "Direccion"}
        // Piezas --> {"Codigo", "Nombre", "Precio", "Descripcion"}
        // Proyectos --> {"Codigo", "Nombre", "Ciudad"}
        // Asignaciones --> {}
        JPDatosQuery = ViewsController.getDataPanel(claseBusqueda);


    }


}
