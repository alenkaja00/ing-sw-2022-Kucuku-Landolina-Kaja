package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;


public class Island {

    private int ID;
    private int playerNumber;
    private ArrayList<Integer> graphicalIsland ;
    private ArrayList<Student> students;
    private int islandTowers [];

    public Island(int ID,int playerNumber){

        this.ID = ID;
        this.playerNumber = playerNumber;
        this.graphicalIsland = new ArrayList<>();
        graphicalIsland.add(ID);
        // at the beginning, the island number x corresponds to the graphical island number x


        islandTowers = new int[playerNumber];
        for(int i= 0; i<playerNumber;i++)
        {
            islandTowers[i]=0;
        }
        // islandTowers may contain two or three integers, depending on the number of players
        // islandTowers[0]--> number of 1st player towers, islandTowers[1]--> number of 2nd player towers and so on


    }

    public  int evaluateInfluence()
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
    }

    public void addStudent(Student newStudent)
    {
        this.students.add(newStudent);

    }

    public void removeStudent(Student newStudent)
    {
        for(int i= 0; i<students.size();i++)
        {
            if(students.get(i).equals(newStudent))
            {
                students.remove(i);
                return;
            }
        }

    }

}