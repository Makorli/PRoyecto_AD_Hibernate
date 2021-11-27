package com.Utils;

public class Misc {

    /**
     * Funcion que realziar un cast del objeto pasado como parametro a la clase espcificada
     * y retorna el objeto convertido
     * @param clazz clase a la que castear el objeto
     * @param object objeto a castear
     * @param <T> objeto ya convertido ala clse especificada.
     * @return
     */
    public static  <T> T castObject(Class<T> clazz, Object object) {
        return (T) object;
    }
}
