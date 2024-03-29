package it.polimi.ingsw.server.model.components;

import java.security.InvalidParameterException;
import java.util.ArrayList;

/**
 * class that represents the element cloud of the board game
 */
public class Cloud {
    private ArrayList<ColoredDisc> studentSpots;
    private int cloudCapacity;

    public Cloud(int cloudCapacity){
        this.cloudCapacity = cloudCapacity;
        this.studentSpots = new ArrayList<ColoredDisc>();
    }

    /**
     * this method is used to refill the cloud.
     *
     * @param newStudentArray
     * @throws IndexOutOfBoundsException if the number of new students is incorrect (exceeds cloud capacity)
     */
    public void AddStudents(ArrayList<ColoredDisc> newStudentArray) throws IndexOutOfBoundsException
    {
        if (studentSpots.size() + newStudentArray.size() > cloudCapacity) {throw new IndexOutOfBoundsException();}
        for (ColoredDisc student:newStudentArray){
            this.studentSpots.add(student); //copying the new students in the arraylist from the parameter
        }
    }

    /**
     * this method adds a student to the class array
     * @param student
     * @throws IndexOutOfBoundsException if the insertion will exceed the cloud capacity
     */
    public void AddStudent(ColoredDisc student) throws IndexOutOfBoundsException
    {
        if (studentSpots.size() + 1 > cloudCapacity) {throw new IndexOutOfBoundsException();}
        this.studentSpots.add(student); //copying the new students in the arraylist from the parameter
    }

    public ArrayList<ColoredDisc> getStudents()
    {
        return (ArrayList<ColoredDisc>) studentSpots.clone();
    }

    /**
     * this method removes all students from the clouds and returns them (for the cloud)
     * to entrance method
     * @return all the students
     */
    public ArrayList<ColoredDisc> removeAll()
    {
        ArrayList<ColoredDisc> clone = (ArrayList<ColoredDisc>) studentSpots.clone();
        studentSpots.removeAll(studentSpots);
        return clone;
    }

    /**
     *
     * @param myStudent is the color of the student to remove
     * @return the color of the selected student
     * @throws InvalidParameterException
     */
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
