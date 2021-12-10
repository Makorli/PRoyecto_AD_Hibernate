package com.Views;

import com.Controllers.ProveedoresDAO;
import com.Controllers.ViewsController;
import com.Model.PiezasEntity;
import com.Model.ProveedoresEntity;
import com.Model.ProyectosEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class SuministrosProveedor {
    private JPanel JPGeneral;
    private JComboBox cbProv;
    private JTextField tfNombre;
    private JTextField tfApellidos;
    private JTextField tfDireccion;
    private JButton btConsulta;
    private JTextField tfNpiezas;
    private JTextField tfNProyectos;
    private JPanel JPProveedor;
    private JPanel JPResultados;

    //Controladores de entidades utilizadas en las ventanas.
    private ProveedoresDAO ctProvs;             //Controlador de Proveedores

    //Otros objetos utilizados
    ProveedoresEntity selectedProv;

    public SuministrosProveedor() {
        ViewsController.DisableAllFields(JPGeneral);
        //Instanciamos controladores
        this.ctProvs = new ProveedoresDAO();

        //Cargamos los comboboxes con el contenido necesario
        loadCbSelector(cbProv, ctProvs.getAll());
        cbProv.setSelectedIndex(-1);
        //Listener
        cbProv.addItemListener(e -> {
            int items = cbProv.getItemCount();
            boolean isChanged = (e.getStateChange() == ItemEvent.SELECTED);

            //Si el combobox contiene elementos y ha cambiado su estado.
            if (items > 0 && isChanged) {
                Object o = cbProv.getSelectedItem();
                assert o != null;
                limpiar();
                selectedProv = (ProveedoresEntity) o;
                tfNombre.setText(String.format("%s", selectedProv.getNombre()));
                tfApellidos.setText(String.format("%s", selectedProv.getApellidos()));
                tfDireccion.setText(String.format("%s ", selectedProv.getDireccion()));
            }
        });
        btConsulta.addActionListener(e -> {
            assert selectedProv != null;
            tfNpiezas.setText(String.format("%.0f",
                    ctProvs.getTotalPartsSupplied(selectedProv))
            );
            tfNProyectos.setText(String.format("%d",
                    ctProvs.getNumberProyects(selectedProv))
            );
        });
    }

    public JPanel getJPGeneral() {
        return JPGeneral;
    }

    private void limpiar(){

        tfNProyectos.setText("");
        tfNpiezas.setText("");
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
        jComboBox.setRenderer(new SuministrosProveedor.searchFieldRenderer());
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
