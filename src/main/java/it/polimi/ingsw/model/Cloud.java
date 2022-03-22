package it.polimi.ingsw.model;

import java.util.ArrayList;

public class Cloud {


    private ArrayList<Student> studentSpots;
    private int cloudCapacity;

    public Cloud(int cloudCapacity){

        this.cloudCapacity = cloudCapacity;
        this.studentSpots = new ArrayList<Student>();

    }
    public void refillCloud(ArrayList<Student> newStudentArray)
    {
        for(int i = 0; i < newStudentArray.size(); i++){
            //copying the new students in the arraylist from the parameter
            this.studentSpots.set(i,newStudentArray.get(i));
        }

    }

    public void removeStudent()
    {


    }



}
