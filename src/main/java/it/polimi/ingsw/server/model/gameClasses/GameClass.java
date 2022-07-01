package it.polimi.ingsw.server.model.gameClasses;

import it.polimi.ingsw.server.Logger;
import it.polimi.ingsw.server.model.cards.Wizard;
import it.polimi.ingsw.server.model.components.*;

import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * this class contains all the data needed to describe a game
 */
public class GameClass {

    protected StudentBag bag;
    protected String GameID ;
    protected int PlayerNumber;
    protected ArrayList<Island> islands ;
    protected ArrayList<ColoredDisc> StudentBag ;
    protected ArrayList<Cloud> clouds;
    protected ArrayList<Player> players;
    protected int[] playerMaxMoves;

    //aggiunta
    protected int[] playerCardValue;

    protected ArrayList<String> nicknames;
    protected int NumOfIslands = 12;
    protected Island CurrentIsland;


    /**
     * creates the islands,clouds arrays, creates the player objects with their nicknames and wizards
     * @param ID uniquely identifies the game
     * @param PlayerNumber can be 2 or 3
     * @param nicknames are the chosen nicknames for the players
     * @param wizards are the chosen wizards for the players
     */
    public GameClass(String ID, int PlayerNumber, ArrayList<String> nicknames, ArrayList<Wizard> wizards)
    {
        this.GameID = ID;
        this.PlayerNumber = PlayerNumber;
        this.nicknames = nicknames;


        //---Start of setup---

        islands = new ArrayList<>();
        for(int i=0;i<NumOfIslands;i++)
        {
            islands.add(new Island(i));
        }

        //Random random = new Random();
        this.CurrentIsland = islands.get((new Random()).nextInt(NumOfIslands));

        //initialize islands with 2 students for each color, no students on mother nature island and opposite island
        bag = new StudentBag(); //add 10 students, 2 for each color

        ArrayList<ColoredDisc> initialStudents = bag.pop2forColor();

        for (Island myIsland: islands)
        {
            if (myIsland!= CurrentIsland && (CurrentIsland.getID() + NumOfIslands/2) % NumOfIslands != myIsland.getID())
            {
                myIsland.addStudent(initialStudents.remove(0));
            }
        }

        clouds = new ArrayList<>();
        players = new ArrayList<>();
        for(int i=0;i<PlayerNumber;i++)
        {
            clouds.add(new Cloud(PlayerNumber+1));  // Cloud Capacity
            players.add(new Player(i,nicknames.get(i), Tower.values()[i], wizards.get(i),PlayerNumber));
        }
        //players = (ArrayList<Player>) Collections.unmodifiableCollection(players); //this way PlayerID and players Indexes will always be the same
        playerMaxMoves = new int[PlayerNumber];
        playerCardValue = new int[PlayerNumber];


        // filling the entrance of each player for the first time with 7 or 9 students
        for(Player player: players)
        {
            bagToEntrance(player.myDashboard.maxEntrance,player);
        }


    }

    /**
     * this method moves the students from the bag to the player's entrance
     * @param number is the number of students to move
     * @param player is the player whose entrance has to be filled
     * @throws IndexOutOfBoundsException if the insertion will exceed the max entrance capacity
     */
    public void bagToEntrance(int number, Player player) throws IndexOutOfBoundsException
    {
        int entranceindex = 0;
        for(int i=0;i<number;i++){
            while(player.myDashboard.getEntranceSpots()[entranceindex]!= null)
            {
                entranceindex++;
            }
            if(entranceindex > player.myDashboard.maxEntrance)
            {
                throw new IndexOutOfBoundsException();
            }
            player.myDashboard.AddToEntrance(bag.popRandom(),entranceindex);
            entranceindex ++;
        }
    }


    /**
     moves all the students from cloud to the player's entrance
     @param CloudIndex is the index of the cloud to empty in the clouds array
     @param PlayerID is the ID of the player whose entrance has to be filled
     */
    public void CloudToEntrance(int CloudIndex, int PlayerID)
    {
        if (clouds.get(CloudIndex).getStudents().size()==0)
            throw new InvalidParameterException();

        ArrayList<ColoredDisc> students = clouds.get(CloudIndex).removeAll();
        int entranceindex = 0;
        for(ColoredDisc student : students)
        {
            while(players.get(PlayerID).myDashboard.getEntranceSpots()[entranceindex]!= null)
            {
                entranceindex++;
            }
            if(entranceindex > players.get(PlayerID).myDashboard.maxEntrance)
            {
                throw new IndexOutOfBoundsException();
            }
            players.get(PlayerID).myDashboard.AddToEntrance(student,entranceindex);
            entranceindex ++;
        }
    }

    /**
     * method used to refill all the clouds of new students
     */
    public void BagToCloud()
    {
        for(Cloud cloud: clouds)
        {
            if (cloud.getStudents().size()==0)
            {
                for(int i=0;i<clouds.get(0).getCloudCapacity();i++) {
                    cloud.AddStudent(bag.popRandom());
                }
            }
        }
        setClouds(clouds);
    }

    /**
     * moves the student of index "index" from entrance to tables
     * the student will be moved in the correct table depending on its color
     * after the moving, professors' ownership is updated using the "evaluateProfessors" method
     * @param PlayerID is the ID of the player whose dashboard is updated
     * @param index is the index of the chosen student to move
     */
    public void EntranceToTables(int PlayerID, int index)
    {
        ColoredDisc color = players.get(PlayerID).myDashboard.getEntranceSpots()[index];
        players.get(PlayerID).myDashboard.MoveToTables(index);
        evaluateProfessors(PlayerID, color);
    }

    /**
     * checks if the player identified with "PlayerID" has the right to claim the professor
     * @param PlayerID is the ID of the new candidate owner
     * @param student is the color of the professor to check
     */
    protected void evaluateProfessors(int PlayerID, ColoredDisc student)
    {
        Player lastPlayer = players.get(PlayerID);
        for (Player player: players)
        {
            if (lastPlayer.myDashboard.SittedStudents(student) <= player.myDashboard.SittedStudents(student) && lastPlayer != player)
                return;
        }
        for (Player player: players)
        {
            player.myDashboard.professorSpots.remove(student);
        }
        lastPlayer.myDashboard.professorSpots.add(student);
    }

    /**
     * this method moves a student from the dashboard's entrance at position "index" to the chosen island
     * @param PlayerID is the ID of the player who performs the action
     * @param IslandID is the ID of the chosen island
     * @param index is the index of the chosen student in the dashboard's entrance
     */
    public void EntranceToIsland(int PlayerID, int IslandID,int index)
    {
        ColoredDisc color = players.get(PlayerID).myDashboard.getEntranceSpots()[index];
        getIslandById(IslandID).addStudent(color);
        players.get(PlayerID).myDashboard.RemoveFromEntrance(index);
    }

    /**
     * this method handles the selection of the helper card in the pianification phase
     * it sets the selected card to used
     * it checks that the player doesn't choose a card that was already selected in the same turn
     * @param playerID is the ID of the player who performs the action
     * @param cardNumber is the chosen card ID
     * @throws InvalidKeyException if the card was already selected in that turn
     */
    public void useHelperCard(int playerID, int cardNumber) throws InvalidKeyException
    {
        //controllare che non giochi le stesse carte gia giocate nel turno
        players.get(playerID).deck.useCard(cardNumber);
        playerMaxMoves[playerID] = players.get(playerID).deck.returnCard(cardNumber).getMaxMoves();

        //added
        playerCardValue[playerID] = cardNumber;
    }



    public int playerMaxMoves(int playerID)
    {
        return playerMaxMoves[playerID];
    }


    /**
     * this method moves mother nature N steps where N="moves"
     * it also calls the "EvaluateInfluence" method to entablish who has the influence on the current Island
     * @param moves is the number of steps mother nature has to take
     * @throws RuntimeException if the player has not the sufficient amount of towers
     */
    public void MoveMotherNature(int moves) throws RuntimeException
    {
        if (moves==0) throw new RuntimeException();
        CurrentIsland = islands.get((islands.indexOf(CurrentIsland)+moves) % islands.size() );

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
            //CurrentIsland.addGraphicalIslands(rightIsland.getID());
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
    }

    /**
     *  Returns the playerID of the player who has influence on that island
     * @param island is the ID of the chosen island
     */
    public int EvaluateInfluence(Island island)
    {
        Tower [] towers = island.getTowers();
        HashMap<ColoredDisc,Integer> students = island.getStudents();
        int PlayersInfluence[] = new int[PlayerNumber];
        Arrays.fill(PlayersInfluence,0);
        for (Player player : players)
        {
            int ID = player.getID();
            Tower color = player.getTowerColor();

            if (towers.length>0 && towers[0].equals(color))
            {
                PlayersInfluence[ID]+= towers.length;
            }
        }
        for(ColoredDisc color:  students.keySet())
        {
            for(int i=0; i<PlayerNumber;i++)
            {
                if(players.get(i).myDashboard.professorSpots.contains(color))
                {
                    PlayersInfluence[i]+= students.get(color);
                }
            }
        }

        int index = -1;
        int max = 0;
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


    /**
     * if a player uses his last tower, the game has to end
     * @return if the game is finished or not
     */
    public Boolean towerGameEnded()
    {
        /*
        game ends when
            1) player uses his last tower
            2) <=3 groups of islands
            3) empty students bag or 0 assistant cards (end of round)
        */

        // 1)
        for(Player player : players)
        {
            if(player.myDashboard.TowerNumber()==0)
            {
                Logger.storeLog("Player "+player.getNickname() +" has no towers");
                return true;
            }
        }

        return false;
    }

    /**
     * @return true if the number of islands is less then three
     */
    public Boolean islandsGameEnded()
    {
        // 2)

        if(islands.size() <= 3)
        {
            Logger.storeLog("There are less than 3 islands");
            return true;
        }

        return false;
    }

    /**
     * @return true if either the bag is empty or some player has 0 assistant cards
     */
    public Boolean roundGameEnded()
    {
        /*
        game ends when
            1) player uses his last tower
            2) <=3 groups of islands
            3) empty students bag or 0 assistant cards (end of round)
        */

        // 3)

        if(bag.getSize()==0)
        {
            Logger.storeLog("The bag size is 0");
            return true;
        }
        for(Player player: players)
        {
            if(player.deck.returnUnused().size()==0)
            {
                Logger.storeLog("Player "+player.getNickname()+"has no cards");
                return true;
            }
        }
        return false;
    }

    /**
     * @return the ID of the winner player
     */
    public int lessTowersMoreProfessors()
    {
        int winnerIndex = 0;

        for (int i = 1; i<PlayerNumber; i++)
        {
            if (players.get(i).myDashboard.TowerNumber() < players.get(winnerIndex).myDashboard.TowerNumber()
                || (players.get(i).myDashboard.TowerNumber() == players.get(winnerIndex).myDashboard.TowerNumber() && players.get(i).myDashboard.professorSpots.size() > players.get(winnerIndex).myDashboard.professorSpots.size()))
                winnerIndex = i;
        }

        return winnerIndex;
    }

    protected Island getIslandById(int Island)
    {
        return islands.stream().filter(x->x.getID() == Island).collect(Collectors.toList()).get(0);
    }

    public ArrayList<Cloud> getClouds() {
        return (ArrayList<Cloud>) clouds.clone();
    }

    public ArrayList<Player> getPlayers() {
        return (ArrayList<Player>) players.clone();
    }

    public void setClouds(ArrayList<Cloud> clouds) {
        this.clouds = clouds;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

}
