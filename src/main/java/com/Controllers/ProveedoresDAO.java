package com.Controllers;

import com.Model.ProveedoresEntity;

import java.util.List;

public class ProveedoresDAO extends GenericDAO<ProveedoresEntity> {

    public ProveedoresDAO() {
        super.setType(ProveedoresEntity.class);
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
