
package App;

import MySQL.ConexionBD;
import MySQL.Crud;
import java.util.Optional;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author AppsMX
 */
public class ContableMX extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/MenuFXML.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("ContableMX versión BETA");
        stage.setResizable(false);
        stage.getIcons().add( new Image("/img/copyright.png") );
        stage.show();
        stage.setWidth(800);
        //stage.setHeight(800);
        root.requestFocus();
        stage.setOnCloseRequest( new EventHandler<WindowEvent>(){
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Crud.setCn( ConexionBD.conectarBase("root", "root") );
        launch(args);
    }
    
}
