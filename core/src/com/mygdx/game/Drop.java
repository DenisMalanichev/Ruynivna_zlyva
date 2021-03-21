package com.mygdx.game;

public class Drop {
    public  final double VARIATE = 25;
    int x_axis;
    int y_axis;

    public Drop(int x_axis, int y_axis) {
        this.x_axis = x_axis;
        this.y_axis = y_axis;
    }

    public void corruption(Cell cell, Cell cells[][]){
        int randPit = (int) (Math.random() * cell.pits(cells).size());
        cell.pits(cells).get(randPit).setDepth(cell.pits(cells).get(randPit).getDepth()+1);
    }
}

