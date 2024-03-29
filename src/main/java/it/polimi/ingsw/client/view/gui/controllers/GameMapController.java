package it.polimi.ingsw.client.view.gui.controllers;

import com.google.gson.Gson;
import it.polimi.ingsw.client.controller.ClientController;
import it.polimi.ingsw.client.controller.ClientControllerSingleton;
import it.polimi.ingsw.client.view.jsonObjects.*;
import it.polimi.ingsw.server.model.components.ColoredDisc;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * this class shows the main game screen, with all the dashboards, clouds, islands, ecc.
 */
public class GameMapController
{
    @FXML
    ImageView effectOneImage, effectTwoImage, effectThreeImage;
    @FXML
    Label cardLabel1, cardLabel2, cardLabel3;
    @FXML
    StackPane helpStack;
    //scrollpanes
    @FXML
    private ScrollPane centralPane, centralPane1;
    //deckpane
    @FXML
    private StackPane deckStack;
    @FXML
    private ImageView card1, card2, card3, card4, card5, card6, card7, card8, card9, card10;

    @FXML
    private Label bannerText;

    @FXML
    private StackPane islandStack1, islandStack2, islandStack3, islandStack4, islandStack5, islandStack6, islandStack7, islandStack8, islandStack9, islandStack10, islandStack11, islandStack12;


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
    @FXML
    private ImageView usedEffectOne, usedEffectTwo, usedEffectThree;
    @FXML
    private TilePane effectPane1, effectPane2, effectPane3;

    private HashMap<String,String> effectPaths = new HashMap<>(){{
        put("/Effects/monk.jpg","MONK");
        put("/Effects/queen.jpg","QUEEN");
        put("/Effects/lady.jpg","LADY");
        put("/Effects/jolly.jpg","JOLLY");
        put("/Effects/cavalier.jpg","CAVALIER");
        put("/Effects/lord.jpg","LORD");
        put("/Effects/centaur.jpg","CENTAUR");
        put("/Effects/cook.jpg","COOK");
        put("/Effects/villain.jpg","VILLAIN");
        put("/Effects/magician.jpg","MAGICIAN");
        put("/Effects/musician.jpg","MUSICIAN");
        put("/Effects/bandit.jpg","BANDIT");
    }};
    private HashMap<ColoredDisc,String> studentsPath = new HashMap<>(){{
        put(ColoredDisc.RED,"/StudentDisks/redDisk.png");
        put(ColoredDisc.YELLOW,"/StudentDisks/yellowDisk.png");
        put(ColoredDisc.BLUE,"/StudentDisks/blueDisk.png");
        put(ColoredDisc.PINK,"/StudentDisks/pinkDisk.png");
        put(ColoredDisc.GREEN,"/StudentDisks/greenDisk.png");
    }};
    private String denialPath = "/denial.png";


    ///island
    @FXML
    private TilePane tilePane1, tilePane2, tilePane3, tilePane4, tilePane5, tilePane6, tilePane7, tilePane8, tilePane9, tilePane10, tilePane11, tilePane12;
    @FXML
    private ImageView island1, island2, island3, island4, island5, island6, island7, island8, island9, island10, island11, island12;

    ///third player panes
    @FXML
    private StackPane stack3;
    @FXML
    private GridPane namePanel1, namePanel2, namePanel3;
    @FXML
    private StackPane spacer;
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
    //
    private ArrayList<ImageView> deck;
    private ArrayList<ImageView> effectList;
    private ArrayList<ImageView> usedEffectList;
    private ArrayList<TilePane> effectPanesList;

    private ArrayList<StackPane> IslandStacks;
    private ArrayList<ImageView> IslandImages;


    ///logic variables
    private Gson gson = new Gson();
    private jGameClassExpert gameData;
    private ClientController clientController;


    private Boolean entranceClickable = false;
    private int clickedEntrance = -1;

    private Boolean islandClickable = false;
    private int clickedIsland = -1;

    private Boolean cloudClickable = false;
    private int clickedCloud = -1;

    private Boolean tablesClickable = false;
    private Boolean tablesClicked = false;
    private ColoredDisc tableStudent = null;

    private int chosenCard;
    private int currentIslandID;
    private ArrayList<jIsland> islandsData;

    private Boolean expertMode = false;
    private Boolean effectPlayed = false;
    private Boolean runningEffect = false;
    private ColoredDisc clickedEffectChildValue = null;
    private ImageView clickedEffectChildImage = null;
    private StackPane clickedEffectParent = null;

    private int clickedCard = -1;

    /**
     * here we create all the imageviews needed to show the game items
     */
    @FXML
    public void initialize() throws IOException {
        //deck management
        deck = new ArrayList<>(Arrays.asList(card1, card2, card3, card4, card5, card6, card7, card8, card9, card10));
        showDeck(false);

        effectList = new ArrayList<>(Arrays.asList(effectOne, effectTwo, effectThree));
        usedEffectList = new ArrayList<>(Arrays.asList(usedEffectOne, usedEffectTwo, usedEffectThree));
        effectPanesList = new ArrayList<>(Arrays.asList(effectPane1, effectPane2, effectPane3));

        //IslandStacks
        IslandStacks = new ArrayList<>(Arrays.asList(islandStack1, islandStack2, islandStack3, islandStack4, islandStack5, islandStack6, islandStack7, islandStack8, islandStack9, islandStack10, islandStack11, islandStack12));
        IslandImages = new ArrayList<>(Arrays.asList(island1, island2, island3, island4, island5, island6, island7, island8, island9, island10, island11, island12));

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
        CloudThreeGrids.add(cloudGrid31);
        CloudThreeGrids.add(cloudGrid32);
        CloudThreeGrids.add(cloudGrid33);
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

    /**
     * handles the click on a card
     * @param mouseEvent is the event(click) that triggers the execution of this method
     */
    @FXML
    private void cardClicked(MouseEvent mouseEvent)
    {
        System.out.println("Card selected");
        ImageView chosen = (ImageView) mouseEvent.getSource();

        for(int i =0; i<deck.size();i++)
        {
            deck.get(i).setDisable(true);
            if(deck.get(i).equals(chosen))
            {
                clickedCard = i+1;
                chosen.setOpacity(1);
                deck.get(i).setScaleY(1.1);
                deck.get(i).setScaleX(1.1);
            }
            else
            {
                deck.get(i).setOpacity(0.5);
            }
        }
    }

    /**
     * this method is called whenever an entrance student is clicked
     * the variable clickedEntrance is set to the index of the selected student
     * @param mouseEvent
     */
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
        System.out.println("Clicked entrance student "+clickedEntrance);
    }

    /**
     * handles click on a table student
     * @param mouseEvent
     */
    @FXML
    private void tableStudentClicked(MouseEvent mouseEvent)
    {
        ImageView clickedImage = (ImageView) mouseEvent.getSource();
        if (clickedImage.getImage()!=null)
        {
            String source = (clickedImage).getImage().getUrl();
            String sourceUrl = source.substring(source.indexOf("/Student"));
            tableStudent = studentsPath.entrySet().stream().filter(x->x.getValue().equals(sourceUrl)).map(x->x.getKey()).collect(Collectors.toList()).get(0);
        }
        else
        {
            tableStudent = null;
        }
    }

    /**
     * this method is called whenever a tables student is clicked
     * we set the flag "tablesClicked" to true
     */
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

    /**
     * this method is called whenever a cloud is clicked
     * if used properly, the flag clickedCloud is set to true
     * @param mouseEvent triggers this method
     */
    public void cloudClicked(MouseEvent mouseEvent)
    {
        if(!cloudClickable) return;
        int index = -1;

        for(int i=0;i<CloudTwoGrids.size();i++) {
            if (mouseEvent.getSource().equals(CloudTwoGrids.get(i))) {
                index = i;
            }
        }
        for(int i=0;i<CloudThreeGrids.size();i++) {
            if (mouseEvent.getSource().equals(CloudThreeGrids.get(i))) {
                index = i;
            }
        }

        if( index != -1)
        {
            clickedCloud = index;
        }
    }

    /**
     * this method is called whenever an island is clicked
     * if used properly, the flag clickedIsland is set to true
     * @param mouseEvent is the event that triggers this method
     */
    public void islandClicked(MouseEvent mouseEvent)
    {
        if(!islandClickable) return;

        TilePane tilePane = (TilePane) mouseEvent.getSource();
        String ID = tilePane.getId();
        String[] IDchars = ID.split("");
        ID = ID.length()==10? IDchars[8] + IDchars[9]: IDchars[8];


        clickedIsland = Integer.parseInt(ID);
    }

    private void selectEffect(ImageView card)
    {
        //card.setStyle(style);
        card.setScaleY(1.2);
        card.setScaleX(1.2);
        Glow effect = new Glow();
        card.setEffect(effect);
    }

    private void effectFailed()
    {
        resetEffects();
        Platform.runLater(()->{
            String previousMessage = bannerText.getText();
            bannerMessage("Unable to play effect Card!");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bannerMessage(previousMessage);
        });
    }
    private void resetEffects()
    {
        effectPlayed = false;
        Platform.runLater(()->{
            effectList.stream().forEach(x->{
                x.setEffect(null);
                x.setScaleY(1);
                x.setScaleX(1);
            });
        });
    }

    @FXML
    private void effectChildClicked(MouseEvent mouseEvent)
    {
        clickedEffectChildImage = (ImageView) mouseEvent.getSource();
        String source = (clickedEffectChildImage).getImage().getUrl();
        String sourceUrl = source.substring(source.indexOf("/Student"));
        //System.out.println(sourceUrl);
        clickedEffectChildValue = studentsPath.entrySet().stream().filter(x->x.getValue().equals(sourceUrl)).map(x->x.getKey()).collect(Collectors.toList()).get(0);
        //System.out.println(clickedEffectChild);
        clickedEffectParent = (StackPane)((TilePane) clickedEffectChildImage.getParent()).getParent();
    }

    public void effectClicked(MouseEvent mouseEvent)
    {
        if (!expertMode || effectPlayed || ClientControllerSingleton.getInstance().getClientController().getViewLocked()) return;
        Task effectTask = new Task<Void>() {
            @Override
            public Void call() throws InterruptedException {
                effectPlayed = true;
                runningEffect = true;
                Boolean success = false;
                //System.out.println("inside the effect click");

                String oldBanner = bannerText.getText();

                int oldClickedCard = clickedCard;
                int oldClickedIsland = clickedIsland;
                int oldClickedEntrance = clickedEntrance;
                Boolean oldTablesClicked = tablesClicked;
                int oldClickedCloud = clickedCloud;

                resetClickedValues();

                Boolean oldEntranceClickable = entranceClickable;
                Boolean oldCloudClickable = cloudClickable;
                Boolean oldIslandClickable = islandClickable;
                Boolean oldTablesClickable = tablesClickable;

                lockGui();


                ImageView card = (ImageView) mouseEvent.getSource();
                String url = ((Image) card.getImage()).getUrl();
                url = url.substring(url.indexOf("/Effect"));
                System.out.println(url);

                selectEffect(card);

                switch (effectPaths.get(url))
                {
                    case "CAVALIER":
                    case "CENTAUR":
                    case "VILLAIN":
                    case "MAGICIAN":
                        success = ClientControllerSingleton.getInstance().getClientController().requestString("EFFECT|"+effectPaths.get(url));
                        break;
                    case "LADY": {
                        bannerMessage("Click on the island to lock!");
                        islandClickable = true;
                        while (clickedIsland == -1) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        success = ClientControllerSingleton.getInstance().getClientController().requestString("EFFECT|LADY|" + (clickedIsland - 1));
                        islandClickable = false;
                    }
                    break;
                    case "LORD": {
                        bannerMessage("Click on the island to evaluate!");
                        islandClickable = true;
                        while (clickedIsland == -1) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        success = ClientControllerSingleton.getInstance().getClientController().requestString("EFFECT|LORD|" + (clickedIsland - 1));
                        islandClickable = false;
                    }
                    break;
                    case "COOK": {
                        clickedEffectParent = null;
                        clickedEffectChildValue = null;
                        bannerMessage("Choose the color to block!");
                        while (clickedEffectChildValue == null || clickedEffectParent != ((StackPane) card.getParent()))
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        success = ClientControllerSingleton.getInstance().getClientController().requestString("EFFECT|COOK|" + clickedEffectChildValue.toString());
                    }
                    break;
                    case "BANDIT": {
                        clickedEffectParent = null;
                        clickedEffectChildValue = null;
                        bannerMessage("Choose the color to steal!");
                        while (clickedEffectChildValue == null || clickedEffectParent != ((StackPane) card.getParent())) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        success = ClientControllerSingleton.getInstance().getClientController().requestString("EFFECT|BANDIT|" + clickedEffectChildValue.name());
                    }
                    break;
                    case "MONK": {
                        clickedEffectParent = null;
                        clickedEffectChildValue = null;
                        bannerMessage("Choose the student you want to move!");
                        while (clickedEffectChildValue == null || clickedEffectParent != ((StackPane) card.getParent())) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        int clickedIndex = ((TilePane) clickedEffectChildImage.getParent()).getChildren().indexOf(clickedEffectChildImage);
                        bannerMessage("Select the destination island!");
                        islandClickable = true;
                        while (clickedIsland == -1) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        islandClickable = false;
                        success = ClientControllerSingleton.getInstance().getClientController().requestString("EFFECT|MONK|" + (clickedIsland-1) + "|" + clickedIndex);
                    }
                    break;
                    case "QUEEN": {
                        clickedEffectParent = null;
                        clickedEffectChildValue = null;
                        bannerMessage("Click on the student you want to move to your tables!");
                        while (clickedEffectChildValue == null || clickedEffectParent != ((StackPane) card.getParent())) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        success = ClientControllerSingleton.getInstance().getClientController().requestString("EFFECT|QUEEN|" + ((TilePane) clickedEffectChildImage.getParent()).getChildren().indexOf(clickedEffectChildImage));
                    }
                    break;
                    case "JOLLY":
                        String jollyMessage = "EFFECT|JOLLY";

                        var jollyCondition = new Object()
                        {
                            int counter = 0;
                            boolean move = false;
                        };

                        Platform.runLater(() ->
                        {
                            Alert alert = new Alert(Alert.AlertType.NONE);
                            ButtonType b1 = new ButtonType("1");
                            ButtonType b2 = new ButtonType("2");
                            ButtonType b3 = new ButtonType("3");
                            alert.getButtonTypes().add(b1);
                            alert.getButtonTypes().add(b2);
                            alert.getButtonTypes().add(b3);

                            alert.setTitle("Play effect");
                            alert.setHeaderText("How many students would you like to switch?");
                            alert.setContentText("Jolly allows you to switch up to 3 students between the card and your entrance");

                            Optional<ButtonType> result = alert.showAndWait();

                            if(result.get()==b1)
                            {
                                jollyCondition.counter = 1;
                            }
                            else if(result.get()==b2)
                            {
                                jollyCondition.counter = 2;
                            }
                            else if(result.get()==b3)
                            {
                                jollyCondition.counter = 3;
                            }
                            else
                                jollyCondition.counter = 3;
                            jollyCondition.move = true;

                            alert.setOnCloseRequest(event->{ alert.close();});
                        });

                        while (!jollyCondition.move) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        for (int i=0 ; i< jollyCondition.counter ; i++)
                        {
                            resetClickedValues();
                            clickedEffectParent = null;
                            clickedEffectChildValue = null;
                            bannerMessage("Choose the student you want to move! ("+(jollyCondition.counter-i)+" left)");
                            while (clickedEffectChildValue == null || clickedEffectParent != ((StackPane) card.getParent())) {
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            jollyMessage += "|" + ((TilePane) clickedEffectChildImage.getParent()).getChildren().indexOf(clickedEffectChildImage);
                            bannerMessage("Select an entrance student to switch with!");
                            entranceClickable = true;
                            while (clickedEntrance == -1) {
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            entranceClickable = false;
                            jollyMessage += "|" + clickedEntrance;
                            //System.out.println("out with value " + clickedEntrance);
                        }
                        success = ClientControllerSingleton.getInstance().getClientController().requestString(jollyMessage);
                        break;
                    case "MUSICIAN":
                        String musicianMessage = "EFFECT|MUSICIAN";

                        var musicianCondition = new Object()
                        {
                            int counter = 0;
                            boolean move = false;
                        };

                        Platform.runLater(() ->
                        {
                            Alert alert = new Alert(Alert.AlertType.NONE);
                            ButtonType b1 = new ButtonType("1");
                            ButtonType b2 = new ButtonType("2");
                            alert.getButtonTypes().add(b1);
                            alert.getButtonTypes().add(b2);

                            alert.setTitle("Musician Effect");
                            alert.setHeaderText("How many students would you like to switch?");
                            alert.setContentText("Musician allows you to switch up to 2 students between your entrance and your tables");

                            Optional<ButtonType> result = alert.showAndWait();

                            if(result.get()==b1)
                            {
                                musicianCondition.counter = 1;
                            }
                            else if(result.get()==b2)
                            {
                                musicianCondition.counter = 2;
                            }
                            else
                                musicianCondition.counter = 2;
                            musicianCondition.move = true;

                            alert.setOnCloseRequest(event->{ alert.close();});
                        });
                        while (!musicianCondition.move) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if(gameData.players.stream().filter(x->x.nickname.equals(clientController.getNickname())).collect(Collectors.toList()).get(0).myDashboard.tables.entrySet().stream().mapToInt(x->x.getValue()).sum() < musicianCondition.counter )
                        {
                            success = false;
                            break;
                        }

                        for (int i=0; i<musicianCondition.counter; i++)
                        {
                            resetClickedValues();
                            entranceClickable = true;

                            bannerMessage("Choose the student you want to move! ("+(musicianCondition.counter-i)+" left)");
                            while (clickedEntrance ==-1 ) {
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            musicianMessage += "|" + clickedEntrance;
                            tableStudent = null;
                            while (tableStudent == null) {
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            bannerMessage("Select a student to switch with from the tables!");
                            musicianMessage += "|" + tableStudent.name();
                        }
                        success = ClientControllerSingleton.getInstance().getClientController().requestString(musicianMessage);
                        break;
                    default:
                        break;
                }


                entranceClickable = oldEntranceClickable;
                cloudClickable = oldCloudClickable;
                islandClickable = oldIslandClickable;
                tablesClickable = oldTablesClickable;

                clickedCard= oldClickedCard;
                clickedIsland= oldClickedIsland;
                clickedEntrance=oldClickedEntrance;
                tablesClicked=oldTablesClicked;
                clickedCloud=oldClickedCloud;
                runningEffect = false;
                if (!success)
                {//effectFailed();
                    String previousMessage = bannerText.getText();
                    bannerMessage("Unable to play effect card!");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //bannerMessage(oldBanner);
                    resetEffects();
                }
                bannerMessage(oldBanner);
                return null;
            }
        };
        new Thread(effectTask).start();
    }

    /**
     * given the json it updates al the elements of the screen
     * @param json is used to create the game class object and change all the elements
     */
    public void updateView(String json) {

        gameData = gson.fromJson(json, jGameClassExpert.class);

        currentIslandID = gameData.CurrentIsland.ID;
        islandsData = gameData.islands;

        //manage expert mode
        if (gameData.ChosenCards==null)
        {
            expertMode = false;
            cardsGrid.setDisable(true);
            cardsGrid.setManaged(false);
            cardsGrid.getChildren().removeAll(cardsGrid.getChildren());

            CoinsAmount.stream().forEach(x->x.setDisable(true));
            CoinsAmount.stream().forEach(x->x.setVisible(false));
            CoinsAmount.stream().forEach(x->x.setManaged(false));
            CoinsImages.stream().forEach(x->x.setDisable(true));
            CoinsImages.stream().forEach(x->x.setVisible(false));
            CoinsImages.stream().forEach(x->x.setManaged(false));
        }
        else
        {
            expertMode = true;
            cardsGrid.setDisable(false);
            cardsGrid.setManaged(true);
            CoinsAmount.stream().forEach(x->x.setDisable(false));
            CoinsAmount.stream().forEach(x->x.setVisible(true));
            CoinsAmount.stream().forEach(x->x.setManaged(true));
            CoinsImages.stream().forEach(x->x.setDisable(false));
            CoinsImages.stream().forEach(x->x.setVisible(true));
            CoinsImages.stream().forEach(x->x.setManaged(true));


            for (int i=0; i<3; i++)
            {
                jEffectCard effect = gameData.ChosenCards.get(i);

                for (Map.Entry<String,String> entry: effectPaths.entrySet())
                {
                    if (effect.ID.toString().equals(entry.getValue()))
                    {
                        effectList.get(i).setImage(new Image(entry.getKey()));
                    }
                }

                usedEffectList.get(i).setVisible(effect.used);

                switch (effect.ID)
                {
                    case LADY:
                        for (int k=0; k < gameData.prohibitionCards; k++) {
                            if (effect.prohibitionCard > k) {
                                ((ImageView) ((TilePane) effectPanesList.get(i)).getChildren().get(k)).setImage(new Image(denialPath));
                            } else {
                                ((ImageView) ((TilePane) effectPanesList.get(i)).getChildren().get(k)).setImage(null);
                            }
                        }
                        break;
                    case MONK:
                    case QUEEN:
                    case JOLLY:
                        for (int k=0; k< effect.students.length; k++)
                            if (effect.students[k] != null)
                            {
                                ((ImageView)((TilePane)effectPanesList.get(i)).getChildren().get(k)).setImage(new Image(studentsPath.get(effect.students[k])));
                            }
                            else
                            {
                                ((ImageView)((TilePane)effectPanesList.get(i)).getChildren().get(k)).setImage(null);
                            }
                        break;
                    case COOK:
                    case BANDIT:
                        ArrayList<ColoredDisc> colorsArray = (ArrayList<ColoredDisc>) Arrays.stream(ColoredDisc.values()).collect(Collectors.toList());
                        for (int k=0; k<6; k++)
                            if (k<5)
                            {
                                ((ImageView)((TilePane)effectPanesList.get(i)).getChildren().get(k)).setImage(new Image(studentsPath.get(colorsArray.get(k))));
                            }
                            else
                            {
                                ((ImageView)((TilePane)effectPanesList.get(i)).getChildren().get(k)).setImage(null);
                            }
                        break;
                    default:
                        break;
                }
            }
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
            spacer.setVisible(false);
            spacer.setManaged(false);

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
            spacer.setVisible(true);
            spacer.setManaged(true);

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
        List<Integer> islandsID = gameData.islands.stream().map(x->x.ID).collect(Collectors.toList());
        for(int i =0; i<Tilepanes.size();i++)
        {
            if(islandsID.contains(i))
            {
                int index = islandsID.indexOf(i);
                islandHandler.updateIsland(Tilepanes.get(i), IslandImages.get(i), gameData.islands.get(index), gameData.CurrentIsland.ID);
                IslandStacks.get(i).setVisible(true);
                IslandStacks.get(i).setDisable(false);
                IslandStacks.get(i).setManaged(true);
            }
            else
            {
                IslandStacks.get(i).setVisible(false);
                IslandStacks.get(i).setDisable(true);
                IslandStacks.get(i).setManaged(false);
            }
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


        //deck management
        resetDeck();
    }

    /**
     * method called when the player has to wait his turn
     */
    public void lockGui()
    {
        entranceClickable = false;
        cloudClickable = false;
        islandClickable = false;
        tablesClickable = false;
    }

    /**
     * method used to reset the last click to none
     */
    private void resetClickedValues()
    {
        clickedCard = -1;
        clickedIsland = -1;
        clickedEntrance = -1;
        tablesClicked = false;
        clickedCloud = -1;
    }

    /**
     * shows the helper cards deck
     * @param val
     */
    private void showDeck(boolean val)
    {
        deckStack.setManaged(val);
        deckStack.setVisible(val);
        deckStack.setDisable(!val);
        deckStack.getChildren().forEach(x->{x.setManaged(val); x.setVisible(val); x.setDisable(!val);});
    }

    /**
     * resets the graphical settings of the deck
     */
    private void resetDeck()
    {
        for (ImageView card : deck)
        {
            card.setScaleX(1);
            card.setScaleY(1);
            card.setDisable(false);
            card.setOpacity(1);
        }
        ArrayList<jHelperCard> playerDeck = gameData.players.stream().filter(x->x.nickname.equals(clientController.getNickname())).map(x->x.deck.deck).collect(Collectors.toList()).get(0);
        for (int i=0; i<deck.size(); i++)
        {
            if (playerDeck.get(i).used)
            {
                deck.get(i).setOpacity(0.5);
                deck.get(i).setDisable(true);
            }
        }
    }

    /**
     * performs the action ETI(EntranceToIsland) or ETT(EntranceToTables)
     */
    public void ETX()
    {
        resetEffects();
        //helper card selection
        showDeck(true);
        do {
            bannerMessage("Select a card!");

            while (clickedCard == -1) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (clientController.requestHelper(clickedCard))
            {
                resetClickedValues();
                resetDeck();
                showDeck(false);
                break;
            }
            else
            {
                resetClickedValues();
                bannerMessage("The card you selected is not valid");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                resetDeck();
            }
        } while (true);

        //wait for turn
        bannerMessage("Waiting for your turn...");
        ClientControllerSingleton.getInstance().getClientController().waitViewUnlock();

        int count = 0;
        do
        {
            bannerMessage("Move a student to an island or to the tables! (" + (gameData.PlayerNumber+1-count) + " left)");
            lockGui();
            entranceClickable = true;

            while (clickedEntrance ==-1 || runningEffect)
            {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Clicked entrance student: " + clickedEntrance);
            entranceClickable = false;
            islandClickable = true;
            tablesClickable = true;

            while ((clickedIsland == -1 && !tablesClicked) || runningEffect)
            {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //System.out.println("out of the box");

            islandClickable = false;
            tablesClickable = false;

            if (clickedIsland != -1) {
                if (!clientController.requestETI(clickedIsland-1, clickedEntrance))
                {
                    bannerMessage("Invalid student move. Try again!");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    System.out.println("Played ETI");
                    count++;
                }
            }
            else if (tablesClicked) {
                if (!clientController.requestETT(clickedEntrance))
                {
                    bannerMessage("Invalid student move. Try again!");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    System.out.println("Played ETT");
                    count++;
                }
            }

            resetClickedValues();

            System.out.println("ETX executed");

        } while (count < gameData.PlayerNumber+1);

        System.out.println("Exiting ETX phase");

        Nature();
    }

    /**
     * Moves mother nature to the proper island
     */
    public void Nature()
    {
        bannerMessage("Move mother nature!");
        do
        {
            lockGui();
            islandClickable = true;

            while (clickedIsland ==-1 || runningEffect)
            {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Clicked island: " + clickedIsland);
            islandClickable = false;

            int currentIslandPosition = islandsData.stream().filter(x->x.ID == currentIslandID).map(x->islandsData.indexOf(x)).collect(Collectors.toList()).get(0);
            int clickedIslandPosition = islandsData.stream().filter(x->x.ID == clickedIsland-1).map(x->islandsData.indexOf(x)).collect(Collectors.toList()).get(0);
            int motherNatureMoves = (clickedIslandPosition + islandsData.size() - currentIslandPosition) % islandsData.size();
            resetClickedValues();

            if (!clientController.requestNature(motherNatureMoves))
            {
                bannerMessage("Invalid mother nature move. Try again!");
            }
            else {
                System.out.println("Moved mother nature");
                break;
            }
        } while (true);

        CTE();
    }

    /**
     * performs the action CloudToEntrance
     */
    public void CTE()
    {
        bannerMessage("Select a cloud to refill your entrance!");
        do
        {
            lockGui();
            cloudClickable = true;

            while (clickedCloud ==-1 || runningEffect)
            {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Clicked cloud: " + (clickedCloud));
            cloudClickable = false;

            if (!clientController.requestCTE(clickedCloud))
            {
                bannerMessage("Invalid cloud selection. Try again!");
                resetClickedValues();
            }
            else {
                System.out.println("Correctly done CTE");
                resetClickedValues();
                break;
            }
        } while (true);
        System.out.println("ONE ROUND OK");

        bannerMessage("Waiting for your turn...");
        ClientControllerSingleton.getInstance().getClientController().waitViewUnlock();
        ETX();
    }

    /**
     * handles graphical effects for cards on mouseEnter
     * @param mouseEvent user generated event
     */
    @FXML
    private void mouseEntered(MouseEvent mouseEvent)
    {
        if (effectList.contains((ImageView) mouseEvent.getSource()) && effectPlayed) return;
        if (ClientControllerSingleton.getInstance().getClientController().getViewLocked()) return;
        ImageView image = (ImageView) mouseEvent.getSource();
        DropShadow effect = new DropShadow();
        effect.setColor(Color.YELLOW);
        effect.setRadius(30);
        image.setEffect(effect);
        image.setScaleX(1.1);
        image.setScaleY(1.1);
    }

    /**
     * handles graphical effects for cards on mouseExit
     * @param mouseEvent
     */
    @FXML
    private void mouseExited(MouseEvent mouseEvent)
    {
        if (effectList.contains((ImageView) mouseEvent.getSource()) && effectPlayed) return;
        if (ClientControllerSingleton.getInstance().getClientController().getViewLocked()) return;
        ImageView image = (ImageView) mouseEvent.getSource();
        image.setScaleX(1);
        image.setScaleY(1);
        image.setEffect(null);
        /*for(ImageView img : deck)
        {
            img.setScaleY(1);
            img.setScaleX(1);
        }
        for(ImageView img : )*/
    }

    /**
     * shows a message on the game scene top banner
     * @param text the text to be shown
     */
    public void bannerMessage(String text)
    {
        Platform.runLater(()->bannerText.setText(text));
    }

    /**
     * initializes the effect card with descriptions
     * @param actionEvent user generated event
     */
    public void activateHelp(ActionEvent actionEvent)
    {
        cardLabel1.setText(gameData.ChosenCards.get(0).description);
        cardLabel2.setText(gameData.ChosenCards.get(1).description);
        cardLabel3.setText(gameData.ChosenCards.get(2).description);

        effectOneImage.setImage(effectList.get(0).getImage());
        effectTwoImage.setImage(effectList.get(1).getImage());
        effectThreeImage.setImage(effectList.get(2).getImage());

        helpStack.setDisable(false);
        helpStack.setManaged(true);
        helpStack.setVisible(true);
    }

    /**
     * shows or hided the go back button
     * @param actionEvent
     */
    public void hideButton(ActionEvent actionEvent) {
            helpStack.setDisable(true);
            helpStack.setManaged(false);
            helpStack.setVisible(false);
    }
}