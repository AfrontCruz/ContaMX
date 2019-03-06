
package Model;

import MySQL.Crud;
import java.text.SimpleDateFormat;
import java.util.LinkedList;


/**
 *
 * @author AppsMX
 */

public class Egreso{
    private final String query_create = "INSERT INTO egresos( tipo, cantidad, fecha ) VALUES( '@tipo', @cantidad, '@fecha' )";
    private final String query_readAll = "SELECT * FROM egresos";
    private final String query_delete = "DELETE FROM egresos WHERE id = '@id'";
    private final String query_sum = "SELECT SUM( cantidad ) FROM egresos;";
    private Integer id;
    private String tipo;
    private float cantidad;
    private String fecha;
    
    public void create(){
        String sql = query_create;
        sql = this.replaceSQL(sql);
        Crud.create(sql);
    }
    
    public LinkedList<Egreso> read(){
        LinkedList<Egreso> list = new LinkedList<Egreso>();
        System.out.println("Query: " + this.query_readAll);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        for( Object[] aux : Crud.read( this.query_readAll, 4) ){
            list.add( new Egreso( (Integer)aux[0], (String)aux[1], (float)aux[2], format.format(aux[3]) ) );
        }
        return list;
    }
    
    public void delete(){
        String del = this.query_delete;
        del = del.replace("@id", "" + this.id);
        Crud.delete( del );
    }
    
    public double readSum(){
        double sum = 0;
        for( Object [] aux : Crud.read(this.query_sum, 1) ){
            sum = (double) aux[0];
        }
        return sum;
    }

    public Egreso() {
        
    }

    public Egreso(Integer id) {
        this.id = id;
    }

    public Egreso(Integer id, String tipo, float cantidad, String fecha) {
        this.id = id;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public String replaceSQL(String sql){
        if( this.tipo != null )
            sql = sql.replace("@tipo", this.tipo);
        sql = sql.replace("@cantidad", "" + cantidad );
        if( this.fecha != null )
            sql = sql.replace("@fecha", this.fecha);
        
        return sql;
    }
}
