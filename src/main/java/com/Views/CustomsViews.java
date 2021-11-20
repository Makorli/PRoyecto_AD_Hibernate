package com.Views;

import com.Controllers.MyEntitys;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomsViews {

    public static class MyMenuBar {

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
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    //Clase personalizada de JPanel de contenido dinámico según los modelos.
    public static class DinamicJpanel extends JPanel {

        private final MyEntitys type;
        private HashMap<String, JTextField> fields;
        private JPanel dataLines;

        public DinamicJpanel(MyEntitys type) {
            //super(new GridLayout(0, 1));
            super(new BorderLayout());

            this.type = type;
            fields = new HashMap<>();
            String[] campos = new String[0];

            switch (type) {
                /* ref -> https://stackoverflow.com/questions/8545301/put-text-boxes-in-separate-lines */
                case Proveedores -> {
                    campos = new String[]{"Codigo", "Nombre", "Apellidos", "Direccion"};
                }
                case Piezas -> {
                    campos = new String[]{"Codigo", "Nombre", "Precio", "Descripcion"};
                }
                case Proyectos -> {
                    campos = new String[]{"Codigo", "Nombre", "Ciudad"};
                }
                case Asignaciones -> {
                }
                default -> {
                }
            }

            this.dataLines = new JPanel(new GridLayout(campos.length, 2));
            //JPanel dataLines = new JPanel(new FlowLayout(FlowLayout.CENTER));

            for (String campo : campos) {
                //Etiqueta
                JLabel label = new JLabel(campo + ": ", SwingConstants.LEFT);
                label.setMinimumSize(new Dimension(-1, -1));

                //TextField
                JTextField field = new JTextField(20);
                String fieldName = "tb" + campo.toLowerCase();
                field.setName(fieldName);

                //Anexamos los objetos al jpanel datalines
                dataLines.add(label);
                dataLines.add(field);

                //Anexamos el campo al diccionarion de la clase.
                fields.put(fieldName, field);
            }
            //Añadimos los campos a nuestra clase
            this.add(dataLines, BorderLayout.CENTER);

            //Ponemos margen al JPanel.
            //ref -> https://stackoverflow.com/questions/5854005/setting-horizontal-and-vertical-margins
            this.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        }

        public HashMap<String, JTextField> getFieldsMap() {
            return fields;
        }

        public MyEntitys getType() {
            return type;
        }

        public List<String> getFieldsNames() {
            return new ArrayList<>(this.fields.keySet());
        }

        public JPanel getDataLines(){
            return dataLines;
        }
    }


}

