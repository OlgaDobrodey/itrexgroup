package com.company.Task2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EXPEREMENT {


    private static Map<Integer, Point[][]> labyrinth;
    private static String FILE_NAME = "INPUT.TXT";
    public static List<Point> labyrint2;


    public static void main(String[] args) {

        makeLabyrinth();
      //  System.out.println(labyrint2);
        ;
        System.out.println(getPointLeft(new Point(1,2,1,'.')));
    }

    public static void makeLabyrinth() {
        String path = FormatePathInputTXT.getPath(EXPEREMENT.class) + FILE_NAME;

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String firstLine = reader.readLine();
            String[] s = firstLine.split(" "); //The first line of INPUT.TXT file contains digits H, M, N - count of levels and rectangle size of each level.
            int h = Integer.parseInt(s[0]); //H blocks
            int m = Integer.parseInt(s[1]);// M rows
            int n = Integer.parseInt(s[2]); //N columns
            //Map<Integer, Point[][]> laberint = new HashMap<>();
            List<Point> list = new ArrayList<>();
            String line = reader.readLine();
            for (int i = 0; i < h; i++) {
                line = reader.readLine();
                Point[][] matrixLevel = new Point[m][n];
                for (int i1 = 0; i1 < m; i1++) {
                    if (line.length() == n && line.matches("[.o21]{3}")) {
                        for (int i2 = 0; i2 < n; i2++) {
                            list.add(new Point(i + 1, i1, i2, line.charAt(i2)));
                        }
                    } else {
                        throw new IllegalArgumentException("file contains lines that do not fit the conditional:\n" +
                                " H- blocks, M -rows,N columns");
                    }
                    // laberint.put(i + 1, matrixLevel);
                    line = reader.readLine();
                }
            }
           printLabyrinth(list);  //It's for my test

            labyrint2 = list;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printMatrix(Point[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
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
