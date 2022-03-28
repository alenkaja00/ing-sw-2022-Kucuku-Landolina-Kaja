package it.polimi.ingsw.model;

import it.polimi.ingsw.model.cards.AbstractCard;
import it.polimi.ingsw.model.cards.HelperCard;
import it.polimi.ingsw.model.cards.Wizard;

import javax.swing.plaf.ColorUIResource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class GameClass {

    private StudentBag bag;
    private String GameID ;
    private int PlayerNumber;
    private ArrayList<Island> islands ;
    private ArrayList<ColoredDisc> StudentBag ;
    private ArrayList<Cloud> clouds;
    private ArrayList<Player> players;
    private Player PlayerRound;
    private ArrayList<AbstractCard> SpecialCards;
    private ArrayList<AbstractCard> ChosenCards;
    private ArrayList<String> nicknames;
    private int NumOfIslands = 12;


    private Island CurrentIsland;
    public GameClass(String ID, int PlayerNumber, ArrayList<String> nicknames, ArrayList<Wizard> wizards)
    {
        this.GameID = ID;
        this.PlayerNumber = PlayerNumber;
        this.nicknames = nicknames;

        islands = new ArrayList<>();
        for(int i=0;i<NumOfIslands;i++)
        {
            islands.add(new Island(i));
        }

        //Random random = new Random();
        this.CurrentIsland = islands.get((new Random()).nextInt(NumOfIslands));

        //initialize islands with 2 students for each color, no students on mother nature island and opposite island
        bag = new StudentBag();
        for (Island myIsland: islands)
        {
            if (myIsland!= CurrentIsland && (CurrentIsland.getID() + NumOfIslands/2) % NumOfIslands != myIsland.getID())
            {
                myIsland.addStudent(bag.popRandom());
            }
        }

        clouds = new ArrayList<>();
        players = new ArrayList<>();
        for(int i=0;i<PlayerNumber;i++)
        {
            clouds.add(new Cloud(PlayerNumber+1)); // Cloud Capacity
            players.add(new Player(i,nicknames.get(i),Tower.values()[i],Wizard.values()[i],PlayerNumber));
        }
    }

    public void CloudToEntrance(int CloudIndex, int PlayerID)
    {
        ArrayList<ColoredDisc> students = clouds.get(CloudIndex).removeAll();
        for(ColoredDisc student : students)
        {
            players.get(PlayerID).myDashboard.AddToEntrance(student); // access to public attribute
        }
    }



    public void EntranceToTables(int PlayerID, ColoredDisc student)
    {
        players.get(PlayerID).myDashboard.MoveToTables(student);
    }


    public void EntranceToIsland(int PlayerID, int IslandID, ColoredDisc student)
    {
        islands.get(IslandID).addStudent(student);
        players.get(PlayerID).myDashboard.RemoveFromEntrance(student);
    }


    public void MoveMotherNature(int moves)
    {
        CurrentIsland = islands.get((islands.indexOf(CurrentIsland)+moves) % islands.size() );

        //evaluate influence
        //check united islands
    }



    public ArrayList<Cloud> getClouds() {
        return (ArrayList<Cloud>) clouds.clone();
    }

    public ArrayList<Island> getIslands() {
        return (ArrayList<Island>) islands.clone();
    }

    public int getPlayerNumber() {
        return PlayerNumber;
    }

    public String getGameID() {
        return GameID;
    }

    public Player getPlayerRound() {
        return PlayerRound;
    }

    public ArrayList<String> getNicknames() {
        return (ArrayList<String>) nicknames.clone();
    }

    public ArrayList<Player> getPlayers() {
        return (ArrayList<Player>) players.clone();
    }

    public ArrayList<ColoredDisc> getStudentBag() {
        return (ArrayList<ColoredDisc>) StudentBag.clone();
    }

    public void setClouds(ArrayList<Cloud> clouds) {
        this.clouds = clouds;
    }

    public void setGameID(String gameID) {
        GameID = gameID;
    }

    public void setIslands(ArrayList<Island> islands) {
        this.islands = islands;
    }

    public void setNicknames(ArrayList<String> nicknames) {
        this.nicknames = nicknames;
    }


    public void setPlayerNumber(int playerNumber) {
        PlayerNumber = playerNumber;
    }

    public void setPlayerRound(Player playerRound) {
        PlayerRound = playerRound;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setStudentBag(ArrayList<ColoredDisc> studentBag) {
        StudentBag = studentBag;
    }

}
