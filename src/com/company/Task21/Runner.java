package com.company.Task21;

import java.util.*;


public class Runner {


    public static void main(String[] args) throws Exception {
        Labyrinth.makeLabyrinth();

        Point prince = Labyrinth.getLabyrinth()
                .stream()
                .filter(p -> p.getValue() == Symbols.PRINCE)
                .findFirst()
                .orElse(null);
        System.out.println(prince);
        if (prince != null) {
           int move= new Move(Labyrinth.getLabyrinth()).rescuePrincessStart(prince);

        }

            Set<List<Point>> set = VriablePath.getInstance().get();
            Set<Integer> setTime = new HashSet<>(0);
            set.forEach(list->setTime.add(list.size()));
            System.out.println();
            Integer step = setTime.stream().min((o1, o2) -> 1).stream().findFirst().get()-1;
            int timeRescue = 5 * step;
            System.out.println("step= " + timeRescue / 5);
            System.out.println(timeRescue);
        }

    }





