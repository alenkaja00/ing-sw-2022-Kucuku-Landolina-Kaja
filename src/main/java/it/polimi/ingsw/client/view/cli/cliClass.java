package it.polimi.ingsw.client.view.cli;

import com.google.gson.Gson;
import it.polimi.ingsw.client.view.ViewInterface;
import it.polimi.ingsw.client.view.cli.components.EriantysCLI;
import it.polimi.ingsw.client.view.cli.utils.ANSIColor;
import it.polimi.ingsw.client.view.gui.controllers.ClientControllerSingleton;
import it.polimi.ingsw.client.view.jsonObjects.jEffectCard;
import it.polimi.ingsw.client.view.jsonObjects.jGameClassExpert;
import it.polimi.ingsw.server.model.cards.Wizard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * class that implements the view interface and contains the logic of the command line version of the game
 * the methods work as a finite state machine and move from one state to the other in order to show different screens of the cli
 */
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

    private HashMap<jEffectCard, String> effectCommands;
    private HashMap<jEffectCard, String> effectDescriptions;
    private ArrayList<String> effectStrings = new ArrayList<>(Arrays.asList("EFFECT|CAVALIER","EFFECT|CENTAUR", "EFFECT|VILLAIN",
            "EFFECT|MAGICIAN", "EFFECT|MONK|islandID|cardStudentIndex", "EFFECT|COOK|coloredDisc", "EFFECT|QUEEN|cardStudentIndex",
            "EFFECT|LADY|islandID", "EFFECT|JOLLY|indexCard1|indexEntrance1|[indexCard2]|[indexEntrance2]|[indexCard3]|[indexEntrance3]",
            "EFFECT|MUSICIAN|entranceIndex1|switchColor1|[entranceIndex2]|[switchColor2]","EFFECT|BANDIT|studentColor", "EFFECT|LORD|islandID"));

    /**
     * the constructor method starts the thread used to parse the user's input
     */
    public cliClass()
    {
        eriantysCLI = new EriantysCLI();

        effectCommands = new HashMap<>();
        effectDescriptions = new HashMap<>();

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

    /**
     * method used to change the displayed screen
     * @param screen is the input string that determines the screen to be shown at a specific point
     */
    private void changeState(String screen)
    {
        currentState = screen;
        switch (screen)
        {
            case "START":
                flushCLI();
                printBlock(eriantysCLI.welcomeScene(ClientControllerSingleton.getInstance().getClientController().getServerIP()));
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

    /**
     * method that prints info in order to help the user and suggest the command to type
     */
    private void helpMessages()
    {
        if(expertMode) {
            for (jEffectCard card: gameData.ChosenCards) {
                effectCommands.put(card, effectStrings.stream().filter(x -> x.contains(card.ID.toString())).collect(Collectors.toList()).get(0));
                effectDescriptions.put(card, card.description);
            }
        }

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
                showEffectsMenu();
                break;
            case "NATURE":
                System.out.println("NATURE|motherNatureMoves");
                showEffectsMenu();
                break;
            case "CTE":
                System.out.println("CTE|cloudIndex");
                showEffectsMenu();
                break;
        }
    }

    /**
     * this method parses the user's input and calls other methods of the class that will parse the input
     * @param input string coming from the user input
     */
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

    /**
     * this method handles the input in the start screen and changes state to the next one based on the input command
     * @param input string coming from the user input
     */
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

    /**
     * this method handles the input in the connection screen and changes state to the next one based on the input command
     * @param input string coming from the user input
     */
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

    /**
     * this method handles the input in the nickname screen and changes state to the next one based on the input command
     * @param input user input string
     */
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

    /**
     * this method handles the input in the newgame screen and changes state to the next one based on the input command
     * @param input user input string
     */
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

    /**
     * this method handles the input in the expert mode screen and changes state to the next one based on the input command
     * @param input user input string
     */
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

    /**
     * this method handles the input in the lobby screen
     * @param input user input string
     */
    private void parseLobby(String input)
    {
        if (input.equals("QUIT"))
        {
            ClientControllerSingleton.getInstance().getClientController().quitLobby();
        }
    }

    /**
     * this method handles the input in the wizard selection screen and changes state to the next one based on the server response
     * @param input user input string
     */
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

    /**
     * this method handles the input in the helper card screen
     * @param input user input string
     */
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
            playedEffect = false;
            changeState("ETX");
        }
        else
        {
            System.out.println("Unavailable selection, try again [1-10]: ");
        }

    }

    /**
     * this method handles the input during the entrance to tables or entrance to island moves
     * it requests to the server the moves and based on the response changes state
     * it also includes the management of an effect card selection
     * @param input  user input string
     */
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

    /**
     * this method handles the move mother nature action of the player and includes the management of the effect card selection
     * @param input user input string
     */
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

    /**
     * this method handles the selection of a cloud from the user
     * @param input user input string
     */
    private void parseCTE(String input)
    {
        List<String> parsedMessage = Arrays.asList(input.split("\\|"));
        if (parsedMessage.get(0).equals("CTE"))
        {
            if (ClientControllerSingleton.getInstance().getClientController().requestString(input))
            {
                System.out.println("Correctly filled the entrance with students from cloud "+Integer.parseInt(parsedMessage.get(1))+".");
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

    /**
     * this method handles the quit from the game
     * @param input user input string
     */
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

    /**
     * this method is used to update the view visualizing the data from the json
     * it prints the gameMap calling the method that prints the ArrayList containing all the elements
     * @param json the input state of the game, contains all the data
     */
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

    /**
     * this method changes state to the Start Scene
     */
    @Override
    public void startScene(String serverIP) {
        changeState("START");
    }

    /**
     * this method changes state to the Wizard Scene
     */
    @Override
    public void wizardScene() {
        changeState("WIZARD");
    }

    /**
     * this method changes state to the Helper Scene
     */
    @Override
    public void helperScene() {
        changeState("HELPER");
    }

    /**
     * this method changes state to the Wait Lobby
     */
    @Override
    public void waitLobbyScene() {
        changeState("WAITLOBBY");
    }


    @Override
    public void messageScene(String message) {

    }

    /**
     * this method displays the winner of the game and brings back to the start screen where the user can play a newgame
     * @param endMessage message that is shown
     */
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

    /**
     * method that locks the view
     */
    @Override
    public void lockView() {
        changeState("LOCK");
    }

    /**
     * this method handles the reconnection functionality from the command line version
     */
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

    /**
     * method used to print an ArrayList of string
     * @param message the ArrayList to be printed
     */
    private void printBlock(ArrayList<String> message)
    {
        message.stream().forEach(x->System.out.println(x));
    }

    /**
     * this method blocks the input waiting for the server unlock
     */
    private void waitUnlock()
    {
        System.out.println("Waiting for your turn");
        ClientControllerSingleton.getInstance().getClientController().waitViewUnlock();
        System.out.println("It's your turn");
    }

    /**
     * this method filters the effect card descriptions and shows the current game helper cards' commands and descriptions
     */
    private void showEffectsMenu()
    {
        if(expertMode)
        {
            System.out.println("Effects: commands and descriptions");
            for (jEffectCard card : gameData.ChosenCards)
            {
                System.out.print(effectCommands.get(card) + " -> ");
                System.out.println(effectDescriptions.get(card));
            }
        }
    }

    /**
     * this method clears the screen
     */
    private void flushCLI()
    {
        System.out.println("_______________________________________________________________________________________________");
        eriantysCLI.clearConsole();
    }

    /**
     * method that prints a message and reads the input from the dedicated thread
     * @param str string that is printed
     * @return the read string
     */
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