package it.polimi.ingsw.client.view.jsonObjects;

import it.polimi.ingsw.server.model.cards.EffectName;
import it.polimi.ingsw.server.model.components.ColoredDisc;

public class jEffectCard {
    public EffectName ID;
    public int price;
    public boolean used;
    public String description;
    public ColoredDisc[] students;
    public int prohibitionCard = 0;
}