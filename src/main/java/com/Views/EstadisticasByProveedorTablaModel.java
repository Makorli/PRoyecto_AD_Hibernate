package com.Views;

import com.Controllers.HibernateUtil;
import com.Controllers.PiezasDAO;
import com.Controllers.ProveedoresDAO;
import com.Controllers.ProyectosDAO;
import com.Model.AsignacionesEntity;
import com.Model.ProveedoresEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class EstadisticasByProveedorTablaModel extends AbstractTableModel {

    //COLUMNAS
    private final String[] columnas = {
            "COD. PROVEEDOR",   //POS 0
            "NOMBRE",           //POS 1
            "APELLIDOS",        //POS 2
            "CANT. SUMNISTRADA",//POS 3
            "TIPOS DE PIEZA",   //POS 4
            "Nº PROYECTOS"      //POS 5
    };

    //LISTA DE PROVEEDORES
    private final List<Object> datosTabla;

    public EstadisticasByProveedorTablaModel() {
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
            //COD PROVEEDOR
            case 0 ->{ return fila[0]; }
            //NOMBRE
            case 1 ->{ return fila[1]; }
            //APELLIDOS
            case 2 ->{ return fila[2]; }
            //NUMERO DE PIEZAS
            case 3 ->{ return fila[3]; }
            //CANTIDAD
            case 4 ->{ return fila[4]; }
            //NºPROYECTOS
            case 5 ->{ return fila[5]; }
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    // Ejecuta consulta personalizada para obtener los datos de la tabla
    // de estadíticas de proveedores.
    private List<Object> customQuery(){
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        String customQuery = "select \n" +
                "pr.codigo as \"Cod Proveedor\", \n" +
                "pr.nombre as \"Nombre\", \n" +
                "pr.apellidos as \"Apellidos\", \n" +
                "sum(asg.cantidad) as \"Cantidad Suministrada\",\n" +
                "count(asg.idpieza) as \"Tipos de Piezas\",\n" +
                "count(pj.id)  as \"Nº Proyectos\"\n" +
                "from Proveedores pr, Asignaciones asg, Proyectos pj\n" +
                "where asg.idproyecto=pj.id and asg.idproveedor=pr.id \n" +
                "group by pr.codigo";

        Query q = session.createSQLQuery(customQuery);
        List mylist = q.list();
        session.close();
        return new ArrayList<Object>(mylist);
    }
}
