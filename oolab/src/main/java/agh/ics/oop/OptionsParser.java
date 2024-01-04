package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OptionsParser {
    public static List<MoveDirection> parse(String [] args){
        List<MoveDirection> options = new ArrayList<>();
        MoveDirection option;
        for (String arg : args) {
            option = switch (arg) {
                case "f" -> MoveDirection.FORWARD;
                case "b" -> MoveDirection.BACKWARD;
                case "l" -> MoveDirection.LEFT;
                case "r" -> MoveDirection.RIGHT;
                default -> null;
            };
            if (option == null) throw new IllegalArgumentException(arg + " is not legal move specification");
            options.add(option);
        }
        return options;
    }
}