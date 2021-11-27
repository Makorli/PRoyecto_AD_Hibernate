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

}
