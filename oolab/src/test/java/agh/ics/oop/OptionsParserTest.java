package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OptionsParserTest {
    @Test
    public void parse(){
        // test 1 - given args are correct
        // given
        String[] args = new String[] {"f", "f", "b", "b", "l", "r"};

        // then
        ArrayList<MoveDirection> result = new ArrayList<>(
                Arrays.asList(MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.RIGHT)
        );
        assertEquals(OptionsParser.parse(args), result);

        // test 2 - some given args are incorrect
        // given
        args = new String[] {"f", "b", "v", "l","fbd" ,"r"};
        result = new ArrayList<>(
                Arrays.asList(MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.RIGHT)
        );
        // then
        assertEquals(OptionsParser.parse(args), result);

        // test 3 - all args are incorrect
        // given
        args = new String[] {"v", "ge", "ff"};
        result = new ArrayList<>();
        // then
        assertTrue(OptionsParser.parse(args).equals(result));
    }
}
