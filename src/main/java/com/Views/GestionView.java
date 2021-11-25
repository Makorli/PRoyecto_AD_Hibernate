package com.Views;

import com.Controllers.*;
import com.Model.PiezasEntity;
import com.Model.ProveedoresEntity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.AbstractMap;
import java.util.List;

import com.Controllers.Validator.*;
import com.Model.ProyectosEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TransientPropertyValueException;
import org.hibernate.exception.ConstraintViolationException;

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
    private JButton btBaja;

    //COMPONENETES PANEL DE LISTADOS
    private MyEntitys tipoVentana;
    private DinamicJpanel myGestionPanel;
    private DinamicJpanel myListadosPanel;

    //Controladores Posibles
    private ProveedoresDAO ctProvs; //Controlador de Proveedores
    private ProyectosDAO ctProjects; //Controlador de Proyectos
    private PiezasDAO ctPiezas; //Controlador de Piezas
    private AsignacionesDAO ctAsigns; //Controlador de Asignaciones

    //Controlador
    Object controlador;


    public GestionView(MyEntitys myEntitys) {
        tipoVentana = myEntitys;
        //Inicializamos los listeners de los botones
        initListeners();
        //Creamos el controlador especifico seún el tipoVentana
        switch (tipoVentana) {
            case Proveedores -> this.ctProvs = new ProveedoresDAO();
            case Piezas -> this.ctPiezas = new PiezasDAO();
            case Proyectos -> this.ctProjects = new ProyectosDAO();
            case Asignaciones -> this.ctAsigns = new AsignacionesDAO();
        }
    }

    public JPanel getJPGeneral() {
        return JPGeneral;
    }

    private void createUIComponents() {
        //Creamos los dos paneles de Datos de las pestañas Gestion y Listados.
        this.myGestionPanel = ViewsController.CreateDataPanel(tipoVentana);
        this.myListadosPanel = ViewsController.CreateDataPanel(tipoVentana);

        //Pintamos el Panel de Gestion
        JPDatosGestion = myGestionPanel.getDataLines();
        myGestionPanel.repaint();

        //Pintamos el Panel de Lists
        JPDatosListados = myListadosPanel.getDataLines();
        ViewsController.DisableAllFields(JPDatosListados);
        myListadosPanel.repaint();

    }

    private void initListeners() {
        //PESTAÑA DATOS GESTION
        // limpiarButton , insertarButton , modificarButton , eliminarButton
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewsController.ClearAllFields(JPDatosGestion);
            }
        });

        insertarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Object o = ViewsController.getObjectFromMyCustomView(myGestionPanel);
                switch (tipoVentana) {
                    case Proveedores -> {

                        AbstractMap.SimpleEntry<String, Object> validacion =
                                Validator.checkInputErrors(myGestionPanel, checkCause.insert);
                        String errores = validacion.getKey();
                        ProveedoresEntity p = (ProveedoresEntity) validacion.getValue();
                        if (errores.equals("")) {
                            if (p != null) {
                                /*
                                SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
                                Session session = sessionFactory.openSession();
                                Transaction tx = session.beginTransaction();
                                session.save(p);
                                try {
                                    tx.commit();
                                    ViewsController.ClearAllFields(JPDatosGestion);
                                    JPDatosGestion.repaint();
                                } catch (Exception ex) {
                                    System.out.println(ex.getMessage());
                                }
                                */
                                errores="";
                                try {
                                    ctProvs.insert(p);
                                } catch (ConstraintViolationException cpr) {
                                    errores = "Objeto duplicado en la base de datos\nInserción no realizada";
                                } catch (TransientPropertyValueException tpr) {
                                    errores ="Error entre objetos realcionados\nInserción no realizada";
                                }
                                if (!errores.equals(""))
                                    JOptionPane.showMessageDialog(
                                            null,
                                            validacion.getKey(),
                                            "Error",
                                            JOptionPane.ERROR_MESSAGE);
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
                                /*
                                SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
                                Session session = sessionFactory.openSession();
                                Transaction tx = session.beginTransaction();
                                session.save(p);
                                try {
                                    tx.commit();
                                    ViewsController.ClearAllFields(JPDatosGestion);
                                    JPDatosGestion.repaint();
                                } catch (Exception ex) {
                                    System.out.println(ex.getMessage());
                                }
                                */

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
                                SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
                                Session session = sessionFactory.openSession();
                                Transaction tx = session.beginTransaction();
                                session.save(p);
                                try {
                                    tx.commit();
                                    ViewsController.ClearAllFields(JPDatosGestion);
                                    JPDatosGestion.repaint();
                                } catch (Exception ex) {
                                    System.out.println(ex.getMessage());
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

        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });

        // PESTAÑA DATOS LISTADOS
        // btListadosInicio , btListadosAtras , btListadosAvance , btListadosFinal
        // btConsulta , btBaja;


        btConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (tipoVentana) {
                    case Proveedores -> {
                        //obtener lista
                        List<ProveedoresEntity> misProvs = ctProvs.getAll();
                        //del objeto de la lista al panel
                        myListadosPanel.getFieldsMap().get("tbcodigo").setText(
                                misProvs.get(0).getCodigo()
                        );
                        myListadosPanel.getFieldsMap().get("tbnombre").setText(
                                misProvs.get(0).getNombre()
                        );
                        myListadosPanel.getFieldsMap().get("tbapellidos").setText(
                                misProvs.get(0).getApellidos()
                        );
                        myListadosPanel.getFieldsMap().get("tbdireccion").setText(
                                misProvs.get(0).getDireccion()
                        );
                        myListadosPanel.repaint();
                    }
                    case Piezas -> {
                    }
                    case Proyectos -> {
                    }
                    case Asignaciones -> {
                    }
                }
            }
        });


    }

}
