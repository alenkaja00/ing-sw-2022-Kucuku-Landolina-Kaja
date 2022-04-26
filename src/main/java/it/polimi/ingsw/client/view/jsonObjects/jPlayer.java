package it.polimi.ingsw.client.view.jsonObjects;

import it.polimi.ingsw.server.model.cards.Wizard;
import it.polimi.ingsw.server.model.components.Tower;

public class jPlayer{
    private int ID;
    private String nickname;
    private Tower towerColor;
    private Wizard wizardID;
    private int coinsAmount;
    public jDashboard myDashboard;
    public jDeck deck;
    public boolean online;
}