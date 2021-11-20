package com.Controllers;

import com.Utils.Checkers;
import com.Views.CustomsViews.DinamicJpanel;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;

public class Validator {

    public enum checkCause{
        insertar,
        borrar,
        modificar
    }

    public static String checkInputErrors(DinamicJpanel dinamicJpanel,checkCause cause){

        String errores="";
        MyEntitys type= dinamicJpanel.getType();
        switch (type){
            case Proveedores -> {
                /*
                  id ->int
                  codigo"->varchar(6)" length="6"
                  nombre"->varchar(20)" length="20"
                  apellidos->varchar(30)" length="30"
                  direccion->varchar(50)" length="50"
                 */
                ViewsController.TrimAllFields(dinamicJpanel);
                errores = proveedoresChecker(dinamicJpanel,cause);
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
            case Asignaciones -> {}
        }
        return errores;
    }

    private static String proveedoresChecker(DinamicJpanel dinamicJpanel, checkCause cause) {
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
        switch (cause){
            case insertar -> {
                //CODIGO
                String codigo =dinamicJpanel.getFieldsMap().get("tbcodigo").getText();
                String cod="";
                for (JTextField jtext: misTextFields){
                    String name= jtext.getName();
                    if (name.equals("tbcodigo")){
                        cod= jtext.getText();
                    }
                }
                if (!Checkers.isOKStrLenghtLimits(codigo,1,6))
                    erroresMap.put("Codigo", "El CODIGO debe tener entre 1 y 6 caracteres" );

                //NOMBRE
                String nombre =dinamicJpanel.getFieldsMap().get("tbnombre").getText();
                if (!Checkers.isOKStrMaxLenght(nombre,20))
                    erroresMap.put("Nombre", "El NOMBRE excede en longitud. MAX 20." );

                //APELLIDOS
                String apellidos= dinamicJpanel.getFieldsMap().get("tbapellidos").getText();
                if (!Checkers.isOKStrMaxLenght(apellidos,30))
                    erroresMap.put("Apellidos", "Los APELLIDOS exceden en longitud. MAX 30." );

                //DIRECCION
                String direccion = dinamicJpanel.getFieldsMap().get("tbdireccion").getText();
                if (!Checkers.isOKStrMaxLenght(direccion,50))
                    erroresMap.put("direccion", "La DIRECCION excede en longitud. MAX 50." );

            }
            case modificar -> {

            }
            case borrar -> {}
        }
        //Guardamos todos los errores en un único String para devolverlo
        StringBuilder errores = new StringBuilder();

        for (String error: erroresMap.values()){
            errores.append(error + "\n");
        }
        return errores.toString();

    }


}
