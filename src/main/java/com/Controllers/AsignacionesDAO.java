package com.Controllers;

import com.Model.AsignacionesEntity;
import com.Model.PiezasEntity;
import com.Model.ProveedoresEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.*;
import java.util.function.DoubleConsumer;


public class AsignacionesDAO extends GenericDAO<AsignacionesEntity> {

    public AsignacionesDAO() {
        super.setType(AsignacionesEntity.class);
    }

    public List<AsignacionesEntity> getByCandidateKey(int idProv, int idPieza, int idProyecto) {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("from AsignacionesEntity where idproveedor = :idprov and idproyecto = :idproyecto and idpieza = :idpieza");
        query.setParameter("idprov", idProv);
        query.setParameter("idproyecto", idProyecto);
        query.setParameter("idpieza", idPieza);

        List myList = query.list();
        session.close();
        return new ArrayList<AsignacionesEntity>(myList);
    }

    public boolean exists(AsignacionesEntity asignacionesEntity) {
        AsignacionesEntity a = getByProvProyPie(
                asignacionesEntity.getIdproveedor(),
                asignacionesEntity.getIdproyecto(),
                asignacionesEntity.getIdpieza());
        return a != null;
    }

    public AsignacionesEntity getByProvProyPie(int idProv, int idProyecto, int idPieza) {

        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from AsignacionesEntity where idproveedor = :idproveedor and idproyecto = :idproyecto and idpieza = :idpieza ");
        q.setParameter("idproveedor", idProv);
        q.setParameter("idproyecto", idProyecto);
        q.setParameter("idpieza", idPieza);
        AsignacionesEntity p = (AsignacionesEntity) q.uniqueResult();
        session.close();
        return p;
    }

    public List<AsignacionesEntity> getByProvProy(int idProv, int idProyecto){
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from AsignacionesEntity where idproveedor = :idproveedor and idproyecto = :idproyecto");
        q.setParameter("idproveedor", idProv);
        q.setParameter("idproyecto", idProyecto);
        List mylist = q.list();
        session.close();
        return new ArrayList<AsignacionesEntity>(mylist);
    }

    @Override
    public AsignacionesEntity getById(int id) {
        //return super.getById(id);
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Object o = session.get(AsignacionesEntity.class, id);
        session.close();
        return (AsignacionesEntity) o;
    }

    public List<AsignacionesEntity> getByPart(int idPieza) {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from AsignacionesEntity where idpieza = :idpieza ");
        q.setParameter("idpieza", idPieza);
        List mylist = q.list();
        session.close();
        return new ArrayList<AsignacionesEntity>(mylist);
    }

    public List<AsignacionesEntity> getByProv(int idProv) {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from AsignacionesEntity where idproveedor = :idprov ");
        q.setParameter("idprov", idProv);
        List mylist = q.list();
        session.close();
        return new ArrayList<AsignacionesEntity>(mylist);
    }

    public List<AsignacionesEntity> getByProyecto(int idProy) {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from AsignacionesEntity where idproyecto = :idproy ");
        q.setParameter("idproy", idProy);
        List mylist = q.list();
        session.close();
        return new ArrayList<AsignacionesEntity>(mylist);
    }

    public double getPartCantProvided(PiezasEntity piezasEntity){
        List<AsignacionesEntity> myList = getByPart(piezasEntity.getId());
        Double cant= 0.0;
        for (AsignacionesEntity asignacion: myList){
            cant+= asignacion.getCantidad();
        }
        return cant;
    }

    public AbstractMap.SimpleEntry<String, String> getPiezaMasSumnistrada(){

        AbstractMap.SimpleEntry<String,String> result=null;

        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        String consulta = "select distinct(pi.nombre) as nombrepieza,\n" +
                "sum(asg.cantidad) as total \n" +
                "from Asignaciones AS asg, Piezas pi, Proyectos pr\n" +
                "where asg.idproyecto=pr.id and asg.idpieza=pi.id \n" +
                "group by pi.nombre\n" +
                "order by total desc";
        Query q = session.createSQLQuery(consulta);
        Iterator iter = q.stream().iterator();
        if (iter.hasNext()){
            Object [] row = (Object[]) iter.next();
            String myKey= (String) row[0];
            String myValue = String.valueOf((Double) row[1]);
            return new AbstractMap.SimpleEntry<String,String>(
                    String.valueOf(myKey),
                    myValue);
        }
        session.close();
        return result;
    }

    public AbstractMap.SimpleEntry<String, String> getProveedorMasCantidadSuministra(){

        AbstractMap.SimpleEntry<String,String> result=null;

        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        String consulta = "select \n" +
                "pr.nombre as \"Nombre\",\n" +
                "sum(asg.cantidad) as \"Cantidad Total Suministrada\"\n" +
                "from Asignaciones as asg, Proveedores as pr\n" +
                "where asg.idproveedor=pr.id \n" +
                "group by pr.nombre \n" +
                "order by sum(asg.cantidad) desc";
        Query q = session.createSQLQuery(consulta);
        List mylist = q.list();
        Iterator iter = q.stream().iterator();
        if (iter.hasNext()){
            Object [] row = (Object[]) iter.next();
            String myKey= (String) row[0];
            String myValue = String.valueOf((Double) row[1]);
            return new AbstractMap.SimpleEntry<String,String>(
                    String.valueOf(myKey),
                    myValue);
        }
        session.close();
        return result;
    }

    //getPiezaEnMasProyectos
    public AbstractMap.SimpleEntry<String, String> getPiezaEnMasProyectos(){

        AbstractMap.SimpleEntry<String,String> result = null;

        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        //Todo consulta
        String consulta = "select \n" +
                "pr.nombre as \"Nombre\",\n" +
                "sum(asg.cantidad) as \"Cantidad Total Suministrada\"\n" +
                "from Asignaciones as asg, Proveedores as pr\n" +
                "where asg.idproveedor=pr.id \n" +
                "group by pr.nombre \n" +
                "order by sum(asg.cantidad) desc";
        Query q = session.createSQLQuery(consulta);
        Iterator iter = q.stream().iterator();
        if (iter.hasNext()){
            Object [] row = (Object[]) iter.next();
            String myKey= (String) row[0];
            String myValue = String.valueOf((Double) row[1]);
            return new AbstractMap.SimpleEntry<String,String>(
                    String.valueOf(myKey),
                    myValue);
        }
        session.close();
        return result;
    }

    //tfProvMasTipoDePiezasSuministra
    public AbstractMap.SimpleEntry<String, String> getProvMasTipoDePiezasSuministra(){

        AbstractMap.SimpleEntry<String,String> result=null;

        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        //Todo consulta
        String consulta = "select \n" +
                "pr.nombre as \"Nombre\",\n" +
                "sum(asg.cantidad) as \"Cantidad Total Suministrada\"\n" +
                "from Asignaciones as asg, Proveedores as pr\n" +
                "where asg.idproveedor=pr.id \n" +
                "group by pr.nombre \n" +
                "order by sum(asg.cantidad) desc";
        Query q = session.createSQLQuery(consulta);
        List mylist = q.list();
        Iterator iter = q.stream().iterator();
        if (iter.hasNext()){
            Object [] row = (Object[]) iter.next();
            String myKey= (String) row[0];
            String myValue = String.valueOf((Double) row[1]);
            return new AbstractMap.SimpleEntry<String,String>(
                    String.valueOf(myKey),
                    myValue);
        }
        session.close();
        return result;
    }

    //getProvEnMasProyectos
    public AbstractMap.SimpleEntry<String, String> getProvEnMasProyectos(){

        AbstractMap.SimpleEntry<String,String> result=null;

        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        //Todo consulta
        String consulta = "select \n" +
                "pr.nombre as \"Nombre\",\n" +
                "sum(asg.cantidad) as \"Cantidad Total Suministrada\"\n" +
                "from Asignaciones as asg, Proveedores as pr\n" +
                "where asg.idproveedor=pr.id \n" +
                "group by pr.nombre \n" +
                "order by sum(asg.cantidad) desc";
        Query q = session.createSQLQuery(consulta);
        List mylist = q.list();
        Iterator iter = q.stream().iterator();
        if (iter.hasNext()){
            Object [] row = (Object[]) iter.next();
            String myKey= (String) row[0];
            String myValue = String.valueOf((Double) row[1]);
            return new AbstractMap.SimpleEntry<String,String>(
                    String.valueOf(myKey),
                    myValue);
        }
        session.close();
        return result;
    }
}
