package com.Controllers;

import com.Model.ProveedoresEntity;
import org.hibernate.*;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import java.util.List;

public abstract class GenericDAO<T> {

    private Object T;

    protected T getType(){
        return (T) T;
    }

    protected void setType(Class<T> t){
        this.T = t;
    }

    public void insert(T t){
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
    public T getById(int id) {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Object o = session.get(T.getClass(), id);
        return (T) o;
    }

    public List<T>getAll(){
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();

        String className = T.toString();
        className = className.substring(className.lastIndexOf('.')+1);

        Query query = session.createQuery(String.format("from %s",className));
        List<T> myList = query.list();
        /*
        Iterator<T> myIterator = myList.iterator();
        while (myIterator.hasNext()){
            T t = (T) myIterator.next();
        }*/
        return myList;
    }

    //UPDATE
    public void update(T t) {
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
