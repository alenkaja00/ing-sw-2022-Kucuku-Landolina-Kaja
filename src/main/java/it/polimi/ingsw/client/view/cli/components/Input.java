package it.polimi.ingsw.client.view.cli.components;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

public class Input implements Callable<String> {
    BufferedReader reader;

    public Input() throws IOException {
        reader = new BufferedReader (new InputStreamReader(System.in));
    }

    @Override
    public String call() throws IOException, InterruptedException {
        String input;
        while (!reader.ready()) {
            Thread.sleep(200);
        }
        input = reader.readLine();
        return input;
    }
}
