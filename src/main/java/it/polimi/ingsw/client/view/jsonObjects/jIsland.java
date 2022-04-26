package it.polimi.ingsw.client.view.jsonObjects;

import it.polimi.ingsw.server.model.components.ColoredDisc;
import it.polimi.ingsw.server.model.components.Tower;

import java.util.ArrayList;
import java.util.HashMap;

public class jIsland {
    private int ID;
    private ArrayList<Integer> graphicalIsland = new ArrayList<>();
    private HashMap<ColoredDisc, Integer> students = new HashMap<ColoredDisc, Integer>();
    private ArrayList<Tower> towerList = new ArrayList<Tower>();
    public boolean prohibited;
}