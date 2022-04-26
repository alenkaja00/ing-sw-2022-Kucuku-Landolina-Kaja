package it.polimi.ingsw.client.view.jsonObjects;

import it.polimi.ingsw.server.model.cards.EffectName;
import it.polimi.ingsw.server.model.components.ColoredDisc;

import java.util.ArrayList;

public class jEffectCard {
    private EffectName ID;
    private int price;
    private boolean used;
    private ArrayList<ColoredDisc> students;
    public int prohibitionCard = 0;
}
