package com.Controllers;

import com.Model.ProveedoresEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


public class ProveedoresDAO extends GenericDAO<ProveedoresEntity> {

    public ProveedoresDAO() {
        super.setType(ProveedoresEntity.class);
    }

    public ProveedoresEntity getByCodigo(String codigo) {
        codigo= codigo.toLowerCase().trim();
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
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

    @Override
    public ProveedoresEntity getById(int id) {
        //return super.getById(id);
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Object o = session.get(ProveedoresEntity.class, id);
        session.close();
        return (ProveedoresEntity) o;
    }


    public double getTotalPartsSupplied(ProveedoresEntity proveedoresEntity) {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Query q = session.createSQLQuery("select sum(cantidad) from gestionpiezas.Asignaciones where idproveedor = :micodigo");
        q.setParameter("micodigo",proveedoresEntity.getId());
        assert q.list()!=null;
        double numero =Double.parseDouble(q.list().get(0).toString());
        session.close();
        return numero;
    }

    public int getNumberProyects(ProveedoresEntity proveedoresEntity){
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Query q = session.createSQLQuery("select count(idproyecto) from gestionpiezas.Asignaciones where idproveedor = :micodigo");
        q.setParameter("micodigo",proveedoresEntity.getId());
        assert q.list()!=null;
        int numero =Integer.parseInt(q.list().get(0).toString());
        session.close();
        return numero;
    }

}
