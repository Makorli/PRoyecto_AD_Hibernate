package com.Controllers;

import com.Model.AsignacionesEntity;
import com.Model.PiezasEntity;
import com.Model.ProveedoresEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
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

    public int getNumberProyects(PiezasEntity piezasEntity){
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Query q = session.createSQLQuery("select count(idproyecto) from gestionpiezas.Asignaciones where idpieza = :micodigo");
        q.setParameter("micodigo",piezasEntity.getId());
        assert q.list()!=null;
        int numero =Integer.parseInt(q.list().get(0).toString());
        session.close();
        return numero;
    }

    public int getNumberProveedores(PiezasEntity piezasEntity){
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Query q = session.createSQLQuery("select count(idproveedor) from gestionpiezas.Asignaciones where idpieza = :micodigo");
        q.setParameter("micodigo",piezasEntity.getId());
        assert q.list()!=null;
        int numero =Integer.parseInt(q.list().get(0).toString());
        session.close();
        return numero;
    }

}
