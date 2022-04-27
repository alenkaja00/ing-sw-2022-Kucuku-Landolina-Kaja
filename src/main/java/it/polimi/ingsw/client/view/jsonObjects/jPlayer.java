package it.polimi.ingsw.client.view.jsonObjects;

import it.polimi.ingsw.server.model.cards.Wizard;
import it.polimi.ingsw.server.model.components.Tower;

public class jPlayer{
    public int ID;
    public String nickname;
    public Tower towerColor;
    public Wizard wizardID;
    public int coinsAmount;
    public jDashboard myDashboard;
    public jDeck deck;
    public boolean online;
}