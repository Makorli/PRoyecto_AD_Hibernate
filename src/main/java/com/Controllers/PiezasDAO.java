package com.Controllers;

import com.Model.PiezasEntity;

import java.util.List;

public class PiezasDAO extends GenericDAO<PiezasEntity> {

    public PiezasDAO() {
        super.setType(PiezasEntity.class);
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
