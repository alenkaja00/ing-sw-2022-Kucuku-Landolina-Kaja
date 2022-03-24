package it.polimi.ingsw.model;

import java.util.ArrayList;

public class Cloud {


    private ArrayList<ColoredDisc> studentSpots;
    private int cloudCapacity;

    public Cloud(int cloudCapacity){

        this.cloudCapacity = cloudCapacity;
        this.studentSpots = new ArrayList<ColoredDisc>();

    }
    public void refillCloud(ArrayList<ColoredDisc> newStudentArray)
    {
        for(int i = 0; i < newStudentArray.size(); i++){
            //copying the new students in the arraylist from the parameter
            this.studentSpots.set(i,newStudentArray.get(i));
        }

    }

    public ArrayList<ColoredDisc> removeStudent()
    {
        ArrayList<ColoredDisc> ret = new ArrayList<ColoredDisc>();
        for(int i = 0 ; i < this.cloudCapacity ; i++)
        {
            ret.add(this.studentSpots.get(i));
            this.studentSpots.remove(i);

        }
        return ret;

    }



}
