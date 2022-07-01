package it.polimi.ingsw.client.controller;

import it.polimi.ingsw.client.view.gui.controllers.StageSingleton;
import it.polimi.ingsw.client.view.gui.controllers.WaitingController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

/**
 * Class ClientNetwork manages the flow of information on client side
 * it sends to the client controller the messages received from the server
 * similarly it sends to the server requests from the client controller
 */
public class ClientNetwork{

    Socket socket;
    Scanner socketIn;
    ClientController controller;
    PrintWriter socketOut;
    Boolean connected = false;

    /**
     * Constructor initializes the ClientNetwork instance, sets the ip and port on the socket
     * it contains a "ping" logic in order to keep the connection "alive"
     * @param controller reference of the ClientController
     * @throws IOException because of the socket nextLine method
     */
    public ClientNetwork(String ip, int port, ClientController controller) throws IOException {
        this.socket = new Socket(ip, port);
        this.controller = controller;
        socketOut = new PrintWriter(socket.getOutputStream());
        socketIn = new Scanner(socket.getInputStream());
        connected = true;

        new Thread(()->{
            InetAddress inet = null;
            while (true)
            {
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    inet = InetAddress.getByName(ip);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                try {
                    if (inet== null || !inet.isReachable(5000))
                    {
                        catchException();
                        finalException();
                    }
                } catch (IOException e) {
                    //e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            //System.out.println("Connection established");
            try
            {
                //manage reconnection
                String firstMessage = socketIn.nextLine();
                List<String> parsedMessage = Arrays.asList(firstMessage.split("\\|"));
                if (parsedMessage.get(0).equals("JSON"))
                    controller.reconnected(parsedMessage.get(1));
                else
                    controller.parseServerMessage(firstMessage);

                //manage normal messages
                while (true) {
                    String receivedLine = socketIn.nextLine();
                    this.controller.parseServerMessage(receivedLine);
                }
            }
            catch(Exception e)
            {
                catchException();
            }
            finally
            {
                finalException();
            }
        }).start();

        //Executors.newCachedThreadPool().execute(new ServerManager(socket, controller));
    }

    /**
     * informs the controller of a disconnected player
     */
    private void catchException()
    {
        //e.printStackTrace();
        System.out.println("Connection closed");
        controller.playerDisconnected();
        controller.lockGUI();
        connected = false;
    }

    /**
     * closes the connection after an exception, such as a disconnection
     */
    private void finalException()
    {
        socketIn.close();
        socketOut.close();
        try {
            socket.close();
        } catch (IOException e) {
            //e.printStackTrace();
        }

        System.out.println("Network disconnection. Close the game and reopen it to continue.");
        if (controller.getViewMode().equals("GUI"))
        {
            Parent root = null;
            Stage stage;
            Scene scene;
            stage = StageSingleton.getInstance().getStage();
            FXMLLoader loader = null;
            loader = new FXMLLoader(getClass().getResource("/fxmlFiles/networkDisconnected.fxml"));
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            scene = new Scene(root);
            stage.setTitle("Network error");
            stage.setScene(scene);
            stage.show();
        }
        for (int i=0; i<10; i++)
        {
            System.out.println("Closing in "+(10-i)+"s...");
        }
        System.exit(0);

    }

    /**
     * sends a message out on the socket
     */
    public void sendMessage(String message) {
        socketOut.println(message);
        socketOut.flush();
    }
}