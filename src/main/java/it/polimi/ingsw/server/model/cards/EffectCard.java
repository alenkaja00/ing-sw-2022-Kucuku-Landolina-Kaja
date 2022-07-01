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
    private String description;
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
                description = "Take 1 Student from this card and place it on an Island of your choice. Then, a new Student from the Bag will be placed on the card" ;
                studentsSize = 4;
                break;
            case QUEEN:
                price = 2;
                description = "Take 1 Student from this card and place it in your Dining Room. Then a Student from the bag will be placed on the card";
                studentsSize = 4;
                break;
            case LADY:
                price = 2;
                prohibitionCard = 4;
                description = "Place a No Entry tile on an Island you choice. The first time Mother Nature ends her movement there, No Entry tile will be put back onto this card, the influence will not be calculated on that Island, no Towers will be placed";
                break;
            case JOLLY:
                price = 1;
                description= "You may take up to 3 Students from this card and replace them with the same number of Students from your Entrance";
                studentsSize = 6;
                break;
            case CAVALIER:
                price = 2;
                description = "During the influence calculation this turn, you count as having 2 more influence";
                break;
            case LORD:
                price = 3;
                description = "During the influence calculation this turn, you count as having 2 more influence";
                break;
            case CENTAUR:
                price = 3;
                description = "When resolving a Conquering on an Island, Towers do not count towards influence";
                break;
            case COOK:
                price = 3;
                description = "Choose a color of Student: during the influence calculation this turn, that color adds no influence";
                break;
            case VILLAIN:
                price = 2;
                description = "During this turn, you take control of any number of Professors even if you have the same number of Students as the player who currently controls them";
                break;
            case MAGICIAN:
                price = 1;
                description = "You may move Mother Nature up to 2 additional Islands than is indicated by the Assistant card you've played";
                break;
            case MUSICIAN:
                price = 1;
                description = "You may exchange up to 2 Students between your Entrance and your Dining Room";
                break;
            case BANDIT:
                price = 3;
                description = "Choose a type of Student: every player must return 3 Students of that type from their Dining Room to the bag. If any player has fewer than 3 Students of that type, return as many Students as they have";
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
