package it.polimi.ingsw.model.gameClasses;

import it.polimi.ingsw.model.cards.EffectCard;
import it.polimi.ingsw.model.cards.EffectName;
import it.polimi.ingsw.model.components.ColoredDisc;
import it.polimi.ingsw.model.components.Island;
import it.polimi.ingsw.model.components.Player;
import it.polimi.ingsw.model.cards.Wizard;
import it.polimi.ingsw.model.components.Tower;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Collectors;

public class GameClassExpert extends GameClass
{
    protected ArrayList<EffectCard> ChosenCards;
    private ArrayList<Integer> extraInfluencePlayers = new ArrayList<Integer>();
    private ArrayList<ColoredDisc> cookColors = new ArrayList<ColoredDisc>();
    private ArrayList<Integer> villainContribution = new ArrayList<>();
    private ArrayList<Integer> extraMotherNatureMoves = new ArrayList<>();
    private boolean centaurEffect = false;


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
                extraInfluencePlayers.add(PlayerID);
                break;
            case CENTAUR:
                centaurEffect = true;
                break;
            case VILLAIN:
                villainContribution.add(PlayerID);
                break;
            case MAGICIAN:
                extraMotherNatureMoves.add(PlayerID);
                break;
            default:
                break;
        }
        //cosa fare con le carte personaggio
    }

    public void monkEffect(EffectName name, ColoredDisc color, int Island)
    {
        EffectCard card = getCardByName(name);
        card.removeStudent(color);
        card.addStudent(bag.popRandom());
        getIslandById(Island).addStudent(color);
    }

    public void cookEffect(ColoredDisc color)
    {
        cookColors.add(color);
    }

    public void queenEffect(int PlayerID, EffectName name, ColoredDisc color)
    {
        EffectCard card = getCardByName(name);
        card.removeStudent(color);
        handleCoins(PlayerID, players.get(PlayerID).myDashboard.addToTables(color));
        card.addStudent(bag.popRandom());
        evaluateProfessors(PlayerID, color);
    }

    public void ladyEffect(int IslandID) throws InvalidParameterException
    {
        if (getIslandById(IslandID).prohibited)
            throw new InvalidParameterException();
        else
            getIslandById(IslandID).prohibited = true;
    }

    /**
     *
     * @param PlayerID
     * @param name
     * @param cardColor
     * @param entranceColor
     * the function allows the player to switch up to 3 students from the card to his entrance
     */
    public void jollyEffect(int PlayerID, EffectName name, ColoredDisc cardColor, ColoredDisc entranceColor)
    {
        EffectCard card = getCardByName(name);
        card.removeStudent(cardColor);
        players.get(PlayerID).myDashboard.RemoveFromEntrance(entranceColor);
        players.get(PlayerID).myDashboard.AddToEntrance(cardColor);
        card.addStudent(entranceColor);
    }

    /**
     *
     * @param PlayerID
     * @param entranceColor
     * @param tableColor
     * the function allows the player to switch up to 2 students between his entrance and his dining room
     */
    public void musicianEffect(int PlayerID, ColoredDisc entranceColor, ColoredDisc tableColor)
    {
        players.get(PlayerID).myDashboard.MoveToTables(entranceColor);
        players.get(PlayerID).myDashboard.RemoveFromTables(tableColor);
        players.get(PlayerID).myDashboard.AddToEntrance(tableColor);
        evaluateProfessors(PlayerID, entranceColor);
        evaluateProfessors(PlayerID, tableColor);
    }

    /**
     *
     * @param PlayerID
     * @param color
     * this effect removes from all the players dining rooms 3 or less students, putting back to the bag those students
     */
    public void banditEffect(int PlayerID, ColoredDisc color)
    {
        for(Player player:players)
        {
            int dimColor = player.myDashboard.SittedStudents(color);
            if(dimColor >= 3)
            {
                player.myDashboard.RemoveFromTables(color, 3);
                bag.addToBag(color, 3);
            }
            else{
                player.myDashboard.RemoveFromTables(color, dimColor);
                bag.addToBag(color, dimColor);
            }
        }
    }

    public void lordEffect(int IslandID)
    {
        MoveMotherNature(getIslandById(IslandID));
    }

    public void endCardEffect(int PlayerID, EffectCard card)
    {
        extraInfluencePlayers.removeAll(extraInfluencePlayers);
        cookColors.removeAll(cookColors);
        villainContribution.removeAll(villainContribution);
        extraMotherNatureMoves.removeAll(extraMotherNatureMoves);
        centaurEffect = false;
    }

    private EffectCard getCardByName(EffectName name)
    {
        return ChosenCards.stream().filter(x->x.getID()==name).collect(Collectors.toList()).get(0);
    }

    @Override
    public int EvaluateInfluence(Island island)
    {

        Tower[] towers = island.getTowers();
        HashMap<ColoredDisc,Integer> students = island.getStudents();
        int PlayersInfluence[] = new int[PlayerNumber];
        Arrays.fill(PlayersInfluence,0);
        for (Player player : players)
        {
            int ID = player.getID();
            Tower color = player.getTowerColor();

            if (towers[0].equals(color) && centaurEffect == false)
            {
                PlayersInfluence[ID]+= towers.length;
            }
            //effect card cavalier
            if(extraInfluencePlayers.contains(player.getID()))
                PlayersInfluence[player.getID()]+=2;
        }
        for(ColoredDisc color:  students.keySet())
        {
            //effect card cook
            if(cookColors.contains(color))
                continue;
            for(int i=0; i<PlayerNumber;i++)
            {
                if(players.get(i).myDashboard.professorSpots.contains(color))
                {
                    PlayersInfluence[i]+= students.get(color);
                }
            }
        }
        return Arrays.asList(PlayersInfluence).indexOf(Arrays.stream(PlayersInfluence).max());
    }

    @Override
    protected void evaluateProfessors(int PlayerID, ColoredDisc student)
    {
        Player lastPlayer = players.get(PlayerID);
        for (Player player: players)
        {
            if (lastPlayer.myDashboard.SittedStudents(student) + (villainContribution.contains(PlayerID)?1:0) <= player.myDashboard.SittedStudents(student) && lastPlayer != player)
                return;
        }
        for (Player player: players)
        {
            player.myDashboard.professorSpots.remove(student);
        }
        lastPlayer.myDashboard.professorSpots.add(student);
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

    public void MoveMotherNature(Island chosenIsland) throws RuntimeException
    {
        Island temp = CurrentIsland;
        CurrentIsland = chosenIsland;

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

        CurrentIsland = temp;
    }

    public int playerMaxMoves(int playerID)
    {
        return playerMaxMoves[playerID] + (extraMotherNatureMoves.contains(playerID)?2:0);
    }
}
