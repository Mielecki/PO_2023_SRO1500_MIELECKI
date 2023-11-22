package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    @Test
    public void equals(){
        // test 1 - same objects with same values
        // given
        Vector2d vector1 = new Vector2d(1, 1);
        Vector2d vector2 = new Vector2d(1, 1);

        // then
        assertEquals(vector1, vector2);

        // test 2 - same objects with different values
        // given
        vector1 = new Vector2d(0, 0);
        vector2 = new Vector2d(1, 0);

        // then
        assertFalse(vector1.equals(vector2));

        // test 3 - different objects
        // given
        vector1 = new Vector2d(1,1);
        String object1 = new String("Object");

        // then
        assertFalse(vector1.equals(object1));
    }

    @Test
    public void toStringTest(){
        // given
        Vector2d vector1 = new Vector2d(1, 1);

        // then
        assertTrue(Objects.equals(vector1.toString(), "(1,1)"));
    }

    @Test
    public void precedes(){
        // test 1 - lower x and lower y
        // given
        Vector2d vector1 = new Vector2d(0, 0);
        Vector2d vector2 = new Vector2d(1, 1);

        // then
        assertTrue(vector1.precedes(vector2));

        // test 2 - lower x and higher y
        // given
        vector1 = new Vector2d(0, 1);
        vector2 = new Vector2d(1, 0);

        // then
        assertFalse(vector1.precedes(vector2));

        // test 3 - higher x and higher y
        // given
        vector1 = new Vector2d(1, 1);
        vector2 = new Vector2d(0, 0);

        // then
        assertFalse(vector1.precedes(vector2));
    }

    @Test
    public void follows(){
        // test 1 - lower x and lower y
        // given
        Vector2d vector1 = new Vector2d(0, 0);
        Vector2d vector2 = new Vector2d(1, 1);

        // then
        assertFalse(vector1.follows(vector2));

        // test 2 - lower x and higher y
        // given
        vector1 = new Vector2d(0, 1);
        vector2 = new Vector2d(1, 0);

        // then
        assertFalse(vector1.follows(vector2));

        // test 3 - higher x and higher y
        // given
        vector1 = new Vector2d(1, 1);
        vector2 = new Vector2d(0, 0);

        // then
        assertTrue(vector1.follows(vector2));
    }

    @Test
    public void upperRight(){
        // given
        Vector2d vector1 = new Vector2d(10, 0);
        Vector2d vector2 = new Vector2d(0, 10);

        // then
        assertEquals(vector1.upperRight(vector2), new Vector2d(10,10));
    }

    @Test
    public void lowerLeft(){
        // given
        Vector2d vector1 = new Vector2d(10, -10);
        Vector2d vector2 = new Vector2d(-10, 10);

        // then
        assertEquals(vector1.lowerLeft(vector2), new Vector2d(-10,-10));
    }

    @Test
    public void add(){
        // given
        Vector2d vector1 = new Vector2d(1, 2);
        Vector2d vector2 = new Vector2d(2, 3);

        // then
        assertEquals(vector1.add(vector2), new Vector2d(3, 5));
    }

    @Test
    public void subtract(){
        Vector2d vector1 = new Vector2d(1, 2);
        Vector2d vector2 = new Vector2d(2, 3);

        // then
        assertEquals(vector1.subtract(vector2), new Vector2d(-1, -1));
    }

    @Test
    public void opposite(){
        // given
        Vector2d vector1 = new Vector2d(-5,5);

        // then
        Vector2d vector2 = vector1.opposite();
        assertEquals(vector2, new Vector2d(5, -5));
    }
}
