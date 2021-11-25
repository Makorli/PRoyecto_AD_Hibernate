package com.Controllers;

import com.Model.AsignacionesEntity;
import com.Model.PiezasEntity;
import com.Model.ProveedoresEntity;
import com.Model.ProyectosEntity;
import com.Utils.Checkers;
import com.Views.DinamicJpanel;

import javax.swing.*;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;

public class Validator {

    public enum checkCause {
        insert,
        update,
        delete
    }

    public static AbstractMap.SimpleEntry<String, Object>
    checkInputErrors(DinamicJpanel dinamicJpanel, checkCause cause) {

        String errores = "";
        MyEntitys type = dinamicJpanel.getType();
        switch (type) {
            case Proveedores -> {
                /*
                  id ->int
                  codigo"->varchar(6)" length="6"
                  nombre"->varchar(20)" length="20"
                  apellidos->varchar(30)" length="30"
                  direccion->varchar(50)" length="50"
                 */
                ViewsController.TrimAllFields(dinamicJpanel);
                return proveedoresChecker(dinamicJpanel, cause);
            }
            case Proyectos -> {
                /*
                id ->int
                codigo->varchar(6)" length="6"
                nombre->"varchar(40)" length="40"
                ciudad->"varchar(40)" length="40"
                 */
            }
            case Piezas -> {
                /*
                id->int
                codigo ->varchar(6)" length="6"
                nombre ->varchar(20)" length="20"
                precio ->"float" precision="-1"
                descripcion->text" not-null="true"/>
                 */
            }
            case Asignaciones -> {
            }
        }
        return new AbstractMap.SimpleEntry<>("", null);
    }


    public static boolean checkIfObjectExist(Object o) {
        if (o instanceof ProveedoresEntity) {
            ProveedoresEntity p = (ProveedoresEntity) o;
            return new ProveedoresDAO().exists(p);
        }
        if (o instanceof ProyectosEntity) {
            ProyectosEntity p = (ProyectosEntity) o;
            return new ProyectosDAO().exists(p);
        }
        if (o instanceof PiezasEntity) {
            PiezasEntity p = (PiezasEntity) o;
            return new PiezasDAO().exists(p);
        }
        if (o instanceof AsignacionesEntity) {
            //TODO
        }
        return false;
    }


    /**
     * Funcion que valida los datos del proveedor contenido en nuestro dinamic panel en base a un
     * cometido concreto contra la base de datos insert, update, delete..et
     *
     * @param dinamicJpanel Jpanel conteiendo los datos del panel.
     * @param cause         finalidad del chequeo
     * @return EntryMap con la key que contiene los errores y el value el objeto chequeado correcto o null is hay errores
     */
    private static AbstractMap.SimpleEntry<String, Object>
    proveedoresChecker(DinamicJpanel dinamicJpanel, checkCause cause) {
        /*        id ->int
                  codigo"->varchar(6)" length="6"
                  nombre"->varchar(20)" length="20"
                  apellidos->varchar(30)" length="30"
                  direccion->varchar(50)" length="50"
         */

        //Diccionario donde almacenaremos los errores
        HashMap<String, String> erroresMap = new HashMap<>();
        ProveedoresEntity proveedoresEntity = new ProveedoresEntity();
        //Chequeamos lo tipos de datos y longitudes permitidas de los datos
        switch (cause) {
            case insert -> {
                //COMPROBACION DE TIPO DE DATOS

                //CODIGO
                String codigo = dinamicJpanel.getFieldsMap().get("tbcodigo").getText();
                if (!Checkers.isOKStrLenghtLimits(codigo, 1, 6))
                    erroresMap.put("Codigo", "El CODIGO debe tener entre 1 y 6 caracteres");
                else proveedoresEntity.setCodigo(
                        dinamicJpanel.getFieldsMap().get("tbcodigo").getText());

                //NOMBRE
                String nombre = dinamicJpanel.getFieldsMap().get("tbnombre").getText();
                if (!Checkers.isOKStrMaxLenght(nombre, 20))
                    erroresMap.put("Nombre", "El NOMBRE excede en longitud. MAX 20.");
                else proveedoresEntity.setNombre(
                        dinamicJpanel.getFieldsMap().get("tbnombre").getText());

                //APELLIDOS
                String apellidos = dinamicJpanel.getFieldsMap().get("tbapellidos").getText();
                if (!Checkers.isOKStrMaxLenght(apellidos, 30))
                    erroresMap.put("Apellidos", "Los APELLIDOS exceden en longitud. MAX 30.");
                else proveedoresEntity.setApellidos(
                        dinamicJpanel.getFieldsMap().get("tbapellidos").getText());

                //DIRECCION
                String direccion = dinamicJpanel.getFieldsMap().get("tbdireccion").getText();
                if (!Checkers.isOKStrMaxLenght(direccion, 50))
                    erroresMap.put("direccion", "La DIRECCION excede en longitud. MAX 50.");
                else proveedoresEntity.setDireccion(
                        dinamicJpanel.getFieldsMap().get("tbdireccion").getText());


            }
            case delete -> {

            }
            case update -> {
            }
        }
        //Guardamos todos los errores en un único String para devolverlo
        StringBuilder errores = new StringBuilder();
        for (String error : erroresMap.values()) errores.append(error + "\n");

        //COMPROBACION DE CODIGO E ID INEXISTENTE
        if (new ProveedoresDAO().exists(proveedoresEntity))
            errores.append("Codigo  o ID ya existente en la base de datos");

        if (!errores.toString().equals("")) {proveedoresEntity = null;}
        return new AbstractMap.SimpleEntry<>(errores.toString(), proveedoresEntity);


    }

    private static String proyectosChecker(DinamicJpanel dinamicJpanel, checkCause cause) {
        /*        id ->int
                  codigo"->varchar(6)" length="6"
                  nombre"->varchar(20)" length="20"
                  apellidos->varchar(30)" length="30"
                  direccion->varchar(50)" length="50"
         */

        //Diccionario donde almacenaremos los errores
        HashMap<String, String> erroresMap = new HashMap<>();
        List<JTextField> misTextFields = ViewsController.GetJTextFieldsFromJPanel(
                dinamicJpanel.getDataLines()
        );
        //Chequeamos lo tipos de datos y longitudes permitidas de los datos
        switch (cause) {
            case insert -> {
                //CODIGO
                String codigo = dinamicJpanel.getFieldsMap().get("tbcodigo").getText();
                String cod = "";
                for (JTextField jtext : misTextFields) {
                    String name = jtext.getName();
                    if (name.equals("tbcodigo")) {
                        cod = jtext.getText();
                    }
                }
                if (!Checkers.isOKStrLenghtLimits(codigo, 1, 6))
                    erroresMap.put("Codigo", "El CODIGO debe tener entre 1 y 6 caracteres");

                //NOMBRE
                String nombre = dinamicJpanel.getFieldsMap().get("tbnombre").getText();
                if (!Checkers.isOKStrMaxLenght(nombre, 20))
                    erroresMap.put("Nombre", "El NOMBRE excede en longitud. MAX 20.");

                //APELLIDOS
                String apellidos = dinamicJpanel.getFieldsMap().get("tbapellidos").getText();
                if (!Checkers.isOKStrMaxLenght(apellidos, 30))
                    erroresMap.put("Apellidos", "Los APELLIDOS exceden en longitud. MAX 30.");

                //DIRECCION
                String direccion = dinamicJpanel.getFieldsMap().get("tbdireccion").getText();
                if (!Checkers.isOKStrMaxLenght(direccion, 50))
                    erroresMap.put("direccion", "La DIRECCION excede en longitud. MAX 50.");

            }
            case delete -> {

            }
            case update -> {
            }
        }
        //Guardamos todos los errores en un único String para devolverlo
        StringBuilder errores = new StringBuilder();

        for (String error : erroresMap.values()) {
            errores.append(error + "\n");
        }
        return errores.toString();

    }

    private static String piezasChecker(DinamicJpanel dinamicJpanel, checkCause cause) {
        /*        id ->int
                  codigo"->varchar(6)" length="6"
                  nombre"->varchar(20)" length="20"
                  apellidos->varchar(30)" length="30"
                  direccion->varchar(50)" length="50"
         */

        //Diccionario donde almacenaremos los errores
        HashMap<String, String> erroresMap = new HashMap<>();
        List<JTextField> misTextFields = ViewsController.GetJTextFieldsFromJPanel(
                dinamicJpanel.getDataLines()
        );
        //Chequeamos lo tipos de datos y longitudes permitidas de los datos
        switch (cause) {
            case insert -> {
                //CODIGO
                String codigo = dinamicJpanel.getFieldsMap().get("tbcodigo").getText();
                String cod = "";
                for (JTextField jtext : misTextFields) {
                    String name = jtext.getName();
                    if (name.equals("tbcodigo")) {
                        cod = jtext.getText();
                    }
                }
                if (!Checkers.isOKStrLenghtLimits(codigo, 1, 6))
                    erroresMap.put("Codigo", "El CODIGO debe tener entre 1 y 6 caracteres");

                //NOMBRE
                String nombre = dinamicJpanel.getFieldsMap().get("tbnombre").getText();
                if (!Checkers.isOKStrMaxLenght(nombre, 20))
                    erroresMap.put("Nombre", "El NOMBRE excede en longitud. MAX 20.");

                //APELLIDOS
                String apellidos = dinamicJpanel.getFieldsMap().get("tbapellidos").getText();
                if (!Checkers.isOKStrMaxLenght(apellidos, 30))
                    erroresMap.put("Apellidos", "Los APELLIDOS exceden en longitud. MAX 30.");

                //DIRECCION
                String direccion = dinamicJpanel.getFieldsMap().get("tbdireccion").getText();
                if (!Checkers.isOKStrMaxLenght(direccion, 50))
                    erroresMap.put("direccion", "La DIRECCION excede en longitud. MAX 50.");

            }
            case delete -> {

            }
            case update -> {
            }
        }
        //Guardamos todos los errores en un único String para devolverlo
        StringBuilder errores = new StringBuilder();

        for (String error : erroresMap.values()) {
            errores.append(error + "\n");
        }
        return errores.toString();

    }

}
