package it.polimi.ingsw.model.components;

import java.util.*;

public class StudentBag {
    private HashMap<ColoredDisc, Integer> bag = new HashMap<ColoredDisc, Integer>();

    public StudentBag(boolean first)
    {
        int bagDimension;
        if(first)
        {
            bagDimension = 2;
        }
        else{
            bagDimension = 25;
        }
        {
            for (ColoredDisc color : ColoredDisc.values())
                bag.put(color, bagDimension);
        }
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


    /**
     * the function adds a new color in the bag
     * @param color
     */
    public void addToBag(ColoredDisc color)
    {
        bag.put(color, bag.get(color)+1);
    }

    public void addToBag(ColoredDisc color, int n)
    {
        bag.put(color, bag.get(color)+n);
    }
}
