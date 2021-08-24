package com.company.Task2;

import java.util.*;


public class Runner {


    public static void main(String[] args) throws Exception {
        Labyrinth.makeLabyrinth();

        Point prince = Labyrinth.getLabyrinth()
                .stream()
                .filter(p -> p.getValue() == Symbols.PRINCE)
                .findFirst()
                .orElse(null);
        //System.out.println(prince);
        if (prince != null) {
            int move = new Move(Labyrinth.getLabyrinth(), new ArrayList<Point>()).rescuePrincessStart(prince);

        }

        Set<Integer> set = VriablePath.getInstance().get();

        List<Integer> setTime = new ArrayList<>();
        set.forEach(element -> setTime.add(element));
        Integer step = setTime.stream().sorted().findFirst().get();
        //  System.out.println(setTime);

        int timeRescue = 5 * step;
        System.out.println(timeRescue);
    }

}





