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

    public jGameClass jGameClass;
    public jGameClassExpert jGameClassExpert;

    public EriantysCLI() {
        jGameClass = new jGameClass();
        jGameClassExpert = new jGameClassExpert();
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

    public ArrayList<String> nicknameScene(boolean valid) {
        ArrayList<String> result = new ArrayList<>();
        if (valid) {
            result.add("PLEASE INSERT YOUR NICKNAME");
        } else {
            result.add("This nickname is not available!   ");
            result.add("Please provide a new nickname    ");
        }
        return (ArrayList<String>) result.clone();
    }

    public ArrayList<String> loadingScene() {
        ArrayList<String> result = new ArrayList<>();
        result.add(ANSIColor.GREEN_BOLD);
        result.add(" ");
        result.add("LOADING, PLEASE WAIT ...");
        result.add(" ");
        result.add(ANSIColor.RESET);

        return (ArrayList<String>) result.clone();
    }

    public ArrayList<String> messageScene(String message) {
        ArrayList<String> result = new ArrayList<>();
        result.add(" ");
        result.add(message);
        result.add(" ");
        return (ArrayList<String>) result.clone();
    }

    public ArrayList<String> errorMessage() {
        ArrayList<String> result = new ArrayList<>();
        result.add(ANSIColor.RED_BOLD);
        result.add(" ");
        result.add("WARNING , INSERT A VALID COMMAND ...");
        result.add(" ");
        result.add(ANSIColor.RESET);

        return (ArrayList<String>) result.clone();
    }

    public ArrayList<String> createGameScene(boolean playerVariant) {
        ArrayList<String> result = new ArrayList<>();
        if (playerVariant) {
            result.add("GAME CREATION");
            result.add("Select the number of players (you can choose 2 or 3 by typing the number )");
        } else {
            result.add("Type -> norm  to play the normal version of the game ");
            result.add("Type -> exp  to play the expert version of the game ");
        }
        return (ArrayList<String>) result.clone();
    }

    public ArrayList<String> chooseWizard(ArrayList<Wizard> wizards) {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> cliWizards = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if (i < wizards.size()) {
                cliWizards.add(wizards.get(i).toString());
            } else {
                cliWizards.add(" ");
            }
        }
        result.add("CHOOSE YOUR WIZARD");
        result.add("You can choose between:");
        result.add(cliWizards.get(0) + " " + cliWizards.get(1) + " " + cliWizards.get(2) + " " + cliWizards.get(3));
        return (ArrayList<String>) result.clone();

    }

    public ArrayList<String> effectCardElement(EffectName ID, int price, boolean used, ColoredDisc[] students, int prohibition) {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> cardStudents = new ArrayList<>();
        //if the cards has discs on it this will contain the text "STUDENTS:"
        String labelStudents = "  |-------------------|";
        //fit the name with the shape of the card
        String id = ID.toString();

        //coin over the card after first usage
        int coin = used ? 1:0;
        String spaces = "";

        for (int i = 0; i < 14 - id.length(); i++) {
            spaces += " ";
        }

        if (students != null) {
            labelStudents = "  | STUDENTS          |";
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
        result.add("  +-------------------+");
        result.add("  | PRICE        NAME |");
        result.add("  |   " + price + spaces + id + " |");
        result.add("  | USATA           "+coin+" |");
        result.add(labelStudents);
        result.add("  | " + cardStudents.get(0) + " " + cardStudents.get(1) + " " + cardStudents.get(2) + " " + cardStudents.get(3) + "           |");
        result.add("  +-------------------+");
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

        result.add("        CLOUD #" + ind+"     ");
        if (capacity == 3) {
            result.add("         | |         ");
            result.add("     | |     | |     ");
            result.add(" | |      " + cliStudents.get(2) + "     | |  ");
            result.add(" | |   " + cliStudents.get(0) + "     " + cliStudents.get(1) + "  | |  ");
            result.add(" ------------------  ");
        } else if (capacity == 4) {
            result.add("         | |         ");
            result.add("     | |     | |     ");
            result.add(" | |   " + cliStudents.get(2) + "    " + cliStudents.get(3) + "   | |  ");
            result.add(" | |   " + cliStudents.get(0) + "    " + cliStudents.get(1) + "   | |  ");
            result.add(" ------------------  ");
        }
        return result;
    }

    public ArrayList<String> dashboardElement(ColoredDisc[] entrance, HashSet<ColoredDisc> professors, HashMap<ColoredDisc, Integer> tables, int towers, String towersColor, String nickname, boolean online) {
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<String> cliEntrance = new ArrayList<String>();
        HashMap<String, String> cliTables = new HashMap<>();
        HashMap<String, String> cliProfessors = new HashMap<>();
        ArrayList<String> cliTowers = new ArrayList<>();
        //default towers
        String color = ANSIColor.WHITE_BOLD;
        ArrayList<String> status = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            if (i < entrance.length) {
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
        for (String professor : Arrays.asList("RED", "PINK", "BLUE", "YELLOW", "GREEN")) {
            if (professors.contains(professor))
                cliProfessors.put(professor, "P");
            else
                cliProfessors.put(professor, " ");
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
            status.add(/*ANSIColor.GREEN_BACKGROUND + ANSIColor.BLACK_BOLD +*/ "ONLINE" /*+ ANSIColor.RESET*/);
        } else status.add(/*ANSIColor.RED_BACKGROUND + ANSIColor.BLACK_BOLD +*/ "OFFLINE"/* + ANSIColor.RESET*/);

        result.add(" _________________________________________ ");
        result.add("     Player: " + nickname + "  is   " + status +"             ");
        result.add("   ENTR  TABLES                 PROF TOW   ");
        result.add(" |------|---------------------|----|-----| ");
        result.add(" |    " + cliEntrance.get(0) + ANSIColor.RESET + " | " + ANSIColor.GREEN + cliTables.get("GREEN") + ANSIColor.RESET + "| " + ANSIColor.GREEN + cliProfessors.get("GREEN") + ANSIColor.RESET + "  | " + cliTowers.get(0) + " " + cliTowers.get(1) + ANSIColor.RESET + " | ");
        result.add(" |------|---------------------|----|-----| ");
        result.add(" |  " + cliEntrance.get(1) + " " + cliEntrance.get(2) + ANSIColor.RESET + " | " + ANSIColor.RED + cliTables.get("RED") + ANSIColor.RESET + "| " +ANSIColor.RED + cliProfessors.get("RED") + ANSIColor.RESET + "  | " + cliTowers.get(2) + " " + cliTowers.get(3) + ANSIColor.RESET + " | ");
        result.add(" |------|---------------------|----|-----| ");
        result.add(" |  " + cliEntrance.get(3) + " " + cliEntrance.get(4) + ANSIColor.RESET + " | " + ANSIColor.YELLOW + cliTables.get("YELLOW") + ANSIColor.RESET + "| " + ANSIColor.YELLOW + cliProfessors.get("YELLOW") + ANSIColor.RESET + "  | " + cliTowers.get(4) + " " + cliTowers.get(5) + ANSIColor.RESET + " | ");
        result.add(" |------|---------------------|----|-----| ");
        result.add(" |  " + cliEntrance.get(5) + " " + cliEntrance.get(6) + ANSIColor.RESET + " | " + ANSIColor.PURPLE_BRIGHT + cliTables.get("PINK") + ANSIColor.RESET + "| " + ANSIColor.PURPLE_BRIGHT + cliProfessors.get("PINK") + ANSIColor.RESET + "  | " + cliTowers.get(6) + " " + cliTowers.get(7) + ANSIColor.RESET + " | ");
        result.add(" |------|---------------------|----|-----| ");
        result.add(" |  " + cliEntrance.get(7) + " " + cliEntrance.get(8) + ANSIColor.RESET + " | " + ANSIColor.CYAN + cliTables.get("BLUE") + ANSIColor.RESET + "| " + ANSIColor.CYAN + cliProfessors.get("BLUE") + ANSIColor.RESET + "  | " + "    | ");
        result.add(" |------|---------------------|----|-----| ");

        return (ArrayList<String>) result.clone();
    }

    public ArrayList<String> lastUsedCardElement(String nickname, int cardNumber, int maxMoves, boolean used) {
        ArrayList<String> result = new ArrayList<>();
        result.add("   PLAYER: " + nickname+"        ");
        result.add("   LAST USED CARD     ");
        if(cardNumber==0){
            result.add("   ---not yet played--- ");
            result.add("                        ");
            result.add("                        ");
            result.add("                        ");
            result.add("                        ");
        }else {
            result.add("   +-----------------+");
            result.add("   | NUMBER    MOVES |");
            result.add("   |   " + cardNumber + "           " + maxMoves + "" + " |");
            result.add("   |                 |");
            //result.add("   |                 |");
            result.add("   +-----------------+");
            result.add("                      ");
        }
        return (ArrayList<String>) result.clone();
    }

    private ArrayList<String> islandElement(int ID, HashMap<ColoredDisc, Integer> students, ArrayList<Tower> towers, ArrayList<Integer> graphicalIslands, boolean prohibited) {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> islandStud = new ArrayList<>();
        int tower = 0;
        int id = ID+1;
        String color = "";
        String forbidden = "";

        String space =" ";
        String leftMargin = " ";
        String rightMargin = " ";
        String topMargin ="                 ";
        String bottomMargin = "                 ";

        if(id>=10){
            space = "";
        }

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
            leftMargin ="|";
        }
        if((ID == 7 || ID == 8 || ID == 9|| ID ==11 || ID ==10)){
            rightMargin = "|";
        }
        if((ID == 4 || ID == 5 || ID == 6|| ID == 7 || ID == 8)){
            topMargin = "-----------------";
        }
        if((ID == 0 || ID == 11 || ID == 10 || ID ==1 || ID==2)){
            bottomMargin = "-----------------";
        }
        result.add(leftMargin+topMargin+rightMargin);
        result.add(leftMargin+ forbidden +" ID: " + id +space+"          "+ANSIColor.RESET+rightMargin);
        result.add(leftMargin+" STUDENTS:       "+rightMargin);
        result.add(leftMargin+" " + islandStud.get(0) + " " + islandStud.get(1) + " " + islandStud.get(2) + " " + islandStud.get(3) + " " + islandStud.get(4) + "       "+rightMargin);
        result.add(leftMargin +color+" TOWER:    " +tower+ANSIColor.RESET+ "     "+rightMargin);
        result.add(leftMargin+bottomMargin+rightMargin);

        return (ArrayList<String>) result.clone();
    }

    //empty island space of 6 rows
    private ArrayList<String> emptyIsland(int type){
        ArrayList<String> result = new ArrayList<>();
        String blankSpace = "                   ";
        String edgeSpace = "-------------------";
        String topMargin = blankSpace;
        String bottomMargin = blankSpace;
        String rightMargin = "";
        String leftMargin = "";

        //this will be improved or removed if i change the idea of the united islands
        switch (type){
            case 1: case 2: {
                bottomMargin = edgeSpace;
                break;
            }
            case 7: case 8: {
                topMargin = edgeSpace;
                break;
            }
            case 4: case 5: {
                leftMargin = "|";
                blankSpace = "                  ";
                topMargin = blankSpace;
                bottomMargin = blankSpace;
                break;
            }
            case 10: case 11: {
                rightMargin = "|";
                blankSpace = "                  ";
                topMargin = blankSpace;
                bottomMargin = blankSpace;
            break;
            }
        }
        result.add(leftMargin+topMargin+rightMargin);

        for(int i=0;i<4;i++) {
            result.add(leftMargin+blankSpace+rightMargin);
        }

        result.add(leftMargin+bottomMargin+rightMargin);

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
            addition.add("|" + merged4.get(i) +'|');
        }

        return (ArrayList<String>) addition.clone();

    }

    //this is the method which will draw the islands map
    public ArrayList<String> islandsMap(ArrayList<jIsland> islands){
        String horizontalDelimiter= "-------------------";
        ArrayList<String> result = new ArrayList<>();
        ArrayList<Integer> islandsID = new ArrayList<>();
        ArrayList<String> [] myIsland = new ArrayList[12];

        for(int i=0; i<islands.size();i++)
        {
            islandsID.add(islands.get(i).ID);
        }

        int j = 0;
        for(int i=0; i<12; i++)
        {
            if(islandsID.contains(i))
            {
                myIsland [i] = islandElement(islands.get(j).ID, islands.get(j).students, islands.get(j).towerList, islands.get(j).graphicalIsland, islands.get(j).prohibited);
                j++;
            }
            else
            {
                //empty island given the index
                myIsland [i] = emptyIsland(i);
                //System.out.println("EMPTY"+i);
            }
        }

        result.add(horizontalDelimiter+horizontalDelimiter+horizontalDelimiter+horizontalDelimiter+"  ");
        result = merge4(myIsland[0], myIsland[1], myIsland[2], myIsland[3],result);
        result = merge4(myIsland[11], emptyIsland(-1), emptyIsland(-1), myIsland[4], result);
        result = merge4(myIsland[10], emptyIsland(-1), emptyIsland(-1), myIsland[5], result);
        result = merge4(myIsland[9], myIsland[8], myIsland[7], myIsland[6], result);
        result.add(horizontalDelimiter+horizontalDelimiter+horizontalDelimiter+horizontalDelimiter);

        return result;
    }

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
            for(int j = 0 ; j<arr2.get(arr2.size()-1).length() ; j++){
                space = space +" ";
            }
            for(int j=maxLen+1 ; j<arr1.size() ; j++){
                merged.add(arr1.get(j) + space);
            }
        }
        else if(arr1.size() < arr2.size()){
            for(int j = 0; j<arr1.get(arr1.size()-1).length(); j++){
                space = space +" ";
            }
            for(int j=maxLen+1; j<arr2.size();j++){
                merged.add(space + arr2.get(j));
            }
        }
        return merged;
    }


    public ArrayList<String> effectCardsMap(ArrayList<jEffectCard> effects){
        ArrayList<String> result = new ArrayList<>();

        for(int i=0 ; i<effects.size() ; i++){
            ArrayList<String> effectCard = effectCardElement(effects.get(i).ID, effects.get(i).price, effects.get(i).used, effects.get(i).students, effects.get(i).prohibitionCard);
            result.addAll(effectCard);
        }

        return result;
    }





    public ArrayList<String> dashboardMap(ArrayList<jPlayer> players){
        ArrayList<String> result = new ArrayList<>();

        ArrayList<String> arr1 = dashboardElement(players.get(0).myDashboard.entranceSpots, players.get(0).myDashboard.professorSpots, players.get(0).myDashboard.tables, players.get(0).myDashboard.towerNumber, players.get(0).towerColor.toString(), players.get(0).nickname, players.get(0).online);
        ArrayList<String> arr2 = dashboardElement(players.get(1).myDashboard.entranceSpots, players.get(1).myDashboard.professorSpots, players.get(1).myDashboard.tables, players.get(1).myDashboard.towerNumber, players.get(1).towerColor.toString(), players.get(1).nickname, players.get(1).online);
        result = merge(arr1, arr2);
        switch(players.size()){
            case 2: break;
            case 3:{
                ArrayList<String> arr3 = dashboardElement(players.get(2).myDashboard.entranceSpots, players.get(2).myDashboard.professorSpots, players.get(2).myDashboard.tables, players.get(2).myDashboard.towerNumber, players.get(2).towerColor.toString(), players.get(2).nickname, players.get(2).online);
                result = merge(result, arr3);
                break;
            }
        }
        return result;
    }

    public ArrayList<String> cloudMap(ArrayList<jCloud> clouds){
        ArrayList<String> result = new ArrayList<>();

        for(int i=0; i<clouds.size(); i++) {
            ArrayList<String> cloud1 = cloudElement(i, clouds.get(i).cloudCapacity, clouds.get(i).studentSpots);
            result.addAll(cloud1);
        }
        return result;
    }
    /*
    public ArrayList<String> playedCardsMap(jGameClass myGame){
        ArrayList<String> result = new ArrayList<>();
        int numPlayers = myGame.players.size();

        for(int i=0 ; i< myGame.playerCardValue.length ; i++){
            ArrayList<String> card = lastUsedCardElement(myGame.players.get(i).nickname, myGame.playerCardValue[i],myGame.players.get(i).deck.deck.get(myGame.playerCardValue[i]).maxMoves, myGame.players.get(i).deck.deck.get(myGame.playerCardValue[i]).used);
            result.addAll(card);
        }

        return result;
    }*/

    public ArrayList<String> gameMap(jGameClass myGame){
        ArrayList<String> result = new ArrayList<>();
        result.addAll(cloudMap(myGame.clouds));
        result = merge(result, islandsMap(myGame.islands));
        //if expert game add the effect cards
        //result = merge(result, effectCardsMap(myGame.ChosenCards));
        result.addAll(dashboardMap(myGame.players));
        return result;
    }

    public void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


    public static void main(String[] args) throws FileNotFoundException {
        EriantysCLI n = new EriantysCLI();

        //read the json

        Gson gson = new Gson();
        jGameClass myGame = new jGameClass();

        try (Reader reader = new FileReader("C:\\Users\\alenk\\Desktop\\json.json")) {
            myGame = gson.fromJson(reader, jGameClass.class);
            //System.out.println(myGame);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        ArrayList<String> exa = n.islandsMap(myGame.islands);

        for(String s : exa ){
            System.out.println(s);
        }*/

        ArrayList<String> gg = n.gameMap(myGame);
        for(String s : gg ){
            System.out.println(s);
        }


    }
}