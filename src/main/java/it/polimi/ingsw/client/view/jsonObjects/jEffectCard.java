package it.polimi.ingsw.client.view.jsonObjects;

import it.polimi.ingsw.server.model.cards.EffectName;
import it.polimi.ingsw.server.model.components.ColoredDisc;

import java.util.ArrayList;

public class jEffectCard {
    public EffectName ID;
    public int price;
    public boolean used;
    public ArrayList<ColoredDisc> students;
    public int prohibitionCard = 0;
}
