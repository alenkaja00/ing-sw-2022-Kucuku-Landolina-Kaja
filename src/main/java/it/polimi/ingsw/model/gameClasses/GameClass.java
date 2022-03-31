package it.polimi.ingsw.model.gameClasses;

import it.polimi.ingsw.model.cards.AbstractEffect;
import it.polimi.ingsw.model.cards.Wizard;
import it.polimi.ingsw.model.components.*;

import java.security.InvalidKeyException;
import java.util.*;

public class GameClass {

    protected it.polimi.ingsw.model.components.StudentBag bag;
    protected String GameID ;
    protected int PlayerNumber;
    protected ArrayList<Island> islands ;
    protected ArrayList<ColoredDisc> StudentBag ;
    protected ArrayList<Cloud> clouds;
    protected ArrayList<Player> players;
    protected int[] playerMaxMoves;

    //aggiunta
    protected int[] playerCardValue;

    protected Player PlayerRound;
    protected ArrayList<AbstractEffect> SpecialCards;
    protected ArrayList<AbstractEffect> ChosenCards;
    protected ArrayList<String> nicknames;
    protected int NumOfIslands = 12;

    public int firstPlayer;

    private Island CurrentIsland;
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
        bag = new StudentBag(true); //add 10 students, 2 for each color


        for (Island myIsland: islands)
        {
            if (myIsland!= CurrentIsland && (CurrentIsland.getID() + NumOfIslands/2) % NumOfIslands != myIsland.getID())
            {
                myIsland.addStudent(bag.popRandom());
            }
        }

        bag = new StudentBag(false); //bag with the remaining 120 students

        clouds = new ArrayList<>();
        players = new ArrayList<>();
        for(int i=0;i<PlayerNumber;i++)
        {
            clouds.add(new Cloud(PlayerNumber+1));  // Cloud Capacity
            players.add(new Player(i,nicknames.get(i), Tower.values()[i],Wizard.values()[i],PlayerNumber));
        }
        players = (ArrayList<Player>) Collections.unmodifiableCollection(players); //this way PlayerID and players Indexes will always be the same
        playerMaxMoves = new int[PlayerNumber];
        playerCardValue = new int[PlayerNumber];


        // filling the entrance of each player for the first time with 7 or 9 students
        for(Player player: players)
        {
            bagToEntrance(player.myDashboard.maxEntrance,player);
        }


        //select first player of the game
        firstPlayer = selectRandomPlayer(1);


        //----end of Setup ------

    }

    public void bagToEntrance(int number, Player player)
    {
        for(int i=0;i<number;i++){
            player.myDashboard.AddToEntrance(bag.popRandom());
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
        for(ColoredDisc student : students)
        {
            players.get(PlayerID).myDashboard.AddToEntrance(student); // access to public attribute
        }
    }

    public void BagToCloud(int CloudIndex)
    {
        Cloud myCloud = clouds.get(CloudIndex);
        for(int i=0;i<myCloud.getCloudCapacity();i++)
        {
            myCloud.AddStudent(bag.popRandom());
        }
    }

    public void EntranceToTables(int PlayerID, ColoredDisc student)
    {
        players.get(PlayerID).myDashboard.MoveToTables(student);
        evaluateProfessors(PlayerID, student);
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


    public void EntranceToIsland(int PlayerID, int IslandID, ColoredDisc student)
    {
        islands.get(IslandID).addStudent(student);
        players.get(PlayerID).myDashboard.RemoveFromEntrance(student);
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

            if (towers[0].equals(color))
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
        return Arrays.asList(PlayersInfluence).indexOf(Arrays.stream(PlayersInfluence).max());
    }

    public Boolean gameEnded()
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
                return true;
            }
        }

        // 2)

        if(NumOfIslands <= 3)
        {
            return true;
        }

        // 3)

        if(bag.getSize()==0)
        {
            return true;
        }
        for(Player player: players)
        {
            if(player.deck.returnUnused().size()==0)
            {
                return true;
            }
        }

        return false;

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

    public Player getPlayerRound() {
        return PlayerRound;
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

    public void setPlayerRound(Player playerRound) {
        PlayerRound = playerRound;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setStudentBag(ArrayList<ColoredDisc> studentBag) {
        StudentBag = studentBag;
    }

}
