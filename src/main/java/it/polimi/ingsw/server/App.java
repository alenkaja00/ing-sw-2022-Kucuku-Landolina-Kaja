package it.polimi.ingsw.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw.client.view.cli.utils.ANSIColor;
import it.polimi.ingsw.server.controller.ClientManager;
import it.polimi.ingsw.server.controller.ServerNetwork;
import it.polimi.ingsw.server.controller.ServerController;
import it.polimi.ingsw.server.model.cards.Wizard;
import it.polimi.ingsw.server.model.gameClasses.GameClass;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class App 
{

    public static void main( String[] args ) throws IOException {

        ArrayList<String> nickNames = new ArrayList<>();
        ArrayList<Wizard> wiz = new ArrayList<>();
        nickNames.add("endi");
        nickNames.add("giovanni");
        wiz.add(Wizard.WIZARD1);
        wiz.add(Wizard.WIZARD2);
        GameClass game = new GameClass("asd", 2, nickNames, wiz );
        //----end of Setup ------

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String result = gson.toJson(game);
        System.out.println(result);


        //ServerController theController = new ServerController();
        //ServerNetwork myconn = new ServerNetwork(3030, theController);

    }
}

