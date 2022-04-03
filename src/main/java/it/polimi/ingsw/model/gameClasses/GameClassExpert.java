package it.polimi.ingsw.model.gameClasses;

import it.polimi.ingsw.model.cards.EffectCard;
import it.polimi.ingsw.model.cards.EffectName;
import it.polimi.ingsw.model.components.ColoredDisc;
import it.polimi.ingsw.model.components.Player;
import it.polimi.ingsw.model.cards.Wizard;

import javax.swing.*;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class GameClassExpert extends GameClass
{
    protected ArrayList<EffectCard> ChosenCards;

    public GameClassExpert(String ID, int PlayerNumber, ArrayList<String> nicknames, ArrayList<Wizard> wizards) {
        super(ID, PlayerNumber, nicknames, wizards);


        for (Player player: players) {
            player.addCoins(1);
        }

        //piazzare 3 carte personaggio e inizializzare
        ArrayList<EffectName> randomCards = (ArrayList<EffectName>) Arrays.stream(EffectName.values()).collect(Collectors.toList());
        Collections.shuffle(randomCards);
        for (int i = 0; i<3; i++)
        {
            ChosenCards.add(new EffectCard(randomCards.get(i)));
        }
        for (EffectCard card:ChosenCards)
        {
            int maxS  = 0;
            switch (card.getID())
            {
                case MONK:
                case QUEEN:
                    maxS = 4;
                    break;
                case JOLLY:
                    maxS = 6;
                    break;
                default:
                    break;
            }
            for (int i = 0; i<maxS; i++)
            {
                card.addStudent(bag.popRandom());
            }
        }
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

    public void monkEffect(int PlayerID, EffectCard card, ColoredDisc color, int Island)
    {
        card.removeStudent(color);
        card.addStudent(bag.popRandom());
        getIslandById(Island).addStudent(color);
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
