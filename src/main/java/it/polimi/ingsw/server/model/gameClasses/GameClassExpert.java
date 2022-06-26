package it.polimi.ingsw.server.model.gameClasses;

import it.polimi.ingsw.server.model.cards.EffectCard;
import it.polimi.ingsw.server.model.cards.EffectName;
import it.polimi.ingsw.server.model.components.ColoredDisc;
import it.polimi.ingsw.server.model.components.Island;
import it.polimi.ingsw.server.model.components.Player;
import it.polimi.ingsw.server.model.cards.Wizard;
import it.polimi.ingsw.server.model.components.Tower;

import java.awt.*;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * this class extends the normal gameclass and adds functionalities needed to play the expert mode
 */
public class GameClassExpert extends GameClass
{
    protected ArrayList<EffectCard> ChosenCards;
    private ArrayList<Integer> extraInfluencePlayers = new ArrayList<Integer>();
    private ArrayList<ColoredDisc> cookColors = new ArrayList<ColoredDisc>();
    private ArrayList<Integer> villainContribution = new ArrayList<>();
    private ArrayList<Integer> extraMotherNatureMoves = new ArrayList<>();
    private boolean centaurEffect = false;


    /**
     * class constructor
     * @param ID is the game id
     * @param PlayerNumber can be 2 or 3
     * @param nicknames are the players' nicknames
     * @param wizards are the chosen wizards
     */
    public GameClassExpert(String ID, int PlayerNumber, ArrayList<String> nicknames, ArrayList<Wizard> wizards) {
        super(ID, PlayerNumber, nicknames, wizards);


        for (Player player: players) {
            player.addCoins(1);
        }

        //inizialization
        ChosenCards = new ArrayList<>();

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
                card.addStudent(bag.popRandom(),i);
            }
        }
    }

    /**
     * the only difference with the normal "EntranceToTables" method is that now we handle coins
     * @param PlayerID is the ID of the player whose dashboard is updated
     * @param index is the index of the chosen student to move
     */
    public void EntranceToTables(int PlayerID, int index)
    {
        ColoredDisc color = players.get(PlayerID).myDashboard.getEntranceSpots()[index];
        handleCoins(PlayerID,players.get(PlayerID).myDashboard.MoveToTables(index));
        evaluateProfessors(PlayerID, color);
    }

    /**
     * if the new students goes in position 3,6 or 9, the player earns a coin
     * @param PlayerID is the ID of the player who performs the action
     * @param position is the new student position in the tables
     */
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


    /**
     * the appropriate effect is performed
     * @param PlayerID is the ID of the player who plays the card
     * @param name is the name of the effectCard
     */
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

    /**
     * effect: moves a student from the card to an island
     * @param Island is the island where we put the student
     * @param index is the index of the student in the card
     */
    public void monkEffect(int Island, int index)
    {
        EffectName name = EffectName.MONK;
        ColoredDisc color = getCardByName(name).getStudents()[index];
        EffectCard card = getCardByName(name);
        card.removeStudent(index);
        card.addStudent(bag.popRandom(), index);
        getIslandById(Island).addStudent(color);
    }

    /**
     * effect: adds a student to the card
     * @param color
     */
    public void cookEffect(ColoredDisc color)
    {
        cookColors.add(color);
    }

    /**
     * effect: moves a student from the card to the tables
     * @param PlayerID is the ID of the player who plays the card
     * @param index is the index of the student in the card
     */
    public void queenEffect(int PlayerID, int index)
    {
        EffectName name = EffectName.QUEEN;
        ColoredDisc color = getCardByName(name).getStudents()[index];
        EffectCard card = getCardByName(name);
        card.removeStudent(index);
        handleCoins(PlayerID, players.get(PlayerID).myDashboard.addToTables(color));
        card.addStudent(bag.popRandom(), index);
        evaluateProfessors(PlayerID, color);
    }

    /**
     * effect: the selected island is set to prohibited
     * @param IslandID is the ID of the selected island
     * @throws InvalidParameterException if the island is already prohibited
     */
    public void ladyEffect(int IslandID) throws InvalidParameterException
    {
        if (getIslandById(IslandID).prohibited)
            throw new InvalidParameterException();
        else
            getIslandById(IslandID).prohibited = true;
    }

    /**
     * the function allows the player to switch up to 3 students from the card to his entrance
     * @param PlayerID
     * @param removeIndex
     * @param addIndex
     */
    public void jollyEffectCall(int PlayerID, int removeIndex, int addIndex)
    {
        EffectName name = EffectName.JOLLY;
        ColoredDisc cardColor = getCardByName(name).getStudents()[removeIndex];
        ColoredDisc entranceColor = players.get(PlayerID).myDashboard.getEntranceSpots()[addIndex]; ;
        EffectCard card = getCardByName(name);
        card.removeStudent(removeIndex);
        players.get(PlayerID).myDashboard.RemoveFromEntrance(removeIndex);
        players.get(PlayerID).myDashboard.AddToEntrance(cardColor, addIndex);
        card.addStudent(entranceColor, addIndex);
    }

    /**
     * effect: allows the player to switch up to 2 students between his entrance and his dining room
     * @param PlayerID is the ID of the player who plays the card
     * @param switchIndex is the index of the student that must be switched
     * @param tableColor identifies the table where the student in the entrance must be put
     */
    public void musicianEffect(int PlayerID, int switchIndex, ColoredDisc tableColor)
    {
        ColoredDisc entranceColor = players.get(PlayerID).myDashboard.getEntranceSpots()[switchIndex];
        players.get(PlayerID).myDashboard.MoveToTables(switchIndex);
        players.get(PlayerID).myDashboard.RemoveFromTables(tableColor);
        players.get(PlayerID).myDashboard.AddToEntrance(tableColor,switchIndex);
        evaluateProfessors(PlayerID, entranceColor);
        evaluateProfessors(PlayerID, tableColor);
    }

    /**
     * effect: removes from all the players dining rooms 3 or less students, putting back to the bag those students
     * @param color is the color of the students we want to remove
     */
    public void banditEffect(ColoredDisc color)
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
        players.stream().forEach(x->evaluateProfessors(players.indexOf(x), color));
    }

    /**
     * effect: we move mothernature to the chosen island
     * @param IslandID is the ID of the island where mothernature must be moved
     */
    public void lordEffect(int IslandID)
    {
        MoveMotherNature(getIslandById(IslandID));
    }

    public void endCardEffect()
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

            if (towers.length>0 && towers[0].equals(color) && centaurEffect == false)
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

        //temporary can be substituted by functional
        int index = -1;
        int max = 0;
        //for(int i=0 ; i < players.size(); i++)
        for(int i=0 ; i < PlayersInfluence.length; i++)
        {
            if(PlayersInfluence[i] > max)
            {
                index = i;
                max = PlayersInfluence[i];
            }
            else if(PlayersInfluence[i] == max)
            {
                index = -1;
            }
        }
        return index;

        /*
        //MIGHT RETURN -1
        return IntStream.range(0, PlayersInfluence.length)
                .filter(i -> Arrays.stream(PlayersInfluence).max().getAsInt() == PlayersInfluence[i])
                .findFirst() // first occurrence
                .orElse(-1);
         */
    }

    @Override
    protected void evaluateProfessors(int PlayerID, ColoredDisc student)
    {
        players.stream().forEach(x->{
            if (x.myDashboard.SittedStudents(student) == 0)
                x.myDashboard.professorSpots.remove(student);
        });
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
        if (moves==0) throw new RuntimeException();
        CurrentIsland = islands.get((islands.indexOf(CurrentIsland)+moves) % islands.size() );

        if (CurrentIsland.prohibited)
        {
            CurrentIsland.prohibited = false;
            getCardByName(EffectName.LADY).prohibitionCard++;
            return;
        }

        int InfluencePlayerIndex = EvaluateInfluence(CurrentIsland);
        if (InfluencePlayerIndex == -1)
            return;
        Player influencePlayer = players.get(InfluencePlayerIndex);
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

        //EXECUTION ORDER IS IMPORTANT
        Island rightIsland = islands.get((islands.indexOf(CurrentIsland)+1)%islands.size());

        if (rightIsland.getTowers().length>0 && rightIsland.getTowers()[0] == CurrentIsland.getTowers()[0])
        {
            Arrays.stream(rightIsland.getTowers()).forEach(x->CurrentIsland.AddTower(CurrentIsland.getTowers()[0]));
            CurrentIsland.addStudents(rightIsland.getStudents());
            CurrentIsland.addGraphicalIslands(rightIsland.getID());
            islands.remove(rightIsland);
        }

        /*
        Island leftIsland = islands.get((islands.indexOf(CurrentIsland)-1)%islands.size());

        if (leftIsland.getTowers().length>0 && leftIsland.getTowers()[0] == CurrentIsland.getTowers()[0])
        {
            Arrays.stream(leftIsland.getTowers()).forEach(x->CurrentIsland.AddTower(CurrentIsland.getTowers()[0]));
            CurrentIsland.addStudents(leftIsland.getStudents());
            CurrentIsland.addGraphicalIslands(leftIsland.getID());
            islands.remove(leftIsland);
        }*/

        Island leftIsland = islands.get((islands.indexOf(CurrentIsland)+islands.size()-1)%islands.size());

        if (leftIsland.getTowers().length>0 && leftIsland.getTowers()[0] == CurrentIsland.getTowers()[0])
        {
            Arrays.stream(CurrentIsland.getTowers()).forEach(x->leftIsland.AddTower(leftIsland.getTowers()[0]));
            leftIsland.addStudents(CurrentIsland.getStudents());
            CurrentIsland.getGraphicalIslands().stream().forEach(x->leftIsland.addGraphicalIslands(x));
            islands.remove(CurrentIsland);
            CurrentIsland = leftIsland;
        }

        /*
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

        Island leftIsland = islands.get((islands.indexOf(CurrentIsland)+islands.size()-1)%islands.size());

        if (leftIsland.getTowers().length>0 && leftIsland.getTowers()[0] == CurrentIsland.getTowers()[0])
        {
            Arrays.stream(leftIsland.getTowers()).forEach(x->CurrentIsland.AddTower(CurrentIsland.getTowers()[0]));
            CurrentIsland.addStudents(leftIsland.getStudents());
            CurrentIsland.addGraphicalIslands(leftIsland.getID());
            islands.remove(leftIsland);
        }*/
    }

    /**
     * method used in the lordEffect
     * @param chosenIsland is the island where mothernature is moved
     * @throws RuntimeException if the player who has the influence has not enough towers
     */
    private void MoveMotherNature(Island chosenIsland) throws RuntimeException
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
        //EXECUTION ORDER IS IMPORTANT
        Island rightIsland = islands.get((islands.indexOf(CurrentIsland)+1)%islands.size());

        if (rightIsland.getTowers().length>0 && rightIsland.getTowers()[0] == CurrentIsland.getTowers()[0])
        {
            Arrays.stream(rightIsland.getTowers()).forEach(x->CurrentIsland.AddTower(CurrentIsland.getTowers()[0]));
            CurrentIsland.addStudents(rightIsland.getStudents());
            rightIsland.getGraphicalIslands().stream().forEach(x->CurrentIsland.addGraphicalIslands(x));
            islands.remove(rightIsland);
        }

        /*
        Island leftIsland = islands.get((islands.indexOf(CurrentIsland)-1)%islands.size());

        if (leftIsland.getTowers().length>0 && leftIsland.getTowers()[0] == CurrentIsland.getTowers()[0])
        {
            Arrays.stream(leftIsland.getTowers()).forEach(x->CurrentIsland.AddTower(CurrentIsland.getTowers()[0]));
            CurrentIsland.addStudents(leftIsland.getStudents());
            CurrentIsland.addGraphicalIslands(leftIsland.getID());
            islands.remove(leftIsland);
        }*/

        Island leftIsland = islands.get((islands.indexOf(CurrentIsland)+islands.size()-1)%islands.size());

        if (leftIsland.getTowers().length>0 && leftIsland.getTowers()[0] == CurrentIsland.getTowers()[0])
        {
            Arrays.stream(CurrentIsland.getTowers()).forEach(x->leftIsland.AddTower(leftIsland.getTowers()[0]));
            leftIsland.addStudents(CurrentIsland.getStudents());
            CurrentIsland.getGraphicalIslands().stream().forEach(x->leftIsland.addGraphicalIslands(x));
            islands.remove(CurrentIsland);
            CurrentIsland = leftIsland;
        }

        CurrentIsland = temp;
    }

    public int playerMaxMoves(int playerID)
    {
        return playerMaxMoves[playerID] + (extraMotherNatureMoves.contains(playerID)?2:0);
    }
}
