package com.Controllers;

import com.Model.ProveedoresEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
        ProveedoresEntity p = session.get(ProveedoresEntity.class, codigo);
        return p;
    }

    public boolean exists(ProveedoresEntity proveedoresEntity){
        ProveedoresEntity p = getByCodigo(proveedoresEntity.getCodigo());
        return p != null;
    }

    @Override
    public void insert(ProveedoresEntity proveedores) {
        super.insert(proveedores);
    }

    @Override
    public ProveedoresEntity getById(int id) {
        return super.getById(id);
    }

    @Override
    public List<ProveedoresEntity> getAll() {
        return super.getAll();
    }

    @Override
    public void update(ProveedoresEntity proveedores) {
        super.update(proveedores);
    }

    @Override
    public void delete(ProveedoresEntity proveedores) {
        super.delete(proveedores);
    }
}
