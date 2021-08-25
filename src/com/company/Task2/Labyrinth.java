package com.company.Task2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Labyrinth {
    private static int h; //H blocks
    private static int m; // M rows
    private static int n; //N columns

    private static List<Point> labyrinth;
    private static String FILE_NAME = "INPUT.TXT";

    public static void makeLabyrinth() {
        String path = FormatePathInputTXT.getPath(Labyrinth.class) + FILE_NAME;

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String firstLine = reader.readLine();
            String[] s = firstLine.split(" "); //The first line of INPUT.TXT file contains digits H, M, N - count of levels and rectangle size of each level.
            h = Integer.parseInt(s[0]); //H blocks
            m = Integer.parseInt(s[1]);// M rows
            n = Integer.parseInt(s[2]); //N columns

            List<Point> list = new ArrayList<>();
            String line = reader.readLine(); // empty string
            for (int level = 1; level <= h; level++) {
                line = reader.readLine();

                for (int x = 0; x < m; x++) {
                    if (line.length() == n && line.matches("["
                            +Symbols.FREE_SPACE+Symbols.COLUMN+Symbols.PRINCE+Symbols.PRINCES+
                            "]{"+n+"}")) {
                        for (int y = 0; y < n; y++) {
                            list.add(new Point(level, x, y, line.charAt(y)));
                        }
                    } else {
                        throw new IllegalArgumentException("file contains lines that do not fit the conditional:\n" +
                                " H- blocks, M -rows,N columns");
                    }
                    line = reader.readLine();
                }
            }
         //   printLabyrinth(list);  //It's for my test

            labyrinth = list;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getH() {
        return h;
    }

    public static int getM() {
        return m;
    }

    public static int getN() {
        return n;
    }

    public static List<Point> getLabyrinth() {
        return labyrinth;
    }

    public static void printLabyrinth(List<Point> labyrinth) {
        int count = 0;
        for (int i = 1; i <= 3; i++) {
            System.out.println("Level: " + i);
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    System.out.print(labyrinth.get(count++));
                }
                System.out.println();
            }
            System.out.println();
        }

    }
}
