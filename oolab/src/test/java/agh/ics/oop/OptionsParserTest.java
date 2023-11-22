package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OptionsParserTest {
    @Test
    public void parse(){
        // test 1 - given args are correct
        // given
        String[] args = new String[] {"f", "f", "b", "b", "l", "r"};

        // then
        assertTrue(Arrays.equals(OptionsParser.parse(args), new MoveDirection[] {MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.RIGHT}));

        // test 2 - some given args are incorrect
        // given
        args = new String[] {"f", "b", "v", "l","fbd" ,"r"};

        // then
        assertTrue(Arrays.equals(OptionsParser.parse(args), new MoveDirection[] {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.RIGHT}));

        // test 3 - all args are incorrect
        // given
        args = new String[] {"v", "ge", "ff"};

        // then
        assertTrue(Arrays.equals(OptionsParser.parse(args), new MoveDirection[] {}));
    }
}
