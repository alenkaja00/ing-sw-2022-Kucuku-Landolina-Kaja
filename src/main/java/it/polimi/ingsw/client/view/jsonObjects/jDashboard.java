package it.polimi.ingsw.client.view.jsonObjects;

import it.polimi.ingsw.server.model.components.ColoredDisc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class jDashboard {
    public int maxTowers;
    public ColoredDisc[] entranceSpots = new ColoredDisc[9];
    public HashMap<ColoredDisc, Integer> tables = new HashMap<ColoredDisc, Integer>();
    public int towerNumber;
    public int maxEntrance;
    public HashSet<ColoredDisc> professorSpots = new HashSet<ColoredDisc>();
}