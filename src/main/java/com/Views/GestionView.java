package com.Views;

import com.Controllers.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.AbstractMap;
import java.util.List;

import com.Controllers.Validator.*;
import com.Model.ProyectosEntity;
import com.Model.PiezasEntity;
import com.Model.ProveedoresEntity;

public class GestionView {

    private JPanel JPGeneral;
    private JTabbedPane JTPGestion;

    //COMPONETES PANEL DE GESTION
    private JPanel JPGestion;
    private JLabel lbGestionTitulo;
    private JPanel JPDatosGestion;
    private JPanel JPBotoneraGestion;
    private JButton limpiarButton;
    private JButton insertarButton;
    private JButton modificarButton;
    private JButton eliminarButton;
    private JLabel lbListadosTitulo;
    private JButton btListadosInicio;
    private JButton btListadosAtras;
    private JButton btListadosAvance;
    private JButton btListadosFinal;
    private JPanel JPListados;
    private JPanel JPDatosListados;
    private JPanel JPNavegacion;
    private JPanel JPBotoneraListados;
    private JButton btConsulta;
    private JLabel lbCurrentIdx;
    private JLabel lbMaxIdx;
    private JLabel lbofIdx;
    private JPanel JPIndices;

    //COMPONENENTES PERSONALIZADOS DEPENDIENTES DEL TIPO DE VENTANA
    private MyEntitys tipoVentana;  //Variable define tipo de entidad.
    private DinamicJpanel myGestionPanel;
    private DinamicJpanel myListadosPanel;

    //Controladores de entidades utilizadas en las ventanas.
    private ProveedoresDAO ctProvs; //Controlador de Proveedores
    private ProyectosDAO ctProjects; //Controlador de Proyectos
    private PiezasDAO ctPiezas; //Controlador de Piezas

    //List generica e indices de control
    private List miLista;
    private int listIdx;


    public GestionView(MyEntitys myEntitys) {
        tipoVentana = myEntitys;
        //Inicializamos los listeners de los botones
        initListeners();
        updateControls();
        //Creamos el controlador especifico según el tipoVentana
        switch (tipoVentana) {
            case Proveedores -> this.ctProvs = new ProveedoresDAO();
            case Piezas -> this.ctPiezas = new PiezasDAO();
            case Proyectos -> this.ctProjects = new ProyectosDAO();
        }
    }

    private void createUIComponents() {
        //Creamos los dos paneles de Datos de las pestañas Gestion y Listados.
        this.myGestionPanel = ViewsController.CreateDataPanel(tipoVentana);
        this.myListadosPanel = ViewsController.CreateDataPanel(tipoVentana);

        //Pintamos el Panel de Gestion
        JPDatosGestion = myGestionPanel.getJpDataLines();
        myGestionPanel.repaint();

        //Pintamos el Panel de Lists
        JPDatosListados = myListadosPanel.getJpDataLines();
        ViewsController.DisableAllFields(JPDatosListados);
        myListadosPanel.repaint();


        //Seteamos el estado inicial de los botones del panel de Listados

    }

    /**
     * Procedimiento de creación de listener
     */
    private void initListeners() {
        //  *  *  *  *  PESTAÑA DATOS GESTION  *  *  *  *  *
        // limpiarButton , insertarButton , modificarButton , eliminarButton
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewsController.ClearAllFields(JPDatosGestion);
            }
        });

        //BOTON INSERTAR
        insertarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (tipoVentana) {
                    case Proveedores -> {
                        AbstractMap.SimpleEntry<String, Object> validacion =
                                Validator.checkInputErrors(myGestionPanel, checkCause.insert);
                        String errores = validacion.getKey();
                        ProveedoresEntity p = (ProveedoresEntity) validacion.getValue();
                        if (errores.equals("")) {
                            if (p != null) {
                                if (ctProvs.insert(p)) {
                                    JOptionPane.showMessageDialog(
                                            null,
                                            "Inserción realizada correctamente",
                                            "Error",
                                            JOptionPane.INFORMATION_MESSAGE);
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
                    }
                    case Proyectos -> {
                        AbstractMap.SimpleEntry<String, Object> validacion =
                                Validator.checkInputErrors(myGestionPanel, checkCause.insert);
                        String errores = validacion.getKey();
                        ProyectosEntity p = (ProyectosEntity) validacion.getValue();
                        if (errores.equals("")) {
                            if (p != null) {
                                if (ctProjects.insert(p)) {
                                    JOptionPane.showMessageDialog(
                                            null,
                                            "Inserción realizada correctamente",
                                            "Error",
                                            JOptionPane.INFORMATION_MESSAGE);
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
                                    "Aviso",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    case Piezas -> {
                        AbstractMap.SimpleEntry<String, Object> validacion =
                                Validator.checkInputErrors(myGestionPanel, checkCause.insert);
                        String errores = validacion.getKey();
                        PiezasEntity p = (PiezasEntity) validacion.getValue();
                        if (errores.equals("")) {
                            if (p != null) {
                                if (ctPiezas.insert(p)) {
                                    JOptionPane.showMessageDialog(
                                            null,
                                            "Inserción realizada correctamente",
                                            "Error",
                                            JOptionPane.INFORMATION_MESSAGE);
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
                                    "Aviso",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });

        //BOTON UPDATE
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (tipoVentana) {
                    case Proveedores -> {
                        //VALIDAMOS LOS DATOS INTRODUCIDOS
                        AbstractMap.SimpleEntry<String, Object> validacion =
                                Validator.checkInputErrors(myGestionPanel, checkCause.update);
                        String mensajes = validacion.getKey();
                        ProveedoresEntity p = (ProveedoresEntity) validacion.getValue();
                        //Si recuperamos un objeto
                        if (p != null) {
                            //Si no hay cambios mostramos mensaje
                            if (mensajes.equals("")) {
                                JOptionPane.showMessageDialog(
                                        null,
                                        "No hay cambios que guardar",
                                        "INFO Actualizacion",
                                        JOptionPane.INFORMATION_MESSAGE);
                            }
                            //Si se han detectado cambios poreseguimos con el proceso
                            else {
                                String confiMens = String.format(
                                        "Confirmar actualización del proveedor %S? \n %s",
                                        p.getCodigo(),
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
                                        if (ctProvs.update(p)) {
                                            JOptionPane.showMessageDialog(
                                                    null,
                                                    "Actualización realizada correctamente",
                                                    "ACTUALIZACION",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                            ViewsController.ClearAllFields(JPDatosGestion);
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
                                    }
                                }
                            }
                        }
                        //Si no recuperamos un objeto, hay mensajes y son errores
                        else {
                            JOptionPane.showMessageDialog(
                                    null,
                                    validacion.getKey(),
                                    "Error introdución de datos",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    case Proyectos -> {
                        //VALIDAMOS LOS DATOS INTRODUCIDOS
                        AbstractMap.SimpleEntry<String, Object> validacion =
                                Validator.checkInputErrors(myGestionPanel, checkCause.update);
                        String mensajes = validacion.getKey();
                        ProyectosEntity p = (ProyectosEntity) validacion.getValue();
                        //Si recuperamos un objeto
                        if (p != null) {
                            //Si no hay cambios mostramos mensaje
                            if (mensajes.equals("")) {
                                JOptionPane.showMessageDialog(
                                        null,
                                        "No hay cambios que guardar",
                                        "INFO Actualizacion",
                                        JOptionPane.INFORMATION_MESSAGE);
                            }
                            //Si se han detectado cambios poreseguimos con el proceso
                            else {
                                String confiMens = String.format(
                                        "Confirmar actualización del proyecto %S? \n %s",
                                        p.getCodigo(),
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
                                        if (ctProjects.update(p)) {
                                            JOptionPane.showMessageDialog(
                                                    null,
                                                    "Actualización realizada correctamente",
                                                    "ACTUALIZACION",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                            ViewsController.ClearAllFields(JPDatosGestion);
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
                                    }
                                }
                            }
                        }
                        //Si no recuperamos un objeto, hay mensajes y son errores
                        else {
                            JOptionPane.showMessageDialog(
                                    null,
                                    validacion.getKey(),
                                    "Error introdución de datos",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    case Piezas -> {
                        //VALIDAMOS LOS DATOS INTRODUCIDOS
                        AbstractMap.SimpleEntry<String, Object> validacion =
                                Validator.checkInputErrors(myGestionPanel, checkCause.update);
                        String mensajes = validacion.getKey();
                        PiezasEntity p = (PiezasEntity) validacion.getValue();
                        //Si recuperamos un objeto
                        if (p != null) {
                            //Si no hay cambios mostramos mensaje
                            if (mensajes.equals("")) {
                                JOptionPane.showMessageDialog(
                                        null,
                                        "No hay cambios que guardar",
                                        "INFO Actualizacion",
                                        JOptionPane.INFORMATION_MESSAGE);
                            }
                            //Si se han detectado cambios poreseguimos con el proceso
                            else {
                                String confiMens = String.format(
                                        "Confirmar actualización de la pieza %S? \n %s",
                                        p.getCodigo(),
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
                                        if (ctPiezas.update(p)) {
                                            JOptionPane.showMessageDialog(
                                                    null,
                                                    "Actualización realizada correctamente",
                                                    "ACTUALIZACION",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                            ViewsController.ClearAllFields(JPDatosGestion);
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
                                    }
                                }
                            }
                        }
                        //Si no recuperamos un objeto, hay mensajes y son errores
                        else {
                            JOptionPane.showMessageDialog(
                                    null,
                                    validacion.getKey(),
                                    "Error introdución de datos",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });

        //BOTON DELETE
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (tipoVentana) {
                    case Proveedores -> {
                        //VALIDAMOS LOS DATOS INTRODUCIDOS
                        AbstractMap.SimpleEntry<String, Object> validacion =
                                Validator.checkInputErrors(myGestionPanel, checkCause.delete);
                        String mensajes = validacion.getKey();
                        ProveedoresEntity p = (ProveedoresEntity) validacion.getValue();
                        //Si recuperamos un objeto continuamos con el proceso de borrado
                        if (p != null) {
                            //Solicitamos confirmación al usuario
                            String confiMens = String.format(
                                    "Confirmar actualización del proveedor %S? \n %s",
                                    p.getCodigo(),
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
                                    if (ctProvs.delete(p)) {
                                        JOptionPane.showMessageDialog(
                                                null,
                                                "Borrado realizado correctamente",
                                                "BORRADO",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        ViewsController.ClearAllFields(JPDatosGestion);
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
                                            "Actualización cancelada por parte del usuario....",
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
                    case Proyectos -> {
                        //VALIDAMOS LOS DATOS INTRODUCIDOS
                        AbstractMap.SimpleEntry<String, Object> validacion =
                                Validator.checkInputErrors(myGestionPanel, checkCause.delete);
                        String mensajes = validacion.getKey();
                        ProyectosEntity p = (ProyectosEntity) validacion.getValue();
                        //Si recuperamos un objeto continuamos con el proceso de borrado
                        if (p != null) {
                            //Solicitamos confirmación al usuario
                            String confiMens = String.format(
                                    "Confirmar actualización del proyecto %S? \n %s",
                                    p.getCodigo(),
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
                                    if (ctProjects.delete(p)) {
                                        JOptionPane.showMessageDialog(
                                                null,
                                                "Borrado realizado correctamente",
                                                "BORRADO",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        ViewsController.ClearAllFields(JPDatosGestion);
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
                                            "Actualización cancelada por parte del usuario....",
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
                    case Piezas -> {
                        //VALIDAMOS LOS DATOS INTRODUCIDOS
                        AbstractMap.SimpleEntry<String, Object> validacion =
                                Validator.checkInputErrors(myGestionPanel, checkCause.delete);
                        String mensajes = validacion.getKey();
                        PiezasEntity p = (PiezasEntity) validacion.getValue();
                        //Si recuperamos un objeto continuamos con el proceso de borrado
                        if (p != null) {
                            //Solicitamos confirmación al usuario
                            String confiMens = String.format(
                                    "Confirmar actualización de la pieza %S? \n %s",
                                    p.getCodigo(),
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
                                    if (ctPiezas.delete(p)) {
                                        JOptionPane.showMessageDialog(
                                                null,
                                                "Borrado realizado correctamente",
                                                "BORRADO",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        ViewsController.ClearAllFields(JPDatosGestion);
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
                                            "Actualización cancelada por parte del usuario....",
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
                }
            }
        });

        //  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *

        // *  *  *  *  *  PESTAÑA DATOS LISTADOS  *  *  *  *  *
        // btConsulta , btListadosInicio , btListadosAtras , btListadosAvance ,
        // btListadosFinal , btConsulta , btBaja

        btConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //obtener lista
                //List<ProveedoresEntity> misProvs = ctProvs.getAll();
                populateList();
                //del objeto de la lista al panel
                if (miLista.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            null,
                            "No se han encontrado Proveedores que listar",
                            "Listados de Proveedores",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    listIdx = 0;
                    ViewsController.setObjectToDinamicPanel(miLista.get(listIdx), myListadosPanel);
                    updateControls();
                }
                myListadosPanel.repaint();
            }
        });

        btListadosInicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listIdx = 0;
                ViewsController.setObjectToDinamicPanel(miLista.get(listIdx), myListadosPanel);
                updateControls();
            }
        });

        btListadosAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listIdx --;
                ViewsController.setObjectToDinamicPanel(miLista.get(listIdx), myListadosPanel);
                updateControls();
            }
        });

        btListadosAvance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listIdx ++;
                ViewsController.setObjectToDinamicPanel(miLista.get(listIdx), myListadosPanel);
                updateControls();
            }
        });

        btListadosFinal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listIdx = miLista.size()-1;
                ViewsController.setObjectToDinamicPanel(miLista.get(listIdx), myListadosPanel);
                updateControls();
            }
        });

        btConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        //  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
    }

    private void updateControls(){
        updateListadosPanelButtonsState();
        updateIndicesPanelState();
    }
    
    
    /**
     * Procedimeinto de control de los botnoes del listado en funcion de la lista y el indice
     * actual seleccionado
     */
    private void updateListadosPanelButtonsState() {

        //btListadosInicio, btListadosAtras, btListadosAvance, btListadosFinal;
        if (miLista == null || miLista.size() == 0) {
            btListadosInicio.setEnabled(false);
            btListadosFinal.setEnabled(false);
            btListadosAvance.setEnabled(false);
            btListadosAtras.setEnabled(false);
        } else {
            btListadosInicio.setEnabled(true);
            btListadosFinal.setEnabled(true);
            btListadosAvance.setEnabled(true);
            btListadosAtras.setEnabled(true);

            if (listIdx == 0) {
                btListadosInicio.setEnabled(false);
                btListadosFinal.setEnabled(true);
                btListadosAvance.setEnabled(true);
                btListadosAtras.setEnabled(false);
            }
            if (listIdx == miLista.size()-1) {
                btListadosInicio.setEnabled(true);
                btListadosFinal.setEnabled(false);
                btListadosAvance.setEnabled(false);
                btListadosAtras.setEnabled(true);
            }
        }
    }

    /**
     * Procedimiento que actualiza el estado de los label contenidos en el panel JPIndices
     * en base a los resultados de la que se esá manejando.
     */
    private void updateIndicesPanelState(){
        if (miLista==null || miLista.isEmpty()){
            lbCurrentIdx.setText("¡Pulsa!");
            lbofIdx.setText("");
            lbMaxIdx.setText("");
        } else {
            lbCurrentIdx.setText(String.valueOf(listIdx+1));
            lbofIdx.setText(" de ");
            lbMaxIdx.setText(String.valueOf(miLista.size()));
        }
        
    }

    /**
     * Procedmiento de carga de lista mostrada en el panel myListadosPanel
     */
    private void populateList() {
        switch (tipoVentana) {
            case Proveedores -> {
                miLista = ctProvs.getAll();
            }
            case Proyectos -> {
                miLista = ctProjects.getAll();
            }
            case Piezas -> {
                miLista = ctPiezas.getAll();
            }
            case Asignaciones -> {
            }
        }
        if (miLista.isEmpty()) {
            JOptionPane.showMessageDialog(
                    null,
                    String.format("No se han encontrado %s para listar", tipoVentana.toString()),
                    String.format("Listado de %s", tipoVentana.toString()),
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public JPanel getJPGeneral() {
        return JPGeneral;
    }

}

