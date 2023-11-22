package agh.ics.oop;

import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

public class World {
    public static void run(MoveDirection[] args){
        for(MoveDirection arg : args){
            String toSay = switch (arg){
                case FORWARD -> "Zwierzak idzie do przodu";
                case BACKWARD -> "Zwierzak idzie do tyłu";
                case LEFT -> "Zwierzak skręca w lewo";
                case RIGHT -> "Zwierzak skręca w prawo";
            };
            System.out.println(toSay);
        }
    }
    public static void main(String [] args){
        System.out.println("System wystartował");
        run(OptionsParser.parse(args));
        System.out.println("System zakończył działanie");

        System.out.println(MapDirection.SOUTH.toUnitVector());
    }
}
