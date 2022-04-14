package it.polimi.ingsw.model.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class IslandTest {
    Island island;
    @BeforeEach
    void setUp(){
        island = new Island(1);
    }

    @Test
    void addGraphicalIslands() {
        //assertEquals();
    }

    @Test
    void getStudents() {
        //gia testato
    }

    @Test
    void addStudent() {
        int n = island.getStudents().get(ColoredDisc.RED);
        assertEquals(0,n);
        island.addStudent(ColoredDisc.RED);
        assertEquals(1,n+1);
    }

    @Test
    void addStudents() {
    }

    @Test
    void removeStudent() {
        HashMap<ColoredDisc,Integer> students = new HashMap<ColoredDisc, Integer>();
        students.put(ColoredDisc.BLUE,1);
        island.addStudents(students);
        /*try{
            island.removeStudent(ColoredDisc.RED);
        }catch(IllegalArgumentException e){
            e.printStackTrace();
        }*/
        assertThrows(IllegalArgumentException.class, ()->{island.removeStudent(ColoredDisc.RED);;});
        island.removeStudent(ColoredDisc.BLUE);
        assertEquals(0,island.getStudents().get(ColoredDisc.BLUE));
    }

    @Test
    void getTowers() {
    }

    @Test
    void addTower() {
    }

    @Test
    void removeTower() {
    }
    @Test
    void changeTowerColor() {
    }

    @Test
    void getID() {
    }
}