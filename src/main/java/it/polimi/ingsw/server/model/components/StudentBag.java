package it.polimi.ingsw.server.model.components;

import java.util.*;

/**
 * this class represents the bag, which contains all the disks or tokens of the game
 * the number of disks is 130 and they are of 5 different colors
 */
public class StudentBag {
    private HashMap<ColoredDisc, Integer> bag = new HashMap<ColoredDisc, Integer>();
    int bagDimension = 26;

    /**
     * Constructor puts 26 tokens of each color in the bag
     * the bag is represented by a HashMap
     */
    public StudentBag()
    {
        for (ColoredDisc color : ColoredDisc.values())
            bag.put(color, bagDimension);
    }


    /**
     * this method extracts 2 elements of each color from the bag
     * the selected tokens are collected in a shuffled ArrayList
     * @return the extracted tokens
     * @throws IndexOutOfBoundsException if the bag does not contain at least 2 tokens for a color
     */
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

    /**
     * this method extracts a random disk from the bag
     * it selects a color based on a generated random integer and extracts it, decreasing the number of that color in the bag
     * @return is the extracted disk
     * @throws IndexOutOfBoundsException if the bag is empty
     */
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
                if (bag.get(randColor) > 0)
                {
                    bag.put(randColor, bag.get(randColor)-1 );
                    return (randColor);
                }

            } while(true);
        }
    }

    /**
     *
     * @return the current size of the bag
     */
    public int getSize()
    {

        return bag.values().stream().reduce(0,(x,y)->x+y);
    }

    /**
     *
     * @param color color to evaluate
     * @return remaining number of tokens of the input color
     */
    public int get(ColoredDisc color){
        return bag.get(color);
    }

    /**
     * the function adds a new color in the bag
     * @param color
     */
    public void addToBag(ColoredDisc color)
    {
        bag.put(color, bag.get(color)+1);
    }

    /**
     * the function adds a number n of a color in the bag
     * @param color
     * @param n
     */
    public void addToBag(ColoredDisc color, int n)
    {
        bag.put(color, bag.get(color)+n);
    }
}
