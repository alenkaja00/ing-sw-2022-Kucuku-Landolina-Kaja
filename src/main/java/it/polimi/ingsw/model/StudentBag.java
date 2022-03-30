package it.polimi.ingsw.model;

import java.util.*;

public class StudentBag {
    private HashMap<ColoredDisc, Integer> bag = new HashMap<ColoredDisc, Integer>();

    public StudentBag()
    {
        for (ColoredDisc color: ColoredDisc.values())
            bag.put(color, 26);
    }

    public ArrayList<ColoredDisc> pop2forColor() throws IndexOutOfBoundsException
    {
        ArrayList<ColoredDisc> shuffled = new ArrayList<ColoredDisc>();

        for (ColoredDisc color: ColoredDisc.values())
            if (bag.get(color) <2)
            {
                throw new IndexOutOfBoundsException();
            }

        for (ColoredDisc color: ColoredDisc.values())
        {
            bag.put(color, bag.get(color)-2);
            shuffled.add(color);
            shuffled.add(color);
        }

        Collections.shuffle(shuffled);
        return shuffled;
    }

    public ColoredDisc popRandom() throws IndexOutOfBoundsException
    {
        if (getSize()==0)
        {
            throw new IndexOutOfBoundsException();
        }
        else
        {
            do
            {
                int rand = (new Random()).nextInt(ColoredDisc.values().length);
                ColoredDisc randColor = ColoredDisc.values()[rand];
                if (bag.get(rand) > 0)
                {
                    bag.put(randColor, bag.get(randColor)-1 );
                    return (randColor);
                }

            } while(true);
        }
    }

    public int getSize()
    {

        return bag.values().stream().reduce(0,(x,y)->x+y);
    }
}
