package com.Controllers;

import com.Model.ProyectosEntity;

import java.util.List;

public class ProyectosDAO extends GenericDAO<ProyectosEntity> {

    public ProyectosDAO() {
        super.setType(ProyectosEntity.class);
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
