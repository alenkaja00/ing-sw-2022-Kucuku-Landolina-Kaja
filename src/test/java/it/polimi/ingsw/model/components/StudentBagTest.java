package it.polimi.ingsw.model.components;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class StudentBagTest {

    @Test
    void pop2forColor() {
        StudentBag bag = new StudentBag();
        ArrayList<ColoredDisc> pop2 = new ArrayList<>();
        pop2 = bag.pop2forColor();
        assertEquals(bag.getSize(),120);
        for(ColoredDisc color : pop2) {
            assertEquals(pop2.stream().filter(x->x.equals(color)).collect(Collectors.toList()).size(), 2);
        }
    }

    @Test
    void popRandom() {
        StudentBag bag = new StudentBag();
        ColoredDisc color1 = bag.popRandom();
        assertEquals(bag.getSize(), 129);
        assertEquals(bag.get(color1),25);
    }

    @Test
    void getSize() {
        StudentBag bag = new StudentBag();
        assertEquals(bag.getSize(), 130);
    }

    @Test
    void get() {
        StudentBag bag = new StudentBag();
        for(ColoredDisc disc: ColoredDisc.values() ){
            assertEquals(bag.get(disc),26);
        }
    }

    @Test
    void addToBag() {
        StudentBag bag = new StudentBag();
        bag.addToBag(ColoredDisc.RED);
        assertEquals(bag.get(ColoredDisc.RED),27);
        assertEquals(bag.getSize(),131);
    }

    @Test
    void testAddToBag() {
        int n=5;
        StudentBag bag = new StudentBag();
        bag.addToBag(ColoredDisc.GREEN,n);
        assertEquals(bag.get(ColoredDisc.GREEN),26+n);
    }
}