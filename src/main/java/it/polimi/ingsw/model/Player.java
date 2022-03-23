package it.polimi.ingsw.model;

public class Player{
    private String nickname;
    public Deck deck;
    public Dashboard myDashboard;

    public Player(String nickname) {
        this.nickname = nickname;
        this.deck = new Deck();
        this.myDashboard= new Dashboard();
    }

}