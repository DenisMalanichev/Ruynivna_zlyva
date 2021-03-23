package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;


public class Problem implements Screen {

    private ScreenManager manager;
    private Texture drop;
    private Rectangle dropRec;
    private static final int cellWidth = 200;
    private long startTime = System.currentTimeMillis()/1000;
    private float alpha = 1;

    public Problem(ScreenManager manager) {
        this.manager = manager;
    }

    @Override
    public void show() {
        drop = new Texture(Gdx.files.internal("drop.png"));
        dropRec = new Rectangle();
        dropRec.x = 350 - drop.getWidth()/2;
        dropRec.y = 650 - drop.getHeight()/2;

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.15f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        manager.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        if(System.currentTimeMillis()/1000 - startTime < 4 || System.currentTimeMillis()/1000 - startTime > 9)
            manager.shapeRenderer.setColor(new Color(0,0,1,1));
        else if(System.currentTimeMillis()/1000 - startTime < 10 && System.currentTimeMillis()/1000 - startTime >= 4)
            manager.shapeRenderer.setColor(new Color(0,1,1,1));

            for(int i =0; i < 3; ++i){
                for(int q =0; q < 3; ++q){
                    if(i == 2 && q == 2 && System.currentTimeMillis()/1000 - startTime > 16 && System.currentTimeMillis()/1000 - startTime <= 25) {
                        manager.shapeRenderer.setColor(new Color(1, 0, 1, 1));
                        manager.shapeRenderer.rect(50 + cellWidth * i, 350 + cellWidth * q, cellWidth, cellWidth);
                        manager.shapeRenderer.setColor(new Color(0, 0, 1, 1));
                    }else{
                        manager.shapeRenderer.rect(50 + cellWidth * i, 350 + cellWidth * q, cellWidth, cellWidth);
                    }
                }
            }
            manager.shapeRenderer.setColor(new Color(0,0,0,1));
            manager.shapeRenderer.rectLine(50, 550, 650, 550, 2);
            manager.shapeRenderer.rectLine(50, 750, 650, 750, 2);
            manager.shapeRenderer.rectLine(250, 950, 250, 350, 2);
            manager.shapeRenderer.rectLine(450, 950, 450, 350, 2);
            manager.shapeRenderer.setColor(new Color(0,1,1,1));
            manager.shapeRenderer.rect(1110, 540, 20, 20);
            manager.shapeRenderer.setColor(new Color(1,0,1,1));
            manager.shapeRenderer.rect(1340, 505, 20, 20);
        manager.shapeRenderer.end();

        manager.batch.begin();
        if(System.currentTimeMillis()/1000 - startTime < 5) {
            manager.batch.draw(drop, dropRec.x, dropRec.y);
        }else if(System.currentTimeMillis()/1000 - startTime >= 5 && System.currentTimeMillis()/1000 - startTime < 10){
            alpha -=0.03;
            manager.batch.setColor(1,1,1,alpha);
            manager.batch.draw(drop, dropRec.x, dropRec.y);
            manager.batch.setColor(1,1,1,1);
        }else if(System.currentTimeMillis()/1000 - startTime >= 10 && System.currentTimeMillis()/1000 - startTime < 14){
            alpha +=0.03;
            manager.batch.setColor(1,1,1,alpha);
            manager.batch.draw(drop, dropRec.x, dropRec.y);
            manager.batch.setColor(1,1,1,1);
        }else if(System.currentTimeMillis()/1000 - startTime >= 14 && System.currentTimeMillis()/1000 - startTime < 18){
            alpha -=0.06;
            manager.batch.setColor(1,1,1,alpha);
            manager.batch.draw(drop, dropRec.x, dropRec.y);
            manager.batch.setColor(1,1,1,1);
        }
        if(System.currentTimeMillis()/1000 - startTime == 10){
            alpha = 0;
            dropRec.x = 550 - drop.getWidth()/2;
            dropRec.y = 850 - drop.getHeight()/2;
        }
        if(System.currentTimeMillis()/1000 - startTime > 30){
            startTime = System.currentTimeMillis()/1000;
            dropRec.x = 350 - drop.getWidth()/2;
            dropRec.y = 650 - drop.getHeight()/2;
        }

        manager.font.setColor(new Color(1,1,1,1));
        manager.font.draw(manager.batch, "About problem: ", 750, 900);
        manager.font.draw(manager.batch, "Raindrops falling on the field that consists of L*L cells. \n" +
                "Every cell has own ground level. At the start this level \nis always equal to zero. When the drop falls on the\n" +
                "cell it can dissolve and reduce ground \nlevel by one or move on one of the neighbor cells\nand reduce it by one.\n\n" +
                " In this visualization cells on which drop can move on is\nhighlighted in light blue.\n" +
                " The most deep cell is highlighted in pink.", 800, 850);
        manager.font.draw(manager.batch, "By default drop has equal chance to stay on cell and move on neighbor one. Changing chance parameter \n" +
                "you are changing a ratio by adding number of this cell in selection.\n" +
                "For example adding 2 will mean that in selection of 2 cells percent to stay will be equal to 75%.", 50, 300);



        if(Gdx.input.getX() >= 0 && Gdx.input.getX() <= 70 && Gdx.input.getY() >= 0 && 1000 - Gdx.input.getY() <= 50) {
            manager.font.setColor(0, 1, 1, 1);
            manager.font.getData().setScale(2);
            manager.font.draw(manager.batch, "Back", 10, 40);
            if(Gdx.input.isTouched()){
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                manager.setScreen(new Main(manager));
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
