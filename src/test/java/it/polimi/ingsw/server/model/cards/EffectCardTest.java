package it.polimi.ingsw.server.model.cards;

import it.polimi.ingsw.server.model.components.ColoredDisc;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EffectCardTest {
    EffectCard newCard;

    @Test
    void testLady(){
        newCard = new EffectCard(EffectName.LADY);
        assertEquals(4, newCard.getProhibitionCard());
    }
    @Test
    void useCard() {
        newCard = new EffectCard(EffectName.MONK);
        assertFalse(newCard.isUsed());
        newCard.useCard();
        assertTrue(newCard.isUsed());
    }

    @Test
    void getPrice() {
        newCard = new EffectCard(EffectName.CAVALIER);
        assertEquals(2, newCard.getPrice());
        newCard = new EffectCard(EffectName.BANDIT);
        assertEquals(3, newCard.getPrice());

    }


    @Test
    void getID() {
        newCard = new EffectCard(EffectName.MAGICIAN);
        assertEquals(EffectName.MAGICIAN, newCard.getID());
        newCard = new EffectCard(EffectName.MUSICIAN);
        assertEquals(EffectName.MUSICIAN, newCard.getID());
        newCard = new EffectCard(EffectName.COOK);
        assertEquals(EffectName.COOK, newCard.getID());
        newCard = new EffectCard(EffectName.VILLAIN);
        assertEquals(EffectName.VILLAIN, newCard.getID());
    }

    @Test
    void isUsed() {
        newCard = new EffectCard(EffectName.CENTAUR);
        newCard.useCard();
        assertEquals(4, newCard.getPrice());
        newCard = new EffectCard(EffectName.LORD);
        newCard.useCard();
        assertEquals(4, newCard.getPrice());
    }

    @Test
    void getStudents() {
        //already tested
    }

    @Test
    void addStudent() {
        newCard = new EffectCard(EffectName.QUEEN);
        newCard.addStudent(ColoredDisc.RED,1);
        ColoredDisc [] students = newCard.getStudents();
        assertEquals(ColoredDisc.RED, students[1]);
        assertThrows(IndexOutOfBoundsException.class, ()->newCard.addStudent(ColoredDisc.RED,1));
    }

    @Test
    void removeStudent() {
        newCard = new EffectCard(EffectName.JOLLY);
        ColoredDisc[] students = newCard.getStudents();
        newCard.removeStudent(0);
        assertNull(students[0]);
    }

}