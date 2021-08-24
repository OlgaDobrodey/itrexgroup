package com.company.Task21;

import java.util.ArrayList;
import java.util.List;


public class Move {
    private List<Point> labyrinth;
    public List<Point> pathPrince;

    public Move(List<Point> labyrinth) {
        this.labyrinth = new ArrayList<>();
        labyrinth.forEach(point -> {
            try {
                this.labyrinth.add(point.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        });
    }

    public int rescuePrincessStart(Point point) {
        pathPrince = new ArrayList<>();
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

        System.out.println("level= " + point.getLevel());
        if (point.getLevel() < Labyrinth.getH()) {
           Point pointUnderFloor = getPointUnderFloor(point);
            if (nextLevelFreeSpace(point.getValue(), pointUnderFloor.getValue())) {
                return step(pointUnderFloor);
            }
        }
        if (point.getLevel() == Labyrinth.getH()) {
            if ((point.getX() != 0)) {
                if ((getPointUp(point).getValue() == Symbols.PRINCES)) {
                    pathPrince.add(getPointUp(point));
                    return pathPrince.size() - 1;
                }
            }
            if (point.getX() != (Labyrinth.getM() - 1)) {
                if ((getPointDown(point).getValue() == Symbols.PRINCES)) {
                    pathPrince.add(getPointDown(point));
                    return pathPrince.size() - 1;
                }
            }
            if (point.getY() != 0) {
                if ((getPointLeft(point).getValue() == Symbols.PRINCES)) {
                    pathPrince.add(getPointLeft(point));
                    return pathPrince.size() - 1;
                }
            }
            if (point.getY() != (Labyrinth.getN() - 1)) {
                if ((getPointRight(point).getValue() == Symbols.PRINCES)) {
                    pathPrince.add(getPointRight(point));
                    return pathPrince.size() - 1;
                }
            }
        }
        if ((point.getX() != 0)) {
            if ((getPointUp(point).getValue() == Symbols.FREE_SPACE) && (!pathPrince.contains(getPointUp(point)))) {
                return step(getPointUp(point));
            }
        }
        if (point.getX() != (Labyrinth.getM() - 1)) {
            if ((getPointDown(point).getValue() == Symbols.FREE_SPACE) && (!pathPrince.contains(getPointDown(point)))) {
                return step(getPointDown(point));
            }
        }
        if (point.getY() != 0) {
            if ((getPointLeft(point).getValue() == Symbols.FREE_SPACE) && (!pathPrince.contains(getPointLeft(point)))) {
                return step(getPointLeft(point));
            }
        }
        if (point.getY() != (Labyrinth.getN() - 1)) {
            if ((getPointRight(point).getValue() == Symbols.FREE_SPACE) && (!pathPrince.contains(getPointRight(point)))) {
                return step(getPointRight(point));
            }
        }
        point.setValue('o');
        Boolean remove = pathPrince.remove(point);
        System.out.println("remove"+ remove);

        RescuePrincess(pathPrince.get(pathPrince.size()-1));
        //   System.out.println("i don't know, what you to do");
        return 0;
    }

    private int step(Point point) {
        pathPrince.add(point);
        System.out.println(pathPrince.size() - 1);
        int i = RescuePrincess(point);
        return pathPrince.size() - 1;
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
    private boolean nextLevelFreeSpace(char symbolThisLevel, char symbolNextLevel) {
        return ((symbolThisLevel == Symbols.FREE_SPACE || symbolThisLevel == Symbols.PRINCE)
                && symbolNextLevel == Symbols.FREE_SPACE);
    }


}
