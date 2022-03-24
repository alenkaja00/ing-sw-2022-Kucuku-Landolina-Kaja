package it.polimi.ingsw.model;

import it.polimi.ingsw.model.cards.HelperCard;

import java.util.ArrayList;

public class Deck {
    /**
     * Array of 10 HelperCards, each card has an id number, the mother nature moves and a used flag
     * to access the array use cardNumber-1
     */
    private ArrayList<HelperCard> deck = new ArrayList<HelperCard>();

    public Deck() {
        for(int i=0;i<10;i++)
        {
            deck.add(new HelperCard(i+1,i/2+1));
        }
    }

    public void useCard(int cardNum)
    {
        deck.get(cardNum-1).setUsed(true);
    }

    public ArrayList<HelperCard> returnCards()
    {
        return (ArrayList<HelperCard>) deck.clone();
    }
}