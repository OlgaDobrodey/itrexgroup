package com.company.Task2;

import java.util.*;


public class Runner {


    public static void main(String[] args) throws Exception {
        Labyrinth.makeLabyrinth();     //create schema labyrinth

        Point prince = Labyrinth.getLabyrinth()      //find prince
                .stream()
                .filter(p -> p.getValue() == Symbols.PRINCE)
                .findFirst()
                .orElse(null);
        Point princes = Labyrinth.getLabyrinth()      //find princes
                .stream()
                .filter(p -> p.getValue() == Symbols.PRINCES)
                .findFirst()
                .orElse(null);

        if(prince.getLevel()!=1||princes.getLevel()!=Labyrinth.getH()){throw new RuntimeException("Prince don't find on the first level or Princes don't find in the last level");}
        else if (prince != null ) {
            int move = new Move(Labyrinth.getLabyrinth() ).rescuePrincessStart(prince,new ArrayList<Point>());
        }

        Integer step = new ArrayList<>(VriablePath.getInstance().get()).stream().sorted().findFirst().get();  //min count step prince;
        //  System.out.println(setTime);

        int timeRescue = 5 * step;        //time travel prince
        System.out.println(timeRescue);
    }

}





