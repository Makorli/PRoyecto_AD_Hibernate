package com.Views;

import com.Controllers.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.AbstractMap;

public class EstadisticasView {
    private JPanel JPGeneral;
    private JButton btPiezasProyecto;
    private JButton btPiezasProveedor;
    private JTextField tfNombrePiezaMasProyectos_Cant;
    private JTextField tfNombrePiezaMasProyectos;
    private JTextField tfNombrePiezaMasSuministrada;
    private JTextField tfNombrePiezaMasSuministrada_Cant;
    private JTextField tfProvMasTipoDePiezasSuministra_Cant;
    private JTextField tfProvMasTipoDePiezasSuministra;
    private JTextField tfProvMasCantidadPiezas_Cant;
    private JTextField tfProvMasCantidadPiezas;
    private JTextField tfProvEnMasProyectos_Cant;
    private JTextField tfProvEnMasProyectos;
    private JPanel JPDatosPiezaProyecto;
    private JPanel JPDatosPiezaProveedor;

    //CONTROLADORES
    private AsignacionesDAO ctAsignaciones;

    //Variable recogida de consultas
    AbstractMap.SimpleEntry resultado;

    public JPanel getJPGeneral() {
        return JPGeneral;
    }

    public EstadisticasView() {
        ctAsignaciones= new AsignacionesDAO();
        ViewsController.DisableAllFields(JPDatosPiezaProyecto);
        ViewsController.DisableAllFields(JPDatosPiezaProveedor);

        resultado = ctAsignaciones.getPiezaMasSumnistrada();
        tfNombrePiezaMasSuministrada.setText(String.valueOf(resultado.getKey()));
        tfNombrePiezaMasSuministrada_Cant.setText(String.valueOf(resultado.getValue()));

        resultado = ctAsignaciones.getPiezaEnMasProyectos();
        tfNombrePiezaMasProyectos.setText(String.valueOf(resultado.getKey()));
        tfNombrePiezaMasProyectos_Cant.setText(String.valueOf(resultado.getValue()));

        resultado = ctAsignaciones.getProveedorMasCantidadSuministra();
        tfProvMasCantidadPiezas.setText(String.valueOf(resultado.getKey()));
        tfProvMasCantidadPiezas_Cant.setText(String.valueOf(resultado.getValue()));

        resultado = ctAsignaciones.getProvMasTipoDePiezasSuministra();
        tfProvMasTipoDePiezasSuministra.setText(String.valueOf(resultado.getKey()));
        tfProvMasTipoDePiezasSuministra_Cant.setText(String.valueOf(resultado.getValue()));

        resultado = ctAsignaciones.getProvEnMasProyectos();
        tfProvEnMasProyectos.setText(String.valueOf(resultado.getKey()));
        tfProvEnMasProyectos_Cant.setText(String.valueOf(resultado.getValue()));

        btPiezasProyecto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Montamos la vista de la tabla
                JFrame frame = new JFrame("Numero de Piezas y cantidad de piezas sumnistradas por proyecto");

                //Mostramos la ventana
                frame.setContentPane(new TablasView(
                        new EstadisticasByProyectoTablaModel())
                        .getJPGeneral());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.pack();
                frame.setVisible(true);
            }
        });

        btPiezasProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Numero de Piezas y cantidad de piezas sumnistradas por proveedor");

                //Montamos la vista de la tabla
                /*
                JPanel jptabla= new JPanel();
                JTable jtable = new JTable();
                JScrollPane jScrollPane = new JScrollPane();
                jScrollPane.setViewportView(jtable);
                jtable.setModel(new EstadisticasByProveedorTablaModel());
                jScrollPane.add(jtable);
                jtable.add(jScrollPane);

                jScrollPane.repaint();
                jptabla.repaint();
                jptabla.repaint();

                 */

                //Mostramos la ventana
                frame.setContentPane(new TablasView(
                        new EstadisticasByProveedorTablaModel())
                        .getJPGeneral());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }


}
