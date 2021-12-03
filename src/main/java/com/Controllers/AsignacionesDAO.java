package com.Controllers;

import com.Model.AsignacionesEntity;
import com.Model.PiezasEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleConsumer;


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
    public AsignacionesEntity getById(int id) {
        //return super.getById(id);
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Object o = session.get(AsignacionesEntity.class, id);
        session.close();
        return (AsignacionesEntity) o;
    }

    public List<AsignacionesEntity> getByPart(int idPieza) {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from AsignacionesEntity where idpieza = :idpieza ");
        q.setParameter("idpieza", idPieza);
        List mylist = q.list();
        session.close();
        return new ArrayList<AsignacionesEntity>(mylist);
    }

    public List<AsignacionesEntity> getByProv(int idProv) {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from AsignacionesEntity where idproveedor = :idprov ");
        q.setParameter("idprov", idProv);
        List mylist = q.list();
        session.close();
        return new ArrayList<AsignacionesEntity>(mylist);
    }

    public double getPartCantProvided(PiezasEntity piezasEntity){
        List<AsignacionesEntity> myList = getByPart(piezasEntity.getId());
        Double cant= 0.0;
        for (AsignacionesEntity asignacion: myList){
            cant+= asignacion.getCantidad();
        }
        return cant;
    }

}
