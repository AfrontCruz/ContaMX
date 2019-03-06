
package Controller;

import Model.Egreso;
import Model.Ingreso;
import Model.TipoIngreso;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author AppsMX
 */
public class VentasController implements Initializable {
    @FXML
    private JFXTextField cantidad;
    @FXML
    private JFXDatePicker fecha;
    @FXML
    private JFXComboBox<String> tipo;
    @FXML
    private JFXButton btnRegistrar;
    @FXML
    private TableView tabla;
    
    private ObservableList<Ingreso> data;
    private ObservableList<String> tiposIngresos;
    
    private Ingreso ingreso;
    private TipoIngreso tipoIngreso;
    
    @FXML
    void getCombinacion(KeyEvent event) {
        if( event.isControlDown() && event.getCode() == KeyCode.D ){
            int row = this.tabla.getSelectionModel().getSelectedIndex();
            if( row >= 0 ){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Shure?");
                alert.setHeaderText("Confirmación");
                alert.setContentText("¿Seguro que quieres eliminarlo?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    this.eliminarRegistro(row);
                }else{
                    event.consume();
                }
            }
        }
    }
    
    @FXML
    void registrarIngreso(MouseEvent event) {
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat _f = new SimpleDateFormat("yyyy/MM/dd");
        Date fecha = null;
        try {
            fecha = f.parse( this.fecha.getEditor().getText() );
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        ingreso.setCantidad( Float.parseFloat( this.cantidad.getText() ) );
        ingreso.setFecha( _f.format(fecha) );
        ingreso.setTipo( this.tipo.getValue() );
        ingreso.create();
        this.limpiar();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ingreso = new Ingreso();
        tipoIngreso = new TipoIngreso();
        
        TableColumn colId = new TableColumn("Id");
        colId.setPrefWidth(80);
        colId.setResizable(false);
        TableColumn colTipo = new TableColumn("Tipo");
        colTipo.setPrefWidth(400);
        colTipo.setResizable(false);
        TableColumn colCantidad = new TableColumn("Cantidad");
        colCantidad.setPrefWidth(120);
        colCantidad.setResizable(false);
        TableColumn colFecha = new TableColumn("Fecha");
        colFecha.setPrefWidth(150);
        colFecha.setResizable(false);
        
        colId.setCellValueFactory( new PropertyValueFactory<Ingreso,Integer>("id"));
        colId.setEditable(true);
        colTipo.setCellValueFactory( new PropertyValueFactory<Ingreso,String>("tipo"));
        colTipo.setEditable(true);
        colCantidad.setCellValueFactory( new PropertyValueFactory<Ingreso,Float>("cantidad"));
        colCantidad.setEditable(true);
        colFecha.setCellValueFactory( new PropertyValueFactory<Ingreso,String>("fecha"));
        colFecha.setEditable(true);
        
        this.tabla.setEditable(true);
        this.tabla.getColumns().clear();
        this.tabla.getColumns().addAll(colId, colTipo, colCantidad, colFecha);
        this.cargarTabla();
        this.tiposIngresos = FXCollections.observableArrayList();
        this.tipo.setItems(tiposIngresos);
        this.cargarTipo();
    }
    
    public void cargarTabla(){
        LinkedList<Ingreso> datos = ingreso.read();
        data = FXCollections.observableArrayList( datos );
        this.tabla.setItems(data);
    }
    
    public void cargarTipo(){
        this.tiposIngresos.clear();
        if( tipoIngreso == null ){ tipoIngreso = new TipoIngreso(); }
        tipoIngreso.read().forEach((aux) -> {
            tiposIngresos.add( "" + aux[0]);
        });
    }
    
    public void limpiar(){
        this.cantidad.setText("");
        this.fecha.getEditor().setText("");
        this.cargarTabla();
    }
    
    public void eliminarRegistro(int row){
        Object selectedItem = tabla.getSelectionModel().getSelectedItem();
        Ingreso aux = (Ingreso) selectedItem;
        aux.delete();
        this.cargarTabla();
    }
}
