package it.polimi.ingsw.server.model.components;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * class Island handles all the data needed to represent an island
 */
public class Island {
    private int ID;
    private ArrayList<Integer> graphicalIsland = new ArrayList<>();
    private HashMap<ColoredDisc, Integer> students = new HashMap<ColoredDisc, Integer>();
    private ArrayList<Tower> towerList = new ArrayList<Tower>();
    public int prohibitedValue = 0;

    /**
     * class constructor
     * @param ID is used to uniquely identify the island, range from 0 to 11
     */
    public Island(int ID){
        this.ID = ID;
        this.graphicalIsland.add(ID); // at the beginning, the island number x corresponds to the graphical island number x
        for (ColoredDisc color: ColoredDisc.values())
        {
            students.put(color, 0);
        }
    }

    /**
     * graphical islands separate islands logic from the gui islands logic
     * @param ID
     */
    public void addGraphicalIslands(int ID)
    {
        this.graphicalIsland.add(ID);
    }


    public HashMap<ColoredDisc, Integer> getStudents()
    {
        return (HashMap<ColoredDisc, Integer>) students.clone();
    }

    /**
     * adds a student to island
     * @param newStudent is the color of the new student to add
     */
    public void addStudent(ColoredDisc newStudent)
    {
        students.put(newStudent, students.get(newStudent) + 1);

    }

    /**
     * adds several students
     * @param newStudents represents a list of student to add
     */
    public void addStudents(HashMap<ColoredDisc, Integer> newStudents)
    {
        for(ColoredDisc color : ColoredDisc.values())
        {
            students.put(color,students.get(color)+(newStudents.get(color)!=null ? newStudents.get(color):0));
        }
    }


    /**
     * removes a student from the island
     * @param newStudent is the color we want to remove
     * @return the selected color
     * @throws IllegalArgumentException if the selected color is not present in the island
     */
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
    }

    public Tower[] getTowers() throws IllegalStateException
    {
        if(towerList.stream().filter(x->x==towerList.get(0)).count()!= towerList.size())
        {
            throw new IllegalStateException();
        }
        Tower[] towerArray = new Tower[towerList.size()];
        towerList.toArray(towerArray);
        return towerArray;
    }

    /**
     * adds a tower to the island
     * @param myTower is the tower color
     */
    public void AddTower(Tower myTower)
    {
        towerList.add(myTower);
    }

    /**
     * removes a tower from the island
     * @param myTower is the color of the tower to remove
     * @return the tower color
     * @throws IllegalArgumentException if there are no towers of the selected color in the island
     */
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

    /**
     * changes the color of all the towers
     * @param color
     */
    public void changeTowerColor(Tower color)
    {
        towerList.replaceAll(t-> color);
    }

    public int getID() {
        return ID;
    }

    public ArrayList<Integer> getGraphicalIslands() {
        return (ArrayList<Integer>) graphicalIsland.clone();
    }
}