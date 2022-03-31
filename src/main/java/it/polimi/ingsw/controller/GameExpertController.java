package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.cards.Wizard;

import java.util.ArrayList;

public class GameExpertController extends GameController
{

    public GameExpertController(int playerNumber, Boolean expertMode, String ID, ArrayList<String> nicknames, ArrayList<Wizard> wizards)
    {
        super(playerNumber,expertMode, ID,nicknames, wizards);

    }

}
