package it.polimi.ingsw.server.model.gameClasses;

import it.polimi.ingsw.server.model.cards.Wizard;
import it.polimi.ingsw.server.model.components.*;

import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.Collectors;

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

    //protected Player PlayerRound;
    //protected HashMap<Integer, Integer> RoundOrder = new HashMap<Integer, Integer>();
    //public int firstPlayer;


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

        /*
        //select first player of the game
        firstPlayer = selectRandomPlayer(1);

        for (int i=0; i<PlayerNumber; i++)
            RoundOrder.put(i, (new Random()).nextInt(PlayerNumber-1));

        deprecated*/

    }

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



    public int selectRandomPlayer(int flag)
    {
        switch(flag){
            case 1: return (new Random()).nextInt(PlayerNumber-1);
            default: return 0;
        }
    }


    public void CloudToEntrance(int CloudIndex, int PlayerID)
    {
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

    public void BagToCloud()
    {
        for(Cloud cloud: clouds)
        {
            for(int i=0;i<clouds.get(0).getCloudCapacity();i++) {
                cloud.AddStudent(bag.popRandom());
            }
        }
        setClouds(clouds);
    }

    public void EntranceToTables(int PlayerID, int index)
    {
        ColoredDisc color = players.get(PlayerID).myDashboard.getEntranceSpots()[index];
        players.get(PlayerID).myDashboard.MoveToTables(index);
        evaluateProfessors(PlayerID, color);
    }

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


    public void EntranceToIsland(int PlayerID, int IslandID,int index)
    {
        ColoredDisc color = players.get(PlayerID).myDashboard.getEntranceSpots()[index];
        getIslandById(IslandID).addStudent(color);
        players.get(PlayerID).myDashboard.RemoveFromEntrance(index);
    }


    public void useHelperCard(int playerID, int cardNumber) throws InvalidKeyException
    {
        //controllare che non giochi le stesse carte gia giocate nel turno
        playerMaxMoves[playerID] = players.get(playerID).deck.returnCard(cardNumber).getMaxMoves();
        players.get(playerID).deck.useCard(cardNumber);

        //added
        playerCardValue[playerID] = cardNumber;
    }





    public int playerMaxMoves(int playerID)
    {
        return playerMaxMoves[playerID];
    }

    /** returns true if game ends for lack of towers or maximum number of islands*/
    public void MoveMotherNature(int moves) throws RuntimeException
    {
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

        Island leftIsland = islands.get((islands.indexOf(CurrentIsland)-1)%islands.size());

        if (leftIsland.getTowers().length>0 && leftIsland.getTowers()[0] == CurrentIsland.getTowers()[0])
        {
            Arrays.stream(CurrentIsland.getTowers()).forEach(x->leftIsland.AddTower(leftIsland.getTowers()[0]));
            leftIsland.addStudents(CurrentIsland.getStudents());
            CurrentIsland.getGraphicalIslands().stream().forEach(x->leftIsland.addGraphicalIslands(x));
            islands.remove(CurrentIsland);
            CurrentIsland = leftIsland;
        }
    }

    /** Returns the playerID of the player who has influence on that island*/
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
        //MIGHT RETURN -1
        return Arrays.asList(PlayersInfluence).indexOf(Arrays.stream(PlayersInfluence).max());
    }


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
                System.out.println("player "+player.getNickname() +" has no towers");
                return true;
            }
        }

        return false;
    }

    public Boolean inslandsGameEnded()
    {
        // 2)

        if(NumOfIslands <= 3)
        {
            System.out.println("There are less than 3 islands");
            return true;
        }

        return false;
    }
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
            System.out.println("The bag size is 0");
            return true;
        }
        for(Player player: players)
        {
            if(player.deck.returnUnused().size()==0)
            {
                System.out.println("player "+player.getNickname()+"has no cards");
                return true;
            }
        }

        return false;

    }

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

    public ArrayList<Island> getIslands() {
        return (ArrayList<Island>) islands.clone();
    }

    public int getPlayerNumber() {
        return PlayerNumber;
    }

    public String getGameID() {
        return GameID;
    }

    public ArrayList<String> getNicknames() {
        return (ArrayList<String>) nicknames.clone();
    }

    public ArrayList<Player> getPlayers() {
        return (ArrayList<Player>) players.clone();
    }

    public ArrayList<ColoredDisc> getStudentBag() {
        return (ArrayList<ColoredDisc>) StudentBag.clone();
    }

    public void setClouds(ArrayList<Cloud> clouds) {
        this.clouds = clouds;
    }

    public void setGameID(String gameID) {
        GameID = gameID;
    }

    public void setIslands(ArrayList<Island> islands) {
        this.islands = islands;
    }

    public void setNicknames(ArrayList<String> nicknames) {
        this.nicknames = nicknames;
    }


    public void setPlayerNumber(int playerNumber) {
        PlayerNumber = playerNumber;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setStudentBag(ArrayList<ColoredDisc> studentBag) {
        StudentBag = studentBag;
    }



}
