package com.mygdx.game;

import java.util.ArrayList;

public class Cell {
    int length;
    int depth;
    int x_axis;
    int y_axis;
    int chance;
    public Cell(int depth, int x_axis, int y_axis, int length, int chance){
        this.depth = depth;
        this.x_axis = x_axis;
        this.y_axis = y_axis;
        this.length = length;
        this.chance = chance;
    }

    public ArrayList<Cell> pits(Cell cells[][]){
        ArrayList<Cell> cellsR = new ArrayList<>();
        for(int x = x_axis-1; x <=x_axis+1; ++x){
            if(x <0 || x > length)
                continue;
            for(int y = y_axis-1; y<=y_axis+1; ++y){
                if(y < 0 || y > length)
                    continue;
                if(cells[x][y].getDepth() >= depth){
                    cellsR.add(cells[x][y]);
                }
                if(cells[x][y].x_axis == x_axis && cells[x][y].y_axis == y_axis){
                    for(int i =0; i < chance; ++i){
                        cellsR.add(cells[x][y]);
                    }
                }
            }
        }

        return cellsR;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public  int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getX_axis() {
        return x_axis;
    }

    public  void setX_axis(int x_axis) {
        this.x_axis = x_axis;
    }

    public int getY_axis() {
        return y_axis;
    }

    public void setY_axis(int y_axis) {
        this.y_axis = y_axis;
    }
}
