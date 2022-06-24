package it.polimi.ingsw.server.model.cards;

/**
 * this class contains the data of a helper card such as its number,
 * the mother nature moves and a flag which signals if the card has been used or not
 */

public class HelperCard {
    private int cardNumber;
    private int maxMoves;
    private boolean used;

    public HelperCard(int cardNumber, int maxMoves)
    {
        this.cardNumber=cardNumber;
        this.maxMoves=maxMoves;
        this.used=false;
    }

    public void setUsed(boolean used)
    {
        this.used = used;
    }

    public boolean isUsed() {
        return used;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public int getMaxMoves() {
        return maxMoves;
    }
}
