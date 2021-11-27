package com.Controllers;

import com.Model.AsignacionesEntity;
import com.Model.PiezasEntity;
import com.Model.ProveedoresEntity;
import com.Model.ProyectosEntity;
import com.Utils.Checkers;
import com.Views.DinamicJpanel;

import java.util.AbstractMap;
import java.util.HashMap;

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
        ViewsController.TrimAllFields(dinamicJpanel.getJpDataLines());
        switch (type) {
            case Proveedores -> {
                /*
                  id ->int
                  codigo"->varchar(6)" length="6"
                  nombre"->varchar(20)" length="20"
                  apellidos->varchar(30)" length="30"
                  direccion->varchar(50)" length="50"
                 */
                return proveedoresChecker(dinamicJpanel, cause);
            }
            case Proyectos -> {
                /*
                id ->int
                codigo->varchar(6)" length="6"
                nombre->"varchar(40)" length="40"
                ciudad->"varchar(40)" length="40"
                 */
                return proyectosChecker(dinamicJpanel, cause);
            }
            case Piezas -> {
                /*
                id->int
                codigo ->varchar(6)" length="6"
                nombre ->varchar(20)" length="20"
                precio ->"float" precision="-1"
                descripcion->text" not-null="true"/>
                 */
                return piezasChecker(dinamicJpanel, cause);
            }
            case Asignaciones -> {
                return asignacionesChecker(dinamicJpanel, cause);
            }
        }
        return new AbstractMap.SimpleEntry<>("", null);
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

        //Diccionario donde almacenaremos los errores y objeto para recuperación de la BD
        HashMap<String, String> erroresMap = new HashMap<>();
        ProveedoresEntity proveedoresEntity = new ProveedoresEntity();

        //COMPROBACIONES  DE TIPO DE DATOS Y LOGICAS. SI SON CORRECTAS SE AÑADE EL CAMPO AL OBJETO
        //SI NO SE GUARDA ALMACENAN LOS ERRORES EN UN HASHMAP
        //LAS COMPROBACIONES PUEDEN VARIAR DEPENDIENDO DEL TIPO DE OPERACION PARA LA QUE SE REALIZA

        switch (cause) {
            case insert -> {
                //COMPROBACIONES  DE TIPO DE DATOS Y LOGICAS. SI SON CORRECTAS SE AÑADE EL CAMPO AL OBJETO
                //SI NO SE GUARDA ALMACENAN LOS ERRORES EN UN HASHMAP

                //CODIGO
                String codigo = dinamicJpanel.getFieldsMap().get("tbcodigo").getText();
                if (!Checkers.isOKStrLenghtLimits(codigo, 1, 6))
                    erroresMap.put("Codigo", "El CODIGO debe tener entre 1 y 6 caracteres");
                else {
                    //CODIGO COMPROBACION DE CODIGO O ID INEXISTENTE
                    proveedoresEntity.setCodigo(
                            dinamicJpanel.getFieldsMap().get("tbcodigo").getText());

                    if (new ProveedoresDAO().exists(proveedoresEntity))
                        erroresMap.put("Codigo", "El codigo ya existe en la base de datos");
                }

                //NOMBRE
                String nombre = dinamicJpanel.getFieldsMap().get("tbnombre").getText();
                if (nombre.equals("")) erroresMap.put("Nombre", "El Nombre no puede estar vacio");
                else {
                    if (!Checkers.isOKStrMaxLenght(nombre, 20))
                        erroresMap.put("Nombre", "El NOMBRE excede en longitud. MAX 20.");
                    else proveedoresEntity.setNombre(
                            dinamicJpanel.getFieldsMap().get("tbnombre").getText());
                }
                //APELLIDOS
                String apellidos = dinamicJpanel.getFieldsMap().get("tbapellidos").getText();
                if (!Checkers.isOKStrMaxLenght(apellidos, 30))
                    erroresMap.put("Apellidos", "Los APELLIDOS exceden en longitud. MAX 30.");
                else proveedoresEntity.setApellidos(
                        dinamicJpanel.getFieldsMap().get("tbapellidos").getText());

                //DIRECCION
                String direccion = dinamicJpanel.getFieldsMap().get("tbdireccion").getText().trim();
                if (!Checkers.isOKStrMaxLenght(direccion, 50))
                    erroresMap.put("direccion", "La DIRECCION excede en longitud. MAX 50.");
                else proveedoresEntity.setDireccion(
                        dinamicJpanel.getFieldsMap().get("tbdireccion").getText());


            }
            case delete -> {
                //COMPROBAMOS LA EXISTENCIA DEL CODIGO INTRODUCIDO
                //CODIGO
                String codigo = dinamicJpanel.getFieldsMap().get("tbcodigo").getText();
                if (!Checkers.isOKStrLenghtLimits(codigo, 1, 6))
                    erroresMap.put("Codigo", "El CODIGO debe tener entre 1 y 6 caracteres");
                else {
                    //CODIGO COMPROBACION DE CODIGO O ID INEXISTENTE
                    proveedoresEntity.setCodigo(
                            dinamicJpanel.getFieldsMap().get("tbcodigo").getText());
                    proveedoresEntity= new ProveedoresDAO().getByCodigo(codigo);
                    if (proveedoresEntity==null)
                        erroresMap.put("Codigo", "El codigo NO existe en la base de datos");
                }
            }
            case update -> {
                HashMap<String, String> changesMap = new HashMap<>();
                //COMPROBACIONES  DE TIPO DE DATOS Y LOGICAS. SI SON CORRECTAS SE AÑADE EL CAMPO AL OBJETO
                //SI NO SE GUARDA ALMACENAN LOS ERRORES EN UN HASHMAP

                //CODIGO
                String codigo = dinamicJpanel.getFieldsMap().get("tbcodigo").getText();
                if (!Checkers.isOKStrLenghtLimits(codigo, 1, 6))
                    erroresMap.put("Codigo", "El CODIGO debe tener entre 1 y 6 caracteres");
                else {
                    ProveedoresEntity proveedorOld = new ProveedoresDAO().getByCodigo(codigo);
                    //CODIGO COMPROBACION DE CODIGO O ID EXISTENTE
                    if (proveedorOld == null) {
                        erroresMap.put("Codigo", "El código debe existir para poder actualizar");
                    } else {
                        proveedoresEntity.setId(proveedorOld.getId());
                        proveedoresEntity.setCodigo(
                                dinamicJpanel.getFieldsMap().get("tbcodigo").getText());


                        //NOMBRE
                        String nombre = dinamicJpanel.getFieldsMap().get("tbnombre").getText();
                        if (nombre.equals("")) {
                            proveedoresEntity.setNombre(proveedorOld.getNombre());
                            changesMap.put("Nombre",String.format("Nombre: %S",proveedorOld.getNombre()));
                        }
                        else {
                            if (!Checkers.isOKStrMaxLenght(nombre, 20))
                                erroresMap.put("Nombre", "El NOMBRE excede en longitud. MAX 20.");
                            else {
                                proveedoresEntity.setNombre(
                                        dinamicJpanel.getFieldsMap().get("tbnombre").getText());
                                changesMap.put("Nombre",String.format("Nombre: %S --> %S",
                                        proveedorOld.getNombre(),
                                        proveedoresEntity.getNombre()));
                            }
                        }

                        //APELLIDOS
                        String apellidos = dinamicJpanel.getFieldsMap().get("tbapellidos").getText();
                        if (!Checkers.isOKStrMaxLenght(apellidos, 30))
                            erroresMap.put("Apellidos", "Los APELLIDOS exceden en longitud. MAX 30.");
                        else {
                            proveedoresEntity.setApellidos(
                                    dinamicJpanel.getFieldsMap().get("tbapellidos").getText());
                            changesMap.put("Apellidos",String.format("Apellidos: %S --> %S",
                                    proveedorOld.getApellidos(),
                                    proveedoresEntity.getApellidos()));
                        }
                        //DIRECCION
                        String direccion = dinamicJpanel.getFieldsMap().get("tbdireccion").getText().trim();
                        if (!Checkers.isOKStrMaxLenght(direccion, 50))
                            erroresMap.put("direccion", "La DIRECCION excede en longitud. MAX 50.");
                        else {
                            proveedoresEntity.setDireccion(
                                    dinamicJpanel.getFieldsMap().get("tbdireccion").getText());
                            changesMap.put("Direccion",String.format("Direccion: %S --> %S",
                                    proveedorOld.getDireccion(),
                                    proveedoresEntity.getDireccion()));
                        }

                        //SI NO HAY ERRORES RETORNAMOS VERIFICAMOS DI HAY CAMBIOS Y LOS
                        // RETORNAMOS COMO MENSAJES JUNTO CON EL OBJETO PREPARADO PARA SER ACTUALIZADO
                        if (erroresMap.size()==0){
                            if (proveedoresEntity.equals(proveedorOld)){
                                System.out.println("No se han detectado modificaciones");
                            }
                            else {
                                //Guardamos todos los errores en un único String para devolverlo
                                StringBuilder changes = new StringBuilder();
                                for (String change : changesMap.values()) changes.append(change).append("\n");

                                //Retornamos el objeto y los cambios que se harán.
                                return new AbstractMap.SimpleEntry<>(changes.toString(), proveedoresEntity);
                            }
                        }
                    }
                }

            }
        }

        //Guardamos todos los errores en un único String para devolverlo
        StringBuilder errores = new StringBuilder();
        for (String error : erroresMap.values()) errores.append(error).append("\n");

        //Retornamos errore y objeto a null O No errores y con un objeto
        if (!errores.toString().equals("")) {
            proveedoresEntity = null;
        }
        return new AbstractMap.SimpleEntry<>(errores.toString(), proveedoresEntity);

    }

    private static AbstractMap.SimpleEntry<String, Object>
    proyectosChecker(DinamicJpanel dinamicJpanel, checkCause cause) {
        /*        id ->int
                  codigo"->varchar(6)" length="6"
                  nombre"->varchar(40)" length="40"
                  ciudad->varchar(40)" length="40"
         */

        //Diccionario donde almacenaremos los errores y objeto para recuperación de la BD
        HashMap<String, String> erroresMap = new HashMap<>();
        ProyectosEntity proyectosEntity = new ProyectosEntity();

        //COMPROBACIONES  DE TIPO DE DATOS Y LOGICAS. SI SON CORRECTAS SE AÑADE EL CAMPO AL OBJETO
        //SI NO SE GUARDA ALMACENAN LOS ERRORES EN UN HASHMAP
        //LAS COMPROBACIONES PUEDEN VARIAR DEPENDIENDO DEL TIPO DE OPERACION PARA LA QUE SE REALIZA

        switch (cause) {
            case insert -> {

                //CODIGO
                String codigo = dinamicJpanel.getFieldsMap().get("tbcodigo").getText();
                if (!Checkers.isOKStrLenghtLimits(codigo, 1, 6))
                    erroresMap.put("Codigo", "El CODIGO debe tener entre 1 y 6 caracteres");
                else {
                    //CODIGO COMPROBACION DE CODIGO O ID INEXISTENTE
                    proyectosEntity.setCodigo(
                            dinamicJpanel.getFieldsMap().get("tbcodigo").getText());

                    if (new ProyectosDAO().exists(proyectosEntity))
                        erroresMap.put("Codigo", "El código ya existe en la base de datos");

                }

                //NOMBRE
                String nombre = dinamicJpanel.getFieldsMap().get("tbnombre").getText();
                if (nombre.equals("")) erroresMap.put("Nombre", "El Nombre no puede estar vacio");
                else {
                    if (!Checkers.isOKStrMaxLenght(nombre, 40))
                        erroresMap.put("Nombre", "El NOMBRE excede en longitud. MAX 20.");
                    else proyectosEntity.setNombre(
                            dinamicJpanel.getFieldsMap().get("tbnombre").getText());
                }
                //CIUDAD
                String apellidos = dinamicJpanel.getFieldsMap().get("tbciudad").getText();
                if (!Checkers.isOKStrMaxLenght(apellidos, 40))
                    erroresMap.put("Ciudad", "La CIUDAD exceden en longitud. MAX 30.");
                else proyectosEntity.setCiudad(
                        dinamicJpanel.getFieldsMap().get("tbciudad").getText());


            }
            case delete -> {
                //COMPROBAMOS LA EXISTENCIA DEL CODIGO INTRODUCIDO
                //CODIGO
                String codigo = dinamicJpanel.getFieldsMap().get("tbcodigo").getText();
                if (!Checkers.isOKStrLenghtLimits(codigo, 1, 6))
                    erroresMap.put("Codigo", "El CODIGO debe tener entre 1 y 6 caracteres");
                else {
                    //CODIGO COMPROBACION DE CODIGO O ID INEXISTENTE
                    proyectosEntity.setCodigo(
                            dinamicJpanel.getFieldsMap().get("tbcodigo").getText());
                    proyectosEntity= new ProyectosDAO().getByCodigo(codigo);
                    if (proyectosEntity==null)
                        erroresMap.put("Codigo", "El codigo NO existe en la base de datos");
                }
            }
            case update -> {
                HashMap<String, String> changesMap = new HashMap<>();
                //COMPROBACIONES  DE TIPO DE DATOS Y LOGICAS. SI SON CORRECTAS SE AÑADE EL CAMPO AL OBJETO
                //SI NO SE GUARDA ALMACENAN LOS ERRORES EN UN HASHMAP

                //CODIGO
                String codigo = dinamicJpanel.getFieldsMap().get("tbcodigo").getText();
                if (!Checkers.isOKStrLenghtLimits(codigo, 1, 6))
                    erroresMap.put("Codigo", "El CODIGO debe tener entre 1 y 6 caracteres");
                else {
                    ProyectosEntity proyectoOld = new ProyectosDAO().getByCodigo(codigo);
                    //CODIGO COMPROBACION DE CODIGO O ID EXISTENTE
                    if (proyectoOld == null) {
                        erroresMap.put("Codigo", "El código debe existir para poder actualizar");
                    } else {
                        proyectosEntity.setId(proyectoOld.getId());
                        proyectosEntity.setCodigo(
                                dinamicJpanel.getFieldsMap().get("tbcodigo").getText());


                        //NOMBRE
                        String nombre = dinamicJpanel.getFieldsMap().get("tbnombre").getText();
                        if (nombre.equals("")) {
                            proyectosEntity.setNombre(proyectoOld.getNombre());
                            changesMap.put("Nombre",String.format("Nombre: %S",proyectoOld.getNombre()));
                        }
                        else {
                            if (!Checkers.isOKStrMaxLenght(nombre, 40))
                                erroresMap.put("Nombre", "El NOMBRE excede en longitud. MAX 20.");
                            else {
                                proyectosEntity.setNombre(
                                        dinamicJpanel.getFieldsMap().get("tbnombre").getText());
                                changesMap.put("Nombre",String.format("Nombre: %S --> %S",
                                        proyectoOld.getNombre(),
                                        proyectosEntity.getNombre()));
                            }
                        }

                        //CIUDAD
                        String apellidos = dinamicJpanel.getFieldsMap().get("tbciudad").getText();
                        if (!Checkers.isOKStrMaxLenght(apellidos, 40))
                            erroresMap.put("Ciudad", "La ciudad exceden en longitud. MAX 30.");
                        else {
                            proyectosEntity.setCiudad(
                                    dinamicJpanel.getFieldsMap().get("tbciudad").getText());
                            changesMap.put("Ciudad",String.format("Ciudad: %S --> %S",
                                    proyectoOld.getCiudad(),
                                    proyectosEntity.getCiudad()));
                        }

                        //SI NO HAY ERRORES RETORNAMOS VERIFICAMOS DI HAY CAMBIOS Y LOS
                        // RETORNAMOS COMO MENSAJES JUNTO CON EL OBJETO PREPARADO PARA SER ACTUALIZADO
                        if (erroresMap.size()==0){
                            if (proyectosEntity.equals(proyectoOld)){
                                System.out.println("No se han detectado modificaciones");
                            }
                            else {
                                //Guardamos todos los errores en un único String para devolverlo
                                StringBuilder changes = new StringBuilder();
                                for (String change : changesMap.values()) changes.append(change).append("\n");

                                //Retornamos el objeto y los cambios que se harán.
                                return new AbstractMap.SimpleEntry<>(changes.toString(), proyectosEntity);
                            }
                        }
                    }
                }

            }
        }

        //Guardamos todos los errores en un único String para devolverlo
        StringBuilder errores = new StringBuilder();
        for (String error : erroresMap.values()) errores.append(error).append("\n");

        //Retornamos errore y objeto a null O No errores y con un objeto
        if (!errores.toString().equals("")) {
            proyectosEntity = null;
        }
        return new AbstractMap.SimpleEntry<>(errores.toString(), proyectosEntity);

    }

    private static AbstractMap.SimpleEntry<String, Object>
    piezasChecker(DinamicJpanel dinamicJpanel, checkCause cause) {
        /*        id ->int
                  codigo"->varchar(6)" length="6"
                  nombre"->varchar(20)" length="20"
                  precio->float
                  descripcion->varchar
         */

        //Diccionario donde almacenaremos los errores y objeto para recuperación de la BD
        HashMap<String, String> erroresMap = new HashMap<>();
        PiezasEntity piezasEntity = new PiezasEntity();

        //COMPROBACIONES  DE TIPO DE DATOS Y LOGICAS. SI SON CORRECTAS SE AÑADE EL CAMPO AL OBJETO
        //SI NO SE GUARDA ALMACENAN LOS ERRORES EN UN HASHMAP
        //LAS COMPROBACIONES PUEDEN VARIAR DEPENDIENDO DEL TIPO DE OPERACION PARA LA QUE SE REALIZA

        switch (cause) {
            case insert -> {

                //CODIGO
                String codigo = dinamicJpanel.getFieldsMap().get("tbcodigo").getText();
                if (!Checkers.isOKStrLenghtLimits(codigo, 1, 6))
                    erroresMap.put("Codigo", "El CODIGO debe tener entre 1 y 6 caracteres");
                else {
                    //CODIGO COMPROBACION DE CODIGO O ID INEXISTENTE
                    piezasEntity.setCodigo(
                            dinamicJpanel.getFieldsMap().get("tbcodigo").getText());

                    if (new PiezasDAO().exists(piezasEntity))
                        erroresMap.put("Codigo", "El codigo ya existe en la base de datos");
                }

                //NOMBRE
                String nombre = dinamicJpanel.getFieldsMap().get("tbnombre").getText();
                if (nombre.equals("")) erroresMap.put("Nombre", "El Nombre no puede estar vacio");
                else {
                    if (!Checkers.isOKStrMaxLenght(nombre, 40))
                        erroresMap.put("Nombre", "El NOMBRE excede en longitud. MAX 20.");
                    else piezasEntity.setNombre(
                            dinamicJpanel.getFieldsMap().get("tbnombre").getText());
                }
                //PRECIO
                String precioStr = dinamicJpanel.getFieldsMap().get("tbprecio").getText();
                if (!Checkers.ckStrIsDouble(precioStr))
                    erroresMap.put("Precio", "El Precio debe ser un valor númerico.");
                else piezasEntity.setPrecio(
                        Double.parseDouble(
                                dinamicJpanel.getFieldsMap().get("tbprecio").getText()
                        ));
            }
            case delete -> {
                //COMPROBAMOS LA EXISTENCIA DEL CODIGO INTRODUCIDO
                //CODIGO
                String codigo = dinamicJpanel.getFieldsMap().get("tbcodigo").getText();
                if (!Checkers.isOKStrLenghtLimits(codigo, 1, 6))
                    erroresMap.put("Codigo", "El CODIGO debe tener entre 1 y 6 caracteres");
                else {
                    //CODIGO COMPROBACION DE CODIGO O ID INEXISTENTE
                    piezasEntity.setCodigo(
                            dinamicJpanel.getFieldsMap().get("tbcodigo").getText());
                    piezasEntity= new PiezasDAO().getByCodigo(codigo);
                    if (piezasEntity==null)
                        erroresMap.put("Codigo", "El codigo NO existe en la base de datos");
                }
            }
            case update -> {
                HashMap<String, String> changesMap = new HashMap<>();
                //COMPROBACIONES  DE TIPO DE DATOS Y LOGICAS. SI SON CORRECTAS SE AÑADE EL CAMPO AL OBJETO
                //SE ALMACENAN LOS ERRORES EN UN HASHMAP ERRORESMAP Y LOS CAMBIOS EN OTRO CHANGESMAP

                //CODIGO
                String codigo = dinamicJpanel.getFieldsMap().get("tbcodigo").getText();
                if (!Checkers.isOKStrLenghtLimits(codigo, 1, 6))
                    erroresMap.put("Codigo", "El CODIGO debe tener entre 1 y 6 caracteres");
                else {
                    PiezasEntity piezasOld = new PiezasDAO().getByCodigo(codigo);
                    //COMPROBACION DE CODIGO O ID EXISTENTE
                    if (piezasOld == null) {
                        erroresMap.put("Codigo", "El código debe existir para poder actualizar");
                    } else {
                        piezasEntity.setId(piezasOld.getId());
                        piezasEntity.setCodigo(
                                dinamicJpanel.getFieldsMap().get("tbcodigo").getText());


                        //NOMBRE
                        String nombre = dinamicJpanel.getFieldsMap().get("tbnombre").getText();
                        if (nombre.equals("")) {
                            piezasEntity.setNombre(piezasOld.getNombre());
                            changesMap.put("Nombre",String.format("Nombre: %S",piezasOld.getNombre()));
                        }
                        else {
                            if (!Checkers.isOKStrMaxLenght(nombre, 20))
                                erroresMap.put("Nombre", "El NOMBRE excede en longitud. MAX 20.");
                            else {
                                piezasEntity.setNombre(
                                        dinamicJpanel.getFieldsMap().get("tbnombre").getText());
                                changesMap.put("Nombre",String.format("Nombre: %S --> %S",
                                        piezasOld.getNombre(),
                                        piezasEntity.getNombre()));
                            }
                        }

                        //PRECIO
                        String apellidos = dinamicJpanel.getFieldsMap().get("tbprecio").getText();
                        if (!Checkers.ckStrIsDouble(apellidos))
                            erroresMap.put("Precio", "El precio debe ser un valor numerico");
                        else {
                            piezasEntity.setPrecio(
                                    Double.parseDouble(dinamicJpanel.getFieldsMap().get("tbprecio").getText()));
                            changesMap.put("Ciudad",String.format("Precio: %2f --> %2f",
                                    piezasOld.getPrecio(),
                                    piezasEntity.getPrecio()));
                        }



                        //SI NO HAY ERRORES RETORNAMOS VERIFICAMOS DI HAY CAMBIOS Y LOS
                        // RETORNAMOS COMO MENSAJES JUNTO CON EL OBJETO PREPARADO PARA SER ACTUALIZADO
                        if (erroresMap.size()==0){
                            if (piezasEntity.equals(piezasOld)){
                                System.out.println("No se han detectado modificaciones");
                            }
                            else {
                                //Guardamos todos los errores en un único String para devolverlo
                                StringBuilder changes = new StringBuilder();
                                for (String change : changesMap.values()) changes.append(change).append("\n");

                                //Retornamos el objeto y los cambios que se harán.
                                return new AbstractMap.SimpleEntry<>(changes.toString(), piezasEntity);
                            }
                        }
                    }
                }

            }
        }

        //Guardamos todos los errores en un único String para devolverlo
        StringBuilder errores = new StringBuilder();
        for (String error : erroresMap.values()) errores.append(error).append("\n");

        //Retornamos errore y objeto a null O No errores y con un objeto
        if (!errores.toString().equals("")) {
            piezasEntity = null;
        }
        return new AbstractMap.SimpleEntry<>(errores.toString(), piezasEntity);

    }

    private static AbstractMap.SimpleEntry<String, Object>
    asignacionesChecker(DinamicJpanel dinamicJpanel, checkCause cause) {

        //Diccionario donde almacenaremos los errores
        HashMap<String, String> erroresMap = new HashMap<>();
        AsignacionesEntity asignacionesEntity = new AsignacionesEntity();

        //COMPROBACIONES  DE TIPO DE DATOS Y LOGICAS. SI SON CORRECTAS SE AÑADE EL CAMPO AL OBJETO
        //SI NO SE GUARDA ALMACENAN LOS ERRORES EN UN HASHMAP
        //LAS COMPROBACIONES PUEDEN VARIAR DEPENDIENDO DEL TIPO DE OPERACION PARA LA QUE SE REALIZA

        switch (cause) {
            case insert -> {
            }
            case delete -> {
            }
            case update -> {
            }
        }

        //Guardamos todos los errores en un único String para devolverlo
        StringBuilder errores = new StringBuilder();
        for (String error : erroresMap.values()) errores.append(error).append("\n");

        //Retornamos errore y objeto a null O No errores y con un objeto
        if (!errores.toString().equals("")) {
            asignacionesEntity = null;
        }
        return new AbstractMap.SimpleEntry<>(errores.toString(), asignacionesEntity);

    }

}
