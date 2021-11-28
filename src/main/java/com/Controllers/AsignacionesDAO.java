package com.Controllers;

import com.Model.AsignacionesEntity;
import com.Model.PiezasEntity;
import com.Model.ProveedoresEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AsignacionesDAO extends GenericDAO<AsignacionesDAO> {

    public AsignacionesDAO() {
        super.setType(AsignacionesDAO.class);
    }
/*
    public ProveedoresEntity getByCandidateKey(int idProv, int idPieza, int idProyecto) {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from AsignacionesEntity where id = lower( :micodigo)");
        q.setParameter("micodigo",codigo);
        ProveedoresEntity p = (ProveedoresEntity) q.uniqueResult();
        session.close();
        return p;
    }
    
 */
}
