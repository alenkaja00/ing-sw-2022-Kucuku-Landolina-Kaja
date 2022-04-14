package it.polimi.ingsw.model.components;

import it.polimi.ingsw.model.cards.HelperCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidKeyException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    Deck deck;
    @BeforeEach
    void setUp() {
        deck = new Deck();
    }

    @Test
    void useCard() {
        for(HelperCard card : deck.returnCards()) {
            assertEquals(false, card.isUsed());
        }
        try{
            deck.useCard(2);
        }catch(InvalidKeyException e){
            fail();
        }
        assertEquals(true,deck.returnCard(2).isUsed());
        /*try{
            deck.useCard(2);
        }catch(InvalidKeyException e){
            e.printStackTrace();
        }*/
        assertThrows(InvalidKeyException.class, ()->{deck.useCard(2);});
    }

    @Test
    void returnCard() {
        //already tested
    }

    @Test
    void returnCards() {
        //already tested
    }

    @Test
    void returnUnused() {
        try {
            deck.useCard(3);
            deck.useCard(8);
        }catch(InvalidKeyException e){
            fail();
        }
        assertEquals(8,deck.returnUnused().size());
    }
}