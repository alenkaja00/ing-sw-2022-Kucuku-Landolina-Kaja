package it.polimi.ingsw.cli.components;

import it.polimi.ingsw.cli.utils.ANSIColor;

public class InitScene extends GeneralScene{
    public InitScene(){
        super();
    }
    //Print the title of the game
    @Override
    public void show() {
        clearConsole();
        println(ANSIColor.PURPLE_BACKGROUND);
        println(ANSIColor.BLACK_BOLD + "--------------------------------------------------------------");
        println(ANSIColor.BLACK_BOLD + "-------------------WELCOME TO ERIANTYS GAME-------------------");
        println(ANSIColor.BLACK_BOLD+  "                                                              ");
        println(ANSIColor.BLACK_BOLD+  "                -> THIS IS THE CLI VERSION <-                 ");
        println(ANSIColor.BLACK_BOLD+  "--------------------------------------------------------------");
        println(ANSIColor.BLACK_BOLD+  "                  Press [Enter] to Continue                  ");
        println(ANSIColor.PURPLE_BACKGROUND);
    }

    //WAIT FOR THE ENTER COMMAND AND MOVE ON

}
