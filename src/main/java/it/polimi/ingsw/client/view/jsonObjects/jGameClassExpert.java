package it.polimi.ingsw.client.view.jsonObjects;

import it.polimi.ingsw.client.view.jsonObjects.jEffectCard;
import it.polimi.ingsw.server.model.components.ColoredDisc;

import java.util.ArrayList;

public class jGameClassExpert extends jGameClass {
    protected ArrayList<jEffectCard> ChosenCards;
    private ArrayList<Integer> extraInfluencePlayers = new ArrayList<Integer>();
    private ArrayList<ColoredDisc> cookColors = new ArrayList<ColoredDisc>();
    private ArrayList<Integer> villainContribution = new ArrayList<>();
    private ArrayList<Integer> extraMotherNatureMoves = new ArrayList<>();
    private boolean centaurEffect = false;
}
