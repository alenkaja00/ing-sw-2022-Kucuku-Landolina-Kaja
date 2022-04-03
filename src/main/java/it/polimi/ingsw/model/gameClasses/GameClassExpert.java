package it.polimi.ingsw.model.gameClasses;

import it.polimi.ingsw.model.cards.EffectCard;
import it.polimi.ingsw.model.cards.EffectName;
import it.polimi.ingsw.model.components.ColoredDisc;
import it.polimi.ingsw.model.components.Island;
import it.polimi.ingsw.model.components.Player;
import it.polimi.ingsw.model.cards.Wizard;

import javax.swing.*;
import java.io.FileOutputStream;
import java.security.InvalidParameterException;
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
        handleCoins(PlayerID,players.get(PlayerID).myDashboard.MoveToTables(student));
        evaluateProfessors(PlayerID, student);
    }

    private void handleCoins(int PlayerID, int position)
    {
        switch (position)
        {
            case 3:
            case 6:
            case 9:
                players.get(PlayerID).addCoins(1);
                break;
            default:
                break;
        }
    }

    public void useCardEffect(int PlayerID, EffectName name)
    {
        EffectCard card = getCardByName(name);
        switch (card.getID()) {
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

    public void monkEffect(int PlayerID, EffectName name, ColoredDisc color, int Island)
    {
        EffectCard card = getCardByName(name);
        card.removeStudent(color);
        card.addStudent(bag.popRandom());
        getIslandById(Island).addStudent(color);
    }

    public void queenEffect(int PlayerID, EffectName name, ColoredDisc color)
    {
        EffectCard card = getCardByName(name);
        card.removeStudent(color);
        handleCoins(PlayerID, players.get(PlayerID).myDashboard.addToTables(color));
        card.addStudent(bag.popRandom());
        evaluateProfessors(PlayerID, color);
    }

    public void ladyEffect(int IslandID, EffectName name) throws InvalidParameterException
    {
        if (getIslandById(IslandID).prohibited)
            throw new InvalidParameterException();
        else
            getIslandById(IslandID).prohibited = true;
    }

    public void jollyEffect(int PlayerID, EffectName name, ColoredDisc cardColor, ColoredDisc entranceColor)
    {
        EffectCard card = getCardByName(name);
        card.removeStudent(cardColor);
        players.get(PlayerID).myDashboard.RemoveFromEntrance(entranceColor);
        players.get(PlayerID).myDashboard.AddToEntrance(cardColor);
        card.addStudent(entranceColor);
    }

    public void endCardEffect(int PlayerID, EffectCard card)
    {
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

    private EffectCard getCardByName(EffectName name)
    {
        return ChosenCards.stream().filter(x->x.getID()==name).collect(Collectors.toList()).get(0);
    }

    /** returns true if game ends for lack of towers or maximum number of islands*/
    @Override
    public void MoveMotherNature(int moves) throws RuntimeException
    {
        CurrentIsland = islands.get((islands.indexOf(CurrentIsland)+moves) % islands.size() );

        if (CurrentIsland.prohibited)
        {
            CurrentIsland.prohibited = false;
            getCardByName(EffectName.LADY).prohibitionCard++;
            return;
        }

        Player influencePlayer = players.get(EvaluateInfluence(CurrentIsland));
        if (CurrentIsland.getTowers().length == 0)
        {
            CurrentIsland.AddTower(influencePlayer.getTowerColor());
            influencePlayer.myDashboard.RemoveTower();
        }
        else
        {
            if (influencePlayer.getTowerColor() != CurrentIsland.getTowers()[0])
            {
                if (influencePlayer.myDashboard.TowerNumber()< CurrentIsland.getTowers().length)
                    throw new RuntimeException();

                for (Player player: players)
                {
                    if (player.getTowerColor() == CurrentIsland.getTowers()[0])
                    {
                        for (int i=0; i<CurrentIsland.getTowers().length; i++)
                            player.myDashboard.AddTower();
                    }
                }

                CurrentIsland.changeTowerColor(influencePlayer.getTowerColor());

                for (int i=0; i<CurrentIsland.getTowers().length; i++)
                    influencePlayer.myDashboard.RemoveTower();
            }
        }

        //islands
        //EXECUTION ORDER IS IMPORTANT
        Island rightIsland = islands.get((islands.indexOf(CurrentIsland)+1)%islands.size());

        if (rightIsland.getTowers().length>0 && rightIsland.getTowers()[0] == CurrentIsland.getTowers()[0])
        {
            Arrays.stream(rightIsland.getTowers()).forEach(x->CurrentIsland.AddTower(CurrentIsland.getTowers()[0]));
            CurrentIsland.addStudents(rightIsland.getStudents());
            CurrentIsland.addGraphicalIslands(rightIsland.getID());
            islands.remove(rightIsland);
        }

        Island leftIsland = islands.get((islands.indexOf(CurrentIsland)-1)%islands.size());

        if (leftIsland.getTowers().length>0 && leftIsland.getTowers()[0] == CurrentIsland.getTowers()[0])
        {
            Arrays.stream(leftIsland.getTowers()).forEach(x->CurrentIsland.AddTower(CurrentIsland.getTowers()[0]));
            CurrentIsland.addStudents(leftIsland.getStudents());
            CurrentIsland.addGraphicalIslands(leftIsland.getID());
            islands.remove(leftIsland);
        }
    }

}
