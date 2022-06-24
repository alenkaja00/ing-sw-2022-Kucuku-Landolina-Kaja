package it.polimi.ingsw.server.model.components;

import it.polimi.ingsw.server.model.cards.HelperCard;

import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * this class contains the deck of each player
 * the deck is based on 10 helper cards
 */
public class Deck {

    private ArrayList<HelperCard> deck = new ArrayList<HelperCard>();

    /**
     * creates the deck adding 10 cards to it, each of them has its own id and the mother nature moves number
     */
    public Deck() {

        for(int i=0;i<10;i++)
        {
            deck.add(new HelperCard(i+1,i/2+1));
        }
    }

    /**
     * this method sets a card as used by changing its flag value to true
     * @param cardNum is the card that is used
     * @throws InvalidKeyException if the card has already been used before
     */
    public void useCard(int cardNum) throws InvalidKeyException
    {
        if (deck.get(cardNum-1).isUsed() == true) {throw new InvalidKeyException(); }

        deck.get(cardNum-1).setUsed(true);
    }

    public HelperCard returnCard(int cardNumber)
    {
        return deck.get(cardNumber-1);
    }

    public ArrayList<HelperCard> returnCards()
    {
        return (ArrayList<HelperCard>) deck.clone();
    }

    /**
     *
     * @return the list of unused cards from the deck
     */
    public ArrayList<HelperCard> returnUnused()
    {
        return (ArrayList<HelperCard>) ((ArrayList<HelperCard>)deck.clone()).stream().filter(x->x.isUsed()==false).collect(Collectors.toList());
    }
}
