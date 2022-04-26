package it.polimi.ingsw.client.view.cli.components;
import it.polimi.ingsw.client.view.cli.utils.ANSIColor;

import java.util.*;

public class EriantysCLI {

    public EriantysCLI(){

    }
    public static ArrayList<String> welcomeScreen(){
        ArrayList<String> result = new ArrayList<>();
        result.add(ANSIColor.CYAN_BACKGROUND);
        result.add(ANSIColor.BLACK_BOLD);
        result.add("_  _  _ _______        _______  _____  _______ _______      _______  _____ ");
        result.add("|  |  | |______ |      |       |     | |  |  | |______         |    |     |");
        result.add("|__|__| |______ |_____ |_____  |_____| |  |  | |______         |    |_____|");
        //result.add(" ");
        result.add("        _______  ______ _____ _______ __   _ _______ __   __ _______");
        result.add("        |______ |_____/   |   |_____| | \\  |    |      \\_/   |______");
        result.add("        |______ |    \\_ __|__ |     | |  \\_|    |       |    ______|");
        result.add("");
        result.add("                    press [ENTER] to continue ...");
        result.add(ANSIColor.RESET);
        return (ArrayList<String>) result.clone();
    }

    public static ArrayList<String> insertNickname(boolean valid) {
        ArrayList<String> result = new ArrayList<>();
        if(valid) {
            result.add(ANSIColor.WHITE_BOLD);
            result.add(" PLEASE INSERT YOUR NICKNAME BELOW ");
            result.add(" --------------------------------- ");
            result.add(" -> ");
            result.add(ANSIColor.RESET);
        }
        else if(!valid){
            result.add(ANSIColor.WHITE_BOLD);
            result.add(" This nickname is not available!   ");
            result.add(" Please, provide a new nickname    ");
            result.add(" --------------------------------- ");
            result.add(" -> ");
            result.add(ANSIColor.RESET);
        }


        return (ArrayList<String>) result.clone();
    }

    public static ArrayList<String> loadingScreen(){
        ArrayList<String> result = new ArrayList<>();
        result.add(ANSIColor.GREEN_BOLD);
        result.add(" ");
        result.add("LOADING, PLEASE WAIT ...");
        result.add(" ");
        result.add(ANSIColor.RESET);

        return (ArrayList<String>) result.clone();
    }

    public static ArrayList<String> errorMessage(){
        ArrayList<String> result = new ArrayList<>();
        result.add(ANSIColor.RED_BOLD);
        result.add(" ");
        result.add("WARNING , INSERT A VALID COMMAND ...");
        result.add(" ");
        result.add(ANSIColor.RESET);

        return (ArrayList<String>) result.clone();
    }


    public static ArrayList<String> createGame(boolean playerVariant){
        ArrayList<String> result = new ArrayList<>();
        if(playerVariant) {
            result.add("Game Creation");
            result.add("Select the number of players (you can choose 2 or 3 by typing the number )");
            result.add("-> ");
        }
        else {
            result.add("Type -> norm  to play the normal version of the game ");
            result.add("Type -> exp  to play the expert version of the game ");
            result.add("-> ");
        }
        return (ArrayList<String>) result.clone();
    }

    public void chooseWizard(){

    }


    public static ArrayList<String> cloudGrid(int index, int capacity, ArrayList<String> students){
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> cliStudents = new ArrayList<>();

        for(int i=0;i<capacity;i++){
                switch(students.get(i)){
                    case "PINK": cliStudents.add(ANSIColor.PURPLE_BRIGHT+'X'+ANSIColor.RESET); break;
                    case "RED": cliStudents.add(ANSIColor.RED+'X'+ANSIColor.RESET); break;
                    case "BLUE": cliStudents.add(ANSIColor.CYAN+'X'+ANSIColor.RESET); break;
                    case "GREEN": cliStudents.add(ANSIColor.GREEN+'X'+ANSIColor.RESET); break;
                    case "YELLOW": cliStudents.add(ANSIColor.YELLOW+'X'+ANSIColor.RESET); break;
                }
            }

        int ind = index +1;
        result.add("        CLOUD #"+ ind );
        if(capacity == 3) {
            result.add("         | |         ");
            result.add("     | |     | |     ");
            result.add(" | |      " + cliStudents.get(2) + "     | |");
            result.add(" | |  " + cliStudents.get(0) + "       " + cliStudents.get(1) + "  | |");
            result.add("     | |     | |     ");
            result.add("         | |         ");
        }
        else if(capacity==4){
            result.add("         | |         ");
            result.add("     | |     | |     ");
            result.add(" | |   " + cliStudents.get(2) + "     " + cliStudents.get(3) + "   | |");
            result.add(" | |   " + cliStudents.get(0) + "     " + cliStudents.get(1) + "   | |");
            result.add("     | |     | |     ");
            result.add("         | |         ");
        }




        return result;
    }

    public static ArrayList<String> dashboardGrid(ArrayList<String> entrance, ArrayList<String> professors, HashMap<String, Integer> tables, int towers, String towersColor, String nickname, boolean online){
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<String> cliEntrance =  new ArrayList<String>();
        HashMap<String, String> cliTables = new HashMap<>();
        HashMap<String, String> cliProfessors = new HashMap<>();
        ArrayList<String> cliTowers = new ArrayList<>();
        //default towers
        String color = ANSIColor.WHITE_BOLD;
        ArrayList<String> status = new ArrayList<>();

        for (int i = 0; i<9; i++)
        {
            if (i<entrance.size())
            {
                switch (entrance.get(i))
                {
                    case "PINK":
                        cliEntrance.add(ANSIColor.PURPLE_BRIGHT+"X");
                        break;
                    case "BLUE":
                        cliEntrance.add(ANSIColor.CYAN+"X");
                        break;
                    case "RED":
                        cliEntrance.add(ANSIColor.RED+"X");
                        break;
                    case "GREEN":
                        cliEntrance.add(ANSIColor.GREEN+"X");
                        break;
                    case "YELLOW":
                        cliEntrance.add(ANSIColor.YELLOW+"X");
                        break;
                }
            }
            else
            {
                cliEntrance.add(" ");
            }
        }
        for (String myString:tables.keySet())
        {
            String table = "";
            for (int i=0; i<10; i++)
            {
                if (tables.get(myString)-1 >= i)
                    table+= "X ";
                else
                    table+= "  ";
            }
            cliTables.put(myString, table);
        }
        for (String professor: Arrays.asList("RED", "PINK", "BLUE", "YELLOW", "GREEN"))
        {
            if (professors.contains(professor))
                cliProfessors.put(professor, "P");
            else
                cliProfessors.put(professor, " ");
        }

        switch(towersColor)
        {
            case "BLACK": color = ANSIColor.BLACK_BOLD; break;
            case "GREY":  color = ANSIColor.BLACK_UNDERLINED; break;
        }

        for(int i=0;i<8;i++)
        {
            if(i<towers)
            {
                cliTowers.add(color+"X");
            }
            else cliTowers.add(" ");
        }

        if(online){
            status.add(ANSIColor.GREEN_BACKGROUND+ANSIColor.BLACK_BOLD+"ONLINE"+ANSIColor.RESET);
        }
        else status.add(ANSIColor.RED_BACKGROUND+ANSIColor.BLACK_BOLD+"OFFLINE"+ANSIColor.RESET);

        result.add(" _______________________________________");
        result.add(" |Dashboard of player: "+nickname+"    "+status+"|");
        result.add("   ENTR  TABLES              PROF TOW  ");
        result.add(" |-----|---------------------|---|-----|");
        result.add(" |   "+cliEntrance.get(0)+ANSIColor.RESET+" | "+ANSIColor.GREEN+cliTables.get("GREEN")+"| "+cliProfessors.get("GREEN")+ANSIColor.RESET+" | "+ cliTowers.get(0)+" "+cliTowers.get(1)+ANSIColor.RESET+" |");
        result.add(" |-----|---------------------|---|-----|");
        result.add(" | "+cliEntrance.get(1)+" "+cliEntrance.get(2)+ANSIColor.RESET+" | "+ANSIColor.RED+cliTables.get("RED")+"| "+cliProfessors.get("RED")+ANSIColor.RESET+" | "+ cliTowers.get(2)+" "+cliTowers.get(3)+ANSIColor.RESET+" |");
        result.add(" |-----|---------------------|---|-----|");
        result.add(" | "+cliEntrance.get(3)+" "+cliEntrance.get(4)+ANSIColor.RESET+" | "+ANSIColor.YELLOW+cliTables.get("YELLOW")+"| "+cliProfessors.get("YELLOW")+ANSIColor.RESET+" | "+ cliTowers.get(4)+" "+cliTowers.get(5)+ANSIColor.RESET+" |");
        result.add(" |-----|---------------------|---|-----|");
        result.add(" | "+cliEntrance.get(5)+" "+cliEntrance.get(6)+ANSIColor.RESET+" | "+ANSIColor.PURPLE_BRIGHT+cliTables.get("PINK")+"| "+cliProfessors.get("PINK")+ANSIColor.RESET+" | "+ cliTowers.get(6)+" "+cliTowers.get(7)+ANSIColor.RESET+" |");
        result.add(" |-----|---------------------|---|-----|");
        result.add(" | "+cliEntrance.get(7)+" "+cliEntrance.get(8)+ANSIColor.RESET+" | "+ANSIColor.CYAN+cliTables.get("BLUE")+"| "+cliProfessors.get("BLUE")+ANSIColor.RESET+" | "+"    |");
        result.add(" |-----|---------------------|---|-----|");

        return (ArrayList<String>) result.clone();
    }


    public static void main(String[] args) {

        ArrayList<String> entr= new ArrayList<>(Arrays.asList("PINK","BLUE","RED","GREEN"));
        ArrayList<String> prof= new ArrayList<>(Arrays.asList("GREEN","YELLOW"));
        HashMap<String, Integer> stud= new HashMap<>();
        stud.put("RED",1);
        stud.put("GREEN",3);
        stud.put("YELLOW",1);
        stud.put("BLUE",0);
        stud.put("PINK",7);
        ArrayList<String> result = dashboardGrid(entr,prof,stud,7,"WHITE","ALEN",true);

        ArrayList<String> cloud = cloudGrid(0,4,entr);
        ArrayList<String> game = welcomeScreen();
        ArrayList<String> nick = insertNickname(true);
        ArrayList<String> game1 = createGame(true);
        ArrayList<String> game2 = createGame(false);


       //dashboard
        for (String s:result)
        {
            System.out.println(s);
        }
        //cloud
        /*
        for (String s:cloud)
        {
            System.out.println(s);
        }*/

        for (String s:cloud)
        {
            System.out.println(s);
        }
        for (String s:game2)
        {
            System.out.println(s);
        }


    }

    public static void clearConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}