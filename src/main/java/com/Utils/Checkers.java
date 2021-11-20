package com.Utils;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Checkers {


    public static boolean isOKStrMaxLenght(String str, int maxlenght){
        if (str.length()>maxlenght) return false;
        return true;
    }

    public static boolean isOKStrMinLenght(String str, int minlenght){
        if (str.length()<minlenght) return false;
        return true;
    }

    public static boolean isOKStrLenghtLimits(String str, int minlenght, int maxlenght){
        if (str.length()<=maxlenght && str.length()>=minlenght) return true;
        return false;
    }

    public static boolean ckStrIsDouble (String str){
        try{
        Double d = Double.parseDouble(str);
        return true;
        }
        catch (NumberFormatException | NullPointerException e){
            return false;
        }
    }

    public static boolean ckStrIsInt (String str){
        try{
            Integer d = Integer.parseInt(str);
            return true;
        }
        catch (NumberFormatException | NullPointerException e){
            return false;
        }
    }

    /*
    public String validaciones(Cliente cliente) {

        HashMap<String, String> errores = new HashMap<>();

        if (cliente.getDni().length() > 9 || cliente.getDni().equals("")) {
            if (cliente.getDni().length() > 9) {
                errores.put("DNI", "El DNI excede en longitud. MAX 9.");
            } else {
                errores.put("DNI", "El DNI no puede estar vacio.");
            }

        }
        if (cliente.getNombre().length() > 40 || cliente.getNombre().equals("")) {
            if (cliente.getNombre().length() > 40) {
                errores.put("Nombre", "El nombre excede en longitud. MAX 40.");
            } else {
                errores.put("Nombre", "El nombre no puede estar vacio.");
            }

        }
        if (cliente.getApellidos().length() > 40 || cliente.getApellidos().equals("")) {
            if (cliente.getApellidos().length() > 40) {
                errores.put("Apellidos", "El apellido excede en longitud. MAX 40.");
            } else {
                errores.put("Apellidos", "El apellido no puede estar vacio.");
            }

        }


        // Validar la fecha tanto de contratación como naciemiento, utilizamos la clase DateValidatorByDateTimeFormateer.java

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


        if (!cliente.getFechaNacimiento().equals("")) {
            DateValidatorByDateTimeFormatter d = new DateValidatorByDateTimeFormatter(formatter);
            if (!(d.isValid(cliente.getFechaNacimiento()))) {
                errores.put("FechaNacimiento", "La fecha de nacimiento no es correcta (dd/mm/yyyy).");
            }
        }

        //Utilizamos esta variable para guardar el mensaje de error.
        StringBuilder texto = new StringBuilder();

        if (errores.size() > 0) {
            for (Map.Entry<String, String> entry : errores.entrySet()) {
                String k = entry.getKey();
                String v = entry.getValue();
                texto.append(v + "\n");
            }
            return texto.toString();
        } else {
            return null;
        }
    }

     */
}
