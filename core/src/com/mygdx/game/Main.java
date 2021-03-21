package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;


public class Main implements Screen {

    private ScreenManager manager;
    private Texture moreButtonTexture;
    private Cell[][] cells;
    private int length = 10;
    private int dropsCount = 50;
    private int maxDepth = 0;
    private OrthographicCamera camera;

    public Main(ScreenManager manager) {
        this.manager = manager;
    }

    @Override
    public void show() {

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1500, 1000);

        moreButtonTexture = new Texture(Gdx.files.internal("visualizeButton.jpg"));
        /** отримуємо параметри*/
         cells = new Cell[length][length];

        /** ініціалізурємо поле length на length*/
         for (int x = 0; x < cells.length; x++) {
         for (int y = 0; y < cells.length; y++) {
         cells[x][y] = new Cell(0, x, y, length);
         }
         }
        /** cтворюємо краплі*/
         for (int i = 0; i < dropsCount; ++i) {
         int x = (int) (Math.random() * length - 1);
         int y = (int) (Math.random() * length - 1);
         Drop drop = new Drop(x, y);
         drop.corruption(cells[x][y], cells);
         }
        /** знаходимо найбільшу глибину*/

         for (int x = 0; x < cells.length; x++) {
             for (int y = 0; y < cells.length; y++) {
                 if(cells[x][y].getDepth() > maxDepth)
                   maxDepth = cells[x][y].getDepth();
                 }
         }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.getX() >= 0 && Gdx.input.getX() <= moreButtonTexture.getWidth() && Gdx.input.getY() >= 0 && 1000 - Gdx.input.getY() <= moreButtonTexture.getHeight()
                && Gdx.input.isTouched()) {
            this.dispose();
            manager.setScreen(new Visualization(cells, dropsCount, maxDepth, manager));
        }
        manager.batch.begin();

        manager.batch.draw(moreButtonTexture, 0, 0);
        manager.font.getData().setScale(2);
        manager.font.setColor(1,1,1,1);
        manager.font.draw(manager.batch, "How to deal with this program?", 10, 950);
        manager.font.draw(manager.batch, "You need to enter number of cells in columns and rows and number of drops", 10, 900);
        manager.font.draw(manager.batch, "You are about to use " + length + " cells in every column and row" , 10, 850);
        manager.font.draw(manager.batch, "You are about to use " + dropsCount +" drops", 10, 800);
        

        if(Gdx.input.getX() >= 1050 && Gdx.input.getX() <= 1100 && 1000 - Gdx.input.getY() >= 800 && 1000 - Gdx.input.getY() <= 850) {
            manager.font.setColor(0, 1, 1, 1);
            manager.font.draw(manager.batch, "edit", 1050, 850);
            if(Gdx.input.isTouched()){
                Input.TextInputListener textListener = new Input.TextInputListener()
                {
                    @Override
                    public void input(String input)
                    {
                       length = Integer.parseInt(input);
                       camera.update();
                    }

                    @Override
                    public void canceled()
                    {
                    }
                };

                Gdx.input.getTextInput(textListener, "Cells: ", "10", "");

            }
        }else{
            manager.font.setColor(0, 0, 1, 1);
            manager.font.draw(manager.batch, "edit", 1050, 850);
        }

        if(Gdx.input.getX() >= 1050 && Gdx.input.getX() <= 1100 && 1000 - Gdx.input.getY() >= 750 && 1000 - Gdx.input.getY() <= 800) {
            manager.font.setColor(0, 1, 1, 1);
            manager.font.draw(manager.batch, "edit", 1050, 800);
            if(Gdx.input.isTouched()){
                Input.TextInputListener textListener = new Input.TextInputListener()
                {
                    @Override
                    public void input(String input)
                    {
                        dropsCount = Integer.parseInt(input);
                    }

                    @Override
                    public void canceled()
                    {
                    }
                };

                Gdx.input.getTextInput(textListener, "Drops: ", "50", "");
            }
        }else{
            manager.font.setColor(0, 0, 1, 1);
            manager.font.draw(manager.batch, "edit", 1050, 800);
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
        moreButtonTexture.dispose();
    }


}
