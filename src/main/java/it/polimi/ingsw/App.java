package it.polimi.ingsw;

import it.polimi.ingsw.model.cards.Wizard;
import it.polimi.ingsw.model.gameClasses.GameClass;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        ArrayList<String> nickNames = new ArrayList<String>();
        ArrayList<Wizard> wiz = new ArrayList<Wizard>();
        nickNames.add("endi");
        nickNames.add("giovanni");
        wiz.add(Wizard.WIZARD1);
        wiz.add(Wizard.WIZARD2);
        GameClass game = new GameClass("asd", 2, nickNames, wiz );

    }
}