package it.polimi.ingsw.client.view.cli;

import com.google.gson.Gson;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import it.polimi.ingsw.client.controller.ClientController;
import it.polimi.ingsw.client.view.ViewInterface;
import it.polimi.ingsw.client.view.cli.components.EriantysCLI;
import it.polimi.ingsw.client.view.gui.controllers.ClientControllerSingleton;
import it.polimi.ingsw.client.view.gui.controllers.GameSceneSingleton;
import it.polimi.ingsw.client.view.jsonObjects.jGameClassExpert;
import it.polimi.ingsw.server.model.cards.Wizard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class cliClass implements ViewInterface
{
    EriantysCLI eriantysCLI;
    BufferedReader gameConsole = new BufferedReader(new InputStreamReader(System.in));
    Gson gson = new Gson();
    jGameClassExpert gameData = new jGameClassExpert();

    private String ip;
    private int port;
    private int playerNumber = 0;
    private Boolean expertMode = false;
    private int countETX = 0;
    private Boolean playedEffect = false;

    private Thread mainThread;
    private ArrayList<String> gameView = new ArrayList<>();
    private String currentState = "START";
    private String nextLineMessage = "";

    public cliClass()
    {
        eriantysCLI = new EriantysCLI();

        /*new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        do {
                            try {
                                Thread.sleep(10000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            printBlock(eriantysCLI.welcomeScene(""));
                        }while (true);
                    }
                }
        ).start();*/
        mainThread = new Thread(
            new Runnable() {
                @Override
                public void run() {
                    flushCLI();
                    printBlock(eriantysCLI.welcomeScene(ClientControllerSingleton.getInstance().getClientController().getServerIP()));
                    do {
                        String s = nextLine("");
                        //System.out.println("[CLI LOG]: " + s);
                        parseInput(s);
                    }while (true);
                }
            }
        );
        mainThread.start();
    }

    private void changeState(String screen)
    {
        currentState = screen;
        switch (screen)
        {
            case "START":
                flushCLI();
                printBlock(eriantysCLI.welcomeScene(ClientControllerSingleton.getInstance().getClientController().getServerIP()));

                //------------

                //---------
                break;

            case "IP":
                System.out.println("Insert the server ip: ");
                break;
            case "PORT":
                System.out.println("Insert the server port: ");
                break;
            case "NICKNAME":
                System.out.println("Insert your nickname: ");
                break;

            case "NEWGAME":
                System.out.println("Insert player number: ");
                break;
            case "EXPERT":
                System.out.println("Do you want to exable expert mode? [yes/no]: ");
                break;
            case "WAITLOBBY":
                flushCLI();
                System.out.println("... Waiting for more players ...");
                System.out.println("Write quit to exit the lobby.");
                break;

            case "WIZARD":
                flushCLI();
                System.out.println("The game started! Select your wizard [WIZARD1-4]: ");
                break;
            case "HELPER":
                System.out.println("Select your helper card[1-10]: ");
                break;
            case "ETX":
                System.out.println("Move a student from your entrance to Islands/Tables" + (expertMode == true ? " or play an effect": "")+": ");
                break;
            case "NATURE":
                System.out.println("Select Mother Nature's number of steps" + (expertMode == true ? " or play an effect": "")+": ");
                break;
            case "CTE":
                System.out.println("Select a cloud to refill the entrance" + (expertMode == true ? " or play an effect": "")+": ");
                break;
            case "RECONNECTION":
                flushCLI();
                System.out.println("Waiting for readmission into the game...");
                break;

            case "WAIT":
                flushCLI();
                System.out.println("... Waiting ...");
                break;



            case "QUIT":
                System.out.println("Are you sure? [yes/no]: ");
                break;

            default:
                break;
        }
    }

    private void helpMessages()
    {
        switch (currentState)
        {
            case "START":
                System.out.println("CONNECT - To connect to a game server");
                System.out.println("NEWGAME - To create a new game");
                System.out.println("QUIT - To exit the game");
                break;
            case "ETX":
                System.out.println("ETT|entranceIndex");
                System.out.println("ETI|islandIndex|entranceIndex");
                if (expertMode)
                    System.out.println("EFFECT|CAVALIER\n" +
                            "EFFECT|CENTAUR\n" +
                            "EFFECT|VILLAIN\n" +
                            "EFFECT|MAGICIAN\n" +
                            "EFFECT|MONK|islandID|cardStudentIndex\n" +
                            "EFFECT|COOK|coloredDisc\n" +
                            "EFFECT|QUEEN|cardStudentIndex\n" +
                            "EFFECT|LADY|islandID\n" +
                            "EFFECT|JOLLY|indexCard1|indexEntrance1|[indexCard2]|[indexEntrance2]|[indexCard3]|[indexEntrance3]\n" +
                            "EFFECT|MUSICIAN|entranceIndex1|switchColor1|[entranceIndex2]|[switchColor2]\n" +
                            "EFFECT|BANDIT|studentColor\n" +
                            "EFFECT|LORD|islandID");
                break;
            case "NATURE":
                System.out.println("NATURE|motherNatureMoves");
                if (expertMode)
                    System.out.println("EFFECT|CAVALIER\n" +
                            "EFFECT|CENTAUR\n" +
                            "EFFECT|VILLAIN\n" +
                            "EFFECT|MAGICIAN\n" +
                            "EFFECT|MONK|islandID|cardStudentIndex\n" +
                            "EFFECT|COOK|coloredDisc\n" +
                            "EFFECT|QUEEN|cardStudentIndex\n" +
                            "EFFECT|LADY|islandID\n" +
                            "EFFECT|JOLLY|indexCard1|indexEntrance1|[indexCard2]|[indexEntrance2]|[indexCard3]|[indexEntrance3]\n" +
                            "EFFECT|MUSICIAN|entranceIndex1|switchColor1|[entranceIndex2]|[switchColor2]\n" +
                            "EFFECT|BANDIT|studentColor\n" +
                            "EFFECT|LORD|islandID");
                break;
            case "CTE":
                System.out.println("CTE|cloudIndex");
                if (expertMode)
                    System.out.println("EFFECT|CAVALIER\n" +
                            "EFFECT|CENTAUR\n" +
                            "EFFECT|VILLAIN\n" +
                            "EFFECT|MAGICIAN\n" +
                            "EFFECT|MONK|islandID|cardStudentIndex\n" +
                            "EFFECT|COOK|coloredDisc\n" +
                            "EFFECT|QUEEN|cardStudentIndex\n" +
                            "EFFECT|LADY|islandID\n" +
                            "EFFECT|JOLLY|indexCard1|indexEntrance1|[indexCard2]|[indexEntrance2]|[indexCard3]|[indexEntrance3]\n" +
                            "EFFECT|MUSICIAN|entranceIndex1|switchColor1|[entranceIndex2]|[switchColor2]\n" +
                            "EFFECT|BANDIT|studentColor\n" +
                            "EFFECT|LORD|islandID");
                break;
        }
    }

    private void parseInput(String input)
    {
        input = input.toUpperCase(Locale.ROOT);

        if (input.equals("HELP"))
        {
            helpMessages();
        }
        else
        {
            switch (currentState)
            {
                case "START":
                    parseStart(input);
                    break;
                case "IP":
                    ip = input;
                    changeState("PORT");
                    break;
                case "PORT":
                    parsePort(input);
                    break;
                case "NICKNAME":
                    parseNickname(input);
                    break;

                case "NEWGAME":
                    parseNewGame(input);
                    break;
                case "EXPERT":
                    parseExpert(input);
                    break;
                case "WAITLOBBY":
                    parseLobby(input);
                    break;

                case "WIZARD":
                    parseWizard(input);
                    break;
                case "HELPER":
                    parseHelper(input);
                    break;
                case "ETX":
                    parseETX(input);
                    break;
                case "NATURE":
                    parseNature(input);
                    break;
                case "CTE":
                    parseCTE(input);
                    break;

                case "QUIT":
                    parseQuit(input);
                    break;
                default:
                    System.out.println("Unrecognized Message");
                    break;
            }
        }
    }

    private void parseStart(String input)
    {
        switch (input)
        {
            case "CONNECT":
                changeState("IP");
                break;
            case "NEWGAME":
                changeState("NEWGAME");
                break;
            case "QUIT":
                changeState("QUIT");
                break;
            default:
                System.out.println("Unrecognised command");
                break;
        }
    }

    //CONNECTION
    private void parsePort(String input)
    {
        try {
            port = Integer.parseInt(input);
        }
        catch (Exception e)
        {
            System.out.println("Not a port number");
            changeState("START");
            return;
        }
        if (!ClientControllerSingleton.getInstance().getClientController().requestConnection(ip, port))
            System.out.println("Server unavailable at the specified ip/port");
        else
        {
            System.out.println("Successfully connected");
            changeState("NICKNAME");
        }
    }
    private void parseNickname(String input)
    {
        if (ClientControllerSingleton.getInstance().getClientController().requestNickname(input))
        {
            System.out.println("Nickname accepted");
            changeState("START");
        }
        else
        {
            System.out.println("Unavailable nickname");
        }
    }

    //NEWGAME
    private void parseNewGame(String input)
    {
        try {
            playerNumber = Integer.parseInt(input);
            if (playerNumber == 2 || playerNumber == 3)
            {
                changeState("EXPERT");
            }
            else
            {
                System.out.println("Not a valid number. Insert again: ");
            }
        } catch (Exception e) {System.out.println("Not a valid number. Insert again: ");}
    }
    private void parseExpert(String input)
    {
        if (input.equals("YES") )
        {
            expertMode = true;
        }
        else if (input.equals("NO"))
        {
            expertMode = false;
        }
        else
        {
            System.out.println("Not a valid option. Insert Again: ");
            return;
        }
        if (!ClientControllerSingleton.getInstance().getClientController().requestNewGame(playerNumber, expertMode))
        {
            System.out.println("The game was not created");
            changeState("START");
        }
    }
    private void parseLobby(String input)
    {
        if (input.equals("QUIT"))
        {
            ClientControllerSingleton.getInstance().getClientController().quitLobby();
        }
    }

    private void parseWizard(String input)
    {
        try {
            Wizard selected = Wizard.valueOf(input);
            if (ClientControllerSingleton.getInstance().getClientController().requestWizard(selected))
            {
                System.out.println("You selected: "+selected);
                changeState("LOCK");
                waitUnlock();
                //System.out.println("fine wait");
                changeState("HELPER");
            }
            else
            {
                System.out.println("Unavailable selection. Try again [WIZARD1-4]: ");
            }
        }catch (Exception e){System.out.println("Unavailable selection. Try again [WIZARD1-4]: ");};
    }

    private void parseHelper(String input)
    {
        int number = 0;
        try {
            number = Integer.parseInt(input);
            if(number <= 0 || number > 10 ){
                System.out.println("Unavailable selection, try again [1-10]: ");
                return;
            }
        }
        catch (Exception e)
        {
            System.out.println("Unavailable selection, try again [1-10]: ");
            return;
        }
        if (ClientControllerSingleton.getInstance().getClientController().requestHelper(number))
        {
            System.out.println("You selected helper card "+ input);
            changeState("LOCK");
            waitUnlock();
            changeState("ETX");
        }
        else
        {
            System.out.println("Unavailable selection, try again [1-10]: ");
        }

    }

    private void parseETX(String input)
    {
        List<String> parsedMessage = Arrays.asList(input.split("\\|"));
        if (parsedMessage.get(0).equals("ETT") || parsedMessage.get(0).equals("ETI"))
        {
            if (ClientControllerSingleton.getInstance().getClientController().requestString(input))
            {
                System.out.println("Correctly moved student "+parsedMessage.get(1)+".");
                countETX++;
                if (countETX == playerNumber+1)
                {
                    changeState("NATURE");
                    countETX=0;
                    return;
                }
            }
            else
            {
                System.out.println("Malformed Input, try again: ");
            }
        }
        else if (parsedMessage.get(0).equals("EFFECT") && playedEffect == false)
        {
            if (ClientControllerSingleton.getInstance().getClientController().requestString(input))
            {
                System.out.println("Correctly played effect "+parsedMessage.get(1)+".");
                playedEffect = true;
            }
            else
            {
                System.out.println("Malformed Input, try again: ");
            }
        }
        else
        {
            System.out.println("Malformed Input, try again: ");
        }
        System.out.println("You can still move a student" + (expertMode == true ? " or play an effect card": "") +": ");
    }

    private void parseNature(String input)
    {
        List<String> parsedMessage = Arrays.asList(input.split("\\|"));
        if (parsedMessage.get(0).equals("NATURE") && parsedMessage.size()==2)
        {
            if (ClientControllerSingleton.getInstance().getClientController().requestString(input))
            {
                System.out.println("Correctly moved Mother Nature.");
                changeState("CTE");
                return;
            }
            else
            {
                System.out.println("Malformed Input, try again: ");
            }
        }
        else if (parsedMessage.get(0).equals("EFFECT") && playedEffect == false)
        {
            if (ClientControllerSingleton.getInstance().getClientController().requestString(input))
            {
                System.out.println("Correctly played effect "+parsedMessage.get(1)+".");
                playedEffect = true;
            }
            else
            {
                System.out.println("Malformed Input, try again: ");
            }
        }
        else
        {
            System.out.println("Malformed Input or illegal move, try again: ");
        }
        System.out.println("You still need to move mother nature" + (expertMode == true ? " or play an effect card": "") +": ");
    }

    private void parseCTE(String input)
    {
        List<String> parsedMessage = Arrays.asList(input.split("\\|"));
        if (parsedMessage.get(0).equals("CTE"))
        {
            if (ClientControllerSingleton.getInstance().getClientController().requestString(input))
            {
                System.out.println("Correctly filled the entrance with students from island "+Integer.parseInt(parsedMessage.get(1))+".");
                changeState("CTE");
                changeState("LOCK");
                waitUnlock();
                //System.out.println("fine wait");
                changeState("HELPER");
                return;
            }
            else
            {
                System.out.println("Malformed Input, try again: ");
            }
        }
        else if (parsedMessage.get(0).equals("EFFECT") && playedEffect == false)
        {
            if (ClientControllerSingleton.getInstance().getClientController().requestString(input))
            {
                System.out.println("Correctly played effect "+parsedMessage.get(1)+".");
                playedEffect = true;
            }
            else
            {
                System.out.println("Malformed Input, try again: ");
            }
        }
        else
        {
            System.out.println("Malformed Input or illegal move, try again: ");
        }
        System.out.println("You still need to select a cloud" + (expertMode == true ? " or play an effect card": "") +": ");
    }

    //QUIT
    private void parseQuit(String input)
    {
        if (input.equals("YES"))
            System.exit(0);
        else
        {
            System.out.println("Returning to initial menu");
            changeState("START");
        }

    }


    @Override
    public void updateView(String json) {
        flushCLI();
        gameData = gson.fromJson(json, jGameClassExpert.class);
        printBlock(eriantysCLI.gameMap(gameData));
        if (ClientControllerSingleton.getInstance().getClientController().getViewLocked())
            System.out.println("Waiting for your turn");
        else
            System.out.println("Your turn to play");
    }

    @Override
    public void startScene(String serverIP) {
        changeState("START");
    }

    @Override
    public void wizardScene() {
        changeState("WIZARD");
    }

    @Override
    public void helperScene() {
        changeState("HELPER");
    }

    @Override
    public void waitLobbyScene() {
        changeState("WAITLOBBY");
    }

    @Override
    public void messageScene(String message) {

    }

    @Override
    public void endScene(String endMessage)
    {
        changeState("ENDSCENE");
        System.out.println("Player "+endMessage+" is the winner!");
        for (int i=1; i<11; i++)
        {
            System.out.println("Returning to start screen in "+(11-i)+"s...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        changeState("START");
    }

    @Override
    public void lockView() {
        changeState("LOCK");
    }

    @Override
    public void manageReconnection()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                changeState("RECONNECTION");
                ClientControllerSingleton.getInstance().getClientController().waitViewUnlock();
                changeState("HELPER");
            }
        }).start();
    }

    private void printBlock(ArrayList<String> message)
    {
        message.stream().forEach(x->System.out.println(x));
    }

    private void waitUnlock()
    {
        System.out.println("Waiting for your turn");
        ClientControllerSingleton.getInstance().getClientController().waitViewUnlock();
        System.out.println("It's your turn");
    }

    private void flushCLI()
    {
        System.out.println("_______________________________________________________________________________________________");
        eriantysCLI.clearConsole();
    }

    private String nextLine(String str)
    {
        String s = "";
        System.out.print(str);
        try {
            s = gameConsole.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
}

