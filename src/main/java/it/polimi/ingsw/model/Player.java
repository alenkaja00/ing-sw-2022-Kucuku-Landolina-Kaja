package it.polimi.ingsw.model;

import it.polimi.ingsw.model.cards.Wizard;

public class Player{
    private int ID;
    private String nickname;
    private Tower towerColor;
    private Wizard wizardID;
    private Deck deck;
    private Dashboard myDashboard;
    private int coinsAmount;

    public Player(String nickname, Tower towerColor, Wizard wizardID, int numberPlayers) {
        //this.ID= ID;
        this.nickname = nickname;
        this.towerColor = towerColor;
        this.wizardID = wizardID;
        this.deck = new Deck();
        this.myDashboard= new Dashboard(numberPlayers);

    }


    public Tower getTowerColor() {

        return towerColor;
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