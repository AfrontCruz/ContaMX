
package Controller;

import Model.Egreso;
import Model.Ingreso;
import Model.TipoEgreso;
import Model.TipoIngreso;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;



/**
 * FXML Controller class
 *
 * @author AppsMX
 */
public class AnalisisController implements Initializable {
    @FXML
    private TableView<Ingreso> tablaIngresos;
    @FXML
    private TableView<Egreso> tablaGastos;
    @FXML
    private JFXTextField totalIngreso;
    @FXML
    private JFXTextField totalGastos;
    @FXML
    private JFXTextField capital;
    
    private ObservableList<Ingreso> dataIngresos;
    private ObservableList<Egreso> dataEgresos;
    
    private Ingreso ingreso;
    private TipoIngreso tipoIngreso;
    private Egreso egreso;
    private TipoEgreso tipoEgreso;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ingreso = new Ingreso();
        tipoIngreso = new TipoIngreso();
        egreso = new Egreso();
        tipoEgreso = new TipoEgreso();
        this.fomatearTableEgreso();
        this.fomatearTableIngreso();
        this.cargarTablaIngresos();
        this.cargarTablaEgresos();
    }
    
    public void fomatearTableIngreso(){
        TableColumn colId = new TableColumn("Id");
        colId.setPrefWidth(50);
        colId.setResizable(false);
        TableColumn colTipo = new TableColumn("Tipo");
        colTipo.setPrefWidth(100);
        colTipo.setResizable(false);
        TableColumn colCantidad = new TableColumn("Cantidad");
        colCantidad.setPrefWidth(100);
        colCantidad.setResizable(false);
        TableColumn colFecha = new TableColumn("Fecha");
        colFecha.setPrefWidth(98);
        colFecha.setResizable(false);
        
        colId.setCellValueFactory( new PropertyValueFactory<Ingreso,Integer>("id"));
        colId.setEditable(true);
        colTipo.setCellValueFactory( new PropertyValueFactory<Ingreso,String>("tipo"));
        colTipo.setEditable(true);
        colCantidad.setCellValueFactory( new PropertyValueFactory<Ingreso,Float>("cantidad"));
        colCantidad.setEditable(true);
        colFecha.setCellValueFactory( new PropertyValueFactory<Ingreso,String>("fecha"));
        colFecha.setEditable(true);
        
        double e = egreso.readSum();
        double i = ingreso.readSum();
        double c = ingreso.readSum() - egreso.readSum();
        
        this.totalIngreso.setText("" + i);
        this.totalGastos.setText("" + e);
        this.capital.setText("" + c);
        if( c < 0 ){
            this.capital.setStyle(  "-fx-prompt-text-fill:red;\n" +
                                    "-fx-text-fill: red;");
        }else{
            this.capital.setStyle(  "-fx-prompt-text-fill:green;\n" +
                                    "-fx-text-fill: green;");
        }
        
        this.tablaIngresos.setEditable(true);
        this.tablaIngresos.getColumns().clear();
        this.tablaIngresos.getColumns().addAll(colId, colTipo, colCantidad, colFecha);
    }
    
    public void fomatearTableEgreso(){
        TableColumn colId = new TableColumn("Id");
        colId.setPrefWidth(50);
        colId.setResizable(false);
        TableColumn colTipo = new TableColumn("Tipo");
        colTipo.setPrefWidth(100);
        colTipo.setResizable(false);
        TableColumn colCantidad = new TableColumn("Cantidad");
        colCantidad.setPrefWidth(100);
        colCantidad.setResizable(false);
        TableColumn colFecha = new TableColumn("Fecha");
        colFecha.setPrefWidth(98);
        colFecha.setResizable(false);
        
        colId.setCellValueFactory( new PropertyValueFactory<Egreso,Integer>("id"));
        colId.setEditable(true);
        colTipo.setCellValueFactory( new PropertyValueFactory<Egreso,String>("tipo"));
        colTipo.setEditable(true);
        colCantidad.setCellValueFactory( new PropertyValueFactory<Egreso,Float>("cantidad"));
        colCantidad.setEditable(true);
        colFecha.setCellValueFactory( new PropertyValueFactory<Egreso,String>("fecha"));
        colFecha.setEditable(true);
        
        this.tablaGastos.setEditable(true);
        this.tablaGastos.getColumns().clear();
        this.tablaGastos.getColumns().addAll(colId, colTipo, colCantidad, colFecha);
    }
    
    
    public void cargarTablaIngresos(){
        LinkedList<Ingreso> datos = ingreso.read();
        dataIngresos = FXCollections.observableArrayList( datos );
        this.tablaIngresos.setItems(dataIngresos);
    }
    
    public void cargarTablaEgresos(){
        LinkedList<Egreso> datos = egreso.read();
        dataEgresos = FXCollections.observableArrayList( datos );
        this.tablaGastos.setItems(dataEgresos);
    }
}
