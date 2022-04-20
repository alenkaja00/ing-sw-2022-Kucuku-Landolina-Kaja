package it.polimi.ingsw.server.model.components;

import it.polimi.ingsw.server.model.components.ColoredDisc;
import it.polimi.ingsw.server.model.components.StudentBag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class StudentBagTest {

    StudentBag bag;
    @BeforeEach
    void setup(){
        bag = new StudentBag();
    }

    @Test
    void pop2forColor() {
        ArrayList<ColoredDisc> pop2 = new ArrayList<>();
        pop2 = bag.pop2forColor();
        assertEquals(bag.getSize(),120);
        for(ColoredDisc color : pop2) {
            assertEquals(pop2.stream().filter(x->x.equals(color)).collect(Collectors.toList()).size(), 2);
        }
        int size=bag.getSize();
        for(int i=0;i<size;i++){
            bag.popRandom();
        }
        try{
            bag.pop2forColor();
        }catch(IndexOutOfBoundsException e){
            assertTrue(true);
        }
    }

    @Test
    void popRandom() {
        int n = bag.getSize();
        ColoredDisc color1 = bag.popRandom();
        assertEquals(bag.getSize(),n-1);
        assertEquals(bag.get(color1),25);
    }

    @Test
    void getSize() {
        assertEquals(bag.getSize(), 130);
        for(int i=0;i<131;i++){
            try{
                bag.popRandom();
            } catch(IndexOutOfBoundsException e){
                assertTrue(true);
            }
        }
    }

    @Test
    void get() {
        for(ColoredDisc disc: ColoredDisc.values() ){
            assertEquals(bag.get(disc),26);
        }
    }

    @Test
    void addToBag() {
        bag.addToBag(ColoredDisc.RED);
        assertEquals(bag.get(ColoredDisc.RED),27);
        assertEquals(bag.getSize(),131);
    }

    @Test
    void testAddToBag() {
        int n=5;
        bag.addToBag(ColoredDisc.GREEN,n);
        assertEquals(bag.get(ColoredDisc.GREEN),26+n);
    }
}