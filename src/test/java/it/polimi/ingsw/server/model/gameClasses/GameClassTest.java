package it.polimi.ingsw.server.model.gameClasses;

import it.polimi.ingsw.server.model.cards.Wizard;
import it.polimi.ingsw.server.model.components.ColoredDisc;
import it.polimi.ingsw.server.model.components.Player;
import it.polimi.ingsw.server.model.components.Tower;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;

import static it.polimi.ingsw.server.model.components.ColoredDisc.GREEN;
import static it.polimi.ingsw.server.model.components.ColoredDisc.RED;
import static org.junit.jupiter.api.Assertions.*;

class GameClassTest {

    GameClass game;
    String id = "asd";
    int playNum = 2;
    ArrayList<String> nick =  new ArrayList<>(Arrays.asList("A","B"));
    ArrayList<Wizard> wiz =  new ArrayList<>(Arrays.asList(Wizard.WIZARD1,Wizard.WIZARD2));

    void initialize(){
        game = new GameClass(id, playNum, nick, wiz);
    }

    @Test
    void bagToEntrance() {
        initialize();

        game.players.get(0).myDashboard.RemoveFromEntrance(2);
        game.players.get(0).myDashboard.RemoveFromEntrance(4);
        assertNull(game.players.get(0).myDashboard.getEntranceSpots()[2]);
        assertNull(game.players.get(0).myDashboard.getEntranceSpots()[4]);
        game.bagToEntrance(2, game.players.get(0));
        assertTrue(game.players.get(0).myDashboard.getEntranceSpots()[2] != null);
        assertTrue(game.players.get(0).myDashboard.getEntranceSpots()[4] != null);
        assertThrows(IndexOutOfBoundsException.class, ()->{game.bagToEntrance(10, game.players.get(0));
        });
    }

    @Test
    void cloudToEntrance() {
        initialize();
        for(int i=4;i<7;i++){
            game.getPlayers().get(0).myDashboard.RemoveFromEntrance(i);
            assertNull(game.getPlayers().get(0).myDashboard.getEntranceSpots()[i]);
        }
        assertThrows(InvalidParameterException.class, ()-> game.CloudToEntrance(0,0));
        game.BagToCloud();

        game.CloudToEntrance(0,0);
        for(int i=0;i<game.getPlayers().get(0).myDashboard.getEntranceSpots().length;i++){
            assertNotNull(game.getPlayers().get(0).myDashboard.getEntranceSpots()[i]);
        }

    }

    @Test
    void bagToCloud() {
        initialize();
        assertEquals(0, game.getClouds().get(0).getStudents().size());
        game.BagToCloud();
        assertEquals(3,game.getClouds().get(0).getStudents().size());
    }

    @Test
    void entranceToTables() {
        initialize();
        assertNotNull(game.players.get(0).myDashboard.getEntranceSpots()[5]);
        game.EntranceToTables(game.getPlayers().get(0).getID(), 5);
        assertNull(game.players.get(0).myDashboard.getEntranceSpots()[5]);
    }

    @Test
    void evaluateProfessors() {
        //already tested
    }

    @Test
    void entranceToIsland() {
        initialize();
        int n;
        ColoredDisc color = game.getPlayers().get(0).myDashboard.getEntranceSpots()[0];
        n = game.getIslandById(1).getStudents().get(color);
        game.EntranceToIsland(0, 1, 0);
        assertEquals(n+1, game.getIslandById(1).getStudents().get(color));
    }

    @Test
    void useHelperCard() throws InvalidKeyException {
        initialize();
        assertEquals(0,game.playerCardValue[0]);
        game.useHelperCard(0, 8);
        assertEquals(8,game.playerCardValue[0]);
        assertEquals(game.getPlayers().get(0).deck.returnCard(8).getMaxMoves(), game.playerMaxMoves(0));
    }

    @Test
    void playerMaxMoves() {
        //already tested
    }

    @Test
    void moveMotherNature() {
        initialize();
        int currPos = game.CurrentIsland.getID();
        int moves = 3;
        game.MoveMotherNature(moves);
        assertEquals((currPos+moves)%12,game.CurrentIsland.getID());


        initialize();
        int currPos2 = game.CurrentIsland.getID();
        game.getIslandById((currPos2+moves)%12).addStudent(GREEN);
        game.getPlayers().get(0).myDashboard.professorSpots.add(GREEN);
        game.MoveMotherNature(moves);


        initialize();
        int currPos3 = game.CurrentIsland.getID();
        int num = game.getPlayers().get(0).myDashboard.TowerNumber();
        Tower t = game.getPlayers().get(0).getTowerColor();
        game.getIslandById((currPos3+moves)%12).addStudent(GREEN);
        game.getPlayers().get(0).myDashboard.professorSpots.add(GREEN);
        game.getIslandById((currPos3+moves-1)%12).AddTower(t);
        game.getIslandById((currPos3+moves+1)%12).AddTower(t);
        game.MoveMotherNature(moves);
        assertEquals(num-1,game.getPlayers().get(0).myDashboard.TowerNumber());


        initialize();
        int currPos4 = game.CurrentIsland.getID();
        int num2 = game.getPlayers().get(0).myDashboard.TowerNumber();
        Tower t2 = game.getPlayers().get(0).getTowerColor();
        game.getIslandById((currPos4+moves)%12).addStudent(RED);
        game.getIslandById((currPos4+moves)%12).addStudent(RED);
        game.getIslandById((currPos4+moves)%12).addStudent(GREEN);
        game.getPlayers().get(0).myDashboard.professorSpots.add(RED);
        game.getPlayers().get(0).myDashboard.professorSpots.add(GREEN);
        game.getIslandById((currPos4+moves)%12).AddTower(game.getPlayers().get(1).getTowerColor());
        game.getPlayers().get(1).myDashboard.RemoveTower();

        game.MoveMotherNature(moves);
        assertEquals(num2-1, game.getPlayers().get(0).myDashboard.TowerNumber());
        assertEquals(8, game.getPlayers().get(1).myDashboard.TowerNumber());
    }

    @Test
    void evaluateInfluence() {
    }

    @Test
    void towerGameEnded() {
        initialize();
        assertFalse(game.towerGameEnded());
        int init = game.getPlayers().get(1).myDashboard.TowerNumber();
        for(int i=0;i<init;i++){
            game.getPlayers().get(1).myDashboard.RemoveTower();
        }
        assertTrue(game.towerGameEnded());
    }

    @Test
    void islandsGameEnded() {
        initialize();
        assertFalse(game.islandsGameEnded());
        for(int i = 10; i>1 ; i--) {
            game.islands.remove(0);
        }
        assertTrue(game.islandsGameEnded());
    }

    @Test
    void roundGameEnded() {
        initialize();
        int value = game.bag.getSize();
        for(int i=0;i<value;i++){
            game.bag.popRandom();
        }
        assertTrue(game.roundGameEnded());

        game.bag.addToBag(RED,1);
        assertFalse(game.roundGameEnded());

        for(int i=1;i<=10;i++){
            try {
                game.getPlayers().get(0).deck.useCard(i);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        assertTrue(game.roundGameEnded());
    }

    @Test
    void lessTowersMoreProfessors() {
        initialize();
        int init = game.getPlayers().get(1).myDashboard.TowerNumber();
        for(int i=0;i<init;i++){
            game.getPlayers().get(1).myDashboard.RemoveTower();
        }
        assertEquals(1, game.lessTowersMoreProfessors());

        for(int i=0;i<8;i++){
            game.getPlayers().get(0).myDashboard.RemoveTower();
        }
        game.getPlayers().get(1).myDashboard.professorSpots.add(ColoredDisc.BLUE);
        assertEquals(1, game.lessTowersMoreProfessors());
    }

    @Test
    void getIslandById() {
        //already tested
    }

    @Test
    void getClouds() {
        //already tested
    }

    @Test
    void getPlayers() {
        //already tested
    }

    @Test
    void setClouds() {
        //already tested
    }

    @Test
    void setPlayers() {
        initialize();
        Player p1 = new Player(0,"C", Tower.GREY,Wizard.WIZARD3, 2);
        Player p2 = new Player(1,"D", Tower.WHITE,Wizard.WIZARD4, 2);
        ArrayList<Player> playersNew = new ArrayList<>(Arrays.asList(p1,p2));
        game.setPlayers(playersNew);
        assertEquals("C",game.players.get(0).getNickname());
        assertEquals("D",game.players.get(1).getNickname());
    }
}