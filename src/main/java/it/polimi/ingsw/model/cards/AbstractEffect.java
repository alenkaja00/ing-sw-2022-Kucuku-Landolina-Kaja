package it.polimi.ingsw.model.cards;

public abstract class AbstractEffect {
    private int price;
    private boolean used;

    public AbstractEffect()
    {
        used = false;
    }

    public int getPrice()
    {
        if (used)
        {
            return price +1;
        }
        else
        {
            return price;
        }

    }

    public void useCard()
    {
        used = true;
    }

    public void setUp() {}

    public void run(){}
}
