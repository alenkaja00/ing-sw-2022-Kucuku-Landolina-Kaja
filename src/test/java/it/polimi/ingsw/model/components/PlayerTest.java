package it.polimi.ingsw.model.components;

import it.polimi.ingsw.server.model.cards.Wizard;
import it.polimi.ingsw.server.model.components.Player;
import it.polimi.ingsw.server.model.components.Tower;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player player;
    @BeforeEach
    void setUp() {
        player = new Player(1,"alen", Tower.BLACK, Wizard.WIZARD2,3);
    }

    @Test
    void getTowerColor() {
        assertEquals(player.getTowerColor(),Tower.BLACK);
    }

    @Test
    void getID() {
        assertEquals(player.getID(),1);
    }

    @Test
    void setWizardID() {
        player.setWizardID(Wizard.WIZARD4);
        assertEquals(player.getWizardID(),Wizard.WIZARD4);
    }

    @Test
    void setTowerColor() {
        player.setTowerColor(Tower.WHITE);
        assertEquals(player.getTowerColor(),Tower.WHITE);
    }

    @Test
    void addCoins() {
        int n = player.getCoinsAmount();
        player.addCoins(1);
        assertEquals(n+1,player.getCoinsAmount());
    }

    @Test
    void spendCoins() {
        int n = player.getCoinsAmount();
        player.addCoins(1);
        player.spendCoins(1);
        assertEquals(n,player.getCoinsAmount());

        try{
            player.spendCoins(1);
        }catch(IllegalArgumentException e){
            assertTrue(true);
        }
    }

    @Test
    void getNickname() {
        assertEquals(player.getNickname(),"alen");
    }

    @Test
    void getCoinsAmount() {
        assertEquals(player.getCoinsAmount(),0);
    }
}