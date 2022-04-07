package it.polimi.ingsw.server.model.components;

import it.polimi.ingsw.server.model.cards.Wizard;

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
        this.coinsAmount = 0;
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

    public Wizard getWizardID(){
        return this.wizardID;
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