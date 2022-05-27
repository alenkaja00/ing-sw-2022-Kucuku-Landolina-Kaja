package it.polimi.ingsw.client.view.gui_java_fx.controllers;

import it.polimi.ingsw.server.model.components.Cloud;
import it.polimi.ingsw.server.model.components.ColoredDisc;
import it.polimi.ingsw.server.model.components.Dashboard;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashSet;

public class GameMapController {

    private ArrayList<javafx.scene.image.ImageView> Entrance1;
    private ArrayList<ImageView> Entrance2;
    private ArrayList<ImageView> Entrance3;

    private ArrayList<ImageView> Tables1;
    private ArrayList<ImageView> Tables2;
    private ArrayList<ImageView> Tables3;

    private ArrayList<ImageView> Professors1;
    private ArrayList<ImageView> Professors2;
    private ArrayList<ImageView> Professors3;


    private ArrayList<ImageView> Towers1;
    private ArrayList<ImageView> Towers2;
    private ArrayList<ImageView> Towers3;


    private ArrayList<GridPane> CloudTwoGrids;
    private ArrayList<GridPane> CloudThreeGrids;


    private int entranceClicked;
    private Boolean tablesClicked;

    private DashboardHandler handler;

    @FXML
    private ImageView dashboard1;

    @FXML
    private ImageView dashboard1Entrance0;
    @FXML
    private ImageView dashboard1Entrance1;
    @FXML
    private ImageView dashboard1Entrance2;
    @FXML
    private ImageView dashboard1Entrance3;
    @FXML
    private ImageView dashboard1Entrance4;
    @FXML
    private ImageView dashboard1Entrance5;
    @FXML
    private ImageView dashboard1Entrance6;
    @FXML
    private ImageView dashboard1Entrance7;
    @FXML
    private ImageView dashboard1Entrance8;

    @FXML
    private ImageView dashboard1Tables0;
    @FXML
    private ImageView dashboard1Tables1;
    @FXML
    private ImageView dashboard1Tables2;
    @FXML
    private ImageView dashboard1Tables3;
    @FXML
    private ImageView dashboard1Tables4;
    @FXML
    private ImageView dashboard1Tables5;
    @FXML
    private ImageView dashboard1Tables6;
    @FXML
    private ImageView dashboard1Tables7;
    @FXML
    private ImageView dashboard1Tables8;
    @FXML
    private ImageView dashboard1Tables9;
    @FXML
    private ImageView dashboard1Tables10;
    @FXML
    private ImageView dashboard1Tables11;
    @FXML
    private ImageView dashboard1Tables12;
    @FXML
    private ImageView dashboard1Tables13;
    @FXML
    private ImageView dashboard1Tables14;
    @FXML
    private ImageView dashboard1Tables15;
    @FXML
    private ImageView dashboard1Tables16;
    @FXML
    private ImageView dashboard1Tables17;
    @FXML
    private ImageView dashboard1Tables18;
    @FXML
    private ImageView dashboard1Tables19;
    @FXML
    private ImageView dashboard1Tables20;
    @FXML
    private ImageView dashboard1Tables21;
    @FXML
    private ImageView dashboard1Tables22;
    @FXML
    private ImageView dashboard1Tables23;
    @FXML
    private ImageView dashboard1Tables24;
    @FXML
    private ImageView dashboard1Tables25;
    @FXML
    private ImageView dashboard1Tables26;
    @FXML
    private ImageView dashboard1Tables27;
    @FXML
    private ImageView dashboard1Tables28;
    @FXML
    private ImageView dashboard1Tables29;
    @FXML
    private ImageView dashboard1Tables30;
    @FXML
    private ImageView dashboard1Tables31;
    @FXML
    private ImageView dashboard1Tables32;
    @FXML
    private ImageView dashboard1Tables33;
    @FXML
    private ImageView dashboard1Tables34;
    @FXML
    private ImageView dashboard1Tables35;
    @FXML
    private ImageView dashboard1Tables36;
    @FXML
    private ImageView dashboard1Tables37;
    @FXML
    private ImageView dashboard1Tables38;
    @FXML
    private ImageView dashboard1Tables39;
    @FXML
    private ImageView dashboard1Tables40;
    @FXML
    private ImageView dashboard1Tables41;
    @FXML
    private ImageView dashboard1Tables42;
    @FXML
    private ImageView dashboard1Tables43;
    @FXML
    private ImageView dashboard1Tables44;
    @FXML
    private ImageView dashboard1Tables45;
    @FXML
    private ImageView dashboard1Tables46;
    @FXML
    private ImageView dashboard1Tables47;
    @FXML
    private ImageView dashboard1Tables48;
    @FXML
    private ImageView dashboard1Tables49;

    @FXML
    private ImageView dashboard1Professors0;
    @FXML
    private ImageView dashboard1Professors1;
    @FXML
    private ImageView dashboard1Professors2;
    @FXML
    private ImageView dashboard1Professors3;
    @FXML
    private ImageView dashboard1Professors4;

    @FXML
    private ImageView dashboard1Towers0;
    @FXML
    private ImageView dashboard1Towers1;
    @FXML
    private ImageView dashboard1Towers2;
    @FXML
    private ImageView dashboard1Towers3;
    @FXML
    private ImageView dashboard1Towers4;
    @FXML
    private ImageView dashboard1Towers5;
    @FXML
    private ImageView dashboard1Towers6;
    @FXML
    private ImageView dashboard1Towers7;





    @FXML
    private ImageView dashboard2Entrance0;
    @FXML
    private ImageView dashboard2Entrance1;
    @FXML
    private ImageView dashboard2Entrance2;
    @FXML
    private ImageView dashboard2Entrance3;
    @FXML
    private ImageView dashboard2Entrance4;
    @FXML
    private ImageView dashboard2Entrance5;
    @FXML
    private ImageView dashboard2Entrance6;
    @FXML
    private ImageView dashboard2Entrance7;
    @FXML
    private ImageView dashboard2Entrance8;

    @FXML
    private ImageView dashboard2Tables0;
    @FXML
    private ImageView dashboard2Tables1;
    @FXML
    private ImageView dashboard2Tables2;
    @FXML
    private ImageView dashboard2Tables3;
    @FXML
    private ImageView dashboard2Tables4;
    @FXML
    private ImageView dashboard2Tables5;
    @FXML
    private ImageView dashboard2Tables6;
    @FXML
    private ImageView dashboard2Tables7;
    @FXML
    private ImageView dashboard2Tables8;
    @FXML
    private ImageView dashboard2Tables9;
    @FXML
    private ImageView dashboard2Tables10;
    @FXML
    private ImageView dashboard2Tables11;
    @FXML
    private ImageView dashboard2Tables12;
    @FXML
    private ImageView dashboard2Tables13;
    @FXML
    private ImageView dashboard2Tables14;
    @FXML
    private ImageView dashboard2Tables15;
    @FXML
    private ImageView dashboard2Tables16;
    @FXML
    private ImageView dashboard2Tables17;
    @FXML
    private ImageView dashboard2Tables18;
    @FXML
    private ImageView dashboard2Tables19;
    @FXML
    private ImageView dashboard2Tables20;
    @FXML
    private ImageView dashboard2Tables21;
    @FXML
    private ImageView dashboard2Tables22;
    @FXML
    private ImageView dashboard2Tables23;
    @FXML
    private ImageView dashboard2Tables24;
    @FXML
    private ImageView dashboard2Tables25;
    @FXML
    private ImageView dashboard2Tables26;
    @FXML
    private ImageView dashboard2Tables27;
    @FXML
    private ImageView dashboard2Tables28;
    @FXML
    private ImageView dashboard2Tables29;
    @FXML
    private ImageView dashboard2Tables30;
    @FXML
    private ImageView dashboard2Tables31;
    @FXML
    private ImageView dashboard2Tables32;
    @FXML
    private ImageView dashboard2Tables33;
    @FXML
    private ImageView dashboard2Tables34;
    @FXML
    private ImageView dashboard2Tables35;
    @FXML
    private ImageView dashboard2Tables36;
    @FXML
    private ImageView dashboard2Tables37;
    @FXML
    private ImageView dashboard2Tables38;
    @FXML
    private ImageView dashboard2Tables39;
    @FXML
    private ImageView dashboard2Tables40;
    @FXML
    private ImageView dashboard2Tables41;
    @FXML
    private ImageView dashboard2Tables42;
    @FXML
    private ImageView dashboard2Tables43;
    @FXML
    private ImageView dashboard2Tables44;
    @FXML
    private ImageView dashboard2Tables45;
    @FXML
    private ImageView dashboard2Tables46;
    @FXML
    private ImageView dashboard2Tables47;
    @FXML
    private ImageView dashboard2Tables48;
    @FXML
    private ImageView dashboard2Tables49;

    @FXML
    private ImageView dashboard2Professors0;
    @FXML
    private ImageView dashboard2Professors1;
    @FXML
    private ImageView dashboard2Professors2;
    @FXML
    private ImageView dashboard2Professors3;
    @FXML
    private ImageView dashboard2Professors4;

    @FXML
    private ImageView dashboard2Towers0;
    @FXML
    private ImageView dashboard2Towers1;
    @FXML
    private ImageView dashboard2Towers2;
    @FXML
    private ImageView dashboard2Towers3;
    @FXML
    private ImageView dashboard2Towers4;
    @FXML
    private ImageView dashboard2Towers5;
    @FXML
    private ImageView dashboard2Towers6;
    @FXML
    private ImageView dashboard2Towers7;






    @FXML
    private ImageView dashboard3Entrance0;
    @FXML
    private ImageView dashboard3Entrance1;
    @FXML
    private ImageView dashboard3Entrance2;
    @FXML
    private ImageView dashboard3Entrance3;
    @FXML
    private ImageView dashboard3Entrance4;
    @FXML
    private ImageView dashboard3Entrance5;
    @FXML
    private ImageView dashboard3Entrance6;
    @FXML
    private ImageView dashboard3Entrance7;
    @FXML
    private ImageView dashboard3Entrance8;

    @FXML
    private ImageView dashboard3Tables0;
    @FXML
    private ImageView dashboard3Tables1;
    @FXML
    private ImageView dashboard3Tables2;
    @FXML
    private ImageView dashboard3Tables3;
    @FXML
    private ImageView dashboard3Tables4;
    @FXML
    private ImageView dashboard3Tables5;
    @FXML
    private ImageView dashboard3Tables6;
    @FXML
    private ImageView dashboard3Tables7;
    @FXML
    private ImageView dashboard3Tables8;
    @FXML
    private ImageView dashboard3Tables9;
    @FXML
    private ImageView dashboard3Tables10;
    @FXML
    private ImageView dashboard3Tables11;
    @FXML
    private ImageView dashboard3Tables12;
    @FXML
    private ImageView dashboard3Tables13;
    @FXML
    private ImageView dashboard3Tables14;
    @FXML
    private ImageView dashboard3Tables15;
    @FXML
    private ImageView dashboard3Tables16;
    @FXML
    private ImageView dashboard3Tables17;
    @FXML
    private ImageView dashboard3Tables18;
    @FXML
    private ImageView dashboard3Tables19;
    @FXML
    private ImageView dashboard3Tables20;
    @FXML
    private ImageView dashboard3Tables21;
    @FXML
    private ImageView dashboard3Tables22;
    @FXML
    private ImageView dashboard3Tables23;
    @FXML
    private ImageView dashboard3Tables24;
    @FXML
    private ImageView dashboard3Tables25;
    @FXML
    private ImageView dashboard3Tables26;
    @FXML
    private ImageView dashboard3Tables27;
    @FXML
    private ImageView dashboard3Tables28;
    @FXML
    private ImageView dashboard3Tables29;
    @FXML
    private ImageView dashboard3Tables30;
    @FXML
    private ImageView dashboard3Tables31;
    @FXML
    private ImageView dashboard3Tables32;
    @FXML
    private ImageView dashboard3Tables33;
    @FXML
    private ImageView dashboard3Tables34;
    @FXML
    private ImageView dashboard3Tables35;
    @FXML
    private ImageView dashboard3Tables36;
    @FXML
    private ImageView dashboard3Tables37;
    @FXML
    private ImageView dashboard3Tables38;
    @FXML
    private ImageView dashboard3Tables39;
    @FXML
    private ImageView dashboard3Tables40;
    @FXML
    private ImageView dashboard3Tables41;
    @FXML
    private ImageView dashboard3Tables42;
    @FXML
    private ImageView dashboard3Tables43;
    @FXML
    private ImageView dashboard3Tables44;
    @FXML
    private ImageView dashboard3Tables45;
    @FXML
    private ImageView dashboard3Tables46;
    @FXML
    private ImageView dashboard3Tables47;
    @FXML
    private ImageView dashboard3Tables48;
    @FXML
    private ImageView dashboard3Tables49;

    @FXML
    private ImageView dashboard3Professors0;
    @FXML
    private ImageView dashboard3Professors1;
    @FXML
    private ImageView dashboard3Professors2;
    @FXML
    private ImageView dashboard3Professors3;
    @FXML
    private ImageView dashboard3Professors4;

    @FXML
    private ImageView dashboard3Towers0;
    @FXML
    private ImageView dashboard3Towers1;
    @FXML
    private ImageView dashboard3Towers2;
    @FXML
    private ImageView dashboard3Towers3;
    @FXML
    private ImageView dashboard3Towers4;
    @FXML
    private ImageView dashboard3Towers5;
    @FXML
    private ImageView dashboard3Towers6;
    @FXML
    private ImageView dashboard3Towers7;



    //two players clouds
    @FXML
    private ImageView cloud21;
    @FXML
    private ImageView cloud22;
    @FXML
    private ImageView cloud31;
    @FXML
    private ImageView cloud32;
    @FXML
    private ImageView cloud33;
    @FXML
    private GridPane cloudGrid21;
    @FXML
    private GridPane cloudGrid22;
    @FXML
    private GridPane cloudGrid31;
    @FXML
    private GridPane cloudGrid32;
    @FXML
    private GridPane cloudGrid33;
    @FXML
    private ImageView student11;
    @FXML
    private ImageView student12;
    @FXML
    private ImageView student13;
    @FXML
    private ImageView student21;
    @FXML
    private ImageView student22;
    @FXML
    private ImageView student23;
    @FXML
    private ImageView studentThree11;
    @FXML
    private ImageView studentThree12;
    @FXML
    private ImageView studentThree13;
    @FXML
    private ImageView studentThree14;
    @FXML
    private ImageView studentThree21;
    @FXML
    private ImageView studentThree22;
    @FXML
    private ImageView studentThree23;
    @FXML
    private ImageView studentThree24;
    @FXML
    private ImageView studentThree31;
    @FXML
    private ImageView studentThree32;
    @FXML
    private ImageView studentThree33;
    @FXML
    private ImageView studentThree34;


    @FXML
    public void initialize() {

        //Entrance4.setImage(yellowImage);
        Entrance1 = new ArrayList<>();
        Entrance1.add(dashboard1Entrance0);
        Entrance1.add(dashboard1Entrance1);
        Entrance1.add(dashboard1Entrance2);
        Entrance1.add(dashboard1Entrance3);
        Entrance1.add(dashboard1Entrance4);
        Entrance1.add(dashboard1Entrance5);
        Entrance1.add(dashboard1Entrance6);
        Entrance1.add(dashboard1Entrance7);
        Entrance1.add(dashboard1Entrance8);

        Tables1 = new ArrayList<>();

        Tables1.add(dashboard1Tables0);
        Tables1.add(dashboard1Tables1);
        Tables1.add(dashboard1Tables2);
        Tables1.add(dashboard1Tables3);
        Tables1.add(dashboard1Tables4);
        Tables1.add(dashboard1Tables5);
        Tables1.add(dashboard1Tables6);
        Tables1.add(dashboard1Tables7);
        Tables1.add(dashboard1Tables8);
        Tables1.add(dashboard1Tables9);
        Tables1.add(dashboard1Tables10);
        Tables1.add(dashboard1Tables11);
        Tables1.add(dashboard1Tables12);
        Tables1.add(dashboard1Tables13);
        Tables1.add(dashboard1Tables14);
        Tables1.add(dashboard1Tables15);
        Tables1.add(dashboard1Tables16);
        Tables1.add(dashboard1Tables17);
        Tables1.add(dashboard1Tables18);
        Tables1.add(dashboard1Tables19);
        Tables1.add(dashboard1Tables20);
        Tables1.add(dashboard1Tables21);
        Tables1.add(dashboard1Tables22);
        Tables1.add(dashboard1Tables23);
        Tables1.add(dashboard1Tables24);
        Tables1.add(dashboard1Tables25);
        Tables1.add(dashboard1Tables26);
        Tables1.add(dashboard1Tables27);
        Tables1.add(dashboard1Tables28);
        Tables1.add(dashboard1Tables29);
        Tables1.add(dashboard1Tables30);
        Tables1.add(dashboard1Tables31);
        Tables1.add(dashboard1Tables32);
        Tables1.add(dashboard1Tables33);
        Tables1.add(dashboard1Tables34);
        Tables1.add(dashboard1Tables35);
        Tables1.add(dashboard1Tables36);
        Tables1.add(dashboard1Tables37);
        Tables1.add(dashboard1Tables38);
        Tables1.add(dashboard1Tables39);
        Tables1.add(dashboard1Tables40);
        Tables1.add(dashboard1Tables41);
        Tables1.add(dashboard1Tables42);
        Tables1.add(dashboard1Tables43);
        Tables1.add(dashboard1Tables44);
        Tables1.add(dashboard1Tables45);
        Tables1.add(dashboard1Tables46);
        Tables1.add(dashboard1Tables47);
        Tables1.add(dashboard1Tables48);
        Tables1.add(dashboard1Tables49);

        Professors1 = new ArrayList<>();

        Professors1.add(dashboard1Professors0);
        Professors1.add(dashboard1Professors1);
        Professors1.add(dashboard1Professors2);
        Professors1.add(dashboard1Professors3);
        Professors1.add(dashboard1Professors4);

        Towers1 = new ArrayList<>();

        Towers1.add(dashboard1Towers0);
        Towers1.add(dashboard1Towers1);
        Towers1.add(dashboard1Towers2);
        Towers1.add(dashboard1Towers3);
        Towers1.add(dashboard1Towers4);
        Towers1.add(dashboard1Towers5);
        Towers1.add(dashboard1Towers6);
        Towers1.add(dashboard1Towers7);


        //dashboard2-----------------------------
        Entrance2 = new ArrayList<>();
        Entrance2.add(dashboard2Entrance0);
        Entrance2.add(dashboard2Entrance1);
        Entrance2.add(dashboard2Entrance2);
        Entrance2.add(dashboard2Entrance3);
        Entrance2.add(dashboard2Entrance4);
        Entrance2.add(dashboard2Entrance5);
        Entrance2.add(dashboard2Entrance6);
        Entrance2.add(dashboard2Entrance7);
        Entrance2.add(dashboard2Entrance8);

        Tables2 = new ArrayList<>();

        Tables2.add(dashboard2Tables0);
        Tables2.add(dashboard2Tables1);
        Tables2.add(dashboard2Tables2);
        Tables2.add(dashboard2Tables3);
        Tables2.add(dashboard2Tables4);
        Tables2.add(dashboard2Tables5);
        Tables2.add(dashboard2Tables6);
        Tables2.add(dashboard2Tables7);
        Tables2.add(dashboard2Tables8);
        Tables2.add(dashboard2Tables9);
        Tables2.add(dashboard2Tables10);
        Tables2.add(dashboard2Tables11);
        Tables2.add(dashboard2Tables12);
        Tables2.add(dashboard2Tables13);
        Tables2.add(dashboard2Tables14);
        Tables2.add(dashboard2Tables15);
        Tables2.add(dashboard2Tables16);
        Tables2.add(dashboard2Tables17);
        Tables2.add(dashboard2Tables18);
        Tables2.add(dashboard2Tables19);
        Tables2.add(dashboard2Tables20);
        Tables2.add(dashboard2Tables21);
        Tables2.add(dashboard2Tables22);
        Tables2.add(dashboard2Tables23);
        Tables2.add(dashboard2Tables24);
        Tables2.add(dashboard2Tables25);
        Tables2.add(dashboard2Tables26);
        Tables2.add(dashboard2Tables27);
        Tables2.add(dashboard2Tables28);
        Tables2.add(dashboard2Tables29);
        Tables2.add(dashboard2Tables30);
        Tables2.add(dashboard2Tables31);
        Tables2.add(dashboard2Tables32);
        Tables2.add(dashboard2Tables33);
        Tables2.add(dashboard2Tables34);
        Tables2.add(dashboard2Tables35);
        Tables2.add(dashboard2Tables36);
        Tables2.add(dashboard2Tables37);
        Tables2.add(dashboard2Tables38);
        Tables2.add(dashboard2Tables39);
        Tables2.add(dashboard2Tables40);
        Tables2.add(dashboard2Tables41);
        Tables2.add(dashboard2Tables42);
        Tables2.add(dashboard2Tables43);
        Tables2.add(dashboard2Tables44);
        Tables2.add(dashboard2Tables45);
        Tables2.add(dashboard2Tables46);
        Tables2.add(dashboard2Tables47);
        Tables2.add(dashboard2Tables48);
        Tables2.add(dashboard2Tables49);


        Professors2 = new ArrayList<>();

        Professors2.add(dashboard2Professors0);
        Professors2.add(dashboard2Professors1);
        Professors2.add(dashboard2Professors2);
        Professors2.add(dashboard2Professors3);
        Professors2.add(dashboard2Professors4);

        Towers2 = new ArrayList<>();

        Towers2.add(dashboard2Towers0);
        Towers2.add(dashboard2Towers1);
        Towers2.add(dashboard2Towers2);
        Towers2.add(dashboard2Towers3);
        Towers2.add(dashboard2Towers4);
        Towers2.add(dashboard2Towers5);
        Towers2.add(dashboard2Towers6);
        Towers2.add(dashboard2Towers7);


        //dashboard3 ------------------
        Entrance3 = new ArrayList<>();
        Entrance3.add(dashboard3Entrance0);
        Entrance3.add(dashboard3Entrance1);
        Entrance3.add(dashboard3Entrance2);
        Entrance3.add(dashboard3Entrance3);
        Entrance3.add(dashboard3Entrance4);
        Entrance3.add(dashboard3Entrance5);
        Entrance3.add(dashboard3Entrance6);
        Entrance3.add(dashboard3Entrance7);
        Entrance3.add(dashboard3Entrance8);

        Tables3 = new ArrayList<>();

        Tables3.add(dashboard3Tables0);
        Tables3.add(dashboard3Tables1);
        Tables3.add(dashboard3Tables2);
        Tables3.add(dashboard3Tables3);
        Tables3.add(dashboard3Tables4);
        Tables3.add(dashboard3Tables5);
        Tables3.add(dashboard3Tables6);
        Tables3.add(dashboard3Tables7);
        Tables3.add(dashboard3Tables8);
        Tables3.add(dashboard3Tables9);
        Tables3.add(dashboard3Tables10);
        Tables3.add(dashboard3Tables11);
        Tables3.add(dashboard3Tables12);
        Tables3.add(dashboard3Tables13);
        Tables3.add(dashboard3Tables14);
        Tables3.add(dashboard3Tables15);
        Tables3.add(dashboard3Tables16);
        Tables3.add(dashboard3Tables17);
        Tables3.add(dashboard3Tables18);
        Tables3.add(dashboard3Tables19);
        Tables3.add(dashboard3Tables20);
        Tables3.add(dashboard3Tables21);
        Tables3.add(dashboard3Tables22);
        Tables3.add(dashboard3Tables23);
        Tables3.add(dashboard3Tables24);
        Tables3.add(dashboard3Tables25);
        Tables3.add(dashboard3Tables26);
        Tables3.add(dashboard3Tables27);
        Tables3.add(dashboard3Tables28);
        Tables3.add(dashboard3Tables29);
        Tables3.add(dashboard3Tables30);
        Tables3.add(dashboard3Tables31);
        Tables3.add(dashboard3Tables32);
        Tables3.add(dashboard3Tables33);
        Tables3.add(dashboard3Tables34);
        Tables3.add(dashboard3Tables35);
        Tables3.add(dashboard3Tables36);
        Tables3.add(dashboard3Tables37);
        Tables3.add(dashboard3Tables38);
        Tables3.add(dashboard3Tables39);
        Tables3.add(dashboard3Tables40);
        Tables3.add(dashboard3Tables41);
        Tables3.add(dashboard3Tables42);
        Tables3.add(dashboard3Tables43);
        Tables3.add(dashboard3Tables44);
        Tables3.add(dashboard3Tables45);
        Tables3.add(dashboard3Tables46);
        Tables3.add(dashboard3Tables47);
        Tables3.add(dashboard3Tables48);
        Tables3.add(dashboard3Tables49);


        Professors3 = new ArrayList<>();

        Professors3.add(dashboard3Professors0);
        Professors3.add(dashboard3Professors1);
        Professors3.add(dashboard3Professors2);
        Professors3.add(dashboard3Professors3);
        Professors3.add(dashboard3Professors4);

        Towers3 = new ArrayList<>();
        Towers3.add(dashboard3Towers0);
        Towers3.add(dashboard3Towers1);
        Towers3.add(dashboard3Towers2);
        Towers3.add(dashboard3Towers3);
        Towers3.add(dashboard3Towers4);
        Towers3.add(dashboard3Towers5);
        Towers3.add(dashboard3Towers6);
        Towers3.add(dashboard3Towers7);


        CloudTwoGrids = new ArrayList<>();

        CloudTwoGrids.add(cloudGrid21);
        CloudTwoGrids.add(cloudGrid22);



        CloudThreeGrids = new ArrayList<>();
        CloudThreeGrids.add(cloudGrid33);
        CloudThreeGrids.add(cloudGrid32);
        CloudThreeGrids.add(cloudGrid31);



        entranceClicked = -1;
        tablesClicked = false;


        Dashboard dashboard = new Dashboard(3);
        dashboard.addToTables(ColoredDisc.RED);
        dashboard.addToTables(ColoredDisc.BLUE);
        dashboard.addToTables(ColoredDisc.BLUE);
        dashboard.AddToEntrance(ColoredDisc.GREEN,0);
        dashboard.AddToEntrance(ColoredDisc.YELLOW,1);
        HashSet<ColoredDisc> set = new HashSet<>();
        set.add(ColoredDisc.BLUE);
        dashboard.professorSpots = set;

        handler = new DashboardHandler();
        handler.updateDashboard(dashboard, Entrance1, Tables1, Professors1);
        handler.updateDashboard(dashboard, Entrance2, Tables2, Professors2);
        handler.updateDashboard(dashboard, Entrance3, Tables3, Professors3);


    }


    public void clickAttempt(MouseEvent mouseEvent)
    {
        for(int i=0;i<Entrance1.size();i++)
        {
            if(Entrance1.get(i).equals(mouseEvent.getSource()))
            {
                entranceClicked = i;
                tablesClicked = false;
            }
        }
    }

    public void MoveToDashboard(MouseEvent mouseEvent)
    {
        if(entranceClicked == -1) return;

        if(mouseEvent.getX() < dashboard1.getFitWidth() * 0.16 || mouseEvent.getX() > dashboard1.getFitWidth() * 0.7 ) return;


        System.out.println("ETT "+entranceClicked);
        entranceClicked = -1;
        tablesClicked = true;

    }

    public void selectCloud(MouseEvent mouseEvent) {
        int index = -1;
        String version = "";

        for(int i=0;i<CloudTwoGrids.size();i++) {
            if (mouseEvent.getSource().equals(CloudTwoGrids.get(i))) {
                index = i;
                version = "TwoPlayers";
            }
        }
        for(int i=0;i<CloudThreeGrids.size();i++) {
            if (mouseEvent.getSource().equals(CloudThreeGrids.get(i))) {
                index = i;
                version = "ThreePlayers";
            }
        }

        if( index != -1)
            System.out.println("CTE "+ index+ " "+ version);


    }
}