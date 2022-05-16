package it.polimi.ingsw.client.view.gui_java_fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class mainStage extends Application {



    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(final Stage primaryStage) throws IOException {
        URL url = new File("src/main/java/it/polimi/ingsw/client/view/gui_java_fx/main.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);


        scene.getStylesheets().add(new File("src/main/java/it/polimi/ingsw/client/view/cssfiles/main.css").toURI().toURL().toExternalForm());

        primaryStage.setTitle("Eriantys");

        root.setId("pane");
        primaryStage.setScene(scene);

        //get the system screen dimensions


        primaryStage.setResizable(true);

        primaryStage.show();


    }
}
