package it.polimi.ingsw.model.gameClasses;

import it.polimi.ingsw.model.cards.EffectCard;
import it.polimi.ingsw.model.components.ColoredDisc;
import it.polimi.ingsw.model.components.Player;
import it.polimi.ingsw.model.cards.Wizard;

import java.util.ArrayList;

public class GameClassExpert extends GameClass
{
    protected ArrayList<EffectCard> ChosenCards;

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

    public void useCardEffect(int PlayerID, EffectCard card)
    {

        card.run();
        switch (card.getID()) {
            case MONK:

                break;
            case QUEEN:

                break;
            case LADY:

                break;
            case JOLLY:

                break;
            case CAVALIER:

                break;
            case LORD:

                break;
            case CENTAUR:

                break;
            case COOK:

                break;
            case VILLAIN:

                break;
            case MAGICIAN:

                break;
            case MUSICIAN:

                break;
            case BANDIT:

                break;
            default:
                break;
        }
        //cosa fare con le carte personaggio
    }

    public void endCardEffect(int PlayerID, EffectCard card)
    {
        card.end();
        switch (card.getID()) {
            case MONK:

                break;
            case QUEEN:

                break;
            case LADY:

                break;
            case JOLLY:

                break;
            case CAVALIER:

                break;
            case LORD:

                break;
            case CENTAUR:

                break;
            case COOK:

                break;
            case VILLAIN:

                break;
            case MAGICIAN:

                break;
            case MUSICIAN:

                break;
            case BANDIT:

                break;
            default:
                break;
        }
    }
}
