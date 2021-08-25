package com.company.Task2;

import java.util.ArrayList;
import java.util.List;


public class Move {
    private List<Point> labyrinth;


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

    public int rescuePrincessStart(Point point, List<Point> pathPrince) {
        int countStepPrince = rescuePrincess(point, pathPrince);
        return pathPrince.size();
    }

    private int rescuePrincess(Point point,List<Point> pathPrince) {
        pathPrince.add(point);
        List<Point> variable = new ArrayList<>();

//        System.out.println("level= " + point.getLevel());
//        System.out.println("point= "+ point);

        if (point.getLevel() < Labyrinth.getH()) {                           //check: if there is not last level
            Point pointUnderFloor = getPointUnderFloor(point);
            if (pointUnderFloor.getValue() == Symbols.PRINCES) {

                VriablePath.getInstance().addSet(pathPrince.size());
              //  System.out.println(pathPrince);
                return pathPrince.size() - 1;
            }
            if (nextLevelFreeSpace(point.getValue(), pointUnderFloor.getValue())) {
                variable.add(pointUnderFloor);

            }
        }
        if (point.getLevel() == Labyrinth.getH()) {                          //if last level
            if ((point.getX() != 0)) {
                if ((getPointUp(point).getValue() == Symbols.PRINCES)) {
                    VriablePath.getInstance().addSet(pathPrince.size());
                  //  System.out.println(pathPrince);
                    return pathPrince.size();
                }
            }
            if (point.getX() != (Labyrinth.getM() - 1)) {
                if ((getPointDown(point).getValue() == Symbols.PRINCES)) {
                    VriablePath.getInstance().addSet(pathPrince.size());
                   // System.out.println(pathPrince);
                    return pathPrince.size();
                }
            }
            if (point.getY() != 0) {
                if ((getPointLeft(point).getValue() == Symbols.PRINCES)) {
                    VriablePath.getInstance().addSet(pathPrince.size());
                //   System.out.println(pathPrince);
                    return pathPrince.size();
                }
            }
            if (point.getY() != (Labyrinth.getN() - 1)) {
                if ((getPointRight(point).getValue() == Symbols.PRINCES)) {
                    VriablePath.getInstance().addSet(pathPrince.size());
                  // System.out.println(pathPrince);
                    return pathPrince.size();
                }
            }
        }
        if ((point.getX() != 0)) {
            if ((getPointUp(point).getValue() == Symbols.FREE_SPACE) && (!pathPrince.contains(getPointUp(point)))) {
                variable.add(getPointUp(point));
                            }
        }
        if (point.getX() != (Labyrinth.getM() - 1)) {
            if ((getPointDown(point).getValue() == Symbols.FREE_SPACE) && (!pathPrince.contains(getPointDown(point)))) {
                variable.add(getPointDown(point));
                          }
        }
        if (point.getY() != 0) {
            if ((getPointLeft(point).getValue() == Symbols.FREE_SPACE) && (!pathPrince.contains(getPointLeft(point)))) {
                variable.add(getPointLeft(point));

            }
        }
        if (point.getY() != (Labyrinth.getN() - 1)) {
            if ((getPointRight(point).getValue() == Symbols.FREE_SPACE) && (!pathPrince.contains(getPointRight(point)))) {
                variable.add(getPointRight(point));

            }
        }

        if (variable.size() == 0) {
            point.setValue(Symbols.COLUMN);
            Boolean remove = pathPrince.remove(point);
            Point recovery = pathPrince.get(pathPrince.size()-1);
            pathPrince.remove(pathPrince.size()-1);

            rescuePrincess(recovery,pathPrince);
        } else if (variable.size() == 1) {
            return step(variable.get(0),pathPrince);
        } else {
            step(variable.get(0),pathPrince);
            for (int i = 1; i < variable.size(); i++) {
                List<Point> labyrinthNew = new ArrayList<>();
                this.labyrinth.forEach(p -> {
                    try {
                        labyrinthNew.add(p.clone());
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                });

                Point search = variable.get(i-1);
                labyrinthNew.remove(search);
                search.setValue(Symbols.COLUMN);
                labyrinthNew.add(search);

                int a = new Move(labyrinthNew).rescuePrincessStart(variable.get(i),pathPrince);
            }
        }
        return pathPrince.size();
    }

    private int step(Point point, List<Point> pathPrince) {
        int i = rescuePrincess(point,pathPrince);
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

