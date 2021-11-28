package com.Controllers;

import com.Model.ProveedoresEntity;
import jakarta.persistence.PersistenceException;
import org.hibernate.*;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericDAO<T> {

    private Object T;

    protected T getType() {
        return (T) T;
    }

    protected void setType(Class<T> t) {
        this.T = t;
    }

    //INSERT
    public boolean insert(T t) {
        SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
        Session session = sessionfactory.openSession();
        Transaction tx = session.beginTransaction();
        boolean todok = false;
        try {
            session.save(t);
            tx.commit();
            todok= true;
        } catch (ConstraintViolationException e) {
            System.out.println("Objeto duplicado en la base de datos");
        } catch (TransientPropertyValueException tr) {
            System.out.println("Objeto asociado no existe");
        } catch (PersistenceException pe) {
            System.out.println("Codigo duplicado o código no coherente.\n" +
                    " o Campos vacios no permitidos");
            pe.printStackTrace();
        }
        session.close();
        return todok;

    }

    //RETRIEVE
    public T getById(int id) {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Object o = session.get(T.getClass(), id);
        return (T) o;
    }

    public List<T> getAll() {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();

        String className = T.toString();
        className = className.substring(className.lastIndexOf('.') + 1);

        Query query = session.createQuery(String.format("from %s", className));
        List myList = query.list();

        return new ArrayList<T>(myList);
        /*
        Iterator<T> myIterator = myList.iterator();
        while (myIterator.hasNext()){
            T t = (T) myIterator.next();
        }
         */
    }

    public List<T> getAllByStringSearch(String campo, String valor){
        valor=valor.trim();

        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();

        String className = T.toString();
        className = className.substring(className.lastIndexOf('.') + 1);

        String strQuery = String.format("from %s where lower(%s) like lower(:%s)", className,campo,campo);
        Query query = session.createQuery(strQuery);
        query.setParameter(campo,valor.trim());

        List myList = query.list();
        session.close();
        return new ArrayList<T>(myList);
    }

    //UPDATE
    public boolean update(T t) {
        SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
        Session session = sessionfactory.openSession();
        Transaction tx = session.beginTransaction();

        boolean todok=false;
        try {
            session.update(t);
            tx.commit();
            todok=true;
        } catch (ObjectNotFoundException o) {
            System.out.println("NO EXISTE EL OBJETO A ACTUALIZAR");
        } catch (ConstraintViolationException c) {
            System.out.println("ERRORES EN CLAVES FORENEAS QUE NO EXISTEN");
        } catch (Exception e) {
            System.out.println("ERROR NO CONTROLADO");
            e.printStackTrace();
        }
        session.close();
        return todok;

    }

    //DELETE
    public boolean delete(T t) {
        SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
        Session session = sessionfactory.openSession();
        Transaction tx = session.beginTransaction();

        boolean todok=false;
        try {
            session.delete(t);
            tx.commit();
            todok= true;
        } catch (ObjectNotFoundException o) {
            System.out.println("NO EXISTE EL OBJETO A ACTUALIZAR");
        } catch (ConstraintViolationException c) {
            System.out.println("OBJETO CON DEPENDENCIAS ASOCIADAS");
        } catch (Exception e) {
            System.out.println("* * * * ERROR CONTROLADO PERO NO TRATADO * * * *");
            e.printStackTrace();
        }
        session.close();
        return todok;
    }

}
