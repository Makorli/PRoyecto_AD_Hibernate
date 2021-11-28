package com.Views;

import com.Controllers.*;
import com.Model.PiezasEntity;
import com.Model.ProveedoresEntity;
import com.Model.ProyectosEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class QueryView {
    private JPanel JPGeneral;
    private JTextField tfBusqueda;
    private JButton btBuscar;
    private JComboBox cbSelector;
    private JPanel JPBusqueda;
    private JPanel JPDatosQuery;
    private JPanel JPSelector;
    private JLabel lbDescripcion;

    //COMPONENENTES PERSONALIZADOS  Y DEPENDIENTES DEL TIPO DE VENTANA
    MyEntitys tipoVentana;  //Tipo de Objeto que muestra el QueryView
    private final String campoBusqueda; // Campo por el que se realiza la busqueda
    private DinamicJpanel myCustomPanel;

    //Controladores Posibles
    private ProveedoresDAO ctProvs; //Controlador de Proveedores
    private ProyectosDAO ctProjects; //Controlador de Proyectos
    private PiezasDAO ctPiezas; //Controlador de Piezas

    //List generica e indices de control
    private List miLista;


    //CONSTRUCTORES

    public QueryView(MyEntitys myEntitys, String campo) {
        tipoVentana = myEntitys;
        this.campoBusqueda = campo;
        this.lbDescripcion.setText(
                String.format("Escribe %S o una parte", this.campoBusqueda)
        );
        //Inicializamos los listeners de los botones
        initListeners();

        //Inicializamos la lista de busquedas.
        miLista = new ArrayList();
        //Creamos el controlador especifico seún el tipoVentana
        switch (tipoVentana) {
            case Proveedores -> this.ctProvs = new ProveedoresDAO();
            case Piezas -> this.ctPiezas = new PiezasDAO();
            case Proyectos -> this.ctProjects = new ProyectosDAO();
        }
    }

    private void createUIComponents() {
        //Construimos la ventana de visualizacion de objetos dinamicamente dependiendo
        // de la clase de busqueda elegida.
        myCustomPanel = ViewsController.CreateDataPanel(tipoVentana);

        //Pintamos el Panel
        JPDatosQuery = myCustomPanel.getJpDataLines();
        ViewsController.DisableAllFields(JPDatosQuery);

    }

    //Procedimiento que inicializa los listeners del panel
    public void initListeners() {

        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String myValue = tfBusqueda.getText().trim();
                ViewsController.ClearAllFields(JPGeneral);
                //Ejecutamos la busqueda
                switch (tipoVentana) {
                    case Proveedores -> {
                        //{"Codigo", "Nombre", "Apellidos", "Direccion"}
                        miLista = ctProvs.getAllByStringSearch(campoBusqueda, "%" + myValue + "%");
                    }
                    case Piezas -> {
                        //{"Codigo", "Nombre", "Precio", "Descripcion"}
                        miLista = ctPiezas.getAllByStringSearch(campoBusqueda, "%" + myValue + "%");
                    }
                    case Proyectos -> {
                        //{"Codigo", "Nombre", "Ciudad"}
                        miLista = ctProjects.getAllByStringSearch(campoBusqueda, "%" + myValue + "%");
                    }
                }
                loadCbSelector(miLista);
                //Si solo hay un elemento en la lista lo cargamos directamente.
                switch (miLista.size()) {
                    //No hay resultado de la busqueda mostramos mensaje de aviso
                    case 0: {
                        JOptionPane.showMessageDialog(
                                null,
                                "No se han encontrado resultados",
                                "Busquedas",
                                JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                    //Seleccionamos el único objeto de la busqueda y lo cargamos en el panel.
                    case 1: {
                        cbSelector.setSelectedIndex(0);
                        ViewsController.setObjectToDinamicPanel(
                                cbSelector.getSelectedItem(),
                                myCustomPanel);
                        myCustomPanel.repaint();
                        break;
                    }
                    //Busqueda devuelve más de un resultado
                    default: {
                        myCustomPanel.repaint();
                        break;
                    }
                }
            }
        });

        cbSelector.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //Solo realizamos cambios en el panel si el Combobox ha cambiado realmente

                int items = cbSelector.getItemCount();
                boolean isChanged = (e.getStateChange() == ItemEvent.SELECTED);

                //Si el combobox contiene elementos y ha cambiado su estado.
                if (items > 0 && isChanged) {
                    Object o = cbSelector.getSelectedItem();
                    assert o != null;
                    ViewsController.setObjectToDinamicPanel(o, myCustomPanel);
                    myCustomPanel.repaint();
                }
            }
        });


    }

    /**
     * Procedimiento de carga de una lista generica en un combobox
     *
     * @param list
     */
    private void loadCbSelector(List list) {
        DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel<>();

        for (Object p : new ArrayList<>(list)) {
            defaultComboBoxModel.addElement(p);
        }
        cbSelector.setModel(defaultComboBoxModel);
        cbSelector.setRenderer(new searchFieldRenderer());
    }

    /**
     * Clase especifica de renderizado para el combobox de la vista.
     * Admite diferentes tipos de objetos seleccionando el campo apropiado para mostrar en funcion
     * del campo de busqueda especificado en la ventana.
     */
    private class searchFieldRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {

            String cb = campoBusqueda.toLowerCase();
            if (value instanceof ProveedoresEntity) {
                ProveedoresEntity p = (ProveedoresEntity) value;
                switch (cb) {
                    case "codigo" -> {
                        String.format("%S - (%s %s)", p.getCodigo(), p.getNombre(), p.getApellidos());
                        value = String.format("%S - (%s %s)", p.getCodigo(), p.getNombre(), p.getApellidos());
                    }
                    case "nombre" -> {
                        value = String.format("%s - ( %S %s)", p.getCodigo(), p.getNombre(), p.getApellidos());
                    }
                    case "apellidos" -> {
                        value = String.format("%s - (%s %S)", p.getCodigo(), p.getNombre(), p.getApellidos());
                    }
                    case "direccion" -> {
                        value = String.format("%S - (%s %s) %s", p.getCodigo(), p.getNombre(), p.getApellidos(), p.getDireccion());
                    }
                }
            } else if (value instanceof ProyectosEntity) {
                ProyectosEntity p = (ProyectosEntity) value;
                switch (cb) {
                    case "codigo" -> {
                        value = String.format("%S - (%s)", p.getCodigo(), p.getNombre());
                    }
                    case "nombre" -> {
                        value = String.format("%S - (%s)", p.getNombre(), p.getCodigo());
                    }
                    case "ciudad" -> {
                        value = String.format("%S - (%s) %S", p.getCodigo(), p.getNombre(), p.getCiudad());
                    }
                }
            } else if (value instanceof PiezasEntity) {
                PiezasEntity p = (PiezasEntity) value;
                switch (cb) {
                    case "codigo" -> {
                        value = String.format("%S - (%s)", p.getCodigo(), p.getNombre());
                    }
                    case "nombre" -> {
                        value = String.format("%S - (%s)", p.getNombre(), p.getCodigo());
                    }
                    case "descripcion" -> {
                        value = String.format("%S - (%s) - %S", p.getCodigo(), p.getNombre(), p.getDescripcion());
                    }
                }
            }

            return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        }

    }

    public JPanel getJPGeneral() {
        return JPGeneral;
    }

}
