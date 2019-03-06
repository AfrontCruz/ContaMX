
package Model;

import MySQL.Crud;
import java.util.LinkedList;

/**
 *
 * @author AppsMX
 */

public class TipoEgreso{
    private final String query_readTipo = "SELECT * FROM tipos_egreso";
    private String tipo;
    
    public LinkedList<Object[]> read(){
        return Crud.read(this.query_readTipo, 1);
        
    }

    public TipoEgreso() {
    }

    public TipoEgreso(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
