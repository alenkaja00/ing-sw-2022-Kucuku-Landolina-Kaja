package it.polimi.ingsw.server.model.gameClasses;

import it.polimi.ingsw.server.model.cards.EffectCard;
import it.polimi.ingsw.server.model.cards.EffectName;
import it.polimi.ingsw.server.model.cards.Wizard;
import it.polimi.ingsw.server.model.components.ColoredDisc;
import it.polimi.ingsw.server.model.components.Player;
import it.polimi.ingsw.server.model.components.Tower;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static it.polimi.ingsw.server.model.components.ColoredDisc.*;
import static org.junit.jupiter.api.Assertions.*;

class GameClassExpertTest {

    GameClassExpert game;

    String id;
    int playNum;
    ArrayList<String> nick;
    ArrayList<Wizard> wiz;
    ArrayList<EffectCard> cards = new ArrayList<>(Arrays.asList(new EffectCard(EffectName.JOLLY),new EffectCard(EffectName.LADY),new EffectCard(EffectName.COOK)));

    @BeforeEach
    void initialize(){
        id = "asd";
        playNum = 2;
        nick =  new ArrayList<>(Arrays.asList("A","B"));
        wiz =  new ArrayList<>(Arrays.asList(Wizard.WIZARD1,Wizard.WIZARD2));
        game = new GameClassExpert(id, playNum, nick, wiz);
        game.ChosenCards = cards;
    }


    @Test
    void useCardEffect() {
        //CAVALIER EFFECT: Evaluate influence on an island controlled by others because +2 influence
        cards.remove(2);
        cards.add(new EffectCard(EffectName.CAVALIER));


        assertTrue(game.getEffectCards().size()==3);

        for(ColoredDisc color: ColoredDisc.values()){
            try{
                game.getIslandById(0).removeStudent(color);
            }catch(Exception e ){
                continue;
            }
        }

        game.ChosenCards = cards;

        game.useCardEffect(0, EffectName.CAVALIER);
        game.getIslandById(0).addStudent(RED);
        game.getPlayers().get(1).myDashboard.professorSpots.add(RED);
        //assert that the influence is of player 0
        assertEquals(0, game.EvaluateInfluence(game.getIslandById(0)));
        game.endCardEffect();
    }

    @Test
    void cookEffect() {
        //already tested
    }

    @Test
    void ladyEffect() {
        cards.remove(2);
        cards.add(new EffectCard(EffectName.LADY));

        game.ChosenCards = cards;
        for(int i=0;i<4;i++){
            game.ladyEffect(10);
        }
        assertEquals(4, game.getIslandById(10).prohibitedValue);
        assertThrows(InvalidParameterException.class, ()->game.ladyEffect(10));

        game.MoveMotherNature(10-game.CurrentIsland.getID());
        assertEquals(3, game.getIslandById(10).prohibitedValue);
    }

    @Test
    void jollyEffectCall() {
        cards.remove(2);
        cards.add(new EffectCard(EffectName.JOLLY));
        game.ChosenCards = cards;

        ColoredDisc cardColor = game.ChosenCards.get(0).getStudents()[0];
        ColoredDisc entranceColor = game.getPlayers().get(0).myDashboard.getEntranceSpots()[0];
        game.jollyEffectCall(0, 0, 0);

        assertEquals(entranceColor, game.ChosenCards.get(0).getStudents()[0]);
        assertEquals(cardColor, game.getPlayers().get(0).myDashboard.getEntranceSpots()[0]);
    }

    @Test
    void musicianEffect() {
        cards.remove(2);
        cards.add(new EffectCard(EffectName.MUSICIAN));
        game.ChosenCards = cards;
        ColoredDisc entranceColor = game.getPlayers().get(0).myDashboard.getEntranceSpots()[0];
        game.getPlayers().get(0).myDashboard.addToTables(GREEN);
        game.musicianEffect(0, 0, GREEN);
        //check that entrance contains the green student taken from the tables
        assertEquals(1, game.getPlayers().get(0).myDashboard.SittedStudents(entranceColor));
        assertTrue(Arrays.stream(game.getPlayers().get(0).myDashboard.getEntranceSpots()).filter(x->x==GREEN).collect(Collectors.toList()).size() >= 1);
    }

    @Test
    void banditEffect() {
        game.getPlayers().get(0).myDashboard.addToTables(ColoredDisc.GREEN);
        for (int i = 0; i < 4; i++) {
            game.getPlayers().get(1).myDashboard.addToTables(ColoredDisc.GREEN);
        }
        game.banditEffect(ColoredDisc.GREEN);
        assertEquals(0, game.getPlayers().get(0).myDashboard.SittedStudents(GREEN));
        //bandit can steal max three students
        assertEquals(1, game.getPlayers().get(1).myDashboard.SittedStudents(GREEN));
    }

    @Test
    void lordEffect() {
        int id = game.CurrentIsland.getID();
        int moves = 2;
        game.islands.get((id+moves) % 12).addStudent(RED);
        game.getPlayers().get(0).myDashboard.professorSpots.add(RED);
        game.lordEffect((id+moves) % 12);
        //mother nature moves (evaluates the influence) but the current island does not change
        assertEquals(id, game.CurrentIsland.getID());
        game.endCardEffect();

        initialize();
        int currPos2 = game.CurrentIsland.getID();
        int moves2 = 2;
        Tower enemy = game.getPlayers().get(1).getTowerColor();


        game.getIslandById((currPos2+moves2)% game.islands.size()).addStudent(RED);
        game.getIslandById((currPos2+moves2)% game.islands.size()).addStudent(RED);
        game.getPlayers().get(0).myDashboard.professorSpots.add(RED);

        for(Player p: game.getPlayers() )
        {
            if(p!=game.getPlayers().get(0))
                assertFalse(p.myDashboard.professorSpots.contains(RED));
        }

        game.getIslandById((currPos2+moves2)% game.islands.size()).AddTower(enemy);
        game.getPlayers().get(1).myDashboard.RemoveTower();
        game.lordEffect((game.CurrentIsland.getID()+moves2)%12);

    }

    @Test
    void endCardEffect() {
        //already tested
    }

    @Test
    void evaluateInfluence() {
        //already tested
    }

    @Test
    void testEvaluateProfessors() {
        //already tested
    }

    @Test
    void moveMotherNature() {
        int currPos = game.CurrentIsland.getID();
        int moves = 3;
        game.MoveMotherNature(moves);
        assertEquals((currPos+moves)%12,game.CurrentIsland.getID());

        int currPos2 = game.CurrentIsland.getID();
        game.getIslandById((currPos2+moves)%12).addStudent(GREEN);
        game.getPlayers().get(0).myDashboard.professorSpots.add(GREEN);

        //testing cook effect
        cards.remove(2);
        cards.add(new EffectCard(EffectName.COOK));
        game.cookEffect(GREEN);
        game.MoveMotherNature(moves);
        game.endCardEffect();

        int currPos3 = game.CurrentIsland.getID();
        int num = game.getPlayers().get(0).myDashboard.TowerNumber();
        Tower t = game.getPlayers().get(0).getTowerColor();
        game.getIslandById((currPos3+moves)%12).addStudent(GREEN);
        game.getPlayers().get(0).myDashboard.professorSpots.add(GREEN);
        game.getIslandById((currPos3+moves-1)%12).AddTower(t);
        game.getIslandById((currPos3+moves+1)%12).AddTower(t);
        game.MoveMotherNature(moves);
        assertEquals(num-1,game.getPlayers().get(0).myDashboard.TowerNumber());
    }

    @Test
    void motherNatureTowersTest(){
        int currPos = game.CurrentIsland.getID();
        int moves = 2;
        Tower enemy = game.getPlayers().get(1).getTowerColor();

        game.getIslandById((currPos+moves)% game.islands.size()).addStudent(RED);
        game.getIslandById((currPos+moves)% game.islands.size()).addStudent(RED);
        game.getPlayers().get(0).myDashboard.professorSpots.add(RED);

        for(Player p: game.getPlayers() )
        {
            if(p!=game.getPlayers().get(0))
                assertFalse(p.myDashboard.professorSpots.contains(RED));
        }

        game.getIslandById((currPos+moves)% game.islands.size()).AddTower(enemy);
        game.getPlayers().get(1).myDashboard.RemoveTower();


        game.MoveMotherNature(moves);
        assertEquals(0, game.EvaluateInfluence(game.getIslandById((currPos+moves) % game.islands.size())));

    }

    @Test
    void testPlayerMaxMoves() {
        ArrayList<EffectCard> cards = new ArrayList<>();
        cards.add(new EffectCard(EffectName.LADY));
        cards.add(new EffectCard(EffectName.COOK));
        cards.add(new EffectCard(EffectName.MAGICIAN));
        game.ChosenCards = cards;
        assertEquals(0, game.playerMaxMoves(0));
        game.useCardEffect(0,EffectName.MAGICIAN);
        assertEquals(2, game.playerMaxMoves(0));
    }

    @Test
    void getEffectCards() {
        //already tested
    }
}