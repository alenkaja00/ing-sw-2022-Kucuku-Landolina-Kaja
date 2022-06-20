package it.polimi.ingsw.server.model.cards;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelperCardTest {
    HelperCard assistant;

    @BeforeEach
    void setUp(){
        assistant = new HelperCard(1,2);
    }

    @Test
    void setUsed() {
        assistant.setUsed(true);
        assertTrue(assistant.isUsed());
    }

    @Test
    void isUsed() {
        //already tested
    }

    @Test
    void getCardNumber() {
        assertEquals(1, assistant.getCardNumber());
    }

    @Test
    void getMaxMoves() {
        assertEquals(2,assistant.getMaxMoves());
    }
}