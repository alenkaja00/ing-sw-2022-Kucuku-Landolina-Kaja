package it.polimi.ingsw.client.view.gui_java_fx.controllers;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class PaneShift {

    @FXML
    private Stage stage;
    @FXML
    private Parent root;

    @FXML
    private Scene scene;
    @FXML
    private StackPane stack;
    @FXML
    private AnchorPane firstPane;
    @FXML
    private AnchorPane secondPane;

    @FXML
    private Button showButton;

    @FXML
    private HBox hboxpane;






    @FXML
    private Button resizeB;
    @FXML
    private StackPane baseStack;
    @FXML
    private StackPane containerStack;
    @FXML
    private GridPane grid;
    @FXML
    private ImageView image;
    @FXML
    private GridPane gridim;



    int firstP = 1;

    double x = 0;
    double y = 0;
    double initialWidht = 1280;
    double initialHeight = 720;


    public void setScene(Scene scene){
        this.scene = scene;
    }

    public Scene getScene(){
        return this.scene;
    }


    @FXML
    public void initialize(){
        System.out.println("Constructor");
        System.out.println(getScene());         //it is null at first

/*
        x = secondPane.getTranslateX();
        System.out.println("X: "+x);

        y = secondPane.getTranslateY();
        System.out.println("Y: "+x);
*/
    }

    public void listen(){

        scene = this.getScene();


        scene.widthProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                System.out.println("Width: " + newSceneWidth);

                Scale scale = new Scale();

                scale.setX(newSceneWidth.doubleValue()/oldSceneWidth.doubleValue());
                scale.setPivotX(0);

                containerStack.getTransforms().addAll(scale);

            }
        });
        scene.heightProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                System.out.println("Height: " + newSceneHeight);

                Scale scale = new Scale();

                scale.setY(newSceneHeight.doubleValue()/oldSceneHeight.doubleValue());
                scale.setPivotY(0);

                containerStack.getTransforms().addAll(scale);
            }
        });
    }


    public void showsecond(ActionEvent actionEvent) {
        if( firstP == 1 ){
            System.out.println("Andiamo al secondo pannello");

            secondPane.setTranslateX(1150);

            firstPane.setDisable(false);
            firstPane.setVisible(true);

            secondPane.setDisable(false);
            secondPane.setVisible(true);
            firstP = 0;

        }
        else if(firstP == 0){
            secondPane.setTranslateX(x);
            System.out.println("Andiamo al primo pannello");

            firstPane.setDisable(true);
            firstPane.setVisible(false);
            firstP = 1;

        }

    }

    public void changePane(ActionEvent actionEvent) {


        secondPane.setTranslateX(x);
        System.out.println("Andiamo al primo pannello");

        firstPane.setDisable(true);
        firstPane.setVisible(false);
        firstP = 1;
    }

    public void resize1(ActionEvent actionEvent) {
        //baseStack.resize(300, 300);
        System.out.println("qua");

        /*
        scale.setX(0.7);
        scale.setY(0.7);

        scale.setPivotX(pivotx);
        scale.setPivotY(pivoty);

        containerStack.getTransforms().addAll(scale);
*/
    }
}


