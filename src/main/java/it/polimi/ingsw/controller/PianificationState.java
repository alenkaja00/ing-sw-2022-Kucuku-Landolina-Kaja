package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.components.Cloud;
import it.polimi.ingsw.model.components.Player;
import it.polimi.ingsw.model.gameClasses.GameClass;
import it.polimi.ingsw.model.gameClasses.GameClassExpert;

import java.util.ArrayList;

public class PianificationState extends AbstractState
{
    public PianificationState(GameClass game)
    {

    }

    public PianificationState(GameClassExpert game)
    {



    }

    public void turn(Player player, GameClass game)
    {
        //1)
        game.BagToCloud();

        //2)

    }



}
