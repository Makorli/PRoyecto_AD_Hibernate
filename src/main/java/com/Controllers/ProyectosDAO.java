package com.Controllers;

import com.Model.ProyectosEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
        ProyectosEntity p = session.get(ProyectosEntity.class, codigo);
        return p;
    }

    public boolean exists(ProyectosEntity proyectosEntity){
        ProyectosEntity p = getByCodigo(proyectosEntity.getCodigo());
        return p != null;
    }

    @Override
    public void insert(ProyectosEntity proyectosEntity) {
        super.insert(proyectosEntity);
    }

    @Override
    public ProyectosEntity getById(int id) {
        return super.getById(id);
    }

    @Override
    public List<ProyectosEntity> getAll() {
        return super.getAll();
    }

    @Override
    public void update(ProyectosEntity proyectosEntity) {
        super.update(proyectosEntity);
    }

    @Override
    public void delete(ProyectosEntity proyectosEntity) {
        super.delete(proyectosEntity);
    }
}
