package com.App;


import com.Controllers.HibernateUtil;
import com.Utils.SimpleBackGround;
import com.Views.MyMenuBar;
import org.hibernate.SessionFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        //Inicializamos la conexión con hibernate
        /*
        SessionFactory sf =HibernateUtil.getSessionFactory();
        if (sf.isOpen()){
            System.out.println("\nInicialización correcta BD");
        }


         */

        JFrame myAppWindow = new JFrame("Gestión de Proyectos");

        //Panel de fondo de pantalla de aplicacion principal
        SimpleBackGround.BackgroundPane background = new SimpleBackGround.BackgroundPane();
        try {
            //Establecemos la imagen
            background.setBackground(ImageIO.read(new File("src/main/resources/Images/pikist.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        myAppWindow.setContentPane(background);
        myAppWindow.setJMenuBar(MyMenuBar.getMyAppMenu());
        myAppWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        myAppWindow.setVisible(true);
        myAppWindow.setExtendedState (Frame.MAXIMIZED_BOTH);

    }


}
