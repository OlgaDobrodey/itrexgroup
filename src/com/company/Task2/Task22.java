package com.company.Task2;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Task22 {
    private static int h; //H blocks
    private static int m; // M rows
    private static int n; //N columns
    private static int step;
    private static Map<Integer, char[][]> labyrinth;

    public static void main(String[] args) throws Exception {
        labyrinth = getLabyrinth();
        int result = 5 * goLevel(1, 0, 0, 0, 0);
        System.out.println(result);


    }

    private static String getPath(Class<?> aClass) {
        String packageName = aClass
                .getPackage()
                .getName()
                .replace(".", File.separator)
                .concat(File.separator);
        String root = System.getProperty("user.dir");
        return root + File.separator + "src" + File.separator + packageName;
    }

    public static void printMatrix(char[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

    public static Map<Integer, char[][]> getLabyrinth() {
        String path = getPath(Task22.class) + "INPUT.TXT";
        long count = 0;
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
                    if (!line.replaceAll("\\s", "").isEmpty()) {
                        if (line.length() == n && line.matches("[.o21]{3}")) {
                            for (int i2 = 0; i2 < n; i2++) {
                                matrixLevel[i1][i2] = line.charAt(i2);
                            }

                            count = count + line.chars().filter(ch -> ch == '.').count();
                        } else {
                            throw new IllegalArgumentException("file contains lines that do not fit the conditional:\n" +
                                    " H- blocks, M -rows,N columns");
                        }
                    }
                    laberint.put(i + 1, matrixLevel);
                    line = reader.readLine();
                }
            }

            for (Map.Entry<Integer, char[][]> entry : laberint.entrySet()) {
                System.out.println("Key: " + entry.getKey());
                System.out.println(" Value: ");
                printMatrix(entry.getValue());

            }
            return laberint;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int goLevel(int level, int positionRow, int positionColoum, int positionRowLast, int positionColoumLast) throws Exception {

        char[][] levelThis = labyrinth.get(level);
        int i1 = positionRow;
        int i2 = positionColoum;


        // if(levelThis[i1][i2] != '.'|| levelThis[i1][i2] != '1'){throw new Exception("Error Algoritm");}
        if (level < labyrinth.size()) {
            char[][] levelNext = labyrinth.get(level + 1);
            System.out.println("level= "+level);
            if (levelThis[i1][i2] == '.' && levelNext[i1][i2] == '.') {
                step += 1;
                goLevel(level + 1, i1, i2, i1, i2);
                return step;
            }
        } else if (level == labyrinth.size()) {
            System.out.println("level3 ="+level+" step ="+ step);
            if ((i1 != 0)) {
                if((levelThis[i1 - 1][i2] == '2')){step+=1; return step;}
                if ((levelThis[i1 - 1][i2] == '.') && (positionRowLast != (i1 - 1))) {
                    System.out.println("step up");
                    step += 1;
                    System.out.println(step);
                    int i = goLevel(level, i1 - 1, i2, i1, i2);
                    return step;
                }
            } if (i1 != (m - 1)) {
                if((levelThis[i1 + 1][i2] == '2')){step+=1; return step;}
                if ((levelThis[i1 + 1][i2] == '.') && (positionRowLast != i1 + 1)) {
                    System.out.println("step down");
                    step += 1;
                    System.out.println(step);
                    int i = goLevel(level, i1 + 1, i2, i1, i2);
                    return step;
                }

            } if (i2 != 0) {
                if((levelThis[i1][i2-1] == '2')){step += 1; return step;}
                if ((levelThis[i1][i2 - 1] == '.') && (positionColoumLast != (i2 - 1))) {
                    System.out.println("step left");
                    step += 1;
                    System.out.println(step);
                    int i = goLevel(level, i1, i2 - 1, i1, i2);
                    return step;
                }
            } if (i2 != (n - 1)) {
                if((levelThis[i1][i2+1]=='2')){
                    step = (step+1);
                    return step ;}
                if( (levelThis[i1][i2 + 1] == '.') && (positionColoumLast != i2 + 1)) {
                    System.out.println("step right");
                    step += 1;
                    System.out.println(step);
                    int i = goLevel(level, i1, i2 + 1, i1, i2);
                    return step;
                }}
            return step;
        }
        if ((i1 != 0)) {
            if ((levelThis[i1 - 1][i2] == '.') && (positionRowLast != (i1 - 1))) {
                System.out.println("step up");
                step += 1;
                System.out.println(step);
                int i = goLevel(level, i1 - 1, i2, i1, i2);
                return step;
            }
        } if (i1 != (m - 1)) {
            if ((levelThis[i1 + 1][i2] == '.') && (positionRowLast != i1 + 1)) {
                System.out.println("step down");
                step += 1;
                System.out.println(step);
                int i = goLevel(level, i1 + 1, i2, i1, i2);
                return step;
            }
        } if (i2 != 0) {
            if ((levelThis[i1][i2 - 1] == '.') && (positionColoumLast != (i2 - 1))) {
                System.out.println("step left");
                step += 1;
                System.out.println(step);
                int i = goLevel(level, i1, i2 - 1, i1, i2);
                return step;
            }
        } if (i2 != (n - 1)) {
            if( (levelThis[i1][i2 + 1] == '.') && (positionColoumLast != i2 + 1)) {
                System.out.println("step right");
                step += 1;
                System.out.println(step);
                int i = goLevel(level, i1, i2 + 1, i1, i2);
                return step;
            }}
       // else System.out.println("i don't know, what i will do");
        return -1;
    }


}

