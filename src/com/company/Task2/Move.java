package com.company.Task2;

import java.util.Map;

public class Move {
    private static Map<Integer, char[][]> labyrinth;
    private static int step;


    public static int RescuePrincess(int level, int x, int y, int positionRowLast, int positionColoumLast) {
        labyrinth = Labyrinth.getLabyrinth();
        char[][] levelThis = labyrinth.get(level);
        System.out.println("level= " + level);
        if (level < labyrinth.size()) {
            char[][] levelNext = labyrinth.get(level + 1);

            if (Condition.nextLevelFreeSpace(levelThis[x][y],levelNext[x][y])) {
                return breakFloor(level,x,y);
            }
        } else if (level == labyrinth.size()) {

            if ((x != 0)) {
                if ((levelThis[x - 1][y] == Symbols.PRINCES)) {
                    step += 1;
                    return step;
                }
                if ((levelThis[x - 1][y] == Symbols.FREE_SPACE) && (positionRowLast != (x - 1))) {
                    return stepUp(level, x, y);
                }
                //
            }
            if (x != (Labyrinth.getM() - 1)) {
                if ((levelThis[x + 1][y] == Symbols.PRINCES)) {
                    step += 1;
                    return step;
                }
                if ((levelThis[x + 1][y] == Symbols.FREE_SPACE) && (positionRowLast != x + 1)) {
                    return stepDown(level, x, y);
                }

            }
            if (y != 0) {
                if ((levelThis[x][y - 1] == Symbols.PRINCES)) {
                    step += 1;
                    return step;
                }
                if ((levelThis[x][y - 1] == Symbols.FREE_SPACE) && (positionColoumLast != (y - 1))) {
                    return stepLeft(level, x, y);
                }
            }
            if (y != (Labyrinth.getN() - 1)) {
                if ((levelThis[x][y + 1] == Symbols.PRINCES)) {
                    step = (step + 1);
                    return step;
                }
                if ((levelThis[x][y + 1] == Symbols.FREE_SPACE) && (positionColoumLast != y + 1)) {
                    return stepRight(level, x, y);
                }
            }
            return step;
        }
        if ((x != 0)) {
            if ((levelThis[x - 1][y] == Symbols.FREE_SPACE) && (positionRowLast != (x - 1))) {
                return stepUp(level, x, y);
            }
        }
        if (x != (Labyrinth.getM() - 1)) {
            if ((levelThis[x + 1][y] == Symbols.FREE_SPACE) && (positionRowLast != x + 1)) {
                return stepDown(level, x, y);
            }
        }
        if (y != 0) {
            if ((levelThis[x][y - 1] == Symbols.FREE_SPACE) && (positionColoumLast != (y - 1))) {
                return stepLeft(level, x, y);
            }
        }
        if (y != (Labyrinth.getN() - 1)) {
            if ((levelThis[x][y + 1] == Symbols.FREE_SPACE) && (positionColoumLast != y + 1)) {
                return stepRight(level, x, y);
            }
        }
        RescuePrincess(level, positionRowLast,positionColoumLast,x,y);
           //System.out.println("i don't know, what i will do");
        return -1;
    }

    public static int stepLeft(int level, int x, int y) {
        System.out.println("step left");
        step += 1;
        System.out.println(step);
        int i = RescuePrincess(level, x, y - 1, x, y);
        return step;
    }

    public static int stepRight(int level, int x, int y) {
        System.out.println("step right");
        step += 1;
        System.out.println(step);
        int i = RescuePrincess(level, x, y + 1, x, y);
        return step;
    }

    public static int stepUp(int level, int x, int y) {
        System.out.println("step up");
        step += 1;
        System.out.println(step);
        int i = RescuePrincess(level, x - 1, y, x, y);
        return step;
    }

    public static int stepDown(int level, int x, int y) {
        System.out.println("step down");
        step += 1;
        System.out.println(step);
        int i = RescuePrincess(level, x + 1, y, x, y);
        return step;
    }
    public static int breakFloor(int level, int x, int y){
        step += 1;
        RescuePrincess(level + 1, x, y, x, y);
        return step;
    }


}
