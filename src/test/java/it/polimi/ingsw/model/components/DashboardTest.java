package it.polimi.ingsw.model.components;

import com.sun.source.tree.AssertTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;


class DashboardTest {

    Dashboard dashboard;

    @BeforeEach
    void setUp() {
        dashboard = new Dashboard(2);
        assertThrows(IndexOutOfBoundsException.class,()->{dashboard = new Dashboard(10);});
    }

    @Test
    void addToEntrance() {
        for (int i = 0; i < 7; i++) {
            assertDoesNotThrow(() -> {
                dashboard.AddToEntrance(ColoredDisc.PINK);
            });
        }
        assertThrows(IndexOutOfBoundsException.class, () -> {
            dashboard.AddToEntrance(ColoredDisc.GREEN);
        });
    }


    @Test
    void removeFromEntrance() {
        int n = dashboard.getEntranceStudents(ColoredDisc.RED);
        dashboard.AddToEntrance(ColoredDisc.RED);
        assertEquals(1,n+1);
        dashboard.RemoveFromEntrance(ColoredDisc.RED);
        assertEquals(0,n);
        assertThrows(InvalidParameterException.class,()->{dashboard.RemoveFromEntrance(ColoredDisc.GREEN);});
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
        /*try{
            dashboard.addToTables(ColoredDisc.RED);
        }catch(IndexOutOfBoundsException e ){
            e.printStackTrace();
        }*/
        assertThrows(IndexOutOfBoundsException.class, ()->{dashboard.addToTables(ColoredDisc.RED);});
        dashboard.AddToEntrance(ColoredDisc.RED);
        assertThrows(IndexOutOfBoundsException.class,()->{dashboard.MoveToTables(ColoredDisc.RED);});
    }

    @Test
    void removeTower() {
        int n = dashboard.TowerNumber();
        dashboard.AddTower();
        assertEquals(n+1,dashboard.TowerNumber());
        dashboard.RemoveTower();
        assertEquals(n,dashboard.TowerNumber());
        for(int i=0;i<8+1;i++){
            assertThrows(IndexOutOfBoundsException.class, ()->{dashboard.RemoveTower();;});
        }
    }

    @Test
    void addTower() {
        dashboard = new Dashboard(3);
        for(int i=0;i<6;i++){
            assertDoesNotThrow(()->{dashboard.AddTower();});
        }
        assertEquals(6,dashboard.TowerNumber());
        assertThrows(IndexOutOfBoundsException.class,()->{dashboard.AddTower();});
    }

    @Test
    void towerNumber() {
    }

    @Test
    void sittedStudents() {
    }

    @Test
    void removeFromTables() {
        dashboard.AddToEntrance(ColoredDisc.BLUE);
        int n = dashboard.SittedStudents(ColoredDisc.BLUE);
        dashboard.MoveToTables(ColoredDisc.BLUE);
        assertEquals(1,n+1);
        assertEquals(0,dashboard.getEntranceStudents(ColoredDisc.BLUE));
        assertDoesNotThrow(()->{dashboard.RemoveFromTables(ColoredDisc.BLUE);});
        assertThrows(IndexOutOfBoundsException.class,()->{dashboard.RemoveFromTables(ColoredDisc.GREEN);});
        dashboard.AddToEntrance(ColoredDisc.PINK);
        dashboard.MoveToTables(ColoredDisc.PINK);
        assertThrows(IndexOutOfBoundsException.class,()->{dashboard.RemoveFromTables(ColoredDisc.PINK,2);});
        assertDoesNotThrow(()->{dashboard.RemoveFromTables(ColoredDisc.PINK,1);});
    }
    @Test
    void testRemoveFromTables() {
    }
}