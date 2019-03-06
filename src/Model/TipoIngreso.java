
package Model;

import MySQL.Crud;
import java.util.LinkedList;

/**
 *
 * @author AppsMX
 */

public class TipoIngreso {
    private final String query_readTipo = "SELECT * FROM tipos_ingreso";
    private String tipo;
    
    public LinkedList<Object[]> read(){
        return Crud.read(this.query_readTipo, 1);
    }

    public TipoIngreso() {
    }

    public TipoIngreso(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
