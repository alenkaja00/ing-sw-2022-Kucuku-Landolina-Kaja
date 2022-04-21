package it.polimi.ingsw.cli.components;
import it.polimi.ingsw.cli.utils.ANSIColor;
import java.util.Scanner;

public class GeneralScene implements CLIScene{

    //protected final Scanner input;

    public GeneralScene(){
        //this.input = new Scanner(System.in);
    }

    //Methods overrided by the scenes
    public void update(){}
    public void show(){}
    public void execute(){}
    public void help(){}

    //println moves the cursor to a new line after printing, print does not
    public void println(String message){
        System.out.println(message);
    }

    public void print(String message){
        System.out.print(message);
    }

    public void print(char character){
        System.out.print(character);
    }
    public void error(String message){
        System.out.println(ANSIColor.RED + message + ANSIColor.RESET);
    }
    public void clearConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}