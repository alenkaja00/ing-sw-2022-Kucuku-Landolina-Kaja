package it.polimi.ingsw.model;

import it.polimi.ingsw.model.cards.HelperCard;

import java.util.ArrayList;

public class Deck {
    private String wizardName;
    private ArrayList<HelperCard> deck;

    public Deck() {
        this.deck = new ArrayList<HelperCard>(10);
        for (int i = 0; i < deck.size(); i++) {
            deck.get(i).setUsed(false);
        }
    }
    //calculate mother nature moves given the card
    public int motherNatureMoves(int index){
        return index/2+1;
    }
    //return the remaining cards of the player
    public ArrayList<HelperCard> returnCards(){
        ArrayList<HelperCard> remainingCards= new ArrayList<HelperCard>();
        for(int i=0;i<deck.size();i++){
            remainingCards.add(deck.get(i));
        }
        return remainingCards;
    }
}