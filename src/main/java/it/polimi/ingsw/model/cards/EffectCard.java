package it.polimi.ingsw.model.cards;

import it.polimi.ingsw.model.components.ColoredDisc;

import javax.swing.*;
import java.util.ArrayList;
import java.util.function.Function;

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

    public void setUp()
    {

    }

    public void useCard()
    {
        used = true;
    }

    public void run(){}

    public void end(){}

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



    public void addStudent(ColoredDisc student) {
        this.students.add(student);
    }
    public ColoredDisc removeStudent(ColoredDisc student)
    {
        this.students.remove(student);
        return student;
    }
    public ArrayList<ColoredDisc> getStudents()
    {
        return (ArrayList<ColoredDisc>) this.students.clone();
    }


}
