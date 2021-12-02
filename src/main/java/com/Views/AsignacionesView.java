package com.Views;

import com.Controllers.*;
import com.Model.AsignacionesEntity;
import com.Model.PiezasEntity;
import com.Model.ProveedoresEntity;
import com.Model.ProyectosEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

public class AsignacionesView {
    private JPanel JPGeneral;
    private JButton btInsertar;
    private JPanel JPBotonera;
    private JButton btModificar;
    private JButton btEliminar;
    private JButton btListado;
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

    //OTROS COMPONENTES UTILIZADOS
    private List miLista;
    private DinamicJpanel myDinamicPanel;

    public JPanel getJPGeneral() {
        return JPGeneral;
    }

    public AsignacionesView() {
        myDinamicPanel = getJPDataToDinamic();
        JPData = myDinamicPanel.getJpDataLines();
        ViewsController.DisableAllFields(JPData);
        tfCantidad.setEnabled(true);

        //Instanciamos controladores
        this.ctProvs = new ProveedoresDAO();
        this.ctPiezas = new PiezasDAO();
        this.ctProjects = new ProyectosDAO();
        this.ctAsignaciones = new AsignacionesDAO();

        initJComboboxes();

        //Creamos los listeners de los Comboboxes
        cbProv.addItemListener(e -> {
            //Solo realizamos cambios en el panel si el Combobox ha cambiado realmente

            int items = cbProv.getItemCount();
            boolean isChanged = (e.getStateChange() == ItemEvent.SELECTED);

            //Si el combobox contiene elementos y ha cambiado su estado.
            if (items > 0 && isChanged) {
                Object o = cbProv.getSelectedItem();
                assert o != null;
                ProveedoresEntity p = (ProveedoresEntity) o;
                tfProv1.setText(String.format("%s , %s", p.getNombre(), p.getApellidos()));
                tfProv2.setText(String.format("%s ", p.getDireccion()));
            }
        });
        cbProyecto.addItemListener(e -> {
            //Solo realizamos cambios en el panel si el Combobox ha cambiado realmente

            int items = cbProyecto.getItemCount();
            boolean isChanged = (e.getStateChange() == ItemEvent.SELECTED);

            //Si el combobox contiene elementos y ha cambiado su estado.
            if (items > 0 && isChanged) {
                Object o = cbProyecto.getSelectedItem();
                assert o != null;
                ProyectosEntity p = (ProyectosEntity) o;
                tfProject1.setText(String.format("%s", p.getNombre()));
                tfProject2.setText(String.format("%s", p.getCiudad()));
            }
        });
        cbPieza.addItemListener(e -> {
            //Solo realizamos cambios en el panel si el Combobox ha cambiado realmente

            int items = cbPieza.getItemCount();
            boolean isChanged = (e.getStateChange() == ItemEvent.SELECTED);

            //Si el combobox contiene elementos y ha cambiado su estado.
            if (items > 0 && isChanged) {
                Object o = cbPieza.getSelectedItem();
                assert o != null;
                PiezasEntity p = (PiezasEntity) o;
                tfPieza1.setText(String.format("%s - %s", p.getNombre(), p.getDescripcion() == null ? "" : p.getDescripcion()));
                tfPieza2.setText(String.format("%.2f €", p.getPrecio()));
            }
        });

        //listeners de botones
        btInsertar.addActionListener(e -> {
            AbstractMap.SimpleEntry<String, Object> validacion =
                    Validator.checkInputErrors(myDinamicPanel, Validator.checkCause.insert);
            String errores = validacion.getKey();
            AsignacionesEntity a = (AsignacionesEntity) validacion.getValue();
            if (errores.equals("")) {
                if (a != null) {
                    if (ctAsignaciones.insert(a)) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Inserción realizada correctamente",
                                "Aviso",
                                JOptionPane.INFORMATION_MESSAGE);
                        initJComboboxes();
                        ViewsController.ClearAllFields(JPData);
                    } else {
                        JOptionPane.showMessageDialog(
                                null,
                                "Error en la inserción",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        validacion.getKey(),
                        "Error introdución de datos",
                        JOptionPane.ERROR_MESSAGE);
            }

        });
        btModificar.addActionListener(e -> {
            //VALIDAMOS LOS DATOS INTRODUCIDOS
            AbstractMap.SimpleEntry<String, Object> validacion =
                    Validator.checkInputErrors(myDinamicPanel, Validator.checkCause.update);
            String mensajes = validacion.getKey();
            AsignacionesEntity a = (AsignacionesEntity) validacion.getValue();

            //Si no hay errores preseguimos con la actualizacion
            if (mensajes.equals("")) {
                String confiMens = String.format(
                        "Confirmar actualización de asignacion %S? \n %s",
                        a,
                        mensajes);
                int resp = JOptionPane.showConfirmDialog(
                        null,
                        confiMens,
                        "Aviso de actualizacion",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );
                //TRATAMOS RESPUESTA DEL USUARIO
                switch (resp) {
                    case 0 -> {
                        //ACEPTADA LA ACTUALZIACION POR PARTE DEL USUARIO
                        if (ctAsignaciones.update(a)) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Actualización realizada correctamente",
                                    "ACTUALIZACION",
                                    JOptionPane.INFORMATION_MESSAGE);
                            initJComboboxes();
                            ViewsController.ClearAllFields(JPData);
                        } else {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "UPS! Error en la actualización....",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    case 1 -> {
                        //OPERACION CANCELADA POR PARTE DEL USUARIO
                        JOptionPane.showMessageDialog(
                                null,
                                "Actualización cancelada por parte del usuario....",
                                "AVISO",
                                JOptionPane.INFORMATION_MESSAGE);
                        initJComboboxes();
                        ViewsController.ClearAllFields(JPData);
                    }
                }
            }
            //Si se han detectado erroes o no hay cambios mostramos menaje de errores.
            else {
                JOptionPane.showMessageDialog(
                        null,
                        validacion.getKey(),
                        "Error introdución de datos",
                        JOptionPane.ERROR_MESSAGE);
            }

        });
        btEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //VALIDAMOS LOS DATOS INTRODUCIDOS
                AbstractMap.SimpleEntry<String, Object> validacion =
                        Validator.checkInputErrors(myDinamicPanel, Validator.checkCause.delete);
                String mensajes = validacion.getKey();
                AsignacionesEntity a = (AsignacionesEntity) validacion.getValue();
                //Si recuperamos un objeto continuamos con el proceso de borrado
                if (a != null) {
                    //Solicitamos confirmación al usuario
                    String confiMens = String.format(
                            "Confirmar el borrado de la asignacion %S? \n %s",
                            a,
                            mensajes);
                    int resp = JOptionPane.showConfirmDialog(
                            null,
                            confiMens,
                            "Aviso de Borrado",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );
                    //TRATAMOS RESPUESTA DEL USUARIO
                    switch (resp) {
                        case 0 -> {
                            //ACEPTADO EL BORRADO POR PARTE DEL USUARIO
                            if (ctAsignaciones.delete(a)) {
                                JOptionPane.showMessageDialog(
                                        null,
                                        "Borrado realizado correctamente",
                                        "BORRADO",
                                        JOptionPane.INFORMATION_MESSAGE);
                                ViewsController.ClearAllFields(JPData);
                            } else {
                                JOptionPane.showMessageDialog(
                                        null,
                                        "UPS! Error de borrado en la base de datos....",
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        case 1 -> {
                            //OPERACION CANCELADA POR PARTE DEL USUARIO
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Borrado cancelado por parte del usuario....",
                                    "AVISO",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }

                }
                //Si no recuperamos un objeto, el codigo introducido no existe.
                else {
                    JOptionPane.showMessageDialog(
                            null,
                            validacion.getKey(),
                            "Error introdución de datos",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btListado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Ventana nueva
                JFrame frameListaPedidos = new JFrame("Listado de asignaciones");
                //Instanciamos la vista de la Tabla
                AsignacionesTableView av = new AsignacionesTableView(ctAsignaciones.getAll());
                frameListaPedidos.setContentPane(av.getJPGeneral());
                frameListaPedidos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                //pintamos el panel de la vista.
                av.getJPGeneral().repaint();
                //mostramos la ventana
                frameListaPedidos.pack();
                frameListaPedidos.setVisible(true);
            }
        });
    }

    private void initJComboboxes() {
        //Cargamos los comboboxes con el contenido necesario
        loadCbSelector(cbProv, ctProvs.getAll());
        loadCbSelector(cbProyecto, ctProjects.getAll());
        loadCbSelector(cbPieza, ctPiezas.getAll());

        //Seteamos los comboboxes, sin seleccion
        cbProv.setSelectedIndex(-1);
        cbProyecto.setSelectedIndex(-1);
        cbPieza.setSelectedIndex(-1);
    }

    private DinamicJpanel getJPDataToDinamic() {
        //Montamos manualmente el dinamicPanel
        DinamicJpanel mydinamicPanel = new DinamicJpanel(MyEntitys.Asignaciones);
        mydinamicPanel.setJpDataLines(JPData);
        //agregamos los objetos al diccionario del panel
        mydinamicPanel.getAsignacionesFieldsMap().put("cbprov", cbProv);
        mydinamicPanel.getAsignacionesFieldsMap().put("tfprov1", tfProv1);
        mydinamicPanel.getAsignacionesFieldsMap().put("tfprov2", tfProv2);

        mydinamicPanel.getAsignacionesFieldsMap().put("cbproyecto", cbProyecto);
        mydinamicPanel.getAsignacionesFieldsMap().put("tfproject1", tfProject1);
        mydinamicPanel.getAsignacionesFieldsMap().put("tfproject2", tfProject2);

        mydinamicPanel.getAsignacionesFieldsMap().put("cbpieza", cbPieza);
        mydinamicPanel.getAsignacionesFieldsMap().put("tfpieza1", tfPieza1);
        mydinamicPanel.getAsignacionesFieldsMap().put("tfpieza2", tfPieza2);

        mydinamicPanel.getAsignacionesFieldsMap().put("tfcantidad",tfCantidad);

        return mydinamicPanel;
    }

    /**
     * Procedimiento de carga de lista mostrada en el panel myListadosPanel
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
