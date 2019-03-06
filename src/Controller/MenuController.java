
package Controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


/**
 * FXML Controller class
 *
 * @author AppsMX
 */
public class MenuController implements Initializable {
     @FXML
    private JFXButton btnIngresos;
    @FXML
    private JFXButton btnGastos;
    @FXML
    private JFXButton btnAnalisis;

    @FXML
    void abrirAnalisis(MouseEvent event) {
        this.deploy("/View/AnalisisFXML.fxml", "Registro de Ingresos");
    }

    @FXML
    void abrirGastos(MouseEvent event) {
        this.deploy("/View/GastosFXML.fxml", "Registro de Gastos");
    }

    @FXML
    void abrirIngresos(MouseEvent event) {
        this.deploy("/View/VentasFXML.fxml", "Registro de Ingresos");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void deploy(String fxml, String title){
        Stage newStage = new Stage();
        Parent root_1 = null;
        try {
            root_1 = FXMLLoader.load(getClass().getResource(fxml));
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Stage stage = (Stage) this.btnAnalisis.getScene().getWindow();
        stage.hide();
        
        Scene scene_1 = new Scene(root_1);
        newStage.setScene(scene_1);
        newStage.setTitle(title);
        newStage.setResizable(false);
        newStage.setWidth(800);
        //newStage.setHeight(800);
        newStage.getIcons().add( new Image("/img/copyright.png") );
        newStage.show();
        root_1.requestFocus();
        newStage.setOnCloseRequest( new EventHandler<WindowEvent>(){
            @Override
            public void handle(WindowEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Shure?");
                alert.setHeaderText("Confirmación de salida");
                alert.setContentText("¿Seguro que quieres salir?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    stage.show();
                    stage.getScene().getRoot().requestFocus();
                }else{
                    event.consume();
                }
            }
            
        });
    }
    
}
