package com.Views;

import com.Controllers.*;
import com.Views.DinamicJpanel;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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

    MyEntitys tipoVentana;  //Tipo de Objeto que muestra el QueryView
    private DinamicJpanel myCustomPanel; //Panel personalizado
    GenericDAO controller;

    //Controladores Posibles
    private ProveedoresDAO ctProvs; //Controlador de Proveedores
    private ProyectosDAO ctProjects; //Controlador de Proyectos
    private PiezasDAO ctPiezas; //Controlador de Piezas
    private AsignacionesDAO ctAsigns; //Controlador de Asignaciones


    //CONSTRUCTORES

    public QueryView(MyEntitys myEntitys, String campo) {
        tipoVentana = myEntitys;
        this.campoBusqueda = campo;
        this.lbDescripcion.setText(
                String.format("Escribe %s o una parte", this.campoBusqueda.toUpperCase())
        );
        //Inicializamos los listeners de los botones
        initListeners();
        //Creamos el controlador especifico seún el tipoVentana
        switch (tipoVentana){
            case Proveedores -> this.ctProvs = new ProveedoresDAO();
            case Piezas -> this.ctPiezas = new PiezasDAO();
            case Proyectos -> this.ctProjects = new ProyectosDAO();
            case Asignaciones -> this.ctAsigns = new AsignacionesDAO();
        }
    }

    private void createUIComponents() {
        //Construimos la ventana de visualizacion de objetos dinamicamente dependiendo
        // de la clase de busqueda elegida.
        tipoVentana = ViewsController.GetSelectedModelFromString(claseBusqueda);
        myCustomPanel = ViewsController.CreateDataPanel(tipoVentana);

        //Pintamos el PAnel
        JPDatosQuery = myCustomPanel.getDataLines();

        switch (tipoVentana){
            case Proveedores -> this.ctProvs = new ProveedoresDAO();
            case Piezas -> this.ctPiezas = new PiezasDAO();
            case Proyectos -> this.ctProjects = new ProyectosDAO();
            case Asignaciones -> this.ctAsigns = new AsignacionesDAO();
        }
    }

    //Procedimiento que inicializa los listeners del panel
    public void initListeners(){
        tfBusqueda.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                switch (tipoVentana){
                    case Proveedores -> {
                        //{"Codigo", "Nombre", "Apellidos", "Direccion"}
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

}
