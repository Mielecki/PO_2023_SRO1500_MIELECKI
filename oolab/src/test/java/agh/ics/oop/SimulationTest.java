package agh.ics.oop;

import agh.ics.oop.model.*;
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
        WorldMap worldMap = new RectangularMap(5, 5);

        // when
        Simulation simulation = new Simulation(positions, directions, worldMap);
        simulation.run();

        // then

        // orientationTest
        assertEquals(simulation.getAnimals().get(0).getOrientation(), MapDirection.WEST);
        assertEquals(simulation.getAnimals().get(1).getOrientation(), MapDirection.EAST);

        // positionTest
        assertEquals(simulation.getAnimals().get(0).getPosition(), new Vector2d(1, 2));
        assertEquals(simulation.getAnimals().get(1).getPosition(), new Vector2d(4, 3));

        // test 2 - map limits
        // given
        directions = OptionsParser.parse(new String[] {"f", "l", "f", "f"});
        positions = new ArrayList<>(Arrays.asList(new Vector2d(4, 4), new Vector2d(1, 1)));
        worldMap = new RectangularMap(5, 5);

        // when
        simulation = new Simulation(positions, directions, worldMap);
        simulation.run();

        // then

        // orientationTest
        assertEquals(simulation.getAnimals().get(0).getOrientation(), MapDirection.NORTH);
        assertEquals(simulation.getAnimals().get(1).getOrientation(), MapDirection.WEST);

        // positionTest
        assertEquals(simulation.getAnimals().get(0).getPosition(), new Vector2d(4, 5));
        assertEquals(simulation.getAnimals().get(1).getPosition(), new Vector2d(0, 1));

        // test 3 - same position
        // given
        directions = OptionsParser.parse(new String[] {"l", "f"});
        positions = new ArrayList<>(Arrays.asList(new Vector2d(3, 3), new Vector2d(3, 2)));
        worldMap = new RectangularMap(5, 5);

        // when
        simulation = new Simulation(positions, directions, worldMap);
        simulation.run();

        // then

        // orientationTest
        assertEquals(simulation.getAnimals().get(0).getOrientation(), MapDirection.WEST);
        assertEquals(simulation.getAnimals().get(1).getOrientation(), MapDirection.NORTH);

        // positionTest
        assertEquals(simulation.getAnimals().get(0).getPosition(), new Vector2d(3, 3));
        assertEquals(simulation.getAnimals().get(1).getPosition(), new Vector2d(3, 2));
    }
}
