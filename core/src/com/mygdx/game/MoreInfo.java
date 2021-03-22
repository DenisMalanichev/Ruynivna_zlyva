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


    public MoreInfo(Cell[][] cells, int dropsCount, int maxDepth, ScreenManager manager, int testsResults[]) {
        this.cells = cells;
        this.dropsCount = dropsCount;
        this.maxDepth = maxDepth;
        this.manager = manager;
        this.testsResults = testsResults;
    }

    @Override
    public void show() {
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

            manager.shapeRenderer.rectLine(40, Gdx.graphics.getHeight()/2 + 40, Gdx.graphics.getWidth()/3 - 40, Gdx.graphics.getHeight()/2 + 40, 2);
            manager.shapeRenderer.rectLine(40, Gdx.graphics.getHeight()/2 + 40, 40, Gdx.graphics.getHeight() - 130, 2);

            manager.shapeRenderer.rectLine(Gdx.graphics.getWidth()/3 + 40, Gdx.graphics.getHeight()/2 + 40,
                    Gdx.graphics.getWidth()/3*2 - 40, Gdx.graphics.getHeight()/2 + 40, 2);
            manager.shapeRenderer.rectLine(Gdx.graphics.getWidth()/3 + 40, Gdx.graphics.getHeight()/2 + 40,
                    Gdx.graphics.getWidth()/3 + 40, Gdx.graphics.getHeight() - 130, 2);

            manager.shapeRenderer.rectLine(Gdx.graphics.getWidth()/3*2 + 40, Gdx.graphics.getHeight()/2 + 40,
                    Gdx.graphics.getWidth() - 40, Gdx.graphics.getHeight()/2 + 40, 2);
            manager.shapeRenderer.rectLine(Gdx.graphics.getWidth()/3*2 + 40, Gdx.graphics.getHeight()/2 + 40,
                    Gdx.graphics.getWidth()/3*2 + 40, Gdx.graphics.getHeight() - 130, 2);

        manager.shapeRenderer.end();

        manager.batch.begin();

        for(int i =0;i<10;++i){
            manager.font.setColor(new Color(1,1,1,1));
            manager.font.getData().setScale(1);
            manager.font.draw(manager.batch, ""+i, 40 + i*46,Gdx.graphics.getHeight()/2 + 25);
            manager.font.draw(manager.batch, ""+i*10, Gdx.graphics.getWidth()/3 + 40 + i*46,Gdx.graphics.getHeight()/2 + 25);
            manager.font.draw(manager.batch, ""+i*100, Gdx.graphics.getWidth()/3*2 + 40 + i*46,Gdx.graphics.getHeight()/2 + 25);
            if(i > 0 && i < 8) {
                manager.font.draw(manager.batch, "" + i, 25, Gdx.graphics.getHeight()/2 + 25 + i * 46);
                manager.font.draw(manager.batch, "" + i*10, Gdx.graphics.getWidth()/3 + 20, Gdx.graphics.getHeight()/2 + 25 + i * 46);
                manager.font.draw(manager.batch, "" + i*100, Gdx.graphics.getWidth()/3*2 + 10, Gdx.graphics.getHeight()/2 + 25 + i * 46);
            }
        }
        if(Gdx.input.getX() >= 0 && Gdx.input.getX() <= 70 && Gdx.input.getY() >= 0 && 1000 - Gdx.input.getY() <= 50) {
            manager.font.setColor(0, 1, 1, 1);
            manager.font.getData().setScale(2);
            manager.font.draw(manager.batch, "Back", 10, 40);
            if(Gdx.input.isTouched()){
                manager.setScreen(new Visualization(cells, dropsCount, maxDepth, manager, testsResults));
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
