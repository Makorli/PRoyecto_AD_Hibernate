package com.Controllers;

import com.Model.PiezasEntity;
import com.Model.ProveedoresEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PiezasDAO extends GenericDAO<PiezasEntity> {

    public PiezasDAO() {
        super.setType(PiezasEntity.class);
    }

    public PiezasEntity getByCodigo(String codigo) {
        codigo= codigo.toLowerCase().trim();
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from PiezasEntity where lower(codigo) = lower( :micodigo)");
        q.setParameter("micodigo",codigo);
        PiezasEntity p = (PiezasEntity) q.uniqueResult();
        session.close();
        return p;
    }

    public boolean exists(PiezasEntity piezasEntity){
        PiezasEntity p = getByCodigo(piezasEntity.getCodigo());
        return p != null;
    }

    @Override
    protected PiezasEntity getType() {
        return super.getType();
    }

    @Override
    protected void setType(Class<PiezasEntity> t) {
        super.setType(t);
    }

    @Override
    public boolean insert(PiezasEntity piezasEntity) {
        return super.insert(piezasEntity);
    }

    @Override
    public PiezasEntity getById(int id) {
        //return super.getById(id);
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Object o = session.get(PiezasEntity.class, id);
        session.close();
        return (PiezasEntity) o;
    }

    @Override
    public List<PiezasEntity> getAll() {
        return super.getAll();
    }

    @Override
    public List<PiezasEntity> getAllByStringSearch(String campo, String valor) {
        return super.getAllByStringSearch(campo, valor);
    }

    @Override
    public boolean update(PiezasEntity piezasEntity) {
        return super.update(piezasEntity);
    }

    @Override
    public boolean delete(PiezasEntity piezasEntity) {
        return super.delete(piezasEntity);
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
