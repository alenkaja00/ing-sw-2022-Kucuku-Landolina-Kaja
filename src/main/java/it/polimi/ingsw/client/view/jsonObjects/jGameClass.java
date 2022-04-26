package it.polimi.ingsw.client.view.jsonObjects;

import it.polimi.ingsw.client.view.jsonObjects.jCloud;
import it.polimi.ingsw.client.view.jsonObjects.jIsland;
import it.polimi.ingsw.client.view.jsonObjects.jPlayer;
import it.polimi.ingsw.server.model.components.ColoredDisc;

import java.util.ArrayList;
import java.util.HashMap;

public class jGameClass {
    public jStudentBag bag;
    public String GameID;
    public int PlayerNumber;
    public ArrayList<jIsland> islands;
    public ArrayList<ColoredDisc> StudentBag;
    public ArrayList<jCloud> clouds;
    public ArrayList<jPlayer> players;
    public int[] playerMaxMoves;
    public int[] playerCardValue;
    public jPlayer PlayerRound;
    public ArrayList<String> nicknames;
    public int NumOfIslands = 12;
    public jIsland CurrentIsland;
    public HashMap<Integer, Integer> RoundOrder = new HashMap<Integer, Integer>();
    public int firstPlayer;
}
