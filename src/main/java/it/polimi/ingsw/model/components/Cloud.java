package it.polimi.ingsw.model.components;

import java.security.InvalidParameterException;
import java.util.ArrayList;

public class Cloud {
    private ArrayList<ColoredDisc> studentSpots;
    private int cloudCapacity;

    public Cloud(int cloudCapacity){
        this.cloudCapacity = cloudCapacity;
        this.studentSpots = new ArrayList<ColoredDisc>();
    }

    public void AddStudents(ArrayList<ColoredDisc> newStudentArray) throws IndexOutOfBoundsException
    {
        if (studentSpots.size() + newStudentArray.size() > cloudCapacity) {throw new IndexOutOfBoundsException();}
        for (ColoredDisc student:newStudentArray){
            this.studentSpots.add(student); //copying the new students in the arraylist from the parameter
        }
    }

    public void AddStudent(ColoredDisc student) throws IndexOutOfBoundsException
    {
        if (studentSpots.size() + 1 > cloudCapacity) {throw new IndexOutOfBoundsException();}
        this.studentSpots.add(student); //copying the new students in the arraylist from the parameter
    }

    public ArrayList<ColoredDisc> getStudents()
    {
        return (ArrayList<ColoredDisc>) studentSpots.clone();
    }

    public ArrayList<ColoredDisc> removeAll()
    {
        ArrayList<ColoredDisc> clone = (ArrayList<ColoredDisc>) studentSpots.clone();
        studentSpots.removeAll(studentSpots);
        return clone;
    }

    public ColoredDisc removeStudent(ColoredDisc myStudent) throws InvalidParameterException
    {
        if (studentSpots.contains(myStudent))
        {
            studentSpots.remove(myStudent);
            return myStudent;
        }
        else
        {
            throw new InvalidParameterException();
        }
    }

    public int getCloudCapacity() {
        return cloudCapacity;
    }
}
