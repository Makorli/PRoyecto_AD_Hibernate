package com.Controllers;


import com.Model.ProveedoresEntity;
import org.hibernate.*;
import org.hibernate.exception.ConstraintViolationException;

public class ProveedoresDAO {

    //CREATE
    public static void create(ProveedoresEntity proveedor){
        SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
        Session session = sessionfactory.openSession();

        Transaction tx = session.beginTransaction();

        try {
            session.save(proveedor);
            tx.commit();
        } catch (ConstraintViolationException e) {
            System.out.println("Objeto duplicado en la base de datos");
            e.printStackTrace();
        } catch (TransientPropertyValueException t){
            System.out.println("Objeto asociado no existe");
        }
        session.close();
    }

    //RETRIEVE
    public static void getById(int id){
        //TODO
    }

    //UPDATE
    public static void update(ProveedoresEntity proveedor) {
        SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
        Session session = sessionfactory.openSession();

        Transaction tx = session.beginTransaction();

        try {
            session.update(proveedor);
            tx.commit();
        } catch (ObjectNotFoundException o) {
            System.out.println("NO EXISTE EL OBJETO A ACTUALIZAR");
        } catch (ConstraintViolationException c) {
            System.out.println("ERRORES EN CLAVES FORENEAS QUE NO EXISTEN");
        } catch (Exception e) {
            System.out.println("ERROR NO CONTROLADO");
            e.printStackTrace();
        }

        session.close();
    }
    //DELETE
    public static void delete(ProveedoresEntity proveedor){
            SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
            Session session = sessionfactory.openSession();

            Transaction tx = session.beginTransaction();

            try {
                session.delete(proveedor);
                tx.commit();
            }catch (ObjectNotFoundException o){
                System.out.println ("NO EXISTE EL OBJETO A ACTUALIZAR");
            }catch (ConstraintViolationException c){
                System.out.println ("OBJETO CON DEPENDENCIAS ASOCIADAS");
            }catch (Exception e){
                System.out.println ("ERROR NO CONTROLADO");
                e.printStackTrace();
            }

            session.close();
    }
}
