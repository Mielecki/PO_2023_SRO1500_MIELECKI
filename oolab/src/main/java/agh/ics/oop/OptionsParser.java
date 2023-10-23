package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class OptionsParser {
    public static MoveDirection[] parse(String [] args){
        MoveDirection[] options = new MoveDirection[args.length];
        for(int i = 0; i < args.length; i++){
            options[i] = switch (args[i]){
                case "f" -> MoveDirection.FORWARD;
                case "b" -> MoveDirection.BACKWARD;
                case "l" -> MoveDirection.LEFT;
                case "r" -> MoveDirection.RIGHT;
                default -> throw new IllegalStateException("Unexpected value: " + args[i]);
            };
        }
        return options;
    }
}
