package it.polimi.ingsw.model.gameClasses;

import it.polimi.ingsw.model.components.ColoredDisc;
import it.polimi.ingsw.model.components.Player;
import it.polimi.ingsw.model.cards.Wizard;

import java.util.ArrayList;

public class GameClassExpert extends GameClass
{
    public GameClassExpert(String ID, int PlayerNumber, ArrayList<String> nicknames, ArrayList<Wizard> wizards) {
        super(ID, PlayerNumber, nicknames, wizards);


        for (Player player: players) {
            player.addCoins(1);
        }

        //piazzare 3 carte personaggio
    }

    public void EntranceToTables(int PlayerID, ColoredDisc student)
    {

        switch (players.get(PlayerID).myDashboard.MoveToTables(student))
        {
            case 3:
            case 6:
            case 9:
                players.get(PlayerID).addCoins(1);
                break;
            default:
                break;
        }
        evaluateProfessors(PlayerID, student);
    }

    public void useCard()
    {
        //cosa fare con le carte personaggio
    }

}
