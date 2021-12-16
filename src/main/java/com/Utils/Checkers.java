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
}
