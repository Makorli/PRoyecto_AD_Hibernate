package com.Controllers;

import com.Model.ProveedoresEntity;
import org.hibernate.*;
import org.hibernate.exception.ConstraintViolationException;

public class GenericDAO<T> {

    private final T t;

    public GenericDAO(T t) { this.t = t; }
    

    public void create(T t){
        SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
        Session session = sessionfactory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            session.save(t);
            tx.commit();
        } catch (ConstraintViolationException e) {
            System.out.println("Objeto duplicado en la base de datos");
            e.printStackTrace();
        } catch (TransientPropertyValueException tr){
            System.out.println("Objeto asociado no existe");
        }
        session.close();
    }

    //RETRIEVE
    public void getB
    (int id){
        //TODO
    }

    //UPDATE
    public  void update(T t) {
        SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
        Session session = sessionfactory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            session.update(t);
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
    public void delete(T t){
        SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
        Session session = sessionfactory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            session.delete(t);
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
