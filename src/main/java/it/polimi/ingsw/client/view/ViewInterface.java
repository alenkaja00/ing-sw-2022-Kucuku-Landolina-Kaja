package it.polimi.ingsw.client.view;

public interface ViewInterface
{
    //all the system.out.println("FATAL ERROR") are only for debugging purpose

    /* ***** ALL THE FOLLOWING METHODS RUN IN A NEW THREAD ***** */
    //salvatevi il clientcontroller da qualche parte, dal costruttore:
    //public ViewInterface(ClientController controller);

    //riceve una stringa con l'ip se c'è un server connesso, altrimenti la stringa nulla
    /*
    - Gestisce schermata iniziale e funzionalità di connessione e creazione partita,
    l'utente è libero di muoversi liberamente tra queste schermate

    - La view chiamerà il metodo tryConnection() e gestirà il valore di ritorno
    true=connesso, false=disconnesso

    - Se ci si riesce a connettere, si chiede al giocatore di inserire un nickname
    si chiama il metodo sendNickname e si gestisce il valore di ritorno
    true = ok, false = nok

    - Se l'utente naviga nella schermata nuova partita gli permette di inviare una richiesta,
    viene chiamato il metodo gameRequest() che ritorna:
    - false se non c'è un server connesso o ci sono errori
      => mostrare un messaggio di errore
    - true in tutti gli altri casi, da qui è il controller a gestire tutto
    */
    //shows message when connected to server
    public void startScene(String serverIP);


    /*
    La schermata wait viene mostrata quando il server è in attesa di altri giocatori per creare la partita
    se si è nella schermata WAIT mostrare un pulsante QUIT,
    che chiama il metodo quitLobby() e riporta l'user nella schermata iniziale*/
    public void waitLobbyScene(); //trasparente


    /* ****** ALL THE FOLLOWING MESSAGES DO NOT RUN IN A NEW THREAD BUT IN THE MAIN THREAD ************ */

    //update view from json
    public void updateView(String json);

    /* ALONG WITH THEIR NORMAL FUNCTION THESE METHODS LET THE USER PLAY AN EFFECT CARD
        WITH THE requesEffect() method if expertMode is ON
     */

    void wizardScene();

    //enables the view to let the user choose a helper,
    // calls the requestHelper() method which return true or false based on the result
    // only allow the user to play the helper according to the rules
    void helperScene();

    //game ends with the following message
    void messageScene(String message);

    //game ends with the following message
    void endScene(String endMessage);


}
