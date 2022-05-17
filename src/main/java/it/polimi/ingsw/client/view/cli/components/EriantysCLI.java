package it.polimi.ingsw.client.view.cli.components;
import com.google.gson.Gson;
import it.polimi.ingsw.client.view.cli.utils.ANSIColor;
import it.polimi.ingsw.client.view.jsonObjects.*;
import it.polimi.ingsw.server.model.cards.EffectName;
import it.polimi.ingsw.server.model.cards.Wizard;
import it.polimi.ingsw.server.model.components.ColoredDisc;
import it.polimi.ingsw.server.model.components.Tower;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Array;
import java.security.InvalidParameterException;
import java.util.*;
import java.util.stream.Collectors;

public class EriantysCLI {

    public EriantysCLI() {

    }

    public ArrayList<String> welcomeScene(String serverIp) {
        ArrayList<String> result = new ArrayList<>();
        result.add(ANSIColor.CYAN_BOLD);
        result.add("  ______                    _             ");
        result.add(" |  ____|    (_)           | |            ");
        result.add(" | |__   _ __ _  __ _ _ __ | |_ _   _ ___ ");
        result.add(" |  __| | '__| |/ _` | '_ \\| __| | | / __|");
        result.add(" | |____| |  | | (_| | | | | |_| |_| \\__ \\");
        result.add(" |______|_|  |_|\\__,_|_| |_|\\__|\\__, |___/");
        result.add("                                 __/ |    ");
        result.add("                                |___/   ");
        result.add("");
        result.add("DEVELOPED BY ENDI, GIOVANNI, ALEN");
        result.add("");
        if( serverIp != null && serverIp !=""){
            result.add("Connected to Server "+serverIp);
        }
        result.add(ANSIColor.RESET);
        result.add("Write help for commands");
        return (ArrayList<String>) result.clone();
    }

    public ArrayList<String> effectCardElement(EffectName ID, int price, boolean used, ColoredDisc[] students, int prohibition) {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> cardStudents = new ArrayList<>();
        //if the cards has discs on it this will contain the text "STUDENTS:"
        String labelStudents = "  ║                   ║";
        //fit the name with the shape of the card
        String id = ID.toString();

        //coin over the card after first usage
        String coin = used ? " USED, PRICE +1  ": "                 ";
        String spaces = "";

        for (int i = 0; i < 14 - id.length(); i++) {
            spaces += " ";
        }

        if (students != null) {
            labelStudents = "  ║ STUDENTS          ║";
            for (int i = 0; i < 4; i++) {
                if (i < students.length) {
                    switch (students[i].toString()) {
                        case "BLUE":
                            cardStudents.add(ANSIColor.CYAN + "X" + ANSIColor.RESET);
                            break;
                        case "PINK":
                            cardStudents.add(ANSIColor.PURPLE_BRIGHT + "X" + ANSIColor.RESET);
                            break;
                        case "RED":
                            cardStudents.add(ANSIColor.RED + "X" + ANSIColor.RESET);
                            break;
                        case "YELLOW":
                            cardStudents.add(ANSIColor.YELLOW + "X" + ANSIColor.RESET);
                            break;
                        case "GREEN":
                            cardStudents.add(ANSIColor.GREEN + "X" + ANSIColor.RESET);
                            break;
                    }
                } else cardStudents.add(" ");
            }
        } else {
            for (int i = 0; i < 4; i++) cardStudents.add(" ");
        }
        result.add("  ╔═══════════════════╗");
        result.add("  ║ PRICE        NAME ║");
        result.add("  ║   " + price + spaces + id + " ║");
        result.add("  ║ " +    coin   + " ║");
        result.add(labelStudents);
        result.add("  ║ " + cardStudents.get(0) + " " + cardStudents.get(1) + " " + cardStudents.get(2) + " " + cardStudents.get(3) + "           ║");
        result.add("  ╚═══════════════════╝");
        result.add(" ");
        return result;
    }

    public ArrayList<String> cloudElement(int index, int capacity, ArrayList<ColoredDisc> students) {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> cliStudents = new ArrayList<>();
        int ind = index + 1;

        for (int i = 0; i < capacity; i++) {
            if(students.size() > 0){
            switch (students.get(i)) {
                case PINK:
                    cliStudents.add(ANSIColor.PURPLE_BRIGHT + 'X' + ANSIColor.RESET);
                    break;
                case RED:
                    cliStudents.add(ANSIColor.RED + 'X' + ANSIColor.RESET);
                    break;
                case BLUE:
                    cliStudents.add(ANSIColor.CYAN + 'X' + ANSIColor.RESET);
                    break;
                case GREEN:
                    cliStudents.add(ANSIColor.GREEN + 'X' + ANSIColor.RESET);
                    break;
                case YELLOW:
                    cliStudents.add(ANSIColor.YELLOW + 'X' + ANSIColor.RESET);
                    break;
            }
        }else{
                for(int j=0;j<4;j++) {
                    cliStudents.add(" ");
                }
            }
        }

        result.add("            CLOUD # " + ind+"        ");
        if (capacity == 3)
        {
            result.add("              ╔═══╗          ");
            result.add("          ╔═══╝   ║          ");
            result.add("      ╔═══╝       ╚═════╗    ");
            result.add("  ╔═══╝        "+ cliStudents.get(2) +"        ╚══╗ ");
            result.add("  ║      " + cliStudents.get(0) + "          " + cliStudents.get(1) + "      ║ ");
            result.add("  ╚════════════════════════╝ ");

        } else if (capacity == 4)
        {
            result.add("              ╔═══╗          ");
            result.add("          ╔═══╝   ║          ");
            result.add("      ╔═══╝       ╚═════╗    ");
            result.add("  ╔═══╝     " +  cliStudents.get(2) + "     " + cliStudents.get(3) + "     ╚══╗ ");
            result.add("  ║         " + cliStudents.get(0) + "     " + cliStudents.get(1) + "        ║ ");
            result.add("  ╚════════════════════════╝ ");
        }
        return result;
    }

    public ArrayList<String> dashboardElement(ColoredDisc[] entrance, HashSet<ColoredDisc> professors, HashMap<ColoredDisc, Integer> tables, int towers, String towersColor, String nickname, boolean online, int coins) {
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<String> cliEntrance = new ArrayList<String>();
        HashMap<String, String> cliTables = new HashMap<>();
        HashMap<String, String> cliProfessors = new HashMap<>();
        ArrayList<String> cliTowers = new ArrayList<>();
        //default towers
        String color = ANSIColor.WHITE_BOLD;
        ArrayList<String> status = new ArrayList<>();

        //padding 8 == [ONLINE], 9 ==[OFFLINE]is the length
        int statusLen = 8;

        for (int i = 0; i < 9; i++) {
            if (i < entrance.length && entrance[i] != null) {
                switch (entrance[i]) {
                    case PINK:
                        cliEntrance.add(ANSIColor.PURPLE_BRIGHT + "X");
                        break;
                    case BLUE:
                        cliEntrance.add(ANSIColor.CYAN + "X");
                        break;
                    case RED:
                        cliEntrance.add(ANSIColor.RED + "X");
                        break;
                    case GREEN:
                        cliEntrance.add(ANSIColor.GREEN + "X");
                        break;
                    case YELLOW:
                        cliEntrance.add(ANSIColor.YELLOW + "X");
                        break;
                }
            } else {
                cliEntrance.add(" ");
            }
        }
        for (ColoredDisc disc : tables.keySet()) {
            String table = "";
            for (int i = 0; i < 10; i++) {
                if (tables.get(disc) - 1 >= i)
                    table += "X ";
                else
                    table += "  ";
            }
            cliTables.put(disc.toString(), table);
        }
        for (ColoredDisc professor : ColoredDisc.values()) {
            if (professors.contains(professor))
                cliProfessors.put(professor.toString(), "P");
            else
                cliProfessors.put(professor.toString(), " ");
        }

        switch (towersColor) {
            case "BLACK":
                color = ANSIColor.BLACK_BOLD;
                break;
            case "GREY":
                color = ANSIColor.BLACK_UNDERLINED;
                break;
        }

        for (int i = 0; i < 8; i++) {
            if (i < towers) {
                cliTowers.add(color + "X");
            } else cliTowers.add(" ");
        }

        if (online) {
            status.add(ANSIColor.GREEN_BACKGROUND + "ONLINE" + ANSIColor.RESET);
        } else {
            status.add(ANSIColor.RED_BACKGROUND + "OFFLINE" + ANSIColor.RESET);
            statusLen = 9;
        }

        result.add(" ║   Player: " + nickname + " " + padLR("", 40 - nickname.length() - statusLen, 0) + status+" ");
        if(coins!=0) result.add(" ║   COINS AMOUNT: " + coins + "                                   ");
        result.add(" ╔═════════╦═════════════════════╦═══════════╦═══════╗ ");
        result.add(" ║Entrance ║Tables               ║Professors ║Towers ║ ");
        result.add(" ║     " + cliEntrance.get(0) + ANSIColor.RESET + "   ║ " + ANSIColor.GREEN + cliTables.get("GREEN") + ANSIColor.RESET + "║     " + ANSIColor.GREEN + cliProfessors.get("GREEN") + ANSIColor.RESET + "     ║  " + cliTowers.get(0) + " " + cliTowers.get(1) + ANSIColor.RESET + "  ║ ");
        result.add(" ║---------║---------------------║-----------║-------║ ");
        result.add(" ║  " + cliEntrance.get(1) + "  " + cliEntrance.get(2) + ANSIColor.RESET + "   ║ " + ANSIColor.RED + cliTables.get("RED") + ANSIColor.RESET + "║     " +ANSIColor.RED + cliProfessors.get("RED") + ANSIColor.RESET + "     ║  " + cliTowers.get(2) + " " + cliTowers.get(3) + ANSIColor.RESET + "  ║ ");
        result.add(" ║---------║---------------------║-----------║-------║ ");
        result.add(" ║  " + cliEntrance.get(3) + "  " + cliEntrance.get(4) + ANSIColor.RESET + "   ║ " + ANSIColor.YELLOW + cliTables.get("YELLOW") + ANSIColor.RESET +  "║     " + ANSIColor.YELLOW + cliProfessors.get("YELLOW") + ANSIColor.RESET + "     ║  " + cliTowers.get(4) + " " + cliTowers.get(5) + ANSIColor.RESET + "  ║ ");
        result.add(" ║---------║---------------------║-----------║-------║ ");
        result.add(" ║  " + cliEntrance.get(5) + "  " + cliEntrance.get(6) + ANSIColor.RESET + "   ║ " + ANSIColor.PURPLE_BRIGHT + cliTables.get("PINK") + ANSIColor.RESET +  "║     " + ANSIColor.PURPLE_BRIGHT + cliProfessors.get("PINK") + ANSIColor.RESET + "     ║  " + cliTowers.get(6) + " " + cliTowers.get(7) + ANSIColor.RESET + "  ║ ");
        result.add(" ║---------║---------------------║-----------║-------║ ");
        result.add(" ║  " + cliEntrance.get(7) + "  " + cliEntrance.get(8) + ANSIColor.RESET + "   ║ " + ANSIColor.CYAN + cliTables.get("BLUE") + ANSIColor.RESET +  "║     " + ANSIColor.CYAN + cliProfessors.get("BLUE") + ANSIColor.RESET + "     ║  " + "     ║ ");
        result.add(" ╚═════════╩═════════════════════╩═══════════╩═══════╝ ");

        return (ArrayList<String>) result.clone();
    }

    public ArrayList<String> lastUsedCardElement(String nickname, int cardNumber, int maxMoves, boolean used) {
        ArrayList<String> result = new ArrayList<>();

        String space = " ";

        if(cardNumber == 10){
            space = "";
        }

        if(cardNumber == 0) {
            result.add(" ->LAST USED CARD  --not played yet--  ");
        } else {
            result.add(" ->LAST USED CARD  NUMBER: " + cardNumber + space + " MOVES: " + maxMoves + space );
        }

        return (ArrayList<String>) result.clone();
    }

    //method that prints an island based to its position
    private ArrayList<String> islandElement(int ID, HashMap<ColoredDisc, Integer> students, ArrayList<Tower> towers, ArrayList<Integer> graphicalIslands, boolean prohibited, boolean curr) {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> islandStud = new ArrayList<>();
        int tower = 0;
        int id = ID+1;
        String motherNature= " ";
        String color = "";
        String forbidden = "";
        String space =" ";
        String leftUpperMargin = " ";
        String rightUpperMargin = " ";
        String leftMargin = " ";
        String leftFinalMargin = " ";
        String rightFinalMargin = " ";
        String rightMargin = " ";
        String topMargin ="                 ";
        String bottomMargin = "                 ";

        if(curr)
            motherNature = ANSIColor.YELLOW_BOLD+"M"+ANSIColor.RESET;

        if(id>=10)
            space = "";

        if(prohibited) {
            forbidden = ANSIColor.RED;
        }

        if (students != null) {
            for (ColoredDisc d : students.keySet()) {
                switch (d.toString()) {
                    case "RED":
                        islandStud.add(ANSIColor.RED + students.get(d) + ANSIColor.RESET);
                        break;
                    case "BLUE":
                        islandStud.add(ANSIColor.CYAN + students.get(d) + ANSIColor.RESET);
                        break;
                    case "GREEN":
                        islandStud.add(ANSIColor.GREEN + students.get(d) + ANSIColor.RESET);
                        break;
                    case "YELLOW":
                        islandStud.add(ANSIColor.YELLOW + students.get(d) + ANSIColor.RESET);
                        break;
                    case "PINK":
                        islandStud.add(ANSIColor.PURPLE_BRIGHT + students.get(d) + ANSIColor.RESET);
                        break;
                }
            }
        } else {
            for (int i = 0; i < 5; i++) {
                islandStud.add(" ");
            }
        }

        for (Tower value : towers) {
            if (value.toString().equals("BLACK")) {
                color = ANSIColor.BLACK;
            } else if (value.toString().equals("GREY"))
                color = ANSIColor.WHITE_BACKGROUND;
            tower++;
        }

        if((ID == 1 || ID == 2 || ID == 3 ||ID == 4 || ID == 5)){
            leftMargin ="║";
        }
        if((ID == 7 || ID == 8 || ID == 9|| ID ==11 || ID ==10)){
            rightMargin = "║";
        }
        if((ID == 4 || ID == 5 || ID == 6|| ID == 7 || ID == 8)){
            topMargin = "═════════════════";
        }
        if((ID == 0 || ID == 11 || ID == 10 || ID ==1 || ID==2)){
            bottomMargin = "═════════════════";
        }
        if(ID == 0 || ID == 11 || ID == 10){
            leftFinalMargin = "═";
        }
        if(ID == 1 || ID ==2 || ID == 3){
            if( ID== 1 || ID ==2 ){
                leftFinalMargin = "╩";
                rightFinalMargin = "═";
            }else{
                leftFinalMargin = "╣";
            }
            leftUpperMargin = "║";
        }
        if(ID == 8 || ID ==7){
            rightUpperMargin = "╦";
            leftUpperMargin = "═";
        }
        if(ID == 11 || ID == 10 ){
            rightUpperMargin = "║";
            rightFinalMargin = "╣";
        }
        if(ID == 9 || ID == 8 || ID == 7){
            rightFinalMargin = "║";
        }
        if(ID == 4 || ID ==5 ){
            leftUpperMargin = "╠";
            leftFinalMargin = "║";
        }
        if(ID == 6){
            leftUpperMargin = "╩";
        }
        if(ID == 4 || ID == 5 || ID ==6){
            rightUpperMargin = "═";
        }
        if(ID == 9 ){
            rightUpperMargin = "╠";
        }
        if(ID == 0){
            rightFinalMargin = "╦";
        }

        result.add(leftUpperMargin + topMargin + rightUpperMargin);
        result.add(leftMargin + forbidden +" ID: " + id +space+"         "+motherNature+ANSIColor.RESET+rightMargin);
        result.add(leftMargin +" STUDENTS:       "+rightMargin);
        result.add(leftMargin +" " + islandStud.get(0) + " " + islandStud.get(1) + " " + islandStud.get(2) + " " + islandStud.get(3) + " " + islandStud.get(4) + "       "+rightMargin);
        result.add(leftMargin + color +" TOWER:    " + tower + ANSIColor.RESET + "     "+ rightMargin);
        result.add(leftFinalMargin + bottomMargin + rightFinalMargin);

        return (ArrayList<String>) result.clone();
    }

    //empty island space of 6 rows
    private ArrayList<String> emptyIsland(int type){
        ArrayList<String> result = new ArrayList<>();
        String blankSpace = "                   ";
        String edgeSpace = "═══════════════════";
        String topMargin = blankSpace;
        String bottomMargin = blankSpace;
        String rightMargin = "";
        String leftMargin = "";
        String leftUpperMargin ="";
        String rightUpperMargin ="";
        String leftLowerMargin = "";
        String rightLowerMargin = "";

        //this will be improved or removed if i change the idea of the united islands
        switch (type){
            case 1: case 2:
                bottomMargin = edgeSpace;
                break;
            case 7: case 8:
                topMargin = edgeSpace;
                break;
            case 4: case 5:
                leftMargin = "║";
                leftUpperMargin = leftMargin;
                leftLowerMargin = leftMargin;
                blankSpace = "                  ";
                topMargin = blankSpace;
                bottomMargin = blankSpace;
                break;
            case 10: case 11:
                rightMargin = "║";
                rightUpperMargin = rightMargin;
                rightLowerMargin = rightMargin;
                blankSpace = "                  ";
                topMargin = blankSpace;
                bottomMargin = blankSpace;
            break;
            case 0:
                bottomMargin = "                  ╔";
                break;
            case 3:
                bottomMargin = "╗                  ";
                break;
            case 6:
                topMargin = "╝                  ";
                break;
            case 9:
                topMargin= "                  ╚";
                break;

        }
        //result.add(leftMargin + topMargin + rightMargin);
        result.add(leftUpperMargin + topMargin + rightUpperMargin);

        for(int i=0;i<4;i++) {
            result.add(leftMargin+blankSpace+rightMargin);
        }

        //result.add(leftMargin+bottomMargin+rightMargin);
        result.add(leftLowerMargin + bottomMargin + rightLowerMargin);

        return (ArrayList<String>) result.clone();
    }

    //this method merges 4 arraylists adding them to a given arraylist
    private ArrayList<String> merge4(ArrayList<String> array1, ArrayList<String> array2,ArrayList<String> array3, ArrayList<String> array4, ArrayList<String> addition){
        if(array1.size()!=array3.size()){
            throw new InvalidParameterException();
        }

        ArrayList<String> merged4 = new ArrayList<>();

        for(int i=0;i<array1.size()||i<array2.size();i++)
        {
            merged4.add(array1.get(i)+array2.get(i)+array3.get(i)+array4.get(i));
        }
        for(int i = 0; i < 6 ; i++)
        {
            addition.add("║" + merged4.get(i) +'║');

        }

        return (ArrayList<String>) addition.clone();

    }

    //this is the method which will draw the islands map
    private ArrayList<String> islandsMap(ArrayList<jIsland> islands, jIsland current){
        String horizontalTopDelimiter= "╔════════════════════════════════════════════════════════════════════════════╗";
        String horizontalLowDelimiter ="╚════════════════════════════════════════════════════════════════════════════╝";
        ArrayList<String> result = new ArrayList<>();
        ArrayList<Integer> islandsID = new ArrayList<>();
        ArrayList<String> [] myIsland = new ArrayList[12];
        int currIslandID = current.ID;
        boolean curr = false;

        for (jIsland island : islands) {
            islandsID.add(island.ID);
        }

        int j = 0;
        for(int i=0; i<12; i++)
        {
            if(islandsID.contains(i))
            {
                if(currIslandID==i){
                    curr = true;
                }
                myIsland [i] = islandElement(islands.get(j).ID, islands.get(j).students, islands.get(j).towerList, islands.get(j).graphicalIsland, islands.get(j).prohibited, curr);
                j++;
                curr = false;
            }
            else
            {
                //empty island given the index
                myIsland [i] = emptyIsland(i);
            }
        }
        result.add(padLR("MAP OF THE ISLANDS", 96, 1));
        //result.add("MAP OF THE ISLANDS");
        result.add(horizontalTopDelimiter);
        result = merge4(myIsland[0], myIsland[1], myIsland[2], myIsland[3],result);
        result = merge4(myIsland[11], emptyIsland(-1), emptyIsland(-1), myIsland[4], result);
        result = merge4(myIsland[10], emptyIsland(-1), emptyIsland(-1), myIsland[5], result);
        result = merge4(myIsland[9], myIsland[8], myIsland[7], myIsland[6], result);
        result.add(horizontalLowDelimiter);

        return result;
    }

    //method that merges two array lists
    private ArrayList<String> merge(ArrayList<String> arr1, ArrayList<String> arr2){
        ArrayList<String> merged = new ArrayList<>();
        int maxLen = 0;
        int minSize = Math.min(arr1.size(), arr2.size());

        String space = "";

        for(int i=0; i<minSize;i++) {
            merged.add(arr1.get(i) + arr2.get(i));
            maxLen = i;
        }

        if(arr1.size() > arr2.size()){
            for(int j = 0 ; j < arr2.get(arr2.size()-1).length() ; j++){
                space = space +" ";
            }
            for(int j=maxLen+1 ; j<arr1.size() ; j++){
                merged.add(arr1.get(j) + space);
            }
        }
        else if(arr1.size() < arr2.size()){
            for(int j = 0; j < arr1.get(arr1.size()-1).length(); j++){
                space = space +" ";
            }
            for(int j=maxLen+1; j<arr2.size();j++){
                merged.add(space + arr2.get(j));
            }
        }
        return merged;
    }

    // LR = 0 for left padding LR = 1 for right padding
    public String padLR(String input, int length, int LR){
        if(input.length() >= length){
            return input;
        }

        StringBuilder sb = new StringBuilder();
        if(LR==1){
            sb.append(input);
        }
        while (sb.length() < length - input.length()) {
            sb.append(' ');
        }
        if(LR == 0){
            sb.append(input);
        }
        return sb.toString();
    }


    private ArrayList<String> effectCardsMap(ArrayList<jEffectCard> effects){
        ArrayList<String> result = new ArrayList<>();

        result.add("  THE 3 EFFECT CARDS  ");
        result.add(" ");
        for (jEffectCard effect : effects) {
            ArrayList<String> effectCard = effectCardElement(effect.ID, effect.price, effect.used, effect.students, effect.prohibitionCard);
            result.addAll(effectCard);
        }
        return result;
    }

    private ArrayList<String> dashboardMap(ArrayList<jPlayer> players)
    {
        ArrayList<String> result = new ArrayList<>();
        result.add("");
        for(jPlayer player : players){
            ArrayList<String> arr1 = dashboardElement(player.myDashboard.entranceSpots, player.myDashboard.professorSpots, player.myDashboard.tables, player.myDashboard.towerNumber, player.towerColor.toString(), player.nickname, player.online, player.coinsAmount);
            result = merge(result, arr1);
        }
        return result;
    }

    private ArrayList<String> cloudMap(ArrayList<jCloud> clouds){
        ArrayList<String> result = new ArrayList<>();

        for(int i=0; i<clouds.size(); i++) {
            ArrayList<String> cloud1 = cloudElement(i, clouds.get(i).cloudCapacity, clouds.get(i).studentSpots);
            result.addAll(cloud1);
            result.add(padLR("",cloud1.get(0).length(),1));
            result.add(padLR("",cloud1.get(0).length(),1));
        }
        return result;
    }

    public ArrayList<String> playedCardsMap(jGameClass myGame){
        ArrayList<String> result = new ArrayList<>();
        result.add("");
        ArrayList<String> padding = new ArrayList<>();
        int cardNumber = -1;
        boolean usedFlag = false;

        for(int i = 0; i < myGame.players.size(); i++){

            //added to fix the problem with the last card with id = 10
            if(myGame.playerCardValue[i] > 0){
                cardNumber = myGame.playerCardValue[i]-1;
                usedFlag = myGame.players.get(i).deck.deck.get(cardNumber).used;
            }

            ArrayList<String> card = lastUsedCardElement(myGame.players.get(i).nickname, myGame.playerCardValue[i], myGame.playerMaxMoves[i], usedFlag);
            result = merge(result,card);
            padding.add(padLR("",16,1));
            result = merge(result,padding);
        }

        return result;
    }

    public ArrayList<String> gameMap(jGameClassExpert myGame){
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> padding = new ArrayList<>();

        //this adds some space to the left
        result.add(padLR("", 1, 1));
        result = merge(result,islandsMap(myGame.islands, myGame.CurrentIsland));
        padding.add(padLR("", 10, 1));
        result = merge(result, padding);
        result = merge(result, cloudMap(myGame.clouds));

        padding.remove(0);

        //effect cards variant
        if(myGame.ChosenCards!= null) {
            padding.add(padLR("", 10, 1));
            result = merge(result, padding);
            result = merge(result, effectCardsMap(myGame.ChosenCards));
        }

        result.addAll(dashboardMap(myGame.players));
        result.addAll(playedCardsMap(myGame));

        return result;
    }

    public void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


    public static void main(String[] args) throws FileNotFoundException {
        /*EriantysCLI n = new EriantysCLI();

        //read the json

        Gson gson = new Gson();
        jGameClass myGame = new jGameClass();
        jGameClassExpert myExGame = new jGameClassExpert();

        try (Reader reader = new FileReader("C:\\Users\\alenk\\Desktop\\expert3.json")) {
            myExGame = gson.fromJson(reader, jGameClassExpert.class);
            //System.out.println(myGame);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> gg = n.gameMap(myExGame);
        for(String s : gg ){
            System.out.println(s);
        }*/

    }
}