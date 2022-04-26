package it.polimi.ingsw.client.view.jsonObjects;

import it.polimi.ingsw.server.model.components.ColoredDisc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class jDashboard {
    private int maxTowers;
    private ArrayList<ColoredDisc> entranceSpots = new ArrayList<ColoredDisc>();
    private HashMap<ColoredDisc, Integer> tables = new HashMap<ColoredDisc, Integer>();
    private int towerNumber;
    public int maxEntrance;
    public HashSet<ColoredDisc> professorSpots = new HashSet<ColoredDisc>();
}