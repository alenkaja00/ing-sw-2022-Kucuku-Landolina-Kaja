package it.polimi.ingsw.model;

import it.polimi.ingsw.model.cards.HelperCard;
import it.polimi.ingsw.model.cards.Wizard;

import java.util.ArrayList;

public class Deck {
    private Wizard wizardID;
    private ArrayList<HelperCard> deck = new ArrayList<HelperCard>();


    public Deck() {
        this.wizardID=wizardID;
        for(int i=0;i<10;i++)
        {
            deck.add(new HelperCard(i+1,i/2+1));
        }
    }

    //calculate mother nature moves given the card
    public int motherNatureMoves(int index){
        return index/2+1;
    }

    public HelperCard useCard(int cardNum)
    {

    }

    public ArrayList<HelperCard> returnCards()
    {
        return (ArrayList<HelperCard>) deck.clone();
    }
    public Wizard getWizardID()
    {
        return wizardID;
    }
    public void setWizardID(Wizard wizardID) {
        this.wizardID = wizardID;
    }
}
