package it.polimi.ingsw.cli.components;

public interface CLIScene {
    void update();
    void execute();
    void help();
    void println(String message);
    void print(String message);
    void print(char character);
    void error(String message);
    void clearConsole();
}
