package com.company.Task2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Labyrinth {
    private static int h; //H blocks
    private static int m; // M rows
    private static int n; //N columns

    private static Map<Integer, char[][]> labyrinth;
    private static String FILE_NAME = "INPUT.TXT";

    public static void printLabyrinth(Map<Integer, char[][]> labyrinth) {
        for (Map.Entry<Integer, char[][]> entry : labyrinth.entrySet()) {
            System.out.println("Level: " + entry.getKey());
            printMatrix(entry.getValue());
        }
    }

    public static void makeLabyrinth() {
        String path = FormatePathInputTXT.getPath(Task2.class) + FILE_NAME;

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String firstLine = reader.readLine();
            String[] s = firstLine.split(" "); //The first line of INPUT.TXT file contains digits H, M, N - count of levels and rectangle size of each level.
            h = Integer.parseInt(s[0]); //H blocks
            m = Integer.parseInt(s[1]);// M rows
            n = Integer.parseInt(s[2]); //N columns
            Map<Integer, char[][]> laberint = new HashMap<>();
            String line = reader.readLine();
            for (int i = 0; i < h; i++) {
                line = reader.readLine();
                char[][] matrixLevel = new char[m][n];
                for (int i1 = 0; i1 < m; i1++) {
                    if (line.length() == n && line.matches("[.o21]{3}")) {
                        for (int i2 = 0; i2 < n; i2++) {
                            matrixLevel[i1][i2] = line.charAt(i2);
                        }
                    } else {
                        throw new IllegalArgumentException("file contains lines that do not fit the conditional:\n" +
                                " H- blocks, M -rows,N columns");
                    }
                    laberint.put(i + 1, matrixLevel);
                    line = reader.readLine();
                }
            }
            Labyrinth.printLabyrinth(laberint);  //It's for my test

            labyrinth = laberint;
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

    public static Map<Integer, char[][]> getLabyrinth() {
        return labyrinth;
    }
    private static void printMatrix(char[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }
}
