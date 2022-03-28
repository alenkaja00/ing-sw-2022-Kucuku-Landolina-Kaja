package it.polimi.ingsw.model;

import it.polimi.ingsw.model.cards.AbstractCard;
import it.polimi.ingsw.model.cards.Wizard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class GameClass {

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

        StudentBag = new ArrayList<>();
        for(ColoredDisc color: ColoredDisc.values())
        {
            for(int i=0; i<26;i++)
            {
                StudentBag.add(color);
            }
        }
        Collections.shuffle(StudentBag);

        clouds = new ArrayList<>();
        players = new ArrayList<>();
        for(int i=0;i<PlayerNumber;i++)
        {
            clouds.add(new Cloud(PlayerNumber+1)); // Cloud Capacity
            players.add(new Player(nicknames.get(i),Tower.values()[i],Wizard.values()[i],PlayerNumber));
        }


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
