package agh.ics.oop;

import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimulationTest {
    @Test
    public void Simulation(){
        // test 1
        // given
        List<MoveDirection> directions = OptionsParser.parse(new String[] {"f", "b", "l", "r"});
        List<Vector2d> positions = new ArrayList<>(Arrays.asList(new Vector2d(1, 1), new Vector2d(4, 4)));

        // when
        Simulation simulation = new Simulation(positions, directions);
        simulation.run();

        // then

        // orientationTest
        assertEquals(simulation.getAnimals().get(0).getOrientation(), MapDirection.WEST);
        assertEquals(simulation.getAnimals().get(1).getOrientation(), MapDirection.EAST);

        // positionTest
        assertEquals(simulation.getAnimals().get(0).getPosition(), new Vector2d(1, 2));
        assertEquals(simulation.getAnimals().get(1).getPosition(), new Vector2d(4, 3));

        // test 2 - map limits
        directions = OptionsParser.parse(new String[] {"f", "b", "r", "l", "f", "f"});
        positions = new ArrayList<>(Arrays.asList(new Vector2d(4, 4), new Vector2d(0, 0)));

        // when
        simulation = new Simulation(positions, directions);
        simulation.run();

        // then

        // orientationTest
        assertEquals(simulation.getAnimals().get(0).getOrientation(), MapDirection.EAST);
        assertEquals(simulation.getAnimals().get(1).getOrientation(), MapDirection.WEST);

        // positionTest
        assertEquals(simulation.getAnimals().get(0).getPosition(), new Vector2d(4, 4));
        assertEquals(simulation.getAnimals().get(1).getPosition(), new Vector2d(0, 0));
    }
}
