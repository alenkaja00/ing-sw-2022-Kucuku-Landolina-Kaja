package it.polimi.ingsw.client.view.jsonObjects;

import com.google.gson.Gson;

public class testObjects {
    public static void main(String[] args) {
        Gson gson = new Gson();
        String json = "{\"bag\":{\"bag\":{\"PINK\":23,\"RED\":21,\"BLUE\":19,\"YELLOW\":22,\"GREEN\":21},\"bagDimension\":26},\"GameID\":\"asd\",\"PlayerNumber\":2,\"islands\":[{\"ID\":0,\"graphicalIsland\":[0],\"students\":{\"PINK\":0,\"RED\":0,\"BLUE\":0,\"YELLOW\":1,\"GREEN\":0},\"towerList\":[],\"prohibited\":false},{\"ID\":1,\"graphicalIsland\":[1],\"students\":{\"PINK\":0,\"RED\":0,\"BLUE\":1,\"YELLOW\":0,\"GREEN\":0},\"towerList\":[],\"prohibited\":false},{\"ID\":2,\"graphicalIsland\":[2],\"students\":{\"PINK\":0,\"RED\":0,\"BLUE\":0,\"YELLOW\":1,\"GREEN\":0},\"towerList\":[],\"prohibited\":false},{\"ID\":3,\"graphicalIsland\":[3],\"students\":{\"PINK\":0,\"RED\":0,\"BLUE\":0,\"YELLOW\":0,\"GREEN\":0},\"towerList\":[],\"prohibited\":false},{\"ID\":4,\"graphicalIsland\":[4],\"students\":{\"PINK\":0,\"RED\":0,\"BLUE\":0,\"YELLOW\":0,\"GREEN\":1},\"towerList\":[],\"prohibited\":false},{\"ID\":5,\"graphicalIsland\":[5],\"students\":{\"PINK\":1,\"RED\":0,\"BLUE\":0,\"YELLOW\":0,\"GREEN\":0},\"towerList\":[],\"prohibited\":false},{\"ID\":6,\"graphicalIsland\":[6],\"students\":{\"PINK\":0,\"RED\":0,\"BLUE\":0,\"YELLOW\":0,\"GREEN\":1},\"towerList\":[],\"prohibited\":false},{\"ID\":7,\"graphicalIsland\":[7],\"students\":{\"PINK\":0,\"RED\":1,\"BLUE\":0,\"YELLOW\":0,\"GREEN\":0},\"towerList\":[],\"prohibited\":false},{\"ID\":8,\"graphicalIsland\":[8],\"students\":{\"PINK\":0,\"RED\":1,\"BLUE\":0,\"YELLOW\":0,\"GREEN\":0},\"towerList\":[],\"prohibited\":false},{\"ID\":9,\"graphicalIsland\":[9],\"students\":{\"PINK\":0,\"RED\":0,\"BLUE\":0,\"YELLOW\":0,\"GREEN\":0},\"towerList\":[],\"prohibited\":false},{\"ID\":10,\"graphicalIsland\":[10],\"students\":{\"PINK\":0,\"RED\":0,\"BLUE\":1,\"YELLOW\":0,\"GREEN\":0},\"towerList\":[],\"prohibited\":false},{\"ID\":11,\"graphicalIsland\":[11],\"students\":{\"PINK\":1,\"RED\":0,\"BLUE\":0,\"YELLOW\":0,\"GREEN\":0},\"towerList\":[],\"prohibited\":false}],\"clouds\":[{\"studentSpots\":[],\"cloudCapacity\":3},{\"studentSpots\":[],\"cloudCapacity\":3}],\"players\":[{\"ID\":0,\"nickname\":\"endi\",\"towerColor\":\"WHITE\",\"wizardID\":\"WIZARD1\",\"coinsAmount\":0,\"myDashboard\":{\"maxTowers\":8,\"entranceSpots\":[\"YELLOW\",\"YELLOW\",\"BLUE\",\"BLUE\",\"BLUE\",\"RED\",\"BLUE\"],\"tables\":{\"PINK\":0,\"RED\":0,\"BLUE\":0,\"YELLOW\":0,\"GREEN\":0},\"towerNumber\":0,\"maxEntrance\":7,\"professorSpots\":[]},\"deck\":{\"deck\":[{\"cardNumber\":1,\"maxMoves\":1,\"used\":false},{\"cardNumber\":2,\"maxMoves\":1,\"used\":false},{\"cardNumber\":3,\"maxMoves\":2,\"used\":false},{\"cardNumber\":4,\"maxMoves\":2,\"used\":false},{\"cardNumber\":5,\"maxMoves\":3,\"used\":false},{\"cardNumber\":6,\"maxMoves\":3,\"used\":false},{\"cardNumber\":7,\"maxMoves\":4,\"used\":false},{\"cardNumber\":8,\"maxMoves\":4,\"used\":false},{\"cardNumber\":9,\"maxMoves\":5,\"used\":false},{\"cardNumber\":10,\"maxMoves\":5,\"used\":false}]},\"online\":true},{\"ID\":1,\"nickname\":\"giovanni\",\"towerColor\":\"BLACK\",\"wizardID\":\"WIZARD2\",\"coinsAmount\":0,\"myDashboard\":{\"maxTowers\":8,\"entranceSpots\":[\"RED\",\"BLUE\",\"GREEN\",\"GREEN\",\"PINK\",\"RED\",\"GREEN\"],\"tables\":{\"PINK\":0,\"RED\":0,\"BLUE\":0,\"YELLOW\":0,\"GREEN\":0},\"towerNumber\":0,\"maxEntrance\":7,\"professorSpots\":[]},\"deck\":{\"deck\":[{\"cardNumber\":1,\"maxMoves\":1,\"used\":false},{\"cardNumber\":2,\"maxMoves\":1,\"used\":false},{\"cardNumber\":3,\"maxMoves\":2,\"used\":false},{\"cardNumber\":4,\"maxMoves\":2,\"used\":false},{\"cardNumber\":5,\"maxMoves\":3,\"used\":false},{\"cardNumber\":6,\"maxMoves\":3,\"used\":false},{\"cardNumber\":7,\"maxMoves\":4,\"used\":false},{\"cardNumber\":8,\"maxMoves\":4,\"used\":false},{\"cardNumber\":9,\"maxMoves\":5,\"used\":false},{\"cardNumber\":10,\"maxMoves\":5,\"used\":false}]},\"online\":true}],\"playerMaxMoves\":[0,0],\"playerCardValue\":[0,0],\"nicknames\":[\"endi\",\"giovanni\"],\"NumOfIslands\":12,\"CurrentIsland\":{\"ID\":3,\"graphicalIsland\":[3],\"students\":{\"PINK\":0,\"RED\":0,\"BLUE\":0,\"YELLOW\":0,\"GREEN\":0},\"towerList\":[],\"prohibited\":false},\"RoundOrder\":{\"0\":0,\"1\":0},\"firstPlayer\":0}";

        jGameClass myGameClass = gson.fromJson(json, jGameClass.class);

        //bag
        /*for(String s:myGameClass.bag) {
            System.out.println("ID " + myGameClass.players.get(0).ID);
            System.out.println("NIK " + myGameClass.players.get(0).nickname);
            System.out.println("NIK " + myGameClass.players.get(0).nickname);
            //System.out.println("dimension "+myGameClass.bag.);
        }*/

        for(jPlayer p: myGameClass.players) {
            System.out.println("ID " + p.ID);
            System.out.println("NIK " + p.nickname);
            System.out.println("TOWER " + p.towerColor);
            System.out.println("WIZARD " + p.wizardID);
            System.out.println("COINS " + p.coinsAmount);
            System.out.println("ONLINE " + p.online);

        }


    }
}
