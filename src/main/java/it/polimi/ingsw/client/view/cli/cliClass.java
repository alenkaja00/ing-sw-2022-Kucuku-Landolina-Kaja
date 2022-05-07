package it.polimi.ingsw.client.view.cli;

import it.polimi.ingsw.client.controller.ClientController;
import it.polimi.ingsw.client.view.ViewInterface;
import it.polimi.ingsw.client.view.cli.components.EriantysCLI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.PortUnreachableException;
import java.util.ArrayList;
import java.util.Locale;

public class cliClass implements ViewInterface
{
    ClientController controller;
    EriantysCLI eriantysCLI;
    BufferedReader gameConsole = new BufferedReader(new InputStreamReader(System.in));


    public cliClass(ClientController controller)
    {
        this.controller = controller;
        eriantysCLI = new EriantysCLI();
    }

    @Override
    public void startScreen(String serverIP) {
        System.out.println("_______________________________________________________________________________________________");
        eriantysCLI.clearConsole();

        printLine(eriantysCLI.welcomeScene(serverIP));
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
                        startScreen(ip);
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
                        String s = nextLine("Do you want to play with expert mode? [yes/no] ");
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
                        System.out.println("Temporary error with the server, the game could not be created");
                    }
                    break;
                case "QUIT":
                    System.out.println("Are you sure? [yes/no]");
                    String s = nextLine("> ");
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
    public void waitScreen(String message) {

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

    @Override
    public void newGame() {

    }

    private void printLine(ArrayList<String> message)
    {
        message.stream().forEach(x->System.out.println(x));
    }

    private String nextLine(String str)
    {
        String s = "";
        System.out.print(str);
        try {
            s = gameConsole.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
}
