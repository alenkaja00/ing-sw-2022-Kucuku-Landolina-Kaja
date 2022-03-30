package it.polimi.ingsw.model;

import it.polimi.ingsw.model.cards.HelperCard;
import it.polimi.ingsw.model.cards.Wizard;

import java.util.ArrayList;

public class Player{
    private int ID;

    private String nickname;
    private Tower towerColor;
    private Wizard wizardID;
    private int coinsAmount;
    public Dashboard myDashboard;
    public Deck deck;
    public boolean online;


    public Player(int ID,String nickname, Tower towerColor, Wizard wizardID, int numberPlayers) {
        this.ID= ID;
        this.online = true;
        this.nickname = nickname;
        this.towerColor = towerColor;
        this.wizardID = wizardID;
        this.deck = new Deck();
        this.myDashboard= new Dashboard(numberPlayers);

    }

    public Tower getTowerColor() {

        return towerColor;
    }

    public int getID() {
        return ID;
    }

    public void setWizardID(Wizard wizardID) {
        this.wizardID = wizardID;
    }

    public void setTowerColor(Tower towerColor) {
        this.towerColor = towerColor;
    }

    public void addCoins(int addition)
    {
        coinsAmount += addition;
    }

    public void spendCoins(int removal) throws  IllegalArgumentException
    {
        if(removal > coinsAmount)
        {
            throw  new IllegalArgumentException();
        }

        else
        {
            coinsAmount -= removal;
        }

    }

    public String getNickname()
    {
        return nickname;
        // Strings are immutable in java, so this should be safe
    }

    public int getCoinsAmount()
    {
        return coinsAmount;
    }


}