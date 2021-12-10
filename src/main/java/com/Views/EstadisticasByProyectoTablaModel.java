package com.Views;

import com.Controllers.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class EstadisticasByProyectoTablaModel extends AbstractTableModel {

    //COLUMNAS
    private final String[] columnas = {
            "COD. PROYECTO",    //POS 0
            "NOMBRE",           //POS 1
            "CIUDAD",           //POS 2
            "CANTIDAD USADA",   //POS 3
            "TIPOS DE PIEZA",   //POS 4
            "Nº PROVEEDORES"    //POS 5
    };

    //LISTA DE PROYECTOS
    private List<Object> datosTabla;

    public EstadisticasByProyectoTablaModel() {
        datosTabla = customQuery();
    }

    @Override
    public int getRowCount() {
        return datosTabla.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Object[] fila = (Object[]) datosTabla.get(rowIndex);

        switch (columnIndex){
            //COD PROYECTO
            case 0 ->{ return String.valueOf(fila[0]); }
            //NOMBRE
            case 1 ->{ return String.valueOf(fila[1]); }
            //CIUDAD
            case 2 ->{ return String.valueOf(fila[2]); }
            //NUMERO DE PIEZAS
            case 3 ->{ return String.valueOf(fila[3]); }
            //CANTIDA
            case 4 ->{ return String.valueOf(fila[4]); }
            //NºPROVEEDORES
            case 5 ->{ return String.valueOf(fila[5]); }
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    // Ejecuta consulta personalizada para obtener los datos de la tabla
    // de estadíticas de proyectos.
    private List<Object> customQuery(){
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        String customQuery = "select \n" +
                "pj.codigo as \"Cod Proyecto\", \n" +
                "pj.nombre as \"Nombre\", \n" +
                "pj.ciudad as \"Ciudad\", \n" +
                "sum(asg.cantidad) as \"Cantidad usada\",\n" +
                "count(asg.idpieza) as \"Tipos de Piezas\",\n" +
                "count(pr.id)  as \"Nº Proveedores\"\n" +
                "from Proveedores pr, Asignaciones asg, Proyectos pj\n" +
                "where asg.idproyecto=pj.id and asg.idproveedor=pr.id \n" +
                "group by pj.codigo";

        Query q = session.createSQLQuery(customQuery);
        List mylist = q.list();
        session.close();
        return new ArrayList<Object>(mylist);
    }
}
