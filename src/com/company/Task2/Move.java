package com.company.Task2;

import java.util.ArrayList;
import java.util.List;


public class Move {
    private List<Point> labyrinth;
    //public List<Point> pathPrince;

    public Move(List<Point> labyrinth
         //   , List<Point> pathPrince
    ) {
        this.labyrinth = new ArrayList<>();
        labyrinth.forEach(point -> {
            try {
                this.labyrinth.add(point.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        });
//        this.pathPrince = new ArrayList<>();
//        pathPrince.forEach(point -> {
//            try {
//                this.pathPrince.add(point.clone());
//            } catch (CloneNotSupportedException e) {
//                e.printStackTrace();
//            }
//        });
    }

    public int rescuePrincessStart(Point point, List<Point> pathPrince) {
       // pathPrince.add(point);
        int countStepPrince = rescuePrincess(point, pathPrince);
        return pathPrince.size() - 1;
    }

    private int rescuePrincess(Point point,List<Point> pathPrince) {
        pathPrince.add(point);
        List<Point> variable = new ArrayList<>();

        System.out.println("level= " + point.getLevel());
        System.out.println("point= "+ point);

        if (point.getLevel() < Labyrinth.getH()) {
            Point pointUnderFloor = getPointUnderFloor(point);
            if (pointUnderFloor.getValue() == Symbols.PRINCES) {

               // pathPrince.add(pointUnderFloor);
                VriablePath.getInstance().addSet(pathPrince.size());
                System.out.println(pathPrince);
                return pathPrince.size() - 1;
            }
            if (nextLevelFreeSpace(point.getValue(), pointUnderFloor.getValue())) {
                variable.add(pointUnderFloor);
                //return step(pointUnderFloor);
            }
        }
        if (point.getLevel() == Labyrinth.getH()) {
            if ((point.getX() != 0)) {
                if ((getPointUp(point).getValue() == Symbols.PRINCES)) {
                    //pathPrince.add(getPointUp(point));
                    VriablePath.getInstance().addSet(pathPrince.size());
                    System.out.println(pathPrince);
                    return pathPrince.size() - 1;
                }
            }
            if (point.getX() != (Labyrinth.getM() - 1)) {
                if ((getPointDown(point).getValue() == Symbols.PRINCES)) {
                   // pathPrince.add(getPointDown(point));
                    VriablePath.getInstance().addSet(pathPrince.size());
                    System.out.println(pathPrince);
                    return pathPrince.size() - 1;
                }
            }
            if (point.getY() != 0) {
                if ((getPointLeft(point).getValue() == Symbols.PRINCES)) {
                 //   pathPrince.add(getPointLeft(point));
                    VriablePath.getInstance().addSet(pathPrince.size());
                   System.out.println(pathPrince);
                    return pathPrince.size() - 1;
                }
            }
            if (point.getY() != (Labyrinth.getN() - 1)) {
                if ((getPointRight(point).getValue() == Symbols.PRINCES)) {
                  //  pathPrince.add(getPointRight(point));
                    VriablePath.getInstance().addSet(pathPrince.size());
                   System.out.println(pathPrince);
                    return pathPrince.size() - 1;
                }
            }
        }
        if ((point.getX() != 0)) {
            if ((getPointUp(point).getValue() == Symbols.FREE_SPACE) && (!pathPrince.contains(getPointUp(point)))) {
                variable.add(getPointUp(point));
                // return step(getPointUp(point));
            }
        }
        if (point.getX() != (Labyrinth.getM() - 1)) {
            if ((getPointDown(point).getValue() == Symbols.FREE_SPACE) && (!pathPrince.contains(getPointDown(point)))) {
                variable.add(getPointDown(point));
                // return step(getPointDown(point));
            }
        }
        if (point.getY() != 0) {
            if ((getPointLeft(point).getValue() == Symbols.FREE_SPACE) && (!pathPrince.contains(getPointLeft(point)))) {
                variable.add(getPointLeft(point));
                // return step(getPointLeft(point));
            }
        }
        if (point.getY() != (Labyrinth.getN() - 1)) {
            if ((getPointRight(point).getValue() == Symbols.FREE_SPACE) && (!pathPrince.contains(getPointRight(point)))) {
                variable.add(getPointRight(point));
                //return step(getPointRight(point));
            }
        }

        if (variable.size() == 0) {
            point.setValue('o');
            Boolean remove = pathPrince.remove(point);
            Point recovery = pathPrince.get(pathPrince.size()-1);
            pathPrince.remove(pathPrince.size()-1);
          //  System.out.println("remove" + remove);

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
                search.setValue('o');
                labyrinthNew.add(search);
               // System.out.println("labyrinthNew="+labyrinthNew);

                int a = new Move(labyrinthNew).rescuePrincessStart(variable.get(i),pathPrince);

            }
        }

        //   System.out.println("i don't know, what you to do");
        return 0;
    }

    private int step(Point point, List<Point> pathPrince) {
      // pathPrince.add(point);
    //    System.out.println(pathPrince.size() - 1);
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

