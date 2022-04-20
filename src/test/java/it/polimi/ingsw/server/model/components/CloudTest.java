package it.polimi.ingsw.server.model.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CloudTest {
    Cloud myCloud;
    ArrayList<ColoredDisc> students;
    @BeforeEach
    void setUp() {
        myCloud = new Cloud(3);
        students = new ArrayList<>();
        //add 3 students to the list
        students.add(ColoredDisc.PINK);
        students.add(ColoredDisc.RED);
        students.add(ColoredDisc.BLUE);
    }

    @Test
    void addStudents() {
        myCloud.AddStudents(students);
        int n = myCloud.getStudents().size();
        assertEquals(n,3);
        students.add(ColoredDisc.GREEN);
        /*try{
            myCloud.AddStudents(students);
        }catch(IndexOutOfBoundsException e) {
            e.printStackTrace();
        }*/
        assertThrows(IndexOutOfBoundsException.class, ()->{myCloud.AddStudents(students);});
    }

    @Test
    void addStudent() {
        myCloud.AddStudent(ColoredDisc.RED);
        int n = myCloud.getStudents().size();
        assertEquals(1,n);
        myCloud.AddStudent(ColoredDisc.RED);
        myCloud.AddStudent(ColoredDisc.BLUE);
        /*try{
            myCloud.AddStudent(ColoredDisc.GREEN);
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }*/
        assertThrows(IndexOutOfBoundsException.class, ()->{myCloud.AddStudent(ColoredDisc.GREEN);});
    }

    @Test
    void getStudents() {
    }

    @Test
    void removeAll() {
        myCloud.AddStudents(students);
        myCloud.removeAll();
        assertEquals(0,myCloud.getStudents().size());
    }

    @Test
    void removeStudent() {
        myCloud.AddStudents(students);
        int n = myCloud.getStudents().size();
        myCloud.removeStudent(ColoredDisc.RED);
        assertEquals(n-1,myCloud.getStudents().size());
        /*try{
            myCloud.removeStudent(ColoredDisc.GREEN);
        }catch(InvalidParameterException e ){
            e.printStackTrace();
        }*/
        assertThrows(InvalidParameterException.class,()->{myCloud.removeStudent(ColoredDisc.GREEN);});
    }

    @Test
    void getCloudCapacity() {
        assertEquals((new Cloud(2)).getCloudCapacity(),2);
    }
}