package it.polimi.ingsw.model;

import it.polimi.ingsw.model.cards.HelperCard;
import it.polimi.ingsw.model.cards.Wizard;

import java.util.ArrayList;

public class Deck {
    private Wizard wizardID;
    private ArrayList<HelperCard> deck = new ArrayList<HelperCard>(10);


    public Deck() {
        for(int i=0;i<10;i++)
        {
            deck.get(i).setUsed(false);
            deck.get(i).setMaxMoves(motherNatureMoves(i));
        }
    }
    //calculate mother nature moves given the card
    public int motherNatureMoves(int index){
        return index/2+1;
    }
    //return the remaining cards of the player


    public HelperCard useCard(){
    }




    public ArrayList<HelperCard> returnCards(){
        ArrayList<HelperCard> remainingCards= new ArrayList<HelperCard>();
        for(int i=0;i<deck.size();i++){
            remainingCards.add(deck.get(i));
        }
        return remainingCards;
    }
}