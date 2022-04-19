package it.polimi.ingsw.server.model.cards;

import it.polimi.ingsw.server.model.components.ColoredDisc;

import java.util.ArrayList;

public class EffectCard {

    private EffectName ID;
    private int price;
    private boolean used;
    private ArrayList<ColoredDisc> students;
    public  int prohibitionCard =0;

    public EffectCard(EffectName myEffect)
    {
        used = false;
        ID = myEffect;

        //we need to initialize the array somewhere, we will discuss this
        students = new ArrayList<ColoredDisc>();

        switch (myEffect)
        {
            case MONK:
                price = 1;
                break;
            case QUEEN:
                price = 2;
                break;
            case LADY:
                price = 2;
                prohibitionCard = 4;
                break;
            case JOLLY:
                price = 1;
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
    }
    
    public void useCard()
    {
        used = true;
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

    public EffectName getID() {
        return ID;
    }

    public boolean isUsed(){
        return  this.used;
    }

    public ArrayList<ColoredDisc> getStudents()
    {
        return (ArrayList<ColoredDisc>) this.students.clone();
    }

    public void addStudent(ColoredDisc myStudent)
    {
        students.add(myStudent);
    }

    public ColoredDisc removeStudent(ColoredDisc student)
    {
        this.students.remove(student);
        return student;
    }

}