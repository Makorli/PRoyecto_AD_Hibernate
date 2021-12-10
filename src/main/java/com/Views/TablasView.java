package com.Views;

import javax.swing.*;
import javax.swing.table.TableModel;

public class TablasView {
    private JPanel JPGeneral;
    private JScrollPane jScrollpane;
    private JTable tablaDatos;

    private TableModel myTableModel;

    public JPanel getJPGeneral() {
        return JPGeneral;
    }


    public TablasView(TableModel tableModel) {
        myTableModel = tableModel;
        jScrollpane.setViewportView(tablaDatos);
        tablaDatos.setModel(myTableModel);
    }

}
