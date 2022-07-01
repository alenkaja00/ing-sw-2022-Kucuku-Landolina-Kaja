package it.polimi.ingsw.server.model.components;

import it.polimi.ingsw.server.model.cards.HelperCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidKeyException;

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
            assertFalse(card.isUsed());
        }
        try{
            deck.useCard(2);
        }catch(InvalidKeyException e){
            fail();
        }
        assertTrue(deck.returnCard(2).isUsed());

        assertThrows(InvalidKeyException.class, ()-> deck.useCard(2));
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

