package agh.ics.opp;

import javax.xml.stream.FactoryConfigurationError;

public class World {

    public static void run(String [] args){
        for(String arg : args){
            String toSay = switch (arg){
                case "f" -> "Zwierzak idzie to przodu";
                case "b" -> "Zwierzak idzie do tyłu";
                case "l" -> "Zwierzak skręca w lewo";
                case "r" -> "Zwierzak skręca w prawo";
                default -> "";
            };
            if (!toSay.isEmpty()) System.out.println(toSay);
        }
    }
    public static void main(String [] args){
        System.out.println("System wystartował");
        run(args);
        System.out.println("System zakończył działanie");
    }
}
