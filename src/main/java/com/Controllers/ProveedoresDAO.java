package com.Controllers;

import com.Model.ProveedoresEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Iterator;
import java.util.List;

public class ProveedoresDAO extends GenericDAO<ProveedoresEntity> {

    public ProveedoresDAO() {
        super.setType(ProveedoresEntity.class);
    }

    public ProveedoresEntity getByCodigo(String codigo) {
        codigo= codigo.toLowerCase().trim();
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from ProveedoresEntity where lower(codigo) = lower( :micodigo)");
        q.setParameter("micodigo",codigo);
        ProveedoresEntity p = (ProveedoresEntity) q.uniqueResult();
        session.close();
        return p;
    }

    public boolean exists(ProveedoresEntity proveedoresEntity){
        ProveedoresEntity p = getByCodigo(proveedoresEntity.getCodigo());
        return p != null;
    }

}
