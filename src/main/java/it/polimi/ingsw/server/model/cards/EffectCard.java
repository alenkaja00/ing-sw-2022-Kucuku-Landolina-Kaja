package it.polimi.ingsw.server.model.cards;

import it.polimi.ingsw.server.model.components.ColoredDisc;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * this class stores the data of the effect card
 * it contains the card id, the price, the used flag and eventually an array of students (disks) on it and prohibition cards
 */
public class EffectCard {

    private EffectName ID;
    private int price;
    private boolean used;
    private ColoredDisc[] students;
    public  int prohibitionCard = 0;

    /**
     * constructor sets up a card based on the input id
     * it sets the price of the card, the used flag and the array of students on it
     * @param myEffect identifier which determines the type of card to instantiate
     */
    public EffectCard(EffectName myEffect)
    {
        used = false;
        ID = myEffect;

        int studentsSize = 0;

        switch (myEffect)
        {
            case MONK:
                price = 1;
                studentsSize = 4;
                break;
            case QUEEN:
                price = 2;
                studentsSize = 4;
                break;
            case LADY:
                price = 2;
                prohibitionCard = 4;
                break;
            case JOLLY:
                price = 1;
                studentsSize = 6;
                break;
            case CAVALIER:
                price = 2;
                break;
            case LORD:
                price = 3;
                break;
            case CENTAUR:
                price = 3;
                break;
            case COOK:
                price = 3;
                break;
            case VILLAIN:
                price = 2;
                break;
            case MAGICIAN:
                price = 1;
                break;
            case MUSICIAN:
                price = 1;
                break;
            case BANDIT:
                price = 3;
                break;
            default:
                break;

        }
        if(studentsSize>0)
        {
            students = new ColoredDisc[studentsSize];
        }
        else
        {
            students = null;
        }

    }
    
    public void useCard()
    {
        used = true;
    }

    /**
     *
     * @return the price of a card
     */
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

    public EffectName getID() {
        return ID;
    }

    public boolean isUsed(){
        return  this.used;
    }

    /**
     *
     * @return the array of disks over a card
     */
    public ColoredDisc[] getStudents()
    {
        return students.clone();
    }

    /**
     *
     * @param myStudent disk to be added over the card
     * @param index array position where to store the disk
     */
    public void addStudent(ColoredDisc myStudent, int index)
    {
        if(students[index]!=null)
        {
            throw new IndexOutOfBoundsException();
        }
        else{
            students[index] = myStudent;
        }

    }

    /**
     *
     * @param index position of the student to be removed from the card
     * @return removed disk from the card
     */
    public ColoredDisc removeStudent(int index)
    {
        ColoredDisc disc = students[index];
        students[index] = null;
        return disc;
    }

    /**
     *
     * @return the number of prohibition tokens over a card
     */
    public int getProhibitionCard(){
        return this.prohibitionCard;
    }
}
