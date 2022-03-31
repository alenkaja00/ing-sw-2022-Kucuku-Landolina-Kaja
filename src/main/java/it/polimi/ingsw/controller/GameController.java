package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.cards.Wizard;
import it.polimi.ingsw.model.gameClasses.GameClass;
import it.polimi.ingsw.model.gameClasses.GameClassExpert;

import java.util.ArrayList;

public class GameController
{

    //private SocketController socketController  = new SocketController();

    public GameController(int playerNumber, Boolean expertMode, String ID, ArrayList<String> nicknames, ArrayList<Wizard> wizards)
    {
        if(expertMode)
        {
            GameClassExpert game = new GameClassExpert(ID,playerNumber,nicknames,wizards);
        }

        else
        {

            GameClass game = new GameClass(ID,playerNumber,nicknames,wizards);
        }


    }

}
