package it.polimi.ingsw.model.cards;

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

}
