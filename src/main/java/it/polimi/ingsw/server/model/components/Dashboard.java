package it.polimi.ingsw.server.model.components;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * class dashboard represents the school board of each player
 */
public class Dashboard {
    private int maxTowers;
    private ColoredDisc[] entranceSpots;
    private HashMap<ColoredDisc, Integer> tables = new HashMap<ColoredDisc, Integer>();
    private int towerNumber;

    public int maxEntrance;
    public HashSet<ColoredDisc> professorSpots = new HashSet<ColoredDisc>();

    /**
     * dashboard constructor
     * @param playerNumber can be 2 or 3
     * @throws IndexOutOfBoundsException if playerNumber is neither 2 nor 3
     */
    public Dashboard(int playerNumber) throws IndexOutOfBoundsException
    {
        switch (playerNumber)
        {
            case 2:
                maxTowers = 8;
                maxEntrance = 7;
                break;
            case 3:
                maxTowers = 6;
                maxEntrance = 9;
                break;
            default:
                throw new IndexOutOfBoundsException();
        }
        towerNumber = maxTowers;
        entranceSpots = new ColoredDisc[maxEntrance];
        for(ColoredDisc color:ColoredDisc.values()){
            tables.put(color,0);
        }
    }

    /**
     * adds a student to the entrance of the dashboard
     * @param myStudent is the chosen color
     * @param index is the selected spot for the student in the entrance
     * @throws IndexOutOfBoundsException if the spot is already occuped
     */
    public void AddToEntrance(ColoredDisc myStudent, int index) throws IndexOutOfBoundsException
    {
        if (entranceSpots[index]!=null)
        {
            throw new IndexOutOfBoundsException();
        }
        else
        {
            entranceSpots[index] = myStudent;
        }
    }

    /**
     * removes a student from entrance
     * @param index is the entrance index of the selected student to remove
     * @throws InvalidParameterException if the selected spot is not occuped
     */
    public void RemoveFromEntrance(int index) throws InvalidParameterException
    {
        if (entranceSpots[index]!=null)
        {
            entranceSpots[index]=null;
        }
        else
        {
            throw new InvalidParameterException();
        }
    }

    /**
     * moves a student identified by its index from entrance to tables
     * @param index is the spot of the student in the entrance
     * @return the number of students of a specific color (the color of the chosen student)
     * @throws InvalidParameterException if the selected spot is occuped
     * @throws IndexOutOfBoundsException if the table has not room for another student
     */
    public int MoveToTables(int index) throws InvalidParameterException, IndexOutOfBoundsException
    {
        ColoredDisc myStudent = entranceSpots[index];
        if (tables.get(myStudent)!= null && tables.get(myStudent)<10)
        {
            RemoveFromEntrance(index);
            tables.put(myStudent, tables.get(myStudent)+1);
            return tables.get(myStudent);
        }
        else
        {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * generic method to add a student to tables
     * @param myStudent is the color of the selected student
     * @return the number of students of color myStudent in tables
     * @throws IndexOutOfBoundsException if the table is full and there is no enough room
     */
    public int addToTables(ColoredDisc myStudent) throws InvalidParameterException, IndexOutOfBoundsException
    {
        if (tables.get(myStudent)<10)
        {
            tables.put(myStudent, tables.get(myStudent)+1);
            return tables.get(myStudent);
        }
        else
        {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * removes tower from dashboard
     * @throws IndexOutOfBoundsException if there are no towers to remove
     */
    public void RemoveTower() throws IndexOutOfBoundsException
    {
        if (towerNumber>0)
        {
            towerNumber--;
        }
        else
        {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * adds a tower in dashboard
     * @throws IndexOutOfBoundsException if there is no room for another tower
     */
    public void AddTower() throws IndexOutOfBoundsException
    {
        if (towerNumber<maxTowers)
        {
            towerNumber++;
        }
        else
        {
            throw new IndexOutOfBoundsException();
        }
    }

    public int TowerNumber()
    {
        return towerNumber;
    }

    public int SittedStudents(ColoredDisc color){
        return tables.get(color);
    }


    /**
     * removes a student from tables
     * @param myStudent is the color we want to remove
     * @throws IndexOutOfBoundsException if the table is empty
     */
    public void RemoveFromTables(ColoredDisc myStudent) throws IndexOutOfBoundsException
    {
        if(tables.get(myStudent) == 0)
        {
            throw new IndexOutOfBoundsException();
        }
        else
        {
            tables.put(myStudent, tables.get(myStudent)-1);
        }
    }

    /**
     * removes num students from table
     * @param myStudent is the color we want to remove
     * @param num is the amount of students we want to remove
     * @throws IndexOutOfBoundsException
     */
    public void RemoveFromTables(ColoredDisc myStudent, int num) throws IndexOutOfBoundsException
    {
        if(tables.get(myStudent) - num < 0)
        {
            throw new IndexOutOfBoundsException();
        }
        else
        {
            tables.put(myStudent, tables.get(myStudent)-num);
        }
    }

    public int getEntranceStudents(ColoredDisc color){
        return Arrays.asList(entranceSpots).stream().filter(x->x==color).collect(Collectors.toList()).size();
    }

    public ColoredDisc[] getEntranceSpots()
    {
        return entranceSpots.clone();

    }

}
