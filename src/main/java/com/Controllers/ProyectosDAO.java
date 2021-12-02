package com.Controllers;

import com.Model.ProveedoresEntity;
import com.Model.ProyectosEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ProyectosDAO extends GenericDAO<ProyectosEntity> {

    public ProyectosDAO() {
        super.setType(ProyectosEntity.class);
    }

    public ProyectosEntity getByCodigo(String codigo) {
        codigo= codigo.toLowerCase().trim();
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from ProyectosEntity where lower(codigo) = lower( :micodigo)");
        q.setParameter("micodigo",codigo);
        ProyectosEntity p = (ProyectosEntity) q.uniqueResult();
        session.close();
        return p;
    }

    public boolean exists(ProyectosEntity proyectosEntity){
        ProyectosEntity p = getByCodigo(proyectosEntity.getCodigo());
        return p != null;
    }

    @Override
    protected ProyectosEntity getType() {
        return super.getType();
    }

    @Override
    protected void setType(Class<ProyectosEntity> t) {
        super.setType(t);
    }

    @Override
    public boolean insert(ProyectosEntity proyectosEntity) {
        return super.insert(proyectosEntity);
    }

    @Override
    public ProyectosEntity getById(int id) {
        //return super.getById(id);
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Object o = session.get(ProyectosEntity.class, id);
        session.close();
        return (ProyectosEntity) o;
    }

    @Override
    public List<ProyectosEntity> getAll() {
        return super.getAll();
    }

    @Override
    public List<ProyectosEntity> getAllByStringSearch(String campo, String valor) {
        return super.getAllByStringSearch(campo, valor);
    }

    @Override
    public boolean update(ProyectosEntity proyectosEntity) {
        return super.update(proyectosEntity);
    }

    @Override
    public boolean delete(ProyectosEntity proyectosEntity) {
        return super.delete(proyectosEntity);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
