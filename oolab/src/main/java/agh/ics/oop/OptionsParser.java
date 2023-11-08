package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.Arrays;

public class OptionsParser {
    public static MoveDirection[] parse(String [] args){
        MoveDirection[] options = new MoveDirection[args.length];
        int skipped = 0;
        for(int i = 0; i < args.length; i++){
            options[i-skipped] = switch (args[i]){
                case "f" -> MoveDirection.FORWARD;
                case "b" -> MoveDirection.BACKWARD;
                case "l" -> MoveDirection.LEFT;
                case "r" -> MoveDirection.RIGHT;
                default -> null;
            };
            if(options[i-skipped] == null) skipped += 1;
        }
        return Arrays.copyOfRange(options, 0, args.length-skipped);
    }
}