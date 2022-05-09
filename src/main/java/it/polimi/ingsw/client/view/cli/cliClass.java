package it.polimi.ingsw.client.view.cli;

import it.polimi.ingsw.client.controller.ClientController;
import it.polimi.ingsw.client.view.ViewInterface;
import it.polimi.ingsw.client.view.cli.components.EriantysCLI;
import it.polimi.ingsw.server.model.cards.Wizard;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.*;

public class cliClass implements ViewInterface
{
    ClientController controller;
    EriantysCLI eriantysCLI;
    BufferedReader gameConsole = new BufferedReader(new InputStreamReader(System.in));
    ExecutorService threadManager = Executors.newCachedThreadPool();

    public cliClass(ClientController controller)
    {
        this.controller = controller;
        eriantysCLI = new EriantysCLI();
    }

    @Override
    public void startScreen(String serverIP) {
        flushCLI();

        printBlock(eriantysCLI.welcomeScene(serverIP));
        String message = "";
        do
        {
            message = nextLine("> ");

            switch (message.toUpperCase(Locale.ROOT))
            {
                case "HELP":
                    System.out.println("CONNECT - To connect to a game server");
                    System.out.println("NEWGAME - To create a new game");
                    System.out.println("QUIT - To exit the game");
                    break;
                case "CONNECT":
                    String ip = nextLine("Insert server ip: ");
                    int port = 0;
                    do {
                        try {
                            port = Integer.parseInt(nextLine("Insert server port: "));
                            break;
                        } catch (Exception e) {System.out.println("Not a port number");};
                    } while (true);
                    if (!controller.tryConnection(ip, port))
                        System.out.println("Server unavailable at the specified ip/port");
                    else
                    {
                        System.out.println("Successfully connected");
                        do
                        {
                            message = nextLine("Insert your nickname: ");
                            if (controller.sendNickname(message))
                            {
                                System.out.println("Nickname accepted");
                                break;
                            }
                            else
                            {
                                System.out.println("Unavailable nickname");
                            }
                        } while (true);
                        //new Thread(()->startScreen(ip)).start();
                        //return;
                    }
                    break;
                case "NEWGAME":
                    int playerNumber = 0;
                    Boolean expertMode = false;
                    do
                    {
                        try {
                            playerNumber = Integer.parseInt(nextLine("Insert player number: "));
                        } catch (Exception e) {System.out.println("Not a valid number");}
                        if (playerNumber == 2 || playerNumber == 3)
                        {
                            break;
                        }
                        else
                        {
                            System.out.println("Not a valid number");
                        }
                    }while (true);
                    do
                    {
                        String s = nextLine("Do you want to play with expert mode? [yes/no]: ");
                        if (s.toUpperCase(Locale.ROOT).equals("YES") )
                        {
                            expertMode = true;
                            break;
                        }
                        else if (s.toUpperCase(Locale.ROOT).equals("NO"))
                        {
                            expertMode = false;
                            break;
                        }
                        else
                        {
                            System.out.println("Not a valid option");
                        }
                    }while (true);
                    if (!controller.gameRequest(playerNumber, expertMode))
                    {
                        System.out.println("The game was not created");
                        break;
                    }
                    return;
                case "QUIT":
                    String s = nextLine("Are you sure? [yes/no]: ");
                    if (s.toUpperCase(Locale.ROOT).equals("YES"))
                        System.exit(0);
                    break;
                default:
                    System.out.println("Unrecognized message");
                    break;
            }
        }while (true);
    }


    @Override
    public void waitLobby() {
        flushCLI();
        System.out.println("... Waiting for more players ...");
        String s = "";
        do
        {
            s =  nextLine("Write quit to exit the lobby: ");
            if (s.toUpperCase(Locale.ROOT).equals("QUIT"))
            {
                controller.quitLobby();
                return;
            }
        } while (true);
    }


    @Override
    public void newGame() {
        flushCLI();
        System.out.println("Hi, the game started!!");
        String s = "";
        do {
            s = nextLine("Choose your wizard [WIZARD1-4]: ");
            try {
                Wizard selected = Wizard.valueOf(s.toUpperCase(Locale.ROOT));
                if (controller.requestWizard(selected))
                {
                    System.out.println("You selected: "+selected);
                    break;
                }
                else
                {
                    System.out.println("Unavailable selection");
                }
            }catch (Exception e){System.out.println("Unavailable selection");};
        } while (true);
    }


    @Override
    public void updateView(String json) {

    }

    @Override
    public void playHelper() {

    }

    @Override
    public void playETX() {

    }

    @Override
    public void playNature() {

    }

    @Override
    public void playCTE() {

    }

    @Override
    public void gameEnded(String endMessage) {

    }

    @Override
    public void messageScreen(String message) {

    }

    private void printBlock(ArrayList<String> message)
    {
        message.stream().forEach(x->System.out.println(x));
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

