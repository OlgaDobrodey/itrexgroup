package com.company.Task2;

import java.util.List;
import java.util.Map;

public class EXPEREMENT {



    private static String FILE_NAME = "INPUT.TXT";
    public static List<Point> labyrint2;


    public static void main(String[] args) {

        Labyrinth.makeLabyrinth();
        List<Point> labyrinth = Labyrinth.getLabyrinth();
        System.out.println( new Point(1,2,0,'.').hashCode());
        System.out.println( new Point(1,1,1,'.').hashCode());
        System.out.println( new Point(1,0,0,'.').equals(new Point(1,0,0,'.')));
        System.out.println(labyrinth.contains(new Point(1,0,0,'0')));
        //printLabyrinth(labyrinth);
        System.out.println("c".hashCode());
        Point point =new Point(1,0,0,'.');

        //  System.out.println(labyrint2);

      //  System.out.println(getPointLeft(new Point(1,2,1,'.')));
    }



    public static void printLabyrinth(List<Point> labyrinth) {
        int count = 0;
        for (int i = 1; i <= 3; i++) {
            System.out.println("Level: "+i);
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    System.out.print(labyrinth.get(count++));
                }
                System.out.println();

            }
            System.out.println();
            System.out.println();

        }

    }

    public static Point getPointLeft(Point point) {
        Point pointResult = labyrint2
                .stream()
                .filter(p -> ((p.getLevel()== point.getLevel()) && (p.getX() == point.getX())&& (p.getY()== point.getY()-1)))
                        .findFirst()
                        .orElse(null);
        return pointResult;
    }

}
