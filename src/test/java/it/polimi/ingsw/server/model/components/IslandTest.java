package it.polimi.ingsw.server.model.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class IslandTest {

    Island island;

    @BeforeEach
    void setUp() {
        island = new Island(1);
    }

    @Test
    void addGraphicalIslands() {
        ArrayList idList = new ArrayList();
        idList.add(Integer.valueOf(1));
        idList.add(Integer.valueOf(3));
        island.addGraphicalIslands(3);
        assertEquals(idList,island.getGraphicalIslands());
    }

    @Test
    void getStudents() {
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
        HashMap<ColoredDisc,Integer> students = new HashMap<>();
        students.put(ColoredDisc.BLUE,1);
        island.addStudents(students);

        assertThrows(IllegalArgumentException.class, ()-> island.removeStudent(ColoredDisc.RED));
        island.removeStudent(ColoredDisc.BLUE);
        assertEquals(0,island.getStudents().get(ColoredDisc.BLUE));
    }

    @Test
    void getTowers() {
        island.AddTower(Tower.WHITE);
        island.AddTower(Tower.WHITE);
        island.AddTower(Tower.BLACK);
        assertThrows(IllegalStateException.class, ()->island.getTowers());
    }

    @Test
    void AddTower() {
        Tower [] towerList;
        island.AddTower(Tower.WHITE);
        towerList = island.getTowers();
        assertEquals(Tower.WHITE, towerList[0]);
    }

    @Test
    void removeTower() {
        assertThrows(IllegalArgumentException.class, ()->island.RemoveTower(Tower.BLACK));
        island.AddTower(Tower.WHITE);
        assertEquals(1, island.getTowers().length);
        island.RemoveTower(Tower.WHITE);
        assertEquals(0, island.getTowers().length);
    }

    @Test
    void changeTowerColor() {
        Tower [] towerList;
        island.AddTower(Tower.BLACK);
        island.changeTowerColor(Tower.WHITE);
        towerList = island.getTowers();
        assertEquals(Tower.WHITE, towerList[0]);
    }

    @Test
    void getID() {
        assertEquals(1, island.getID());
    }

    @Test
    void getGraphicalIslands() {
        assertEquals(1, island.getGraphicalIslands().size());
    }
}