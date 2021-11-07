package com.Utils;

import com.Views.GestionView;
import com.Views.QueryView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomViewObjects {

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

        private static JMenu getMenuProveedores(){

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
                frame.setContentPane(new GestionView().getJPGeneral());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

            });

            itemConsultaProveedoresCodigo.addActionListener(e -> {
                JFrame frame = new JFrame("Consulta de Proveedores por Código");
                frame.setContentPane(new QueryView("Proveedores","Codigo").getJPGeneral());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            });

            itemConsultaProveedoresNombre.addActionListener(e -> {
                //TODO
            });

            itemConsultaProveedoresDireccion.addActionListener(e -> {
                //TODO
            });

            return menuProveedores;

        }

        private static JMenu getMenuPiezas(){
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
                    frame.setContentPane(new GestionView().getJPGeneral());
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setVisible(true);
                }
            });

            itemConsultaPiezasCodigo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //TODO
                }
            });

            itemConsultaPiezasNombre.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //TODO
                }
            });

            return menuPiezas;
        }

        private static JMenu getMenuProyectos(){
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
                frame.setContentPane(new GestionView().getJPGeneral());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            });

            itemConsultaProyectosCodigo.addActionListener(e -> {
                //TODO
            });

            itemConsultaProyectosNombre.addActionListener(e -> {
                //TODO
            });

            itemConsultaProyectosCiudad.addActionListener(e -> {
                //TODO
            });

            return menuProyectos;
        }

        private static JMenu getMenuGestionGlobal(){
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

        private static JMenu getMenuAyuda(){
            JMenu menuAyuda = new JMenu("Ayuda");
            return  menuAyuda;
        }

        public static class MyFrikiMenuBar {

            //Enum que identifica los campos de la barra de Menus.
            public enum MenuOptions {
                Proveedores,  //posicion 0
                Piezas, //posicion 1
                Proyectos,   //posicion 2
                Gestion_Global, //posicion 3
                Ayuda, //posicion 4
            }

            //Enum que identifica los items generales aplicables a los menús similares (Proveedores,Piezas..etc)
            public enum itemsGenerales {
                Gestion,
                Consulta,
            }


            public static JMenuBar getJMenuBar() {

                JMenuBar myJMenuBar = new JMenuBar();

                //Montamos los menus de la barra de menus segun las opciones del enum
                //Menus: clientes, empleados, espectaculos....
                for (MenuOptions menuOption : MenuOptions.values()) {

                    //Creamos el objeto menu y le asignamos el nombre.
                    JMenu menu = new JMenu(menuOption.name());
                    menu.setName(menuOption.name().replace("_"," "));

                    // Creamos los items de cada menu
                    /**Convención de nombres de Items generales
                     * Para texto mostrado: valor enum + de + atributo name de jmenu --> Ejemplo: Gestion de Proveedores
                     * Para atributo name: item+Funcion+nombreMenu --> Ejemplo: itemGestionProveedores
                     */
                    switch (menuOption) {

                        //Items de igual construccion. Se diferencian unicamente por el nombre
                        case Proveedores, Piezas, Proyectos -> {
                            //Recorremos los Items generales comunes a las opciones agrupadas en el case
                            for (itemsGenerales item : itemsGenerales.values()) {

                                switch (item){
                                    //Gestion es un menu, no un submenu
                                    case Gestion -> {
                                        //Creamos el nuevo menu y lo añadimos como opcion al existente
                                        JMenu subMenu= new JMenu(
                                                String.format("%s de %s", item.name(),menu.getName()));
                                        subMenu.setName(item.name());
                                    }

                                    //Consulta son Items de menu
                                    case Consulta -> {
                                        //Creamos el item de menu con su texto
                                        JMenuItem menuItem = new JMenuItem(
                                                String.format("%s de %s", item.name(), menu.getName())
                                        );
                                        //Establecemos el nombre
                                        menuItem.setName(item.name());

                                        menuItem.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                //Crear Jframe
                                                //Crear ventanaGestion
                                                //frame.setcontentPane...
                                                //frame.setdefaultCloseOperation
                                                //frame.pack
                                                //frame.visible
                                            }
                                        });

                                        //Agregamos el item al menu
                                        menu.add(menuItem);
                                    }
                                }
                            }
                        }

                        //Opciones para el Menú de gestión
                        case Gestion_Global -> {
                            // Crear Items a agregar al menu Gestión

                            // CREAR ASIGNACION

                            JMenuItem itemCrearAsignacion = new JMenuItem("Nueva Asignacion");
                            itemCrearAsignacion.setName("itemCrearAsignacionGestion");

                            //TODO add listener
                            //Agregamos el item de asignacion
                            menu.add(itemCrearAsignacion);

                            //SUMNISTROD POR PROVEEDOR

                            JMenuItem itemSuministrosProveedor = new JMenuItem("Suministros por Proveedores");
                            itemSuministrosProveedor.setName("itemSuministrosProveedorGestion");
                            //TODO add listener
                            //Agregamos el item de Sumnistros por Proveedor
                            menu.add(itemSuministrosProveedor);

                            //SUMINISTROS POR PIEZAS

                            JMenuItem itemSuministrosPiezas = new JMenuItem("Suministros por Piezas");
                            itemSuministrosPiezas.setName("itemSuministrosPiezasGestion");
                            //TODO add listener
                            //Agregamos el item de Sumnistros por Piezas
                            menu.add(itemSuministrosPiezas);

                            //ESTADISTICAS

                            JMenuItem itemEstadisticas = new JMenuItem("Estadisticas");
                            itemEstadisticas.setName("itemEstadisticasGestion");
                            //TODO add listener
                            //Agregamos el item de estaditicas.
                            menu.add(itemEstadisticas);

                        }
                        //Opciones para el menú de ayuda
                        case Ayuda -> {}

                        //Opciones del enum no tratadas
                        default -> {System.out.format("Menú %s no tratada en la construcción del JMenuBar",menu.getName());}
                    }

                    //Agregamos el menu a la barra
                    myJMenuBar.add(menu);
                }

                //Retornammos la barra de menús construida con sus listeners y acciones.
                return myJMenuBar;
            }
        }
    }
}
