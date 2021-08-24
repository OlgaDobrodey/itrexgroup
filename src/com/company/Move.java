package com.company;

import com.company.Task21.Labyrinth;
import com.company.Task21.Point;
import com.company.Task21.Symbols;
import com.company.Task21.VriablePath;

import java.util.ArrayList;
import java.util.List;


public class Move {
    private List<Point> labyrinth;
    private List<Point> pathPrince;
    //private Point pointStart;


    public Move(List<Point> labyrinth, List<Point> pathPrince) {
        this.labyrinth = new ArrayList<>();
        labyrinth.forEach(p -> {
            try {
                this.labyrinth.add(p.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        });
        this.pathPrince = new ArrayList<>();
        pathPrince.forEach(p -> {
            try {
                this.pathPrince.add(p.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        });


    }

    public int rescuePrincessStart(Point point) {
        // pathPrince = new ArrayList<>();
        System.out.println(labyrinth);
        pathPrince.add(point);
        int countStepPrince = RescuePrincess(point);
        System.out.println(pathPrince);
//        System.out.println(labyrinth);
//        System.out.println(Labyrinth.getLabyrinth());
        VriablePath.getInstance().addSet(pathPrince);

        return pathPrince.size() - 1;
    }

    private int RescuePrincess(Point point) {
        List<Point> variable = new ArrayList<>();

        System.out.println("level= " + point.getLevel());
        if (point.getLevel() < Labyrinth.getH()) {
            Point pointUnderFloor = getPointUnderFloor(point);
            if (nextLevelFreeSpace(point.getValue(), pointUnderFloor.getValue())) {
                return step(pointUnderFloor, variable);
            }
        }
        if (point.getLevel() == Labyrinth.getH()) {
            if ((point.getX() != 0)) {
                if (princesUp(point)) {
                    pathPrince.add(getPointUp(point));
                    return pathPrince.size() - 1;
                }
            }
            if (point.getX() != (Labyrinth.getM() - 1)) {
                if (princesDown(point)) {
                    pathPrince.add(getPointDown(point));
                    return pathPrince.size() - 1;
                }
            }
            if (point.getY() != 0) {
                if (princesLeft(point)) {
                    pathPrince.add(getPointLeft(point));
                    return pathPrince.size() - 1;
                }
            }
            if (point.getY() != (Labyrinth.getN() - 1)) {
                if (princesRight(point)) {
                    pathPrince.add(getPointRight(point));
                    return pathPrince.size() - 1;
                }
            }
        }
        if ((point.getX() != 0)) {
            if ((getPointUp(point).getValue() == Symbols.FREE_SPACE) && (!pathPrince.contains(getPointUp(point)))) {
                return step(getPointUp(point), variable);
            }
        }
        if (point.getX() != (Labyrinth.getM() - 1)) {
            if ((getPointDown(point).getValue() == Symbols.FREE_SPACE) && (!pathPrince.contains(getPointDown(point)))) {
                return step(getPointDown(point), variable);
            }
        }
        if (point.getY() != 0) {
            if ((getPointLeft(point).getValue() == Symbols.FREE_SPACE) && (!pathPrince.contains(getPointLeft(point)))) {
                return step(getPointLeft(point), variable);
            }
        }
        if (point.getY() != (Labyrinth.getN() - 1)) {
            if ((getPointRight(point).getValue() == Symbols.FREE_SPACE) && (!pathPrince.contains(getPointRight(point)))) {
                return step(getPointRight(point), variable);
            }
        }
        if (variable.size() == 0) {
            point.setValue('o');
            Boolean remove = pathPrince.remove(point);
            System.out.println("remove" + remove);
            RescuePrincess(pathPrince.get(pathPrince.size()-1));
        } else {
            int i = RescuePrincess(variable.get(0));
            for (int i1 = 1; i1 < variable.size(); i1++) {
                int move = new Move(labyrinth, pathPrince).rescuePrincessStart(variable.get(i1));
                return move;


            }
        }


        RescuePrincess(pathPrince.get(pathPrince.size() - 1));
        //   System.out.println("i don't know, what you to do");
        return 0;
    }

    private int step(Point point, List<Point> variable) {
        pathPrince.add(point);
        System.out.println(pathPrince.size() - 1);
        variable.add(point);
        //int i = RescuePrincess(point);
        return pathPrince.size() - 1;
    }

    Point getPointLeft(Point point) {
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

    private boolean nextLevelFreeSpace(char symbolThisLevel, char symbolNextLevel) {
        return ((symbolThisLevel == Symbols.FREE_SPACE || symbolThisLevel == Symbols.PRINCE)
                && symbolNextLevel == Symbols.FREE_SPACE);
    }

    private boolean princesUp(Point point) {
        return getPointUp(point).getValue() == Symbols.PRINCES;
    }

    private boolean princesDown(Point point) {
        return getPointDown(point).getValue() == Symbols.PRINCES;
    }

    private boolean princesLeft(Point point) {
        return getPointLeft(point).getValue() == Symbols.PRINCES;
    }

    private boolean princesRight(Point point) {
        return getPointRight(point).getValue() == Symbols.PRINCES;
    }


}


