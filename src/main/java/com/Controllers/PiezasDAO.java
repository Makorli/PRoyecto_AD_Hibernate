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

}
