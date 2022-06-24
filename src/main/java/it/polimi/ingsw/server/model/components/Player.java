package it.polimi.ingsw.server.model.components;

import it.polimi.ingsw.server.model.cards.Wizard;

/**
 * class Player represents the user and player of the board game
 */

public class Player{
    private int ID;

    private String nickname;
    private Tower towerColor;
    private Wizard wizardID;
    private int coinsAmount;
    public Dashboard myDashboard;
    public Deck deck;
    public boolean online;

    /**
     * Constructor that sets the Player instance
     * @param ID is the player identifier
     * @param nickname is the player nickname
     * @param towerColor is the color of its dashboard's towers
     * @param wizardID is the chosen wizard
     * @param numberPlayers number of players in the game
     */
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

    public String getNickname()
    {
        return nickname;
    }

    public int getID() {
        return ID;
    }

    /**
     *
     * @param wizardID identifier of the player's associate wizard
     */
    public void setWizardID(Wizard wizardID) {
        this.wizardID = wizardID;
    }

    public Wizard getWizardID()
    {
        return this.wizardID;
    }

    /**
     *
     * @param towerColor color of the towers associated to the player
     */
    public void setTowerColor(Tower towerColor)
    {
        this.towerColor = towerColor;
    }

    public Tower getTowerColor()
    {
        return towerColor;
    }

    /**
     * increases the number of coins of the player
     * @param addition number of coins to be added to each player
     */
    public void addCoins(int addition)
    {
        coinsAmount += addition;
    }

    /**
     * decreases the number of coins of the player
     * @param removal is the number of coins to be removed
     * @throws IllegalArgumentException if the coins to be removed are more than the actual coins
     */
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
    public int getCoinsAmount()
    {
        return coinsAmount;
    }
}