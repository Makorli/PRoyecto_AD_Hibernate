package com.Controllers;

import com.Model.AsignacionesEntity;
import com.Model.ProveedoresEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;


public class AsignacionesDAO extends GenericDAO<AsignacionesEntity> {

    public AsignacionesDAO() {
        super.setType(AsignacionesEntity.class);
    }

    public List<AsignacionesEntity> getByCandidateKey(int idProv, int idPieza, int idProyecto) {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("from AsignacionesEntity where idproveedor = :idprov and idproyecto = :idproyecto and idpieza = :idpieza");
        query.setParameter("idprov", idProv);
        query.setParameter("idproyecto", idProyecto);
        query.setParameter("idpieza", idPieza);

        List myList = query.list();
        session.close();
        return new ArrayList<AsignacionesEntity>(myList);
    }

    public boolean exists(AsignacionesEntity asignacionesEntity) {
        AsignacionesEntity a = getByProvProyPie(
                asignacionesEntity.getIdproveedor(),
                asignacionesEntity.getIdproyecto(),
                asignacionesEntity.getIdpieza());
        return a != null;
    }

    public AsignacionesEntity getByProvProyPie(int idProv, int idProyecto, int idPieza) {

        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from AsignacionesEntity where idproveedor = :idproveedor and idproyecto = :idproyecto and idpieza = :idpieza ");
        q.setParameter("idproveedor", idProv);
        q.setParameter("idproyecto", idProyecto);
        q.setParameter("idpieza", idPieza);
        AsignacionesEntity p = (AsignacionesEntity) q.uniqueResult();
        session.close();
        return p;
    }

    @Override
    protected AsignacionesEntity getType() {
        return super.getType();
    }

    @Override
    protected void setType(Class<AsignacionesEntity> t) {
        super.setType(t);
    }

    @Override
    public boolean insert(AsignacionesEntity asignacionesEntity) {
        return super.insert(asignacionesEntity);
    }

    @Override
    public AsignacionesEntity getById(int id) {
        //return super.getById(id);
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Object o = session.get(AsignacionesEntity.class, id);
        session.close();
        return (AsignacionesEntity) o;
    }

    @Override
    public List<AsignacionesEntity> getAll() {
        return super.getAll();
    }

    @Override
    public List<AsignacionesEntity> getAllByStringSearch(String campo, String valor) {
        return super.getAllByStringSearch(campo, valor);
    }

    @Override
    public boolean update(AsignacionesEntity asignacionesEntity) {
        return super.update(asignacionesEntity);
    }

    @Override
    public boolean delete(AsignacionesEntity asignacionesEntity) {
        return super.delete(asignacionesEntity);
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
