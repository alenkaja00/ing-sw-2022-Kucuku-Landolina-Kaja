package it.polimi.ingsw.server.model.components;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Dashboard {
    private int maxTowers;
    private ColoredDisc[] entranceSpots;
    private HashMap<ColoredDisc, Integer> tables = new HashMap<ColoredDisc, Integer>();
    private int towerNumber;

    public int maxEntrance;
    public HashSet<ColoredDisc> professorSpots = new HashSet<ColoredDisc>();

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
        entranceSpots = new ColoredDisc[maxEntrance];
        for(ColoredDisc color:ColoredDisc.values()){
            tables.put(color,0);
        }
    }

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

    public int MoveToTables(int index) throws InvalidParameterException, IndexOutOfBoundsException
    {
        ColoredDisc myStudent = entranceSpots[index];
        if (tables.get(myStudent)<10)
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
     *
     * @param myStudent
     * @throws IndexOutOfBoundsException
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
