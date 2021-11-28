package com.Views;

import com.Controllers.*;
import com.Model.PiezasEntity;
import com.Model.ProveedoresEntity;
import com.Model.ProyectosEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class AsignacionesView {
    private JPanel JPGeneral;
    private JButton insertarButton;
    private JPanel JPBotonera;
    private JButton modificarButton;
    private JButton eliminarButton;
    private JButton listadoButton;
    private JPanel JPTitulo;
    private JComboBox cbProv;
    private JTextField tfProv1;
    private JTextField tfProv2;
    private JPanel JPProveedor;
    private JPanel JPData;
    private JPanel JPControl;
    private JPanel JPPIeza;
    private JTextField tfPieza1;
    private JTextField tfPieza2;
    private JPanel JPProject;
    private JTextField tfProject1;
    private JTextField tfProject2;
    private JPanel JPCantidad;
    private JTextField tfCantidad;
    private JComboBox cbPieza;
    private JComboBox cbProyecto;

    //Controladores de entidades utilizadas en las ventanas.
    private ProveedoresDAO ctProvs;             //Controlador de Proveedores
    private ProyectosDAO ctProjects;            //Controlador de Proyectos
    private PiezasDAO ctPiezas;                 //Controlador de Piezas
    private AsignacionesDAO ctAsignaciones;     //Controlador de Asignaciones

    List miLista;

    public JPanel getJPGeneral() {
        return JPGeneral;
    }

    public AsignacionesView() {
        ViewsController.DisableAllFields(JPData);
        //Instanciamos controladores
        this.ctProvs = new ProveedoresDAO();
        this.ctPiezas = new PiezasDAO();
        this.ctProjects = new ProyectosDAO();

        //Inicializamos Comboboxes con un nombre y le cargamos el contenido y generamos los listeners
        cbProv.setName(MyEntitys.Proveedores.name());
        cbProyecto.setName(MyEntitys.Proyectos.name());
        cbPieza.setName(MyEntitys.Piezas.name());

        //Cargamos los comoboxes con el contenido necesario
        loadCbSelector(cbProv,ctProvs.getAll());
        loadCbSelector(cbProyecto,ctProjects.getAll());
        loadCbSelector(cbPieza,ctPiezas.getAll());

        //Creamos los listeners de los Comboboxes
        cbProv.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //Solo realizamos cambios en el panel si el Combobox ha cambiado realmente

                int items = cbProv.getItemCount();
                boolean isChanged = (e.getStateChange() == ItemEvent.SELECTED);

                //Si el combobox contiene elementos y ha cambiado su estado.
                if (items > 0 && isChanged) {
                    Object o = cbProv.getSelectedItem();
                    assert o != null;
                    ProveedoresEntity p =(ProveedoresEntity) o;
                    tfProv1.setText(String.format("%s , %s",p.getNombre(),p.getApellidos()));
                    tfProv2.setText(String.format("%s ",p.getDireccion()));
                }
            }
        });

        cbProyecto.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //Solo realizamos cambios en el panel si el Combobox ha cambiado realmente

                int items = cbProyecto.getItemCount();
                boolean isChanged = (e.getStateChange() == ItemEvent.SELECTED);

                //Si el combobox contiene elementos y ha cambiado su estado.
                if (items > 0 && isChanged) {
                    Object o = cbProyecto.getSelectedItem();
                    assert o != null;
                    ProyectosEntity p =(ProyectosEntity) o;
                    tfProject1.setText(String.format("%s",p.getNombre()));
                    tfProject2.setText(String.format("%s",p.getCiudad()));
                }
            }
        });

        cbPieza.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //Solo realizamos cambios en el panel si el Combobox ha cambiado realmente

                int items = cbPieza.getItemCount();
                boolean isChanged = (e.getStateChange() == ItemEvent.SELECTED);

                //Si el combobox contiene elementos y ha cambiado su estado.
                if (items > 0 && isChanged) {
                    Object o = cbPieza.getSelectedItem();
                    assert o != null;
                    PiezasEntity p =(PiezasEntity) o;
                    tfPieza1.setText(String.format("%s - %s",p.getNombre(),p.getDescripcion()==null?"":p.getDescripcion()));
                    tfPieza2.setText(String.format("%.2f €",p.getPrecio()));
                }
            }
        });
        
    }


    /**
     * Procedmiento de carga de lista mostrada en el panel myListadosPanel
     */
    private void populateList(MyEntitys myEntity) {
        switch (myEntity) {
            case Proveedores -> {
                miLista = ctProvs.getAll();
            }
            case Proyectos -> {
                miLista = ctProjects.getAll();
            }
            case Piezas -> {
                miLista = ctPiezas.getAll();
            }
        }
        if (miLista.isEmpty()) {
            JOptionPane.showMessageDialog(
                    null,
                    String.format("No se han encontrado %s para listar", "Elementos"),
                    String.format("Listado de %s", "Asignaciones"),
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Procedimiento de carga de una lista generica en un combobox
     *
     * @param list
     */
    private void loadCbSelector(JComboBox jComboBox, List list) {
        DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel<>();

        for (Object p : new ArrayList<>(list)) {
            defaultComboBoxModel.addElement(p);
        }
        jComboBox.setModel(defaultComboBoxModel);
        jComboBox.setRenderer(new searchFieldRenderer());
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

            if (value instanceof ProveedoresEntity) {
                ProveedoresEntity p = (ProveedoresEntity) value;
                value = String.format("%S - (%s %s)", p.getCodigo(), p.getNombre(), p.getApellidos());

            } else if (value instanceof ProyectosEntity) {
                ProyectosEntity p = (ProyectosEntity) value;
                value = String.format("%S - (%s)", p.getCodigo(), p.getNombre());

            } else if (value instanceof PiezasEntity) {
                PiezasEntity p = (PiezasEntity) value;
                value = String.format("%S - (%s)", p.getCodigo(), p.getNombre());

            }
            return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        }

    }

}
