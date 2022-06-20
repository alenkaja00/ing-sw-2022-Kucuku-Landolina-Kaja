package it.polimi.ingsw.client.view.gui.controllers;

import com.google.gson.Gson;
import it.polimi.ingsw.client.controller.ClientController;
import it.polimi.ingsw.client.view.jsonObjects.jGameClassExpert;
import it.polimi.ingsw.client.view.jsonObjects.jPlayer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameMapController
{
    ///clouds
    @FXML
    private StackPane cloudPane21, cloudPane22, cloudPane31, cloudPane32, cloudPane33;
    ///two players clouds
    @FXML
    private GridPane cloudGrid21, cloudGrid22;
    @FXML
    private ImageView student11, student12, student13;
    @FXML
    private ImageView student21, student22, student23;
    ///three players clouds
    @FXML
    private GridPane cloudGrid31, cloudGrid32, cloudGrid33;
    @FXML
    private ImageView studentThree11, studentThree12, studentThree13, studentThree14;
    @FXML
    private ImageView studentThree21, studentThree22, studentThree23, studentThree24;
    @FXML
    private ImageView studentThree31, studentThree32, studentThree33, studentThree34;


    ///expert mode effects
    @FXML
    private GridPane cardsGrid;
    @FXML
    private ImageView effectOne, effectTwo, effectThree;


    ///island tilepanes
    @FXML
    private TilePane tilePane1, tilePane2, tilePane3, tilePane4, tilePane5, tilePane6, tilePane7, tilePane8, tilePane9, tilePane10, tilePane11, tilePane12;


    ///third player panes
    @FXML
    private StackPane stack3;
    @FXML
    private GridPane namePanel1, namePanel2, namePanel3;

    ///Online//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private ImageView online1, online2, online3;

    ///nicknames//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private Label nameLabel1, nameLabel2, nameLabel3;

    ///CoinsAmount//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private Label coinsLabel1, coinsLabel2, coinsLabel3;

    ///CoinsImages//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private ImageView coin1, coin2, coin3;

    ///HelperCards//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private ImageView helper1, helper2, helper3;



    ///dashboard1//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private ImageView dashboard1Entrance0, dashboard1Entrance1, dashboard1Entrance2, dashboard1Entrance3,
            dashboard1Entrance4, dashboard1Entrance5, dashboard1Entrance6, dashboard1Entrance7, dashboard1Entrance8;
    @FXML
    private ImageView dashboard1Tables0, dashboard1Tables1, dashboard1Tables2, dashboard1Tables3, dashboard1Tables4,
            dashboard1Tables5, dashboard1Tables6, dashboard1Tables7, dashboard1Tables8, dashboard1Tables9, dashboard1Tables10,
            dashboard1Tables11, dashboard1Tables12, dashboard1Tables13, dashboard1Tables14, dashboard1Tables15, dashboard1Tables16,
            dashboard1Tables17, dashboard1Tables18, dashboard1Tables19, dashboard1Tables20, dashboard1Tables21, dashboard1Tables22,
            dashboard1Tables23, dashboard1Tables24, dashboard1Tables25, dashboard1Tables26, dashboard1Tables27, dashboard1Tables28,
            dashboard1Tables29, dashboard1Tables30, dashboard1Tables31, dashboard1Tables32, dashboard1Tables33, dashboard1Tables34,
            dashboard1Tables35, dashboard1Tables36, dashboard1Tables37, dashboard1Tables38, dashboard1Tables39, dashboard1Tables40,
            dashboard1Tables41, dashboard1Tables42, dashboard1Tables43, dashboard1Tables44, dashboard1Tables45, dashboard1Tables46,
            dashboard1Tables47, dashboard1Tables48, dashboard1Tables49;
    @FXML
    private ImageView dashboard1Professors0, dashboard1Professors1, dashboard1Professors2, dashboard1Professors3, dashboard1Professors4;
    @FXML
    private ImageView dashboard1Towers0, dashboard1Towers1, dashboard1Towers2, dashboard1Towers3,
            dashboard1Towers4, dashboard1Towers5,dashboard1Towers6, dashboard1Towers7;

    ///dashboard2//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private ImageView dashboard2Entrance0, dashboard2Entrance1, dashboard2Entrance2, dashboard2Entrance3,
            dashboard2Entrance4, dashboard2Entrance5, dashboard2Entrance6, dashboard2Entrance7, dashboard2Entrance8;
    @FXML
    private ImageView dashboard2Tables0, dashboard2Tables1, dashboard2Tables2, dashboard2Tables3, dashboard2Tables4,
            dashboard2Tables5, dashboard2Tables6, dashboard2Tables7, dashboard2Tables8, dashboard2Tables9, dashboard2Tables10,
            dashboard2Tables11, dashboard2Tables12, dashboard2Tables13, dashboard2Tables14, dashboard2Tables15, dashboard2Tables16,
            dashboard2Tables17, dashboard2Tables18, dashboard2Tables19, dashboard2Tables20, dashboard2Tables21, dashboard2Tables22,
            dashboard2Tables23, dashboard2Tables24, dashboard2Tables25, dashboard2Tables26, dashboard2Tables27, dashboard2Tables28,
            dashboard2Tables29, dashboard2Tables30, dashboard2Tables31, dashboard2Tables32, dashboard2Tables33, dashboard2Tables34,
            dashboard2Tables35, dashboard2Tables36, dashboard2Tables37, dashboard2Tables38, dashboard2Tables39, dashboard2Tables40,
            dashboard2Tables41, dashboard2Tables42, dashboard2Tables43, dashboard2Tables44, dashboard2Tables45, dashboard2Tables46,
            dashboard2Tables47, dashboard2Tables48, dashboard2Tables49;
    @FXML
    private ImageView dashboard2Professors0, dashboard2Professors1, dashboard2Professors2, dashboard2Professors3, dashboard2Professors4;
    @FXML
    private ImageView dashboard2Towers0, dashboard2Towers1, dashboard2Towers2, dashboard2Towers3,
            dashboard2Towers4, dashboard2Towers5,dashboard2Towers6, dashboard2Towers7;

    ///dashboard3//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private ImageView dashboard3Entrance0, dashboard3Entrance1, dashboard3Entrance2, dashboard3Entrance3,
            dashboard3Entrance4, dashboard3Entrance5, dashboard3Entrance6, dashboard3Entrance7, dashboard3Entrance8;
    @FXML
    private ImageView dashboard3Tables0, dashboard3Tables1, dashboard3Tables2, dashboard3Tables3, dashboard3Tables4,
            dashboard3Tables5, dashboard3Tables6, dashboard3Tables7, dashboard3Tables8, dashboard3Tables9, dashboard3Tables10,
            dashboard3Tables11, dashboard3Tables12, dashboard3Tables13, dashboard3Tables14, dashboard3Tables15, dashboard3Tables16,
            dashboard3Tables17, dashboard3Tables18, dashboard3Tables19, dashboard3Tables20, dashboard3Tables21, dashboard3Tables22,
            dashboard3Tables23, dashboard3Tables24, dashboard3Tables25, dashboard3Tables26, dashboard3Tables27, dashboard3Tables28,
            dashboard3Tables29, dashboard3Tables30, dashboard3Tables31, dashboard3Tables32, dashboard3Tables33, dashboard3Tables34,
            dashboard3Tables35, dashboard3Tables36, dashboard3Tables37, dashboard3Tables38, dashboard3Tables39, dashboard3Tables40,
            dashboard3Tables41, dashboard3Tables42, dashboard3Tables43, dashboard3Tables44, dashboard3Tables45, dashboard3Tables46,
            dashboard3Tables47, dashboard3Tables48, dashboard3Tables49;
    @FXML
    private ImageView dashboard3Professors0, dashboard3Professors1, dashboard3Professors2, dashboard3Professors3, dashboard3Professors4;
    @FXML
    private ImageView dashboard3Towers0, dashboard3Towers1, dashboard3Towers2, dashboard3Towers3,
            dashboard3Towers4, dashboard3Towers5,dashboard3Towers6, dashboard3Towers7;


    ///Arraylists
    private ArrayList<ImageView> Entrance1, Entrance2, Entrance3;
    private ArrayList<ImageView> Tables1, Tables2, Tables3;
    private ArrayList<ImageView> Professors1, Professors2, Professors3;
    private ArrayList<ImageView> Towers1, Towers2 ,Towers3;
    private ArrayList<GridPane> CloudTwoGrids, CloudThreeGrids;
    private ArrayList<TilePane> Tilepanes;
    private ArrayList<ImageView> Online;
    private ArrayList<Label> Nicknames;
    private ArrayList<Label> CoinsAmount;
    private ArrayList<ImageView> CoinsImages;
    private ArrayList<GridPane> NamePanels;
    private ArrayList<ImageView> HelperCards;
    ///
    private ArrayList<ImageView> Students11, Students12;
    private ArrayList<ImageView> StudentsThree1, StudentsThree2, StudentsThree3;
    ///
    private ArrayList<ArrayList<ImageView>> AllCloudStudentsTwo;
    private ArrayList<ArrayList<ImageView>> AllCloudStudentsThree;
    ///
    private ArrayList<ArrayList<ImageView>> Entrances;
    private ArrayList<ArrayList<ImageView>> AllTables;
    private ArrayList<ArrayList<ImageView>> AllProfessors;
    private ArrayList<ArrayList<ImageView>> AllTowers;


    ///logic variables
    private Gson gson = new Gson();
    private jGameClassExpert gameData;
    private ClientController clientController;


    private Boolean entranceClickable = false;
    private int clickedEntrance;

    private Boolean islandClickable = false;
    private int clickedIsland;

    private Boolean cloudClickable = false;
    private int clickedCloud;

    private Boolean tablesClickable = false;
    private Boolean tablesClicked;

    private int chosenCard;

    private Boolean expertMode = false;
    private Boolean effectPlayed = false;


    @FXML
    public void initialize() throws IOException {
        ///dashboard1//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Entrance1 = new ArrayList<>(Arrays.asList(dashboard1Entrance0, dashboard1Entrance1,dashboard1Entrance2,dashboard1Entrance3,dashboard1Entrance4,
                dashboard1Entrance5,dashboard1Entrance6,dashboard1Entrance7,dashboard1Entrance8));
        Tables1 = new ArrayList<>(Arrays.asList(dashboard1Tables0, dashboard1Tables1, dashboard1Tables2, dashboard1Tables3, dashboard1Tables4,
                dashboard1Tables5, dashboard1Tables6, dashboard1Tables7, dashboard1Tables8, dashboard1Tables9, dashboard1Tables10,
                dashboard1Tables11, dashboard1Tables12, dashboard1Tables13, dashboard1Tables14, dashboard1Tables15, dashboard1Tables16,
                dashboard1Tables17, dashboard1Tables18, dashboard1Tables19, dashboard1Tables20, dashboard1Tables21, dashboard1Tables22,
                dashboard1Tables23, dashboard1Tables24, dashboard1Tables25, dashboard1Tables26, dashboard1Tables27, dashboard1Tables28,
                dashboard1Tables29, dashboard1Tables30, dashboard1Tables31, dashboard1Tables32, dashboard1Tables33, dashboard1Tables34,
                dashboard1Tables35, dashboard1Tables36, dashboard1Tables37, dashboard1Tables38, dashboard1Tables39, dashboard1Tables40,
                dashboard1Tables41, dashboard1Tables42, dashboard1Tables43, dashboard1Tables44, dashboard1Tables45, dashboard1Tables46,
                dashboard1Tables47, dashboard1Tables48, dashboard1Tables49));
        Professors1 = new ArrayList<>(Arrays.asList(dashboard1Professors0, dashboard1Professors1, dashboard1Professors2, dashboard1Professors3, dashboard1Professors4));
        Towers1 = new ArrayList<>(Arrays.asList(dashboard1Towers0, dashboard1Towers1, dashboard1Towers2, dashboard1Towers3,
                dashboard1Towers4, dashboard1Towers5,dashboard1Towers6, dashboard1Towers7));

        ///dashboard2//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Entrance2 = new ArrayList<>(Arrays.asList(dashboard2Entrance0, dashboard2Entrance1,dashboard2Entrance2,dashboard2Entrance3,dashboard2Entrance4,
                dashboard2Entrance5,dashboard2Entrance6,dashboard2Entrance7,dashboard2Entrance8));
        Tables2 = new ArrayList<>(Arrays.asList(dashboard2Tables0, dashboard2Tables1, dashboard2Tables2, dashboard2Tables3, dashboard2Tables4,
                dashboard2Tables5, dashboard2Tables6, dashboard2Tables7, dashboard2Tables8, dashboard2Tables9, dashboard2Tables10,
                dashboard2Tables11, dashboard2Tables12, dashboard2Tables13, dashboard2Tables14, dashboard2Tables15, dashboard2Tables16,
                dashboard2Tables17, dashboard2Tables18, dashboard2Tables19, dashboard2Tables20, dashboard2Tables21, dashboard2Tables22,
                dashboard2Tables23, dashboard2Tables24, dashboard2Tables25, dashboard2Tables26, dashboard2Tables27, dashboard2Tables28,
                dashboard2Tables29, dashboard2Tables30, dashboard2Tables31, dashboard2Tables32, dashboard2Tables33, dashboard2Tables34,
                dashboard2Tables35, dashboard2Tables36, dashboard2Tables37, dashboard2Tables38, dashboard2Tables39, dashboard2Tables40,
                dashboard2Tables41, dashboard2Tables42, dashboard2Tables43, dashboard2Tables44, dashboard2Tables45, dashboard2Tables46,
                dashboard2Tables47, dashboard2Tables48, dashboard2Tables49));
        Professors2 = new ArrayList<>(Arrays.asList(dashboard2Professors0, dashboard2Professors1, dashboard2Professors2, dashboard2Professors3, dashboard2Professors4));
        Towers2 = new ArrayList<>(Arrays.asList(dashboard2Towers0, dashboard2Towers1, dashboard2Towers2, dashboard2Towers3,
                dashboard2Towers4, dashboard2Towers5,dashboard2Towers6, dashboard2Towers7));

        ///dashboard3//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Entrance3 = new ArrayList<>(Arrays.asList(dashboard3Entrance0, dashboard3Entrance1,dashboard3Entrance2,dashboard3Entrance3,dashboard3Entrance4,
                dashboard3Entrance5,dashboard3Entrance6,dashboard3Entrance7,dashboard3Entrance8));
        Tables3 = new ArrayList<>(Arrays.asList(dashboard3Tables0, dashboard3Tables1, dashboard3Tables2, dashboard3Tables3, dashboard3Tables4,
                dashboard3Tables5, dashboard3Tables6, dashboard3Tables7, dashboard3Tables8, dashboard3Tables9, dashboard3Tables10,
                dashboard3Tables11, dashboard3Tables12, dashboard3Tables13, dashboard3Tables14, dashboard3Tables15, dashboard3Tables16,
                dashboard3Tables17, dashboard3Tables18, dashboard3Tables19, dashboard3Tables20, dashboard3Tables21, dashboard3Tables22,
                dashboard3Tables23, dashboard3Tables24, dashboard3Tables25, dashboard3Tables26, dashboard3Tables27, dashboard3Tables28,
                dashboard3Tables29, dashboard3Tables30, dashboard3Tables31, dashboard3Tables32, dashboard3Tables33, dashboard3Tables34,
                dashboard3Tables35, dashboard3Tables36, dashboard3Tables37, dashboard3Tables38, dashboard3Tables39, dashboard3Tables40,
                dashboard3Tables41, dashboard3Tables42, dashboard3Tables43, dashboard3Tables44, dashboard3Tables45, dashboard3Tables46,
                dashboard3Tables47, dashboard3Tables48, dashboard3Tables49));
        Professors3 = new ArrayList<>(Arrays.asList(dashboard3Professors0, dashboard3Professors1, dashboard3Professors2, dashboard3Professors3, dashboard3Professors4));
        Towers3 = new ArrayList<>(Arrays.asList(dashboard3Towers0, dashboard3Towers1, dashboard3Towers2, dashboard3Towers3,
                dashboard3Towers4, dashboard3Towers5,dashboard3Towers6, dashboard3Towers7));



        CloudTwoGrids = new ArrayList<>();
        CloudTwoGrids.add(cloudGrid21);
        CloudTwoGrids.add(cloudGrid22);
        Students11 = new ArrayList<>();
        Students11.add(student11);
        Students11.add(student12);
        Students11.add(student13);
        Students12 = new ArrayList<>();
        Students12.add(student21);
        Students12.add(student22);
        Students12.add(student23);

        AllCloudStudentsTwo = new ArrayList<>();
        AllCloudStudentsTwo.add(Students11);
        AllCloudStudentsTwo.add(Students12);


        CloudThreeGrids = new ArrayList<>();
        CloudThreeGrids.add(cloudGrid33);
        CloudThreeGrids.add(cloudGrid32);
        CloudThreeGrids.add(cloudGrid31);
        StudentsThree1 = new ArrayList<>();
        StudentsThree2 = new ArrayList<>();
        StudentsThree3 = new ArrayList<>();
        StudentsThree1.add(studentThree11);
        StudentsThree1.add(studentThree12);
        StudentsThree1.add(studentThree13);
        StudentsThree1.add(studentThree14);
        StudentsThree2.add(studentThree21);
        StudentsThree2.add(studentThree22);
        StudentsThree2.add(studentThree23);
        StudentsThree2.add(studentThree24);
        StudentsThree3.add(studentThree31);
        StudentsThree3.add(studentThree32);
        StudentsThree3.add(studentThree33);
        StudentsThree3.add(studentThree34);

        AllCloudStudentsThree = new ArrayList<>();
        AllCloudStudentsThree.add(StudentsThree1);
        AllCloudStudentsThree.add(StudentsThree2);
        AllCloudStudentsThree.add(StudentsThree3);

        Tilepanes = new ArrayList<>();
        Tilepanes.add(tilePane1);
        Tilepanes.add(tilePane2);
        Tilepanes.add(tilePane3);
        Tilepanes.add(tilePane4);
        Tilepanes.add(tilePane5);
        Tilepanes.add(tilePane6);
        Tilepanes.add(tilePane7);
        Tilepanes.add(tilePane8);
        Tilepanes.add(tilePane9);
        Tilepanes.add(tilePane10);
        Tilepanes.add(tilePane11);
        Tilepanes.add(tilePane12);

        Online = new ArrayList<>();
        Online.add(online1);
        Online.add(online2);
        Online.add(online3);

        Nicknames = new ArrayList<>();
        Nicknames.add(nameLabel1);
        Nicknames.add(nameLabel2);
        Nicknames.add(nameLabel3);

        CoinsAmount = new ArrayList<>();
        CoinsAmount.add(coinsLabel1);
        CoinsAmount.add(coinsLabel2);
        CoinsAmount.add(coinsLabel3);

        CoinsImages = new ArrayList<>();
        CoinsImages.add(coin1);
        CoinsImages.add(coin2);
        CoinsImages.add(coin3);

        NamePanels = new ArrayList<>();
        NamePanels.add(namePanel1);
        NamePanels.add(namePanel2);
        NamePanels.add(namePanel3);

        HelperCards = new ArrayList<>();
        HelperCards.add(helper1);
        HelperCards.add(helper2);
        HelperCards.add(helper3);

        Entrances = new ArrayList<>();
        Entrances.add(Entrance1);
        Entrances.add(Entrance2);
        Entrances.add(Entrance3);

        AllTables = new ArrayList<>();
        AllTables.add(Tables1);
        AllTables.add(Tables2);
        AllTables.add(Tables3);

        AllProfessors = new ArrayList<>();
        AllProfessors.add(Professors1);
        AllProfessors.add(Professors2);
        AllProfessors.add(Professors3);

        AllTowers = new ArrayList<>();
        AllTowers.add(Towers1);
        AllTowers.add(Towers2);
        AllTowers.add(Towers3);

        clickedEntrance = -1;
        tablesClicked = false;
        chosenCard = -1;

        clientController = ClientControllerSingleton.getInstance().getClientController();

    }


    public void entranceStudentClicked(MouseEvent mouseEvent)
    {
        if(!entranceClickable) return;
        for(int i=0;i<Entrance1.size();i++)
        {
            if(Entrance1.get(i).equals(mouseEvent.getSource()))
            {
                clickedEntrance = i;
            }
        }
    }


    public void tablesClicked(MouseEvent mouseEvent)
    {
        if (!tablesClickable) return;

        tablesClicked = true;
        /*
        if(entranceClicked == -1) return;

        if(mouseEvent.getX() < dashboard1.getFitWidth() * 0.16 || mouseEvent.getX() > dashboard1.getFitWidth() * 0.7 ) return;


        System.out.println("ETT "+entranceClicked);
        entranceClicked = -1;
        tablesClicked = true;
         */

    }


    public void cloudClicked(MouseEvent mouseEvent)
    {
        if(!cloudClickable) return;
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
        {
            clickedCloud = index;
            System.out.println("CTE " + index + " " + version);
        }
    }

    public void islandClicked(MouseEvent mouseEvent)
    {
        if(!islandClickable) return;

        TilePane tilePane = (TilePane) mouseEvent.getSource();
        String ID = tilePane.getId();
        String[] IDchars = ID.split("");
        ID = ID.length()==10? IDchars[8] + IDchars[9]: IDchars[8];

        System.out.println("ETI | "+ ID + " " + clickedEntrance);

        clickedIsland = Integer.parseInt(ID);
    }

    public void effectClicked(MouseEvent mouseEvent)
    {
        if (!expertMode || effectPlayed) return;

        //do things with the effects
    }

    public void updateView(String json) {

        gameData = gson.fromJson(json, jGameClassExpert.class);


        //manage expert mode
        if (gameData.ChosenCards==null)
        {
            expertMode = false;
            cardsGrid.setDisable(true);
            cardsGrid.setManaged(false);
            cardsGrid.getChildren().removeAll(cardsGrid.getChildren());
        }
        else
        {
            expertMode = true;
            cardsGrid.setDisable(false);
            cardsGrid.setManaged(true);
        }

        //enable or disable view elements based on the player number
        if(gameData.players.size() == 2)
        {
            stack3.setDisable(true);
            stack3.setVisible(false);
            stack3.setManaged(false);
            namePanel3.setDisable(true);
            namePanel3.setVisible(false);
            namePanel3.setManaged(false);
            namePanel3.getChildren().removeAll(namePanel3.getChildren());

            cloudPane21.setDisable(false);
            cloudPane21.setVisible(true);
            cloudPane22.setDisable(false);
            cloudPane22.setVisible(true);

            cloudPane31.setDisable(true);
            cloudPane31.setVisible(false);
            cloudPane32.setDisable(true);
            cloudPane32.setVisible(false);
            cloudPane33.setDisable(true);
            cloudPane33.setVisible(false);
        }
        if(gameData.players.size() == 3)
        {
            stack3.setDisable(false);
            stack3.setVisible(true);
            stack3.setManaged(true);
            namePanel3.setDisable(false);
            namePanel3.setVisible(true);
            namePanel3.setManaged(true);

            cloudPane21.setDisable(true);
            cloudPane21.setVisible(false);
            cloudPane22.setDisable(true);
            cloudPane22.setVisible(false);

            cloudPane31.setDisable(false);
            cloudPane31.setVisible(true);
            cloudPane32.setDisable(false);
            cloudPane32.setVisible(true);
            cloudPane33.setDisable(false);
            cloudPane33.setVisible(true);
        }


        //Dashboard management
        DashboardHandler dashboardHandler = new DashboardHandler();
        gameData.players.stream().filter(x->x.nickname.equals(clientController.getNickname())).forEach(x->dashboardHandler.updateDashboard(x.myDashboard,Entrances.get(0),AllTables.get(0),AllProfessors.get(0),AllTowers.get(0),x.towerColor,x,Online.get(0),Nicknames.get(0),CoinsAmount.get(0),HelperCards.get(0),gameData.playerCardValue[gameData.players.indexOf(x)]));
        List<jPlayer> remainingPlayers = gameData.players.stream().filter(x->!x.nickname.equals(clientController.getNickname())).collect(Collectors.toList());
        for(jPlayer player : remainingPlayers)
        {
            int i = remainingPlayers.indexOf(player)+1;
            dashboardHandler.updateDashboard(player.myDashboard,Entrances.get(i),AllTables.get(i),AllProfessors.get(i),AllTowers.get(i),player.towerColor,player,Online.get(i),Nicknames.get(i),CoinsAmount.get(i),HelperCards.get(i),gameData.playerCardValue[gameData.players.indexOf(player)]);
        }


        //Island management
        IslandHandler islandHandler = new IslandHandler();
        for(int i =0; i<Tilepanes.size();i++)
        {
            islandHandler.updateIsland(Tilepanes.get(i), gameData.islands.get(i), gameData.CurrentIsland.ID);
        }


        //cloud management
        CloudHandler cloudHandler = new CloudHandler();
        for(int i=0; i<gameData.clouds.size(); i++)
        {
            if(gameData.clouds.size()==2)
            {
                cloudHandler.updateCloud(AllCloudStudentsTwo.get(i), gameData.clouds.get(i));
            }
            else if(gameData.clouds.size()==3)
            {
                cloudHandler.updateCloud(AllCloudStudentsThree.get(i), gameData.clouds.get(i));
            }
        }

    }

    private void lockGui()
    {
        entranceClickable = false;
        cloudClickable = false;
        islandClickable = false;
        tablesClickable = false;
    }

    private void resetClickedValues()
    {
        clickedIsland = -1;
        clickedEntrance = -1;
        tablesClicked = false;
        clickedCloud = -1;
    }

    public void ETX()
    {
        System.out.println("Entering ETX phase");

        int count = 0;
        do
        {
            lockGui();
            entranceClickable = true;

            while (clickedEntrance ==-1)
            {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            entranceClickable = false;
            islandClickable = true;
            tablesClickable = true;

            while (clickedIsland == -1 && !tablesClicked)
            {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            islandClickable = false;
            tablesClickable = false;

            if (clickedIsland != -1) {
                if (!clientController.requestETI(clickedIsland, clickedEntrance))
                    System.out.println("Unable to play ETI");
                else
                    count++;
            }
            else if (tablesClicked) {
                if (!clientController.requestETT(clickedEntrance))
                    System.out.println("Unable to play ETT");
                else
                    count++;
            }

            resetClickedValues();

            System.out.println("ETX executed");

        } while (count <2);

        System.out.println("Exiting ETX phase");

        Nature();
    }

    public void Nature()
    {
        CTE();
    }

    public void CTE()
    {

        effectPlayed = false;
    }
}
