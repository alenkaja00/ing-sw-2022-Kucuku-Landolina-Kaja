package it.polimi.ingsw.model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Island {
    private int ID;
    private int playerNumber;
    private ArrayList<Integer> graphicalIsland = new ArrayList<>();
    private HashMap<ColoredDisc, Integer> students = new HashMap<ColoredDisc, Integer>();
    private ArrayList<Tower> towerList = new ArrayList<Tower>();

    public Island(int ID){
        this.ID = ID;
        this.graphicalIsland.add(ID); // at the beginning, the island number x corresponds to the graphical island number x
    }

    /*
    public  int returnInfluenceData()
    {
        int player;
        int influence[] = new int[playerNumber];
        for(int i=0;i<this.playerNumber;i++)
        {
            influence[i]=this.islandTowers[i];
        }
        for(int i=0;i<this.students.size();i++)
        {
            // player=Dashboard.getProfessor(students.get(i));
            // i need to know who owns the professors
            // influence[player] ++;
        }
        int pos=0,max=0;
        for(int i=0;i<playerNumber;i++)
        {
            if(influence[i]>max)
            {
                max = influence[i];
                pos =i;
            }
        }
        return pos;
    }*/

    public HashMap<ColoredDisc, Integer> getStudents()
    {
        return (HashMap<ColoredDisc, Integer>) students.clone();
    }

    public void addStudent(ColoredDisc newStudent)
    {
        students.put(newStudent, students.get(newStudent) + 1);

    }

    public ColoredDisc removeStudent(ColoredDisc newStudent) throws IllegalArgumentException
    {
        if (students.get(newStudent) > 0)
        {
            students.put(newStudent, students.get(newStudent) - 1);
            return  newStudent;
        }
        else
        {
            throw new IllegalArgumentException();
        }
//        for(int i= 0; i<students.size();i++)
//        {
//            if(students.get(i).equals(newStudent))
//            {
//                students.remove(i);
//                return newStudent;
//            }
//        }
    }

    public Tower[] getTowers()
    {
        return (Tower[])towerList.stream().toArray();
    }

    public void AddTower(Tower myTower)
    {
        towerList.add(myTower);
    }

    public Tower RemoveTower(Tower myTower) throws IllegalArgumentException
    {
        if (towerList.contains(myTower))
        {
            towerList.remove(myTower);
            return myTower;
        }
        else
        {
            throw new IllegalArgumentException();
        }

    }
}