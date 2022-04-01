package it.polimi.ingsw.model.cards;

import it.polimi.ingsw.model.components.ColoredDisc;

import java.util.ArrayList;

public abstract class AbstractEffect {
    protected int price;
    protected boolean used;
    protected ArrayList<ColoredDisc> students;
    public  int prohibitionCard =0;
    private String ID;

    public AbstractEffect()
    {
        used = false;
    }

    public AbstractEffect(ArrayList<ColoredDisc> students)
    {
        used = false;
        this.students = students;
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

    public String getID() {
        return ID;
    }

    public void useCard()
    {
        used = true;
    }

    public void setUp() {}

    public void run(){}

    public void end(){}

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
