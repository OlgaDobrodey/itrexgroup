package com.company.Task2;


import java.util.Map;

public class Runner {

    private static Map<Integer, char[][]> labyrinth;

    public static void main(String[] args) throws Exception {
        Labyrinth.makeLabyrinth();

        int result = 5 * Move.RescuePrincess(1, 0, 0, 0, 0);
        System.out.println("step= "+result/5);
        System.out.println(result);


    }






}


