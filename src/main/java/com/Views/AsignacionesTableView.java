package com.Views;

import com.Controllers.AsignacionesDAO;
import com.Model.AsignacionesEntity;

import javax.swing.*;
import java.util.List;

public class AsignacionesTableView{
    private JPanel JPGeneral;
    private JTable table1;
    private JScrollPane jScrollPane;

    public JPanel getJPGeneral() {
        return JPGeneral;
    }

    public AsignacionesTableView(List<AsignacionesEntity> asignacionesEntityList) {
        table1 = new JTable();
        jScrollPane.setViewportView(table1);
        table1.setModel(new AsignacionesTablaModel(asignacionesEntityList));
    }
}
