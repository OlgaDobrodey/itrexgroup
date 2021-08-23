package com.company.Task2;

public class Runner {



    public static void main(String[] args) throws Exception {
        Labyrinth.makeLabyrinth();

        Point prince = Labyrinth.getLabyrinth()
                .stream()
                .filter(p -> p.getValue() == Symbols.PRINCE)
                .findFirst()
                .orElse(null);
        System.out.println(prince);

        int timeRescue = 5* new Move(Labyrinth.getLabyrinth()).RescuePrincessStart(prince);

        System.out.println("step= "+timeRescue/5);
        System.out.println(timeRescue);


    }






}

