package com.Views;

import com.Controllers.AsignacionesDAO;
import com.Controllers.PiezasDAO;
import com.Controllers.ProveedoresDAO;
import com.Controllers.ViewsController;
import com.Model.PiezasEntity;
import com.Model.ProveedoresEntity;
import com.Model.ProyectosEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class SuministrosPiezas {
    private JPanel JPGeneral;
    private JComboBox cbPiezas;
    private JTextField tfNombreDescr;
    private JTextField tfPrecio;
    private JTextField tfNproyectos;
    private JTextField tfNProvs;
    private JTextField tfTotalCant;
    private JTable JTProyProvs;
    private JButton btVerPiezas;
    private javax.swing.JScrollPane JScrollPane;
    private JPanel JPDatos;

    //Controladores de entidades utilizadas en las ventanas.
    private PiezasDAO ctPiezas;             //Controlador de Piezas
    private AsignacionesDAO ctAsignaciones;      //Controlador de Proveedores

    //Otros objetos utilizados
    PiezasEntity selectedPart;

    public JPanel getJPGeneral() {
        return JPGeneral;
    }

    public SuministrosPiezas() {
        ViewsController.DisableAllFields(JPGeneral);
        //Instanciamos controladores
        this.ctPiezas = new PiezasDAO();
        this.ctAsignaciones = new AsignacionesDAO();
        //Cargamos los comboboxes con el contenido necesario
        loadCbSelector(cbPiezas, ctPiezas.getAll());
        //inicializamos la Tabla de datos
        JScrollPane.setViewportView(JTProyProvs);
        JTProyProvs.setModel(new PiezasTablaModel(new PiezasEntity()));

        //Listeners

        cbPiezas.addItemListener(e -> {
            int items = cbPiezas.getItemCount();
            boolean isChanged = (e.getStateChange() == ItemEvent.SELECTED);

            //Si el combobox contiene elementos y ha cambiado su estado.
            if (items > 0 && isChanged) {
                Object o = cbPiezas.getSelectedItem();
                assert o != null;
                selectedPart = (PiezasEntity) o;
                JTProyProvs.setModel(new PiezasTablaModel(new PiezasEntity()));
                tfNombreDescr.setText(String.format("%s %s",
                        selectedPart.getNombre(),
                        (selectedPart.getDescripcion()==null)?"":"-> "+selectedPart.getDescripcion()));
                tfPrecio.setText(String.format("%.2f", selectedPart.getPrecio()));
            }
        });

        btVerPiezas.addActionListener(e -> {
            if (selectedPart==null){
                JOptionPane.showMessageDialog(
                        null,
                        "Tienes que seleccionar una pieza",
                        "Error Seleccion Pieza",
                        JOptionPane.ERROR_MESSAGE);
            }
            else{
                JTProyProvs.setModel(new PiezasTablaModel(selectedPart));
                tfNproyectos.setText(String.valueOf(ctPiezas.getNumberProyects(selectedPart)));
                tfNProvs.setText(String.valueOf(ctPiezas.getNumberProveedores(selectedPart)));
                tfTotalCant.setText(String.format("%.2f",ctAsignaciones.getPartCantProvided(selectedPart)));

            }
        });

    }




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
