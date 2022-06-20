package it.polimi.ingsw.server.model.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

class DashboardTest {

    Dashboard dashboard;

    @BeforeEach
    void setUp() {
        dashboard = new Dashboard(2);
        assertThrows(IndexOutOfBoundsException.class,()-> dashboard = new Dashboard(10));
    }


    @Test
    void addToEntrance() {
        dashboard.AddToEntrance(ColoredDisc.PINK, 0);
        assertThrows(IndexOutOfBoundsException.class, () -> dashboard.AddToEntrance(ColoredDisc.GREEN,0));
        ColoredDisc [] entrance = dashboard.getEntranceSpots();
        assertEquals(ColoredDisc.PINK,entrance[0]);
    }

    @Test
    void removeFromEntrance() {
        int n = dashboard.getEntranceStudents(ColoredDisc.RED);
        dashboard.AddToEntrance(ColoredDisc.RED,0);
        assertEquals(1,n+1);
        dashboard.RemoveFromEntrance(0);
        assertEquals(0,n);
        assertThrows(InvalidParameterException.class,()-> dashboard.RemoveFromEntrance(5));
    }

    @Test
    void moveToTables() {
    }

    @Test
    void addToTables() {
        int n = dashboard.SittedStudents(ColoredDisc.RED);
        for(int i=0;i<10;i++){
            dashboard.addToTables(ColoredDisc.RED);
        }
        assertEquals(n+10,dashboard.SittedStudents(ColoredDisc.RED));

        assertThrows(IndexOutOfBoundsException.class, ()-> dashboard.addToTables(ColoredDisc.RED));
        dashboard.AddToEntrance(ColoredDisc.RED,5);
        assertThrows(IndexOutOfBoundsException.class,()-> dashboard.MoveToTables(5));
    }

    @Test
    void removeTower() {
        int n = dashboard.TowerNumber();
        dashboard.RemoveTower();
        assertEquals(n-1,dashboard.TowerNumber());
        for(int i=0;i<7;i++){
            dashboard.RemoveTower();
        }
        assertThrows(IndexOutOfBoundsException.class, ()-> dashboard.RemoveTower());
    }

    @Test
    void addTower() {
        dashboard = new Dashboard(3);
        dashboard.RemoveTower();
        assertEquals(5,dashboard.TowerNumber());
        dashboard.AddTower();
        assertThrows(IndexOutOfBoundsException.class,()-> dashboard.AddTower());

    }

    @Test
    void towerNumber() {
    }

    @Test
    void sittedStudents() {
    }

    @Test
    void removeFromTables() {
        dashboard.AddToEntrance(ColoredDisc.BLUE,0);
        int n = dashboard.SittedStudents(ColoredDisc.BLUE);
        dashboard.MoveToTables(0);
        assertEquals(1,n+1);
        assertEquals(0,dashboard.getEntranceStudents(ColoredDisc.BLUE));
        assertDoesNotThrow(()-> dashboard.RemoveFromTables(ColoredDisc.BLUE));
        assertThrows(IndexOutOfBoundsException.class,()-> dashboard.RemoveFromTables(ColoredDisc.GREEN));
        dashboard.AddToEntrance(ColoredDisc.PINK,2);
        dashboard.MoveToTables(2);
        IndexOutOfBoundsException indexOutOfBoundsException = assertThrows(IndexOutOfBoundsException.class, () -> dashboard.RemoveFromTables(ColoredDisc.PINK, 2));
        assertDoesNotThrow(()-> dashboard.RemoveFromTables(ColoredDisc.PINK,1));
    }

    @Test
    void getEntranceStudents() {
        //already tested
    }

    @Test
    void getEntranceSpots() {
        //already tested
    }
}