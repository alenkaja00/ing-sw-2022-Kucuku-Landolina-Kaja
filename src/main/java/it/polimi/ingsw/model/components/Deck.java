package it.polimi.ingsw.model.components;

import it.polimi.ingsw.model.cards.HelperCard;

import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.stream.Collectors;

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

    public void useCard(int cardNum) throws InvalidKeyException
    {
        if (deck.get(cardNum-1).isUsed() == true) {throw new InvalidKeyException();}

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

    public ArrayList<HelperCard> returnUnused()
    {
        return (ArrayList<HelperCard>) ((ArrayList<HelperCard>)deck.clone()).stream().filter(x->x.isUsed()==false).collect(Collectors.toList());
    }
}
