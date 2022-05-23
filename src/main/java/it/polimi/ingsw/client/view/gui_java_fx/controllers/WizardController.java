package it.polimi.ingsw.client.view.gui_java_fx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class WizardController {
    @FXML
    ImageView wizard1;
    @FXML
    ImageView wizard2;
    @FXML
    ImageView wizard3;
    @FXML
    ImageView wizard4;

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void chooseWizard(MouseEvent mouseEvent) throws IOException {
        if(mouseEvent.getSource().equals(wizard1)){
            System.out.println("MAGO1");
        }
        if(mouseEvent.getSource().equals(wizard2)){
            System.out.println("MAGO2");
        }
        if(mouseEvent.getSource().equals(wizard3)){
            System.out.println("MAGO3");
        }
        if(mouseEvent.getSource().equals(wizard4)){
            System.out.println("MAGO4");
        }
        FXMLLoader loader = new FXMLLoader(new File("src/main/java/it/polimi/ingsw/client/view/gui_java_fx/fxmlFiles/main.fxml").toURI().toURL());
        root = loader.load();
        root.setId("dashboard");
        stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);



        scene.getStylesheets().add(new File("src/main/java/it/polimi/ingsw/client/view/gui_java_fx/cssFiles/main.css").toURI().toURL().toExternalForm());
        stage.setTitle("Ip and Port");
        stage.setScene(scene);


        /*
        PaneShift controller = loader.getController();
        controller.setScene(scene);
        System.out.println("Scene: " + scene);

        //ExternalDashboardController controller = new ExternalDashboardController();
        controller.listen();
*/

        stage.show();

    }
}
