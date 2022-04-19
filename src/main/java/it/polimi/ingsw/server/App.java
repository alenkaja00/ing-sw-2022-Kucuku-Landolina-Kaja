package it.polimi.ingsw.server;

import it.polimi.ingsw.server.controller.ServerNetwork;
import it.polimi.ingsw.server.controller.ServerController;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        /*
        ArrayList<String> nickNames = new ArrayList<String>();
        ArrayList<Wizard> wiz = new ArrayList<Wizard>();
        nickNames.add("endi");
        nickNames.add("giovanni");
        wiz.add(Wizard.WIZARD1);
        wiz.add(Wizard.WIZARD2);
        GameClass game = new GameClass("asd", 2, nickNames, wiz );
        //----end of Setup ------

        Gson gson = new Gson();
        String result = gson.toJson(game);
        System.out.println(result);
        */

        ServerController theController = new ServerController();
        ServerNetwork myconn = new ServerNetwork(3030, theController);

    }
}