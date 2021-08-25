package com.company.Task2;


import java.io.Serializable;

public class Point  implements Serializable {

    private int level;
    private int x;
    private int y;
    private char value;

    public Point() {
    }

    public Point(int level, int x, int y, char value) {
        this.level = level;
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return
                "Point{" +
                     "level=" + level +
                        ", x=" + x +
                        ", y=" + y +
                       ", value=" +
                        "" + value +
                        '}'
                ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return level == point.level && x == point.x && y == point.y && value == point.value;
    }

    @Override
    public int hashCode() {
        int result = String.valueOf(value) == null ? 0 : String.valueOf(value).hashCode();
        result = result + level * 1310 + x * 137 + y * 13;
        return result;
    }

    @Override
    public Point clone() throws CloneNotSupportedException {

        Point point = new Point();
        point.level = this.level;
        point.x = this.x;
        point.y = this.y;
        point.value = this.value;
        return point;
    }
}
