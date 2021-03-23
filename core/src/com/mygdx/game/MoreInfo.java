package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class MoreInfo implements Screen {
    private ScreenManager manager;
    private Cell[][] cells;
    private int dropsCount;
    private int maxDepth;
    private int testsResults[];
    private int length;
    private int results10[];
    private int results100[];
    private int results1000[];
    private int results10000[];
    private int resultsDensity[];
    private int density;
    private int chance;

    public MoreInfo(Cell[][] cells, int dropsCount, int maxDepth, ScreenManager manager, int testsResults[] , int density, int chance) {
        this.cells = cells;
        this.dropsCount = dropsCount;
        this.maxDepth = maxDepth;
        this.manager = manager;
        this.testsResults = testsResults;
        this.density = density;
        this.chance = chance;
        length = cells.length;
    }

    public int[] tests(int drops){
        int results[] = new int[100];
        for(int q =0; q <100; ++q){
            Cell[][] cells = new Cell[length][length];
            int cellNum = 0;

            /** ініціалізурємо поле length на length*/
            for (int x = 0; x < cells.length; x++) {
                for (int y = 0; y < cells.length; y++) {
                    cells[x][y] = new Cell(0, x, y, length, chance);
                }
            }
            for (int i = 0; i < drops; ++i) {
                int x = (int) (Math.random() * length - 1);
                int y = (int) (Math.random() * length - 1);
                Drop drop = new Drop(x, y);
                drop.corruption(cells[x][y], cells);
            }
            for (int x = 0; x < cells.length; x++) {
                for (int y = 0; y < cells.length; y++) {
                    if(cells[x][y].getDepth() > 0)
                        ++cellNum;
                }
            }
            results[q] = cellNum;
        }
        return results;
    }

    @Override
    public void show() {
        results10 = tests(10);
        results100 = tests(100);
        results1000 = tests(1000);
        results10000 = tests(10000);
        resultsDensity = tests(density*cells.length*cells.length);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.15f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        manager.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        /** розмітка поля*/
            manager.shapeRenderer.setColor(0.5f, 0.5f, 0.5f, 1);
            manager.shapeRenderer.rectLine(0, Gdx.graphics.getHeight()/2,Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/2, 2);
            manager.shapeRenderer.rectLine(Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight(),Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/2, 2);
            manager.shapeRenderer.rectLine(Gdx.graphics.getWidth()/3*2, Gdx.graphics.getHeight(),Gdx.graphics.getWidth()/3*2, Gdx.graphics.getHeight()/2, 2);
            manager.shapeRenderer.rectLine(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2,Gdx.graphics.getWidth()/2, 0, 2);

        /** відмальовуємо графіки*/

            /** 10-графік*/
            manager.shapeRenderer.rectLine(40, Gdx.graphics.getHeight()/2 + 40, Gdx.graphics.getWidth()/3 - 40, Gdx.graphics.getHeight()/2 + 40, 2);
            manager.shapeRenderer.rectLine(40, Gdx.graphics.getHeight()/2 + 40, 40, Gdx.graphics.getHeight() - 130, 2);
                manager.shapeRenderer.setColor(0, 0.7f, 0, 1);
                 for(int i =0; i < results10.length-1; ++i){
                     float y1 = results10[i]*33 + 540;
                     float y2 = results10[i+1]*33 + 540;
                     manager.shapeRenderer.rectLine(40+4.2f*i, y1, 40+4.2f*(i+1), y2, 2);
                 }
            /** 100-графік*/
            manager.shapeRenderer.setColor(0.5f, 0.5f, 0.5f, 1);
            manager.shapeRenderer.rectLine(Gdx.graphics.getWidth()/3 + 40, Gdx.graphics.getHeight()/2 + 40,
                    Gdx.graphics.getWidth()/3*2 - 40, Gdx.graphics.getHeight()/2 + 40, 2);
            manager.shapeRenderer.rectLine(Gdx.graphics.getWidth()/3 + 40, Gdx.graphics.getHeight()/2 + 40,
                    Gdx.graphics.getWidth()/3 + 40, Gdx.graphics.getHeight() - 130, 2);
                manager.shapeRenderer.setColor(0, 0.7f, 0, 1);
                for(int i =0; i < results100.length-1; ++i){
                    float y1 = results100[i]*3.3f + 540;
                    float y2 = results100[i+1]*3.3f + 540;
                    manager.shapeRenderer.rectLine(540+4.2f*i, y1, 540+4.2f*(i+1), y2, 2);
                }
            /** 1000-графік*/
            manager.shapeRenderer.setColor(0.5f, 0.5f, 0.5f, 1);
            manager.shapeRenderer.rectLine(Gdx.graphics.getWidth()/3*2 + 40, Gdx.graphics.getHeight()/2 + 40,
                    Gdx.graphics.getWidth() - 40, Gdx.graphics.getHeight()/2 + 40, 2);
            manager.shapeRenderer.rectLine(Gdx.graphics.getWidth()/3*2 + 40, Gdx.graphics.getHeight()/2 + 40,
                    Gdx.graphics.getWidth()/3*2 + 40, Gdx.graphics.getHeight() - 130, 2);

                manager.shapeRenderer.setColor(0, 0.7f, 0, 1);
                for(int i =0; i < results1000.length-1; ++i){
                    float y1 = results1000[i]*0.33f + 540;
                    float y2 = results1000[i+1]*0.33f + 540;
                    manager.shapeRenderer.rectLine(1040+4.2f*i, y1, 1040+4.2f*(i+1), y2, 2);
                }

            /** 10000-графік*/
            manager.shapeRenderer.setColor(0.5f, 0.5f, 0.5f, 1);
            manager.shapeRenderer.rectLine(100, 40, 700, 40, 2);
            manager.shapeRenderer.rectLine(100, 40, 100, 400, 2);
                manager.shapeRenderer.setColor(0, 0.7f, 0, 1);
                for(int i =0; i < results10000.length-1; ++i){
                    float y1 = results1000[i]*0.36f + 40;
                    float y2 = results1000[i+1]*0.36f + 40;
                    manager.shapeRenderer.rectLine(100+6*i, y1, 100+6*(i+1), y2, 2);
                }

            /** ро-графік*/
            manager.shapeRenderer.setColor(0.5f, 0.5f, 0.5f, 1);
            manager.shapeRenderer.rectLine(850, 40, 1450, 40, 2);
            manager.shapeRenderer.rectLine(850, 40, 850, 400, 2);
                manager.shapeRenderer.setColor(0, 0.7f, 0, 1);
                for(int i =0; i < results10000.length-1; ++i){
                    float y1 = resultsDensity[i]*3.6f + 40;
                    float y2 = resultsDensity[i+1]*3.6f + 40;
                    manager.shapeRenderer.rectLine(850+6*i, y1, 850+6*(i+1), y2, 2);
                }

        manager.shapeRenderer.end();

        manager.batch.begin();

        manager.font.setColor(new Color(1,1,1,1));
        manager.font.getData().setScale(1);
        manager.font.draw(manager.batch, "Cells num after 10 drops falls (100 starts)", 20, 950);
        manager.font.draw(manager.batch, "Cells num after 100 drops falls (100 starts)", 520, 950);
        manager.font.draw(manager.batch, "Cells num after 1000 drops falls (100 starts)", 1020, 950);
        manager.font.draw(manager.batch, "Cells num after 10000 drops falls (100 starts)", 20, 450);
        manager.font.draw(manager.batch, "Cells num depending on density (100 starts)", 770, 450);

        /** розмітка графіків*/
        for(int i =0;i<=10;++i){
            manager.font.setColor(new Color(1,1,1,1));
            manager.font.getData().setScale(1);
            manager.font.draw(manager.batch, ""+i*10, 40 + i*41,Gdx.graphics.getHeight()/2 + 25);
            manager.font.draw(manager.batch, ""+i*10, Gdx.graphics.getWidth()/3 + 40 + i*41,Gdx.graphics.getHeight()/2 + 25);
            manager.font.draw(manager.batch, ""+i*10, Gdx.graphics.getWidth()/3*2 + 40 + i*41,Gdx.graphics.getHeight()/2 + 25);
            manager.font.draw(manager.batch, ""+i*10,  100 + i*60,25);
            manager.font.draw(manager.batch, ""+i*10,  850 + i*60,25);
            if(i > 0) {
                manager.font.draw(manager.batch, "" + i, 20, Gdx.graphics.getHeight()/2 + 40 + i * 33);
                manager.font.draw(manager.batch, "" + i*10, Gdx.graphics.getWidth()/3 + 10, Gdx.graphics.getHeight()/2 + 40 + i * 33);
                manager.font.draw(manager.batch, "" + i*100, Gdx.graphics.getWidth()/3*2 + 5, Gdx.graphics.getHeight()/2 + 40 + i * 33);
                manager.font.draw(manager.batch, "" + i*1000, 50 ,40 + i*36);
            }
        }
        int c = 0;
        for(int i = cells.length* cells.length/10; i <= cells.length* cells.length; i+= cells.length* cells.length/10) {
            c += 10;
            manager.font.draw(manager.batch, "" + i, 810, 40 + c * 3.6f);
        }
        if(Gdx.input.getX() >= 0 && Gdx.input.getX() <= 70 && Gdx.input.getY() >= 0 && 1000 - Gdx.input.getY() <= 50) {
            manager.font.setColor(0, 1, 1, 1);
            manager.font.getData().setScale(2);
            manager.font.draw(manager.batch, "Back", 10, 40);
            if(Gdx.input.isTouched()){
                manager.setScreen(new Visualization(cells, dropsCount, maxDepth, manager, testsResults, density, chance));
            }
        }else{
            manager.font.setColor(0, 0, 1, 1);
            manager.font.getData().setScale(2);
            manager.font.draw(manager.batch, "Back", 10, 40);
        }

        manager.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
