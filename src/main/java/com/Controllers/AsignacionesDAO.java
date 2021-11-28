package com.Controllers;

import com.Model.AsignacionesEntity;
import com.Model.PiezasEntity;
import com.Model.ProveedoresEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


public class AsignacionesDAO extends GenericDAO<AsignacionesDAO> {

    public AsignacionesDAO() {
        super.setType(AsignacionesDAO.class);
    }

    public ProveedoresEntity getByCandidateKey(int idProv, int idPieza, int idProyecto) {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from AsignacionesEntity where idproveedor = :idprov and idpieza = :idpieza and idproyecto = :idproyecto");
        q.setParameter("idprov",idProv);
        q.setParameter("idpieza",idPieza);
        q.setParameter("idproyecto",idProyecto);
        ProveedoresEntity p = (ProveedoresEntity) q.uniqueResult();
        session.close();
        return p;
    }


}
