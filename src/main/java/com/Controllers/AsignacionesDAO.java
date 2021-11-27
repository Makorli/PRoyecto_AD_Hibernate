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

}
