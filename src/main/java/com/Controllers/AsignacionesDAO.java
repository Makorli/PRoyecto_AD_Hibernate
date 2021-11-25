package com.Controllers;

import com.Model.AsignacionesEntity;
import com.Model.PiezasEntity;
import com.Model.ProveedoresEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class AsignacionesDAO extends GenericDAO<AsignacionesDAO> {

    public AsignacionesDAO() {
        super.setType(AsignacionesDAO.class);
    }

    @Override
    protected AsignacionesDAO getType() {
        return super.getType();
    }

    @Override
    protected void setType(Class<AsignacionesDAO> t) {
        super.setType(t);
    }

    @Override
    public void insert(AsignacionesDAO asignacionesDAO) {
        super.insert(asignacionesDAO);
    }

    @Override
    public AsignacionesDAO getById(int id) {
        return super.getById(id);
    }

    @Override
    public List<AsignacionesDAO> getAll() {
        return super.getAll();
    }

    @Override
    public void update(AsignacionesDAO asignacionesDAO) {
        super.update(asignacionesDAO);
    }

    @Override
    public void delete(AsignacionesDAO asignacionesDAO) {
        super.delete(asignacionesDAO);
    }
}
