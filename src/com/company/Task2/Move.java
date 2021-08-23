package com.company.Task2;

import java.util.List;


public class Move {
    private List<Point> labyrinth;
    private int step;

    public Move(List<Point> labyrinth) {
        this.labyrinth = labyrinth;

    }
    public int RescuePrincessStart(Point point){
        return RescuePrincess(point,0,0);
    }

    private int RescuePrincess(Point point, int positionRowLast, int positionColoumLast) {

        System.out.println("level= " + point.getLevel());
        if (point.getLevel() < Labyrinth.getH()) {
            Point pointUnderFloor = getPointUnderFloor(point);
            if (Condition.nextLevelFreeSpace(point.getValue(),pointUnderFloor.getValue())) {
                return breakFloor(pointUnderFloor);
            }
        }
        if (point.getLevel() == Labyrinth.getH()) {
            if ((point.getX() != 0)) {
                if ((getPointUp(point).getValue() == Symbols.PRINCES)) {
                    step += 1;
                    return step;
                }
            }
            if (point.getX() != (Labyrinth.getM() - 1)) {
                if ((getPointDown(point).getValue() == Symbols.PRINCES)) {
                    step += 1;
                    return step;
                }
            }
            if (point.getY() != 0) {
                if ((getPointLeft(point).getValue() == Symbols.PRINCES)) {
                    step += 1;
                    return step;
                }
            }
            if (point.getY() != (Labyrinth.getN() - 1)) {
                if ((getPointRight(point).getValue() == Symbols.PRINCES)) {
                    step = (step + 1);
                    return step;
                }
            }
        }
        //!!!!!
        if ((point.getX() != 0)) {
            if ((getPointUp(point).getValue() == Symbols.FREE_SPACE) && (positionRowLast != (point.getX() - 1))) {
                return stepUp(getPointUp(point));
            }
        }
        if (point.getX() != (Labyrinth.getM() - 1)) {
            if ((getPointDown(point).getValue()== Symbols.FREE_SPACE) && (positionRowLast != point.getX() + 1)) {
                return stepDown(getPointDown(point));
            }
        }
        if (point.getY() != 0) {
            if ((getPointLeft(point).getValue() == Symbols.FREE_SPACE) && (positionColoumLast != (point.getY() - 1))) {
                return stepLeft(getPointLeft(point));
            }
        }
        if (point.getY() != (Labyrinth.getN() - 1)) {
            if ((getPointRight(point).getValue() == Symbols.FREE_SPACE) && (positionColoumLast != point.getY() + 1)) {
                return stepRight(getPointRight(point));
            }
        }
       // RescuePrincess(level, positionRowLast, positionColoumLast, x, y);
        //System.out.println("i don't know, what i will do");
        return -1;
    }

    private int stepLeft(Point point) {
        System.out.println("step left");
        step += 1;
        System.out.println(step);
        int i = RescuePrincess(point, point.getX(), point.getY()+1);
        return step;
    }

    private int stepRight(Point point) {
        System.out.println("step right");
        step += 1;
        System.out.println(step);
        int i = RescuePrincess(point, point.getX(), point.getY()-1);
        return step;
    }

    private int stepUp(Point point) {
        System.out.println("step up");
        step += 1;
        System.out.println(step);
        int i = RescuePrincess(point, point.getX()+1, point.getY());
        return step;
    }

    private int stepDown(Point point) {
        System.out.println("step down");
        step += 1;
        System.out.println(step);
        int i = RescuePrincess(point, point.getX()-1, point.getY());
        return step;
    }

    private int breakFloor(Point point) {
        step += 1;
        RescuePrincess(point, point.getX(), point.getY());
        return step;
    }

    private Point getPointLeft(Point point) {
        Point pointResult = labyrinth
                .stream()
                .filter(p -> ((p.getLevel() == point.getLevel()) && (p.getX() == point.getX()) && (p.getY() == point.getY() - 1)))
                .findFirst()
                .orElse(null);
        return pointResult;
    }

    private Point getPointRight(Point point) {
        Point pointResult = labyrinth
                .stream()
                .filter(p -> ((p.getLevel() == point.getLevel()) && (p.getX() == point.getX()) && (p.getY() == point.getY() + 1)))
                .findFirst()
                .orElse(null);
        return pointResult;
    }

    private Point getPointUp(Point point) {
        Point pointResult = labyrinth
                .stream()
                .filter(p -> ((p.getLevel() == point.getLevel()) && (p.getX() == point.getX() - 1) && (p.getY() == point.getY())))
                .findFirst()
                .orElse(null);
        return pointResult;
    }
    private Point getPointDown(Point point) {
        Point pointResult = labyrinth
                .stream()
                .filter(p -> ((p.getLevel() == point.getLevel()) && (p.getX() == point.getX() + 1) && (p.getY() == point.getY())))
                .findFirst()
                .orElse(null);
        return pointResult;
    }

    private Point getPointUnderFloor(Point point) {
        Point pointResult = labyrinth
                .stream()
                .filter(p -> ((p.getLevel() == point.getLevel() + 1) && (p.getX() == point.getX()) && (p.getY() == point.getY())))
                .findFirst()
                .orElse(null);
        return pointResult;
    }




}
