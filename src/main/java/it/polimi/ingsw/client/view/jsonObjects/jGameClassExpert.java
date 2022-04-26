package it.polimi.ingsw.client.view.jsonObjects;

import it.polimi.ingsw.client.view.jsonObjects.jEffectCard;
import it.polimi.ingsw.server.model.components.ColoredDisc;

import java.util.ArrayList;

public class jGameClassExpert extends jGameClass {
    public ArrayList<jEffectCard> ChosenCards;
    public ArrayList<Integer> extraInfluencePlayers = new ArrayList<Integer>();
    public ArrayList<ColoredDisc> cookColors = new ArrayList<ColoredDisc>();
    public ArrayList<Integer> villainContribution = new ArrayList<>();
    public ArrayList<Integer> extraMotherNatureMoves = new ArrayList<>();
    public boolean centaurEffect = false;
}
