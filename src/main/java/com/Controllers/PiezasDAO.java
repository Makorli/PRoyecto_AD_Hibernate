package com.Controllers;

import com.Model.PiezasEntity;
import com.Model.ProveedoresEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
        PiezasEntity p = session.get(PiezasEntity.class, codigo);
        return p;
    }

    public boolean exists(PiezasEntity piezasEntity){
        PiezasEntity p = getByCodigo(piezasEntity.getCodigo());
        return p != null;
    }

    @Override
    public void insert(PiezasEntity piezasEntity) {
        super.insert(piezasEntity);
    }

    @Override
    public PiezasEntity getById(int id) {
        return super.getById(id);
    }

    @Override
    public List<PiezasEntity> getAll() {
        return super.getAll();
    }

    @Override
    public void update(PiezasEntity piezasEntity) {
        super.update(piezasEntity);
    }

    @Override
    public void delete(PiezasEntity piezasEntity) {
        super.delete(piezasEntity);
    }
}
