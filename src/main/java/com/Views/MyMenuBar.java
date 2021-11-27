package com.Views;

import com.Controllers.MyEntitys;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 public class MyMenuBar {

    public static JMenuBar getMyAppMenu() {

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(getMenuProveedores());
        menuBar.add(getMenuPiezas());
        menuBar.add(getMenuProyectos());
        menuBar.add(getMenuGestionGlobal());
        menuBar.add(getMenuAyuda());

        return menuBar;
    }

    private static JMenu getMenuProveedores() {

        MyEntitys myEntity = MyEntitys.Proveedores;

        JMenu menuProveedores = new JMenu("Proveedores");
        JMenu menuConsultaProveedores = new JMenu("Consulta de Proveedores");

        JMenuItem itemGestionProveedores = new JMenuItem("Gestión de Proveedores");
        JMenuItem itemConsultaProveedoresCodigo = new JMenuItem("Por Código");
        JMenuItem itemConsultaProveedoresNombre = new JMenuItem("Por Nombre");
        JMenuItem itemConsultaProveedoresDireccion = new JMenuItem("Por Dirección");

        menuConsultaProveedores.add(itemConsultaProveedoresCodigo);
        menuConsultaProveedores.add(itemConsultaProveedoresNombre);
        menuConsultaProveedores.add(itemConsultaProveedoresDireccion);

        menuProveedores.add(itemGestionProveedores);
        menuProveedores.add(menuConsultaProveedores);

        //LISTENERS DE LOS ITEMS
        itemGestionProveedores.addActionListener(e -> {
            JFrame frame = new JFrame("Gestion de Proveedores");
            frame.setContentPane(new GestionView(myEntity).getJPGeneral());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });

        itemConsultaProveedoresCodigo.addActionListener(e -> {
            JFrame frame = new JFrame("Consulta de Proveedores por Código");
            frame.setContentPane(new QueryView(myEntity, "Codigo").getJPGeneral());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });

        itemConsultaProveedoresNombre.addActionListener(e -> {
            JFrame frame = new JFrame("Consulta de Proveedores por Nombre");
            frame.setContentPane(new QueryView(myEntity, "Nombre").getJPGeneral());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });

        itemConsultaProveedoresDireccion.addActionListener(e -> {
            JFrame frame = new JFrame("Consulta de Proveedores por Dirección");
            frame.setContentPane(new QueryView(myEntity, "Direccion").getJPGeneral());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });

        return menuProveedores;

    }

    private static JMenu getMenuPiezas() {

        MyEntitys myEntity = MyEntitys.Piezas;

        JMenu menuPiezas = new JMenu("Piezas");
        JMenu menuConsultaPiezas = new JMenu("Consulta de Piezas");

        JMenuItem itemGestionPiezas = new JMenuItem("Gestión de Piezas");
        JMenuItem itemConsultaPiezasCodigo = new JMenuItem("Por Código");
        JMenuItem itemConsultaPiezasNombre = new JMenuItem("Por Nombre");

        menuConsultaPiezas.add(itemConsultaPiezasCodigo);
        menuConsultaPiezas.add(itemConsultaPiezasNombre);

        menuPiezas.add(itemGestionPiezas);
        menuPiezas.add(menuConsultaPiezas);

        //LISTENERS DE LOS ITEMS
        itemGestionPiezas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Gestion de Piezas");
                frame.setContentPane(new GestionView(myEntity).getJPGeneral());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });

        itemConsultaPiezasCodigo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Consulta de Piezas por Código");
                frame.setContentPane(new QueryView(myEntity, "Codigo").getJPGeneral());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });

        itemConsultaPiezasNombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Consulta de Piezas por Nombre");
                frame.setContentPane(new QueryView(myEntity, "Nombre").getJPGeneral());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });

        return menuPiezas;
    }

    private static JMenu getMenuProyectos() {

        MyEntitys myEntity = MyEntitys.Proyectos;

        JMenu menuProyectos = new JMenu("Proyectos");
        JMenu menuConsultaProyectos = new JMenu("Consulta de Proyectos");

        JMenuItem itemGestionProyectos = new JMenuItem("Gestión de Proyectos");
        JMenuItem itemConsultaProyectosCodigo = new JMenuItem("Por Código");
        JMenuItem itemConsultaProyectosNombre = new JMenuItem("Por Nombre");
        JMenuItem itemConsultaProyectosCiudad = new JMenuItem("Por Ciudad");

        menuConsultaProyectos.add(itemConsultaProyectosCodigo);
        menuConsultaProyectos.add(itemConsultaProyectosNombre);
        menuConsultaProyectos.add(itemConsultaProyectosCiudad);

        menuProyectos.add(itemGestionProyectos);
        menuProyectos.add(menuConsultaProyectos);

        //LISTENERS DE LOS ITEMS
        itemGestionProyectos.addActionListener(e -> {
            JFrame frame = new JFrame("Gestion de Proyectos");
            frame.setContentPane(new GestionView(myEntity).getJPGeneral());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });

        itemConsultaProyectosCodigo.addActionListener(e -> {
            JFrame frame = new JFrame("Consulta de Proyectos por Codigo");
            frame.setContentPane(new QueryView(myEntity, "Codigo").getJPGeneral());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });

        itemConsultaProyectosNombre.addActionListener(e -> {
            JFrame frame = new JFrame("Consulta de Proyectos por Nombre");
            frame.setContentPane(new QueryView(myEntity, "Nombre").getJPGeneral());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });

        itemConsultaProyectosCiudad.addActionListener(e -> {
            JFrame frame = new JFrame("Consulta de Proyectos por Ciudad");
            frame.setContentPane(new QueryView(myEntity, "Ciudad").getJPGeneral());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });

        return menuProyectos;
    }

    private static JMenu getMenuGestionGlobal() {
        JMenu menuGestionGlobal = new JMenu("Gestión Global");

        // CREAR ASIGNACION

        JMenuItem itemCrearAsignacion = new JMenuItem("Nueva Asignacion");
        itemCrearAsignacion.setName("itemCrearAsignacionGestion");

        itemCrearAsignacion.addActionListener(e -> {
            //TODO
        });

        //Agregamos el item de asignacion
        menuGestionGlobal.add(itemCrearAsignacion);

        //SUMNISTROD POR PROVEEDOR

        JMenuItem itemSuministrosProveedor = new JMenuItem("Suministros por Proveedores");
        itemSuministrosProveedor.setName("itemSuministrosProveedorGestion");

        itemSuministrosProveedor.addActionListener(e -> {
            //TODO
        });

        //Agregamos el item de Sumnistros por Proveedor
        menuGestionGlobal.add(itemSuministrosProveedor);

        //SUMINISTROS POR PIEZAS

        JMenuItem itemSuministrosPiezas = new JMenuItem("Suministros por Piezas");
        itemSuministrosPiezas.setName("itemSuministrosPiezasGestion");

        itemSuministrosPiezas.addActionListener(e -> {
            //TODO
        });

        //Agregamos el item de Sumnistros por Piezas
        menuGestionGlobal.add(itemSuministrosPiezas);

        //ESTADISTICAS

        JMenuItem itemEstadisticas = new JMenuItem("Estadisticas");
        itemEstadisticas.setName("itemEstadisticasGestion");

        itemEstadisticas.addActionListener(e -> {
            //TODO
        });

        //Agregamos el item de estaditicas.
        menuGestionGlobal.add(itemEstadisticas);

        return menuGestionGlobal;
    }

    private static JMenu getMenuAyuda() {
        return new JMenu("Ayuda");
    }

}
